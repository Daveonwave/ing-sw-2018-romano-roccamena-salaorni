package mvc.exceptions;

import java.rmi.RemoteException;

/**
 * Exception of the match
 */
public class MatchException extends RemoteException {
    //Eccezione di una partita

    /**
     * Exception constructor
     * @param message error message to send
     */
    public MatchException(String message) {
        super(message);
    }
}
