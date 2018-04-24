package mvc.controller;

import mvc.Controller;
import mvc.controller.builders.MatchBuilder;
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

    //Utente
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

    //Partita
    private synchronized MultiPlayerHandler retrieveMatchStartHandler(int playersCount) throws ControllerException {
        MultiPlayerHandler handler;

        switch (playersCount) {
            case 2:
                return ms2;
            case 3:
                return ms3;
            case 4:
                return ms4;
            default:
                throw new ControllerException("invalid players count");
        }
    }
    private synchronized void matchBroadcastAck(String tokenMatch, String message) {
        MatchModel match = model.retrieveMatch(tokenMatch);

        for (Player player : match.getPlayers())
            player.getUser().getAppView().respondAck(message);
    }
    private synchronized void matchBroadcastError(String tokenMatch, String message) {
        MatchModel match = model.retrieveMatch(tokenMatch);

        for (Player player : match.getPlayers())
            player.getUser().getAppView().respondError(message);
    }

    public synchronized void joinMatch(String tokenUser, int playersCount) throws RemoteException {
        MultiPlayerHandler handler = retrieveMatchStartHandler(playersCount);
        handler.join(tokenUser);

        if (handler.isReady()) {
            List<User> partecipantUsers = new ArrayList<User>();

            for (String partecipantToken : handler.getWaitingUsersToken())
                partecipantUsers.add(model.retrieveUser(partecipantToken));

            Match match = MatchBuilder.createMultiPlayer(partecipantUsers);
            MatchModel matchModel = new MatchModel(match);


            for (User user : partecipantUsers) {
                matchModel.attachMatchObserver(user.getAppView());
            }

            String tokenMatch = model.createMatch(matchModel);

            matchModel.notifyMatchStart(tokenMatch, match);
            handler.clear();
            matchBroadcastAck(tokenMatch, "match started");

            matchModel.notifyChooseWindows(tokenMatch, match);
            matchBroadcastAck(tokenMatch, "waiting player windows choosing");
        }
    }
    public synchronized void cancelJoinMatch(String tokenUser, int playersCount) throws RemoteException {
        MultiPlayerHandler handler = retrieveMatchStartHandler(playersCount);
        handler.leave(tokenUser);

        model.retrieveUser(tokenUser).getAppView().respondAck("match leaved");
    }
    public synchronized void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {
        //?Serve
    }
    public synchronized void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {
        MatchModel match = model.retrieveMatch(tokenMatch);
        User user = model.retrieveUser(tokenUser);
        Player player = model.retrievePlayer(tokenUser, tokenMatch);

        if (!match.getStartWindows().get(tokenUser).contains(window)) {
            String message = "invalid window assignement";
            user.getAppView().respondError(message);
            throw new ControllerException(message);
        }

        player.setWindow(window);
        player.setFavorTokens(window.getDifficulty());

        boolean nextStep = true;
        for (Player p : match.getPlayers()) {
            if (p.getWindow() == null) {
                nextStep = false;
                break;
            }
        }

        if (nextStep) {
            List<Die> draftPool = MatchBuilder.createDraftPool(match);

            match.setRound(1);
            match.setDraftPool(draftPool);

            match.notifyFirstRoundStart(tokenMatch, match);
            matchBroadcastAck(tokenMatch, "first round started");

            match.notifyRoundStart(tokenMatch, match);
            matchBroadcastAck(tokenMatch, "round " + match.getRound() + " started");

        }
    }
    public synchronized void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {

    }
    public synchronized void useToolCard(String tokenUser, String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {

    }
    public synchronized void endTurn(String tokenUser, String tokenMatch) throws RemoteException {
        Player player = model.retrievePlayer(tokenUser, tokenMatch);
        MatchModel match = model.retrieveMatch(tokenMatch);

        if (match.getTurnPlayer().getUser().getName().equals(player.getUser().getName())) {
            Player nextPlayer=null;
            //Modifica roundPlayer in match

            match.notifyPlayerTurnEnd(tokenMatch, match, player);
            matchBroadcastAck(tokenMatch, "player " + player.getUser().getName() + " end turn");

            match.notifyPlayerTurnStart(tokenMatch, match);
            matchBroadcastAck(tokenMatch, "player " + nextPlayer.getUser().getName() + " start turn");
        }
    }

}
