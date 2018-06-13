package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.handlers.ClientResponseHandler;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;
import mvc.model.objects.MultiPlayerMatch;

public class OnPlaceDieResponse extends ExceptionResponse implements ServerResponse {

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final Cell cell;
    private final Die die;

    //Costruttori
    public OnPlaceDieResponse(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) {
        this.tokenMatch = tokenMatch;
        this.match = match;
        this.cell = cell;
        this.die = die;
    }


    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);
    }
}
