package mvc;

import mvc.view.AppView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserController extends Remote {
    //Controllore remoto utente

    String login(String name, AppView view) throws RemoteException;
    void logout(String tokenUser) throws RemoteException;
}
