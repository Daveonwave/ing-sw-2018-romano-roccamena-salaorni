package mvc.stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ViewResponder extends Remote {
    //Risposta della view al controllore

    void respondError(String message) throws RemoteException;
    void respondAck(String message) throws RemoteException;
}