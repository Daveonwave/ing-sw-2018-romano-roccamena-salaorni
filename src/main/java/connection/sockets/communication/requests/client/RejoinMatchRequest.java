package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

public class RejoinMatchRequest implements ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;

    //Costruttori
    public RejoinMatchRequest(String tokenUser, String tokenMatch) {
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) {
        return handler.handleAction(this);
    }
}
