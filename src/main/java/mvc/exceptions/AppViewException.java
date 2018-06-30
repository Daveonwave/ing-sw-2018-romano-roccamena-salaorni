package mvc.exceptions;

import java.rmi.RemoteException;

/**
 * Exception from the view
 */
public class AppViewException extends RemoteException {
    //Eccezione della view dell'applicazione

    /**
     * Exception constructor
     * @param message error message to send
     */
    public AppViewException(String message) {
        super(message);
    }
}