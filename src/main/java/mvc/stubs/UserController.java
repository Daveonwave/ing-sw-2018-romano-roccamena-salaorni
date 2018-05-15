package mvc.stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserController extends Remote {
    //Controllore remoto utente

    String login(String name, AppViewStub view) throws RemoteException;
    void logout(String tokenUser) throws RemoteException;
}
