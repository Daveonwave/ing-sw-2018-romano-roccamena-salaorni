package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.handlers.ClientResponseHandler;
import mvc.model.objects.MultiPlayerMatch;

public class OnTurnEndResponse implements ServerResponse {

    private final String tokenMatch;
    private final MultiPlayerMatch match;

    //Costruttori
    public OnTurnEndResponse(String tokenMatch, MultiPlayerMatch match) {
        this.tokenMatch = tokenMatch;
        this.match = match;
    }


    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);
    }
}
