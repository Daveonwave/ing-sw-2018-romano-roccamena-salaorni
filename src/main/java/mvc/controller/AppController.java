package mvc.controller;

import mvc.Controller;
import mvc.builders.MatchBuilder;
import mvc.controller.handlers.MultiPlayerHandler;
import mvc.model.AppModel;
import mvc.model.MatchModel;
import mvc.model.objects.*;
import mvc.view.AppView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AppController extends UnicastRemoteObject implements Controller {
    //Controllore dell'applicazione

    //Model dell'applicazione
    private transient final AppModel model;
    //Gestore partite multiplayer (2, 3 o 4 giocatori)
    private final MultiPlayerHandler ms2, ms3, ms4;

    //Costruttori
    public AppController() throws RemoteException {
        super();
        this.model = AppModel.get();
        this.ms2 = new MultiPlayerHandler(2);
        this.ms3 = new MultiPlayerHandler(3);
        this.ms4 = new MultiPlayerHandler(4);
    }

    //Operazioni su utente
    public synchronized String login(String name, AppView appView) throws RemoteException {
        String token = model.createUser(name, appView);

        appView.respondAck("logged in as " + name);

        return token;
    }
    public synchronized void logout(String tokenUser) throws RemoteException {
        AppView appView = model.retrieveUser(tokenUser).getAppView();

        model.destroyUser(tokenUser);

        appView.respondAck("logged out");
    }

    //Ottiene gestore di partite corrispondente
    private synchronized MultiPlayerHandler retrieveMatchStartHandler(int playersCount) throws RemoteException {
        switch (playersCount) {
            case 2:
                return ms2;
            case 3:
                return ms3;
            case 4:
                return ms4;
            default:
                throw new ControllerException("unsupported players count");
        }
    }

    //Ack ed error su utenti di un match o singolarmente
    private synchronized void matchBroadcastAck(MatchModel matchModel, String message) {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondAck(message);
    }
    private synchronized void matchBroadcastError(MatchModel matchModel, String message) throws RemoteException {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondError(message);

        throw new ControllerException(message);
    }
    private synchronized void userAck(User user, String message) {
        user.getAppView().respondError(message);
    }
    private synchronized void userError(User user, String message) throws RemoteException {
        user.getAppView().respondError(message);
        throw new ControllerException(message);
    }

    //Mosse degli utenti sulla partita
    public synchronized void joinMatch(String tokenUser, int playersCount) throws RemoteException {
        //Ottiente gestore partite e partecipa all'attesa
        MultiPlayerHandler handler = retrieveMatchStartHandler(playersCount);
        handler.join(tokenUser);

        //Se sono presenti gli utenti partecipanti necessari
        if (handler.isReady()) {
            //Ottiene utenti partecipanti
            List<User> partecipantUsers = new ArrayList<User>();
            for (String partecipantToken : handler.getWaitingUsersToken())
                partecipantUsers.add(model.retrieveUser(partecipantToken));

            //Crea nuova partita
            Match match = MatchBuilder.createMultiPlayer(partecipantUsers);
            MatchModel matchModel = new MatchModel(match);

            //Registra gli utenti come osservatori della partita
            for (User user : partecipantUsers) {
                matchModel.attachMatchObserver(user.getAppView());
            }

            //Registra la nuova partita nel model
            String tokenMatch = model.createMatch(matchModel);

            //Elimina gli utenti in attesa dal gestore multiplayer
            handler.clear();

            //Inizia la parita
            match.beginMatch();

            //Notifica inizio della partita
            matchModel.notifyMatchStart(tokenMatch);
            matchBroadcastAck(matchModel, "match started");

            //Notifica inizio fase di scelta delle finestre
            matchModel.notifyChooseWindows(tokenMatch);
            matchBroadcastAck(matchModel, "waiting player choosing windows");
        }
    }
    public synchronized void cancelJoinMatch(String tokenUser, int playersCount) throws RemoteException {
        //Ottiene gestore partite
        MultiPlayerHandler handler = retrieveMatchStartHandler(playersCount);

        //L'utente lascia l'attesa
        handler.leave(tokenUser);

        //Notifica l'utente dell'uscita
        userAck(model.retrieveUser(tokenUser), "match leaved");
    }
    public synchronized void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {
        //?Serve
    }
    public synchronized void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatch(tokenMatch);
        Match match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        //Esegue la scelta della finestra
        try {
            match.chooseWindow(player, window);
        } catch (MatchException e) {
            userError(user, e.getMessage());
        }

        //Se la partita ha inizio
        if (match.getTurnHandler().isStarted()) {
            //Notifica inizio dei round
            matchModel.notifyFirstRoundStart(tokenMatch);
            matchBroadcastAck(matchModel, "match rounds started");

            //Notifica inizio round
            matchModel.notifyRoundStart(tokenMatch);
            matchBroadcastAck(matchModel, "round " + match.getTurnHandler().getRound() + " started");
        }
    }
    public synchronized void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatch(tokenMatch);
        Match match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        //Esegue il piazzamento del dado
        try {
            match.placeDie(player, cell, die);
        } catch (MatchException e) {
            userError(user, e.getMessage());
        }

        //Notifica il piazzamento del dado
        matchModel.notifyPlaceDie(tokenMatch, player, cell, die);
        matchBroadcastAck(matchModel, "player " + user.getName() + " placed a die");
    }
    public synchronized void useToolCard(String tokenUser, String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {

    }
    public synchronized void endTurn(String tokenUser, String tokenMatch) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatch(tokenMatch);
        Match match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        //Esegue la fine del turno
        try {
            match.endTurn(player);
        } catch (MatchException e) {
            userError(user, e.getMessage());
        }

        //Notifica fine turno del giocatore
        matchModel.notifyPlayerTurnEnd(tokenMatch, player);
        matchBroadcastAck(matchModel, "player " + player.getUser().getName() + " ends turn");

        //Notifica fine round
        if (match.getTurnHandler().isRoundFirstTurn()) {
            matchModel.notifyRoundEnd(tokenMatch);
            matchBroadcastAck(matchModel, "round " + (match.getTurnHandler().getRound() - 1) + " ended");
        }

            //Controllo se i round sono finiti
        if (!match.getTurnHandler().isEnded()) {
            //Notifica inizio nuovo round
            if (match.getTurnHandler().isRoundFirstTurn()) {
                matchModel.notifyRoundStart(tokenMatch);
                matchBroadcastAck(matchModel, "round " + match.getTurnHandler().getRound() + " started");
            }

            //Notifica turno giocatore successivo
            matchModel.notifyPlayerTurnStart(tokenMatch);
            matchBroadcastAck(matchModel, "player " + match.getTurnPlayer().getUser().getName() + " starts turn");
        } else {
            //Notifica ultimo turno dei round
            matchModel.notifyLastRoundEnd(tokenMatch);
            matchBroadcastAck(matchModel, "match rounds ended");

            //Per ogni player vengono comunicati i punteggi
            for (Player p : match.getPlayers()) {
                PlayerPoints points = match.getPlayerPoints(p);
                matchModel.notifyGetPoints(tokenMatch, player, points);
                matchBroadcastAck(matchModel, player.getUser().getName() + " totalized " + (points.getPrivateObjectivePoints()+points.getPublicObjectivePoints()+points.getFavorTokensPoints()-points.getOpenSpacesLostPoints()));
            }

            //Notifica fine partita
            matchBroadcastAck(matchModel, "match ended");

            //La partita viene eliminata dal model
            model.destroyMatch(tokenMatch);
        }
    }
}
