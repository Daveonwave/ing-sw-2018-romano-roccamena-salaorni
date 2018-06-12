package mvc.stubs;

import mvc.model.objects.*;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiplayerCommandController extends Remote, Serializable {
    //Controllore remoto operazioni su partite multiplayer

    void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException;
    void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException;
    void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException;
    void endTurn(String tokenUser, String tokenMatch) throws RemoteException;
}
