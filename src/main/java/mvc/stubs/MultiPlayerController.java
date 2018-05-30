package mvc.stubs;

import mvc.model.objects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiPlayerController extends Remote {
    //Controllore remoto partita

    void joinMatch(String tokenUser) throws RemoteException;
    void cancelJoinMatch(String tokenUser) throws RemoteException;
    void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException;
    void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException;
    void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException;
    void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException;
    void endTurn(String tokenUser, String tokenMatch) throws RemoteException;
}
