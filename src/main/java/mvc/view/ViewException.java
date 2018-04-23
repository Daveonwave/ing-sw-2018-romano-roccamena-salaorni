package mvc.view;

import java.rmi.RemoteException;

public class ViewException extends RemoteException {
    //Eccezione della view dell'applicazione

    //Costruttori
    public ViewException(String message) {
        super(message);
    }
}