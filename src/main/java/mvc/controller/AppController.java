package mvc.controller;

import config.TimerConfig;
import mvc.controller.handlers.MultiplayerHandler;
import mvc.controller.handlers.NoPlayersHandler;
import mvc.controller.handlers.TimedTurnHandler;
import mvc.creators.MatchCreator;
import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.exceptions.MatchException;
import mvc.model.AppModel;
import mvc.model.MatchModel;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Server-side application controller
 */
public class AppController implements AppControllerStub {
    //Controllore dell'applicazione

    //Model dell'applicazione
    private transient AppModel model;
    //Configurazioni timer
    private transient TimerConfig timerConfig;
    //Gestore partite multiplayer
    private transient MultiplayerHandler multiPlayerLobby;


    //Costruttori
    public AppController(TimerConfig timerConfig) {
        this.model = AppModel.get();
        this.timerConfig = timerConfig;
        this.multiPlayerLobby = new MultiplayerHandler(this, 4, timerConfig.getJoinWaitTime());
    }


    //Setter/Getter
    public void setModel(AppModel model){
        this.model = model;
    }
    public void setTimerConfig(TimerConfig timerConfig) {
        this.timerConfig = timerConfig;
    }
    public void setMultiPlayerLobby(MultiplayerHandler multiPlayerLobby) {
        this.multiPlayerLobby = multiPlayerLobby;
    }

    public AppModel getModel() {
        return model;
    }
    public TimerConfig getTimerConfig() {
        return timerConfig;
    }
    public MultiplayerHandler getMultiPlayerLobby() {
        return multiPlayerLobby;
    }


    //Resetta i timer
    /**
     * Reset no player handler
     */
    public synchronized void resetNoPlayerHandler() {
        multiPlayerLobby.setNoPlayersHandler(new NoPlayersHandler(this, timerConfig.getJoinWaitTime()));
    }
    /**
     * Reset timed turn handler
     * @param match Match modified by timer
     * @param tokenMatch Token of match
     * @param matchModel Model of match
     */
    public synchronized void resetTimedTurnHandler(MultiPlayerMatch match, String tokenMatch, MatchModel matchModel) {
        match.setTimedTurnHandler(new TimedTurnHandler(this, timerConfig.getTurnMaxTime(), match, tokenMatch, matchModel));
    }


    //Ack ed error su utenti di un match o singolarmente
    /**
     * Sends an ack to users playing a match
     * @param tokenMatch Token of the match
     * @param matchModel Model of the match
     * @param message Message of the ack
     * @throws RemoteException
     */
    public synchronized void matchBroadcastAck(String tokenMatch, MatchModel matchModel, String message) throws RemoteException {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondAck(message, tokenMatch);
    }

    /**
     * Sends an error to users playing a match
     * @param tokenMatch Token of the match
     * @param matchModel Model of the match
     * @param message Message of the error
     * @throws RemoteException AppControllerException as a callback error signal
     */
    public synchronized void matchBroadcastError(String tokenMatch, MatchModel matchModel, String message) throws RemoteException {
        for (Player player : matchModel.getMatch().getPlayers())
            player.getUser().getAppView().respondError(message, tokenMatch);

        throw new AppControllerException(message);
    }
    /**
     * Sends an ack to a single user view
     * @param appView Application view of the user
     * @param message Message of the ack
     * @throws RemoteException
     */
    public synchronized void viewAck(AppViewStub appView, String message) throws RemoteException {
        appView.respondAck(message, null);
    }
    public synchronized void viewError(AppViewStub appView, String message) throws RemoteException {
        appView.respondError(message, null);
        throw new AppControllerException(message);
    }

    /**
     * Sends an ack to a single user
     * @param user User to send ack
     * @param message Message of the ack
     * @throws RemoteException
     */
    public synchronized void userAck(User user, String message) throws RemoteException {
        viewAck(user.getAppView(), message);
    }

    /**
     * Sends an error to a single user
     * @param user User to send error
     * @param message Message of the error
     * @throws RemoteException AppControllerException as a callback error signal
     */
    public synchronized void userError(User user, String message) throws RemoteException {
        viewError(user.getAppView(), message);
    }


    //Operazioni su utente

    /**
     * Request login for new user
     * @param name New user's name
     * @param appView User's application view
     * @return Token of the registered user
     * @throws RemoteException
     */
    public synchronized String login(String name, AppViewStub appView) throws RemoteException {
        String token = "";

        //Crea utente nel model
        try {
            token =  model.createUser(name, appView);
        } catch (AppModelException e) {
            if (appView != null)
                viewError(appView, "nome utente non valido");
            else
                throw new AppControllerException("nome utente non valido");
        }

        viewAck(appView, "loggato come " + name);

        return token;
    }

    /**
     * Request new logout forlogged user
     * @param tokenUser User's token
     * @throws RemoteException
     */
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

