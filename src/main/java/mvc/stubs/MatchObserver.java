package mvc.stubs;

import mvc.model.objects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface MatchObserver extends Remote{
    //Osservatore remoto partita

    void onMatchStart(String tokenMatch, Match match) throws RemoteException;
    void onChooseWindows(String tokenMatch, Match match) throws RemoteException;
    void onTurnStart(String tokenMatch, Match match) throws RemoteException;
    void onTurnEnd(String tokenMatch, Match match) throws RemoteException;
    void onPlaceDie(String tokenMatch, Match match, Cell cell, Die die) throws RemoteException;
    void onUseTool(String tokenMatch, Match match, ToolCard toolCard) throws RemoteException;
    void onGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException;
    void onMatchEnd(String tokenMatch, Match match) throws RemoteException;
}
