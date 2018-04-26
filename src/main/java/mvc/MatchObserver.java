package mvc;

import mvc.model.objects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface MatchObserver extends Remote{
    //Osservatore remoto partita

    void onMatchStart(String tokenMatch, Match match) throws RemoteException;
    void onChooseWindows(String tokenMatch, Match match) throws RemoteException;
    void onFirstRoundStart(String tokenMatch, Match match) throws RemoteException;
    void onRoundStart(String tokenMatch, Match match) throws RemoteException;
    void onPlayerTurnStart(String tokenMatch, Match match) throws RemoteException;
    void onPlaceDie(String tokenMatch, Match match, Player player, Cell cell, Die die) throws RemoteException;
    void onUseTool(String tokenMatch, Match match, Player player, ToolCard toolCard) throws RemoteException;
    void onPlayerTurnEnd(String tokenMatch, Match match, Player player) throws RemoteException;
    void onRoundEnd(String tokenMatch, Match match) throws RemoteException;
    void onLastRoundEnd(String tokenMatch, Match match) throws RemoteException;
    void onGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException;
}
