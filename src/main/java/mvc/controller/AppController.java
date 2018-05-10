package mvc.controller;

import mvc.exceptions.AppModelException;
import mvc.stubs.AppControllerStub;
import mvc.creators.MatchCreator;
import mvc.controller.handlers.MultiPlayerHandler;
import mvc.exceptions.AppControllerException;
import mvc.exceptions.MatchException;
import mvc.model.AppModel;
import mvc.model.MatchModel;
import mvc.model.objects.*;
import mvc.stubs.AppViewStub;
import mvc.view.AppView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class AppController implements AppControllerStub {
    //Controllore dell'applicazione

    //Model dell'applicazione
    private transient  AppModel model;
    //Gestore partite multiplayer (2, 3 o 4 giocatori)
    private transient final MultiPlayerHandler startMultiPlayer2, startMultiPlayer3, startMultiPlayer4;

    //Costruttori
    public AppController() {
        this.model = AppModel.get();
        this.startMultiPlayer2 = new MultiPlayerHandler(2);
        this.startMultiPlayer3 = new MultiPlayerHandler(3);
        this.startMultiPlayer4 = new MultiPlayerHandler(4);
    }

    //Setter/Getter
    public AppModel getModel() {
        return model;
    }

    public void setModel(AppModel model){
        this.model = model;
    }

    //Ack ed error su utenti di un match o singolarmente
    private synchronized void matchBroadcastAck(MatchModel matchModel, String message) throws RemoteException {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondAck(message);
    }
    private synchronized void matchBroadcastError(MatchModel matchModel, String message) throws RemoteException {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondError(message);

        throw new AppControllerException(message);
    }
    private synchronized void viewAck(AppViewStub appView, String message) throws RemoteException {
        appView.respondAck(message);
    }
    private synchronized void viewError(AppViewStub appView, String message) throws RemoteException {
        appView.respondError(message);
        throw new AppControllerException(message);
    }
    private synchronized void userAck(User user, String message) throws RemoteException {
        viewAck(user.getAppView(), message);
    }
    private synchronized void userError(User user, String message) throws RemoteException {
        viewError(user.getAppView(), message);
    }

    //Operazioni su utente
    public synchronized String login(String name, AppView appView) throws RemoteException {
        String token = "";

        try {
            token =  model.createUser(name, appView);
        } catch (AppModelException e) {
            viewError(appView, e.getMessage());
            return null;
        }

        viewAck(appView, "connesso come " + name);

        return token;
    }
    public synchronized void logout(String tokenUser) throws RemoteException {
        AppViewStub appView = null;

        try {
            appView = model.retrieveUser(tokenUser).getAppView();
            model.destroyUser(tokenUser);
        } catch (AppModelException e) {
            return;
        }

        viewAck(appView, "disconnesso");
    }

    //Ottiene gestore di partite corrispondente
    private synchronized MultiPlayerHandler retrieveMatchStartHandler(User user, int playersCount) throws RemoteException {
        switch (playersCount) {
            case 2:
                return startMultiPlayer2;
            case 3:
                return startMultiPlayer3;
            case 4:
                return startMultiPlayer4;
            default:
                userError(user, "numero giocatori non valido");
                return null;
        }
    }

    //Mosse degli utenti sulla partita
    public synchronized void joinMatch(String tokenUser, int playersCount) throws RemoteException {
        //Ottiente gestore partite e partecipa all'attesa
        User user = model.retrieveUser(tokenUser);
        MultiPlayerHandler handler = retrieveMatchStartHandler(user, playersCount);
        handler.join(tokenUser);

        //Se sono presenti gli utenti partecipanti necessari
        if (handler.isReady()) {
            //Ottiene utenti partecipanti
            List<User> partecipantUsers = new ArrayList<User>();
            for (String partecipantToken : handler.getWaitingUsersToken())
                partecipantUsers.add(model.retrieveUser(partecipantToken));

            //Crea nuova partita
            Match match = MatchCreator.createMultiPlayer(partecipantUsers);
            MatchModel matchModel = new MatchModel(match);

            //Registra gli utenti come osservatori della partita
            for (User partecipantUser : partecipantUsers) {
                matchModel.attachMatchObserver(user.getAppView());
            }

            //Registra la nuova partita nel model
            String tokenMatch = model.createMatch(matchModel);

            //Elimina gli utenti in attesa dal gestore multiplayer
            handler.clear();

            //Inizia la parita
            try {
                match.beginMatch();
            } catch (MatchException e) {
                matchBroadcastError(matchModel, e.getMessage());
                return;
            }
            match.beginMatch();

            //Notifica inizio della partita
            matchModel.notifyMatchStart(tokenMatch);
            matchBroadcastAck(matchModel, "partita iniziata");

            //Notifica inizio fase di scelta delle finestre
            matchModel.notifyChooseWindows(tokenMatch);
            matchBroadcastAck(matchModel, "i giocatori stanno scegliendo le finestre");
        }
    }
    public synchronized void cancelJoinMatch(String tokenUser, int playersCount) throws RemoteException {
        //Ottiene gestore partite
        User user = model.retrieveUser(tokenUser);
        MultiPlayerHandler handler = retrieveMatchStartHandler(user, playersCount);

        //L'utente lascia l'attesa
        handler.leave(tokenUser);

        //Notifica l'utente dell'uscita
        userAck(model.retrieveUser(tokenUser), "iscrizione partita cancellata");
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
            return;
        }

        //Se la partita ha inizio
        if (match.getTurnHandler().isStarted()) {
            //Notifica inizio nuovo turno
            matchModel.notifyTurnStart(tokenMatch, match);
            matchBroadcastAck(matchModel, "iniziano i round della partita");
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
            return;
        }

        //Notifica il piazzamento del dado
        matchModel.notifyPlaceDie(tokenMatch, cell, die);
        matchBroadcastAck(matchModel, "il giocatore " + user.getName() + " ha piazzato un dado");
    }
    public synchronized void useToolCard(String tokenUser, String tokenMatch, Match newMatch, ToolCard toolCard) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatch(tokenMatch);
        Match match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        //Utilizza la tool card
        try {
            match.useToolCard(player, newMatch, toolCard);
        } catch (MatchException e) {
            userError(user, e.getMessage());
            return;
        }

        //Notifica l'utilizzo della carta strumento
        matchModel.notifyUseTool(tokenMatch, toolCard);
        matchBroadcastAck(matchModel, "il giocatore " + user.getName() + " ha usato la carta strumento " + toolCard.getName());
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
            return;
        }

        //Notifica fine turno del giocatore
        matchModel.notifyTurnEnd(tokenMatch, match);
        matchBroadcastAck(matchModel, "il giocatore " + player.getUser().getName() + " finisce il suo turno");

        //Controllo se i round sono finiti
        if (match.getTurnHandler().isEnded()) {
            //Per ogni player vengono comunicati i punteggi
            for (Player p : match.getPlayers()) {
                PlayerPoints points = match.getPlayerPoints(p);
                matchModel.notifyGetPoints(tokenMatch, player, points);
                matchBroadcastAck(matchModel, player.getUser().getName() + " ha totalizzato " + points.getTotalPoints() + " punti");
            }

            //Notifica fine partita
            matchModel.notifyMatchEnd(tokenMatch);
            matchBroadcastAck(matchModel, "partita conclusa");

            //La partita viene eliminata dal model
            model.destroyMatch(tokenMatch);
        } else {
            //Notifica inizio nuovo turno
            matchModel.notifyTurnStart(tokenMatch, match);
            matchBroadcastAck(matchModel, "inizio turno del giocatore " + match.getTurnPlayer());
        }
    }
}
