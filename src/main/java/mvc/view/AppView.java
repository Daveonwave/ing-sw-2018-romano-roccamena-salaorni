package mvc.view;

import mvc.model.ModelObserver;
import mvc.model.objects.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public abstract class AppView extends UnicastRemoteObject implements ViewResponse, ModelObserver {
    //View dell'applicazione

    //Costruttori
    protected AppView() throws RemoteException {
        super();
    }

    //Risposta del controllore
    public abstract void respondError(String message);
    public abstract void respondAck(String message);

    //Osservazione model
    public abstract void onMatchCreate(String tokenMatch, Match match) throws RemoteException;
    public abstract void onMatchStart(String tokenMatch, Match match) throws RemoteException;
    public abstract void onChooseWindows(String tokenMatch, Match match, List<Window> windows) throws RemoteException;
    public abstract void onRoundStart(String tokenMatch, Match match) throws RemoteException;
    public abstract void onPlayerStart(String tokenMatch, Match match, Player player) throws RemoteException;
    public abstract void onPlaceDie(String tokenMatch, Match match, Player player, Die die) throws RemoteException;
    public abstract void onUseTool(String tokenMatch, Match match, Player player, ToolCard toolCard) throws RemoteException;
    public abstract void onPlayerEnd(String tokenMatch, Match match, Player player) throws RemoteException;
    public abstract void onRoundEnd(String tokenMatch, Match match) throws RemoteException;
    public abstract void onGetPoints(String tokenMatch, Match mach, Player player, PlayerPoints points) throws RemoteException;
    public abstract void onMatchEnd(String tokenMatch, Match match) throws RemoteException;
}
