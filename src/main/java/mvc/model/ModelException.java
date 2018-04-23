package mvc.model;

import java.rmi.RemoteException;

public class ModelException extends RemoteException {
    //Eccezione del modello dell'applicazione

    //Costruttori
    public ModelException(String message) {
        super(message);
    }
}
