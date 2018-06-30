package mvc.exceptions;

import java.rmi.RemoteException;

/**
 * Exception from the model
 */
public class AppModelException extends RemoteException {
    //Eccezione del modello dell'applicazione

    /**
     * Exception constructor
     * @param message error message to send
     */
    public AppModelException(String message) {
        super(message);
    }
}
