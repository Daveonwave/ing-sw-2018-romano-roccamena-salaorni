package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.handlers.ClientResponseHandler;
import mvc.model.objects.MultiPlayerMatch;

public class OnTurnStartResponse implements ServerResponse {

    private final String tokenMatch;
    private final MultiPlayerMatch match;

    //Costruttori
    public OnTurnStartResponse(String tokenMatch, MultiPlayerMatch match) {
        this.tokenMatch = tokenMatch;
        this.match = match;
    }


    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);
    }
}
