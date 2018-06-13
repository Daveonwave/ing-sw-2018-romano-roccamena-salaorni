package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

public class EndTurnRequest implements ClientRequest {

    private final String userToken;
    private final String userMatch;

    //Costruttori
    public EndTurnRequest(String userToken, String userMatch) {
        this.userToken = userToken;
        this.userMatch = userMatch;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) {
        return handler.handleAction(this);
    }
}
