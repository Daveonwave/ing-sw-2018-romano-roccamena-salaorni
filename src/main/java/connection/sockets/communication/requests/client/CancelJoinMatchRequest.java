package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

public class CancelJoinMatchRequest implements ClientRequest {

    private final String tokenUser;

    //Costruttori
    public CancelJoinMatchRequest(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) {
        return handler.handleAction(this);
    }
}