        viewAck(appView, "logout effettuato");
    }

    //Sottometodi di gestione multiplayer
    public synchronized void startMatch() throws RemoteException {
        //Ottiene utenti partecipanti
        List<String> partecipantTokens = multiPlayerLobby.retrieveWaitingUsersToken();
        List<User> partecipantUsers = new ArrayList<User>();
        for (String partecipantToken : partecipantTokens)
            partecipantUsers.add(model.retrieveUser(partecipantToken));

        //Crea nuova partita
        MatchModel matchModel = new MatchModel(MatchCreator.createMultiPlayer(partecipantUsers));
        MultiPlayerMatch match = matchModel.getMatch();

        //Registra gli utenti come osservatori della partita
        for (User partecipantUser : partecipantUsers) {
            matchModel.attachMatchObserver(partecipantUser.getAppView());
        }

        //Registra la nuova partita nel model
        String tokenMatch = model.createMatch(matchModel);

        //Crea nuovo gestore di controllo tempo turni
        resetTimedTurnHandler(match, tokenMatch, matchModel);

        //Inizia la parita
        try {
            match.beginMatch();
        } catch (MatchException e) {
            matchBroadcastError(tokenMatch, matchModel, e.getMessage());
            return;
        }

        //Notifica inizio della partita
        matchModel.notifyMatchStart(tokenMatch);
        matchBroadcastAck(tokenMatch, matchModel, "partita iniziata");

        //Notifica inizio fase di scelta delle finestre
        matchModel.notifyChooseWindows(tokenMatch);
        matchBroadcastAck(tokenMatch, matchModel, "i giocatori stanno scegliendo le finestre");
    }
    public synchronized void finishTurn(MultiPlayerMatch match, String tokenMatch, MatchModel matchModel, Player player) throws RemoteException {
        //Esegue la fine del turno
        try {
            match.endTurn(player);
        } catch (MatchException e) {
            userError(player.getUser(), e.getMessage());
            return;
        }

        //Termina controllo tempo turno
        match.getTimedTurnHandler().stop();
        resetTimedTurnHandler(match, tokenMatch, matchModel);

        //Notifica fine turno del giocatore
        matchModel.notifyTurnEnd(tokenMatch, match);
        matchBroadcastAck(tokenMatch, matchModel, "il giocatore " + player.getUser().getName() + " finisce il suo turno");

        //Controllo se i round sono finiti
        if (match.getTurnHandler().isEnded()) {
            //Per ogni player vengono comunicati i punteggi
            for (Player p : match.getPlayers()) {
                PlayerPoints points = match.getPlayerPoints(p);
                matchModel.notifyGetPoints(tokenMatch, player, points);
                matchBroadcastAck(tokenMatch, matchModel, player.getUser().getName() + " ha totalizzato " + points.getTotalPoints() + " punti");
            }

            //Notifica fine partita
            matchModel.notifyMatchEnd(tokenMatch);
            matchBroadcastAck(tokenMatch, matchModel, "partita conclusa");

            //Elimina giocatore associato a ogni utente
            for (Player p : match.getPlayers())
                p.getUser().removePlayer(p);

            //La partita viene eliminata dal model
            model.destroyMatch(tokenMatch);
        } else {
            //Notifica inizio nuovo turno
            matchModel.notifyTurnStart(tokenMatch, match);
            matchBroadcastAck(tokenMatch, matchModel, "inizio turno del giocatore " + match.getTurnPlayer().getUser().getName());

            //Avvia controllo tempo turno
            match.getTimedTurnHandler().start();

            //Se il giocatore è inattivo passa il turno per inattività
            if (!match.getTurnPlayer().isActive()) {
                try {
                    finishTurn(match, tokenMatch, matchModel, match.getTurnPlayer());
                } catch (RemoteException e) { }
            }
        }
    }

    //Operazioni su multiplayer
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

        //Notifica abbandono giocatore
        matchModel.notifyPlayerLeave(tokenMatch);
        matchBroadcastAck(tokenMatch, matchModel, "il giocatore " + player.getUser().getName() + " ha abbandonato la partita");
    }
    public synchronized void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatchModel(tokenMatch);
        MultiPlayerMatch match = matchModel.getMatch();
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        //Abbandona la partita
        try {
            match.rejoinMatch(player);
        } catch (MatchException e) {
            userError(user, e.getMessage());
            return;
        }

        //Notifica ripartecipazione giocatore
        matchModel.notifyPlayerRejoin(tokenMatch);
        matchBroadcastAck(tokenMatch, matchModel, "il giocatore " + player.getUser().getName() + " si è riunito alla partita");
    }

    public synchronized void joinMatch(String tokenUser) throws RemoteException {
        //Ottiente gestore partite e partecipa all'attesa
        User user = model.retrieveUser(tokenUser);
        multiPlayerLobby.join(tokenUser);

        //Se sono presenti gli utenti partecipanti necessari inizia partita
        if (multiPlayerLobby.isReady()) {
            //Inizia partita
            startMatch();
        }
    }
    public synchronized void cancelJoinMatch(String tokenUser) throws RemoteException {
        //Ottiene gestore partite
        User user = model.retrieveUser(tokenUser);

        //L'utente lascia l'attesa
        multiPlayerLobby.leave(tokenUser);

        //Notifica l'utente dell'uscita
        userAck(model.retrieveUser(tokenUser), "iscrizione partita cancellata");
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
            matchBroadcastAck(tokenMatch, matchModel, "iniziano i round della partita");

            //Avvia controllo tempo turno
            match.getTimedTurnHandler().start();
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
        matchBroadcastAck(tokenMatch, matchModel, "il giocatore " + user.getName() + " ha piazzato un dado");
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
        matchBroadcastAck(tokenMatch, matchModel, "il giocatore " + user.getName() + " ha usato la carta strumento " + toolCard.getName());
    }
    public synchronized void endTurn(String tokenUser, String tokenMatch) throws RemoteException {
        //Ottiene oggetti dal model
        MatchModel matchModel = model.retrieveMatchModel(tokenMatch);
        MultiPlayerMatch match = matchModel.getMatch();
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        //Esegue la fine del turno
        finishTurn(match, tokenMatch, matchModel, player);
    }
}
