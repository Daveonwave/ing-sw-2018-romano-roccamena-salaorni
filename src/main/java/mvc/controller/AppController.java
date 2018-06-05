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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AppController implements AppControllerStub {
    //Controllore dell'applicazione

    //Tempo massimo di attesa del multiplayer
    public static final int MP_WAIT_TIME = 2 * 1000;

    //Model dell'applicazione
    private transient AppModel model;
    //Gestore partite multiplayer
    private transient MultiPlayerHandler multiPlayerLobby;

    //Costruttori
    public AppController() {
        this.model = AppModel.get();
        this.multiPlayerLobby = new MultiPlayerHandler(this, 4, MP_WAIT_TIME);
    }

    //Setter/Getter
    public void setModel(AppModel model){
        this.model = model;
    }
    public void setMultiPlayerLobby(MultiPlayerHandler multiPlayerLobby) {
        this.multiPlayerLobby = multiPlayerLobby;
    }

    public AppModel getModel() {
        return model;
    }
    public MultiPlayerHandler getMultiPlayerLobby() {
        return multiPlayerLobby;
    }

    //Ack ed error su utenti di un mvc.match o singolarmente
    public synchronized void matchBroadcastAck(MatchModel matchModel, String message) throws RemoteException {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondAck(message);
    }
    public synchronized void matchBroadcastError(MatchModel matchModel, String message) throws RemoteException {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondError(message);

        throw new AppControllerException(message);
    }
    public synchronized void viewAck(AppViewStub appView, String message) throws RemoteException {
        appView.respondAck(message);
    }
    public synchronized void viewError(AppViewStub appView, String message) throws RemoteException {
        appView.respondError(message);
        throw new AppControllerException(message);
    }
    public synchronized void userAck(User user, String message) throws RemoteException {
        viewAck(user.getAppView(), message);
    }
    public synchronized void userError(User user, String message) throws RemoteException {
        viewError(user.getAppView(), message);
    }

    //Operazioni su utente
    public synchronized String login(String name, AppViewStub appView) throws RemoteException {
        String token = "";

        //Crea utente nel model
        try {
            token =  model.createUser(name, appView);
        } catch (AppModelException e) {
            if (appView != null)
                viewError(appView, "utente sconosciuto");
            else
                throw new AppControllerException("utente sconosciuto");
        }

        viewAck(appView, "connesso come " + name);

        return token;
    }
    public synchronized void logout(String tokenUser) throws RemoteException {
        AppViewStub appView = null;

        //Ottiene view e distrugge l'utente
        try {
            appView = model.retrieveUser(tokenUser).getAppView();
            model.destroyUser(tokenUser);
        } catch (AppModelException e) {
            if (appView != null)
                viewError(appView, "utente sconosciuto");
            else
                throw new AppControllerException("utente sconosciuto");
        }

        viewAck(appView, "disconnesso");
    }

    //Inizia partita
    public synchronized void startMatch() throws RemoteException {
        //Ottiene utenti partecipanti
        List<String> partecipantTokens = multiPlayerLobby.retrieveWaitingUsersToken();
        List<User> partecipantUsers = new ArrayList<User>();
        for (String partecipantToken : partecipantTokens)
            partecipantUsers.add(model.retrieveUser(partecipantToken));

        //Crea nuova partita
        MultiPlayerMatch match = MatchCreator.createMultiPlayer(partecipantUsers);
        MatchModel matchModel = new MatchModel(match);

        //Registra gli utenti come osservatori della partita
        for (User partecipantUser : partecipantUsers) {
            matchModel.attachMatchObserver(partecipantUser.getAppView());
        }

        //Registra la nuova partita nel model
        String tokenMatch = model.createMatch(matchModel);

        //Inizia la parita
        try {
            match.beginMatch();
        } catch (MatchException e) {
            matchBroadcastError(matchModel, e.getMessage());
            return;
        }

        //Notifica inizio della partita
        matchModel.notifyMatchStart(tokenMatch);
        matchBroadcastAck(matchModel, "partita iniziata");

        //Notifica inizio fase di scelta delle finestre
        matchModel.notifyChooseWindows(tokenMatch);
        matchBroadcastAck(matchModel, "i giocatori stanno scegliendo le finestre");
    }

    //Mosse degli utenti sulla partita
    public synchronized void joinMatch(String tokenUser) throws RemoteException {
        //Ottiente gestore partite e partecipa all'attesa
        User user = model.retrieveUser(tokenUser);
        multiPlayerLobby.join(tokenUser);

        //Se sono presenti gli utenti partecipanti necessari
        if (multiPlayerLobby.isReady())
            startMatch();
    }
    public synchronized void cancelJoinMatch(String tokenUser) throws RemoteException {
        //Ottiene gestore partite
        User user = model.retrieveUser(tokenUser);

        //L'utente lascia l'attesa
        multiPlayerLobby.leave(tokenUser);

        //Notifica l'utente dell'uscita
        userAck(model.retrieveUser(tokenUser), "iscrizione partita cancellata");
    }
    public synchronized void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatchModel(tokenMatch);
        MultiPlayerMatch match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        //Abbandona la partita
        try {
            match.leaveMatch(player);
        } catch (MatchException e) {
            userError(user, e.getMessage());
            return;
        }

        //TODO: update struttura
    }
    public synchronized void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException {
        //TODO: implementazione
    }
    public synchronized void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatchModel(tokenMatch);
        MultiPlayerMatch match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        window = player.retrieveStartWindow(window);

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
        MatchModel matchModel = model.retrieveMatchModel(tokenMatch);
        MultiPlayerMatch match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        cell = player.getWindow().retrieveCell(cell);
        die = match.getMatchDice().retrieveDieFromDraftPool(die);

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
    public synchronized void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatchModel(tokenMatch);
        MultiPlayerMatch match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        toolCard = match.retrieveToolCard(toolCard);

        //Utilizza la tool card
        try {
            match.useToolCard(player, input, toolCard);
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
        MatchModel matchModel = model.retrieveMatchModel(tokenMatch);
        MultiPlayerMatch match = matchModel.getMatch();
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

            //Elimina giocatore associato a ogni utente
            for (Player p : match.getPlayers())
                p.getUser().removePlayer(p);

            //La partita viene eliminata dal model
            model.destroyMatch(tokenMatch);
        } else {
            //Notifica inizio nuovo turno
            matchModel.notifyTurnStart(tokenMatch, match);
            matchBroadcastAck(matchModel, "inizio turno del giocatore " + match.getTurnPlayer());
        }
    }
}
