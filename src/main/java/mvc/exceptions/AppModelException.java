package mvc.exceptions;

import java.rmi.RemoteException;

public class AppModelException extends RemoteException {
    //Eccezione del modello dell'applicazione

    //Costruttori
    public AppModelException(String message) {
        super(message);
    }
}
