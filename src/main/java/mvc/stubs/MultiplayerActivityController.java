package mvc.stubs;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiplayerActivityController extends Remote, Serializable {
    //Controllore remoto dell'attività di un giocatore in partite multiplayer

    void joinMatch(String tokenUser) throws RemoteException;
    void cancelJoinMatch(String tokenUser) throws RemoteException;
    void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException;
    void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException;
}
