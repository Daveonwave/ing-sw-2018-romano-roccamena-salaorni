package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.client.ClientRequest;
import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

public class RespondAckRequest implements ServerRequest {

    private final String message;

    //Costruttori
    public RespondAckRequest(String message) {
        this.message = message;
    }

    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);    }
}
