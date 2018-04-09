package game.match;

import service.ServiceException;

public class MatchException extends ServiceException {
    //Eccezione del servizio di una partita

    public MatchException(String message) {
        super(message);
    }
}
