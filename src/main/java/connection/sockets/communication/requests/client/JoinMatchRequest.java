package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

public class JoinMatchRequest implements ClientRequest {

    private final String tokenUser;

    //Costruttori
    public JoinMatchRequest(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) {
        return handler.handleAction(this);
    }
}
