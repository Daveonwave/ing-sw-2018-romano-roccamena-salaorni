package mvc.view;

import mvc.MatchObserver;
import mvc.ViewResponder;
import mvc.model.objects.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public abstract class AppView extends UnicastRemoteObject implements ViewResponder, MatchObserver {
    //View astratta dell'applicazione

    //Costruttori
    protected AppView() throws RemoteException {
        super();
    }

    //Risposta del controllore
    public abstract void respondError(String message);
    public abstract void respondAck(String message);

    //Osservazione partita
    public abstract void onMatchStart(String tokenMatch, Match match) throws RemoteException;
    public abstract void onChooseWindows(String tokenMatch, Match match) throws RemoteException;
    public abstract void onFirstRoundStart(String tokenMatch, Match match) throws RemoteException;
    public abstract void onRoundStart(String tokenMatch, Match match) throws RemoteException;
    public abstract void onPlayerTurnStart(String tokenMatch, Match match, Player player) throws RemoteException;
    public abstract void onPlaceDie(String tokenMatch, Match match, Player player, Die die) throws RemoteException;
    public abstract void onUseTool(String tokenMatch, Match match, Player player, ToolCard toolCard) throws RemoteException;
    public abstract void onPlayerTurnEnd(String tokenMatch, Match match, Player player) throws RemoteException;
    public abstract void onRoundEnd(String tokenMatch, Match match) throws RemoteException;
    public abstract void onGetPoints(String tokenMatch, Match mach, Player player, PlayerPoints points) throws RemoteException;
    public abstract void onLastRoundEnd(String tokenMatch, Match match) throws RemoteException;
}
