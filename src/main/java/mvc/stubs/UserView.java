package mvc.stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserView extends Remote {
    //View utente

    String login(String userToken) throws RemoteException;
    void logout() throws RemoteException;
}
