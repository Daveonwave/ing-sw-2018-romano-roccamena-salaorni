package mvc.exceptions;

import java.rmi.RemoteException;

public class AppViewException extends RemoteException {
    //Eccezione della view dell'applicazione

    //Costruttori
    public AppViewException(String message) {
        super(message);
    }
}