package mvc.stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User-communication functional definition
 */
public interface ViewResponder extends Remote {
    //Risposta della view al controllore

    void respondError(String message, String tokenMatch) throws RemoteException;
    void respondAck(String message, String tokenMatch) throws RemoteException;
}
