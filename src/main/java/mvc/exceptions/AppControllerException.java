package mvc.exceptions;

import java.rmi.RemoteException;

public class AppControllerException extends RemoteException {
    //Eccezione del controllore dell'applicazione

    //Costruttori
    public AppControllerException(String message) {
        super(message);
    }
}