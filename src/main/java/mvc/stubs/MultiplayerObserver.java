package mvc.stubs;

import mvc.model.objects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiplayerObserver extends Remote{

    //Osservatore remoto partita

    void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException;

    void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException;
    void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException;
    void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException;
    void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
}
