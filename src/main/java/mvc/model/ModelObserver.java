package mvc.model;

import mvc.model.objects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ModelObserver extends Remote{
    //Osservatore remoto model

    //Match
    void onMatchCreate(String tokenMatch, Match match) throws RemoteException;
    void onChooseWindows(String tokenMatch, Match match, List<Window> windows) throws RemoteException;
    void onMatchStart(String tokenMatch, Match match) throws RemoteException;
    void onRoundStart(String tokenMatch, Match match) throws RemoteException;
    void onPlayerStart(String tokenMatch, Match match, Player player) throws RemoteException;
    void onPlaceDie(String tokenMatch, Match match, Player player, Die die) throws RemoteException;
    void onUseTool(String tokenMatch, Match match, Player player, ToolCard toolCard) throws RemoteException;
    void onPlayerEnd(String tokenMatch, Match match, Player player) throws RemoteException;
    void onRoundEnd(String tokenMatch, Match match) throws RemoteException;
    void onGetPoints(String tokenMatch, Match mach, Player player, PlayerPoints points) throws RemoteException;
    void onMatchEnd(String tokenMatch, Match match) throws RemoteException;
}
