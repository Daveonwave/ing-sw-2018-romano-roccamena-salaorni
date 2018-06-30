package mvc.exceptions;

import java.rmi.RemoteException;

/**
 * Exception from the controller
 */
public class AppControllerException extends RemoteException {
    //Eccezione del controllore dell'applicazione

    /**
     * Exception constructor
     * @param message error message to send
     */
    public AppControllerException(String message) {
        super(message);
    }
}