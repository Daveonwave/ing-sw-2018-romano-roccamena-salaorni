package mvc.model.objects;

import java.rmi.RemoteException;

public class MatchException extends RemoteException {
    //Eccezione di una partita

    //Costruttori
    public MatchException(String message) {
        super(message);
    }
}
