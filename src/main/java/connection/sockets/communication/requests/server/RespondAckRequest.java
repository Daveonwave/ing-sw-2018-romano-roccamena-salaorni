package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;

public class RespondAckRequest implements ServerRequest {

    private final String message;
    private final String tokenMatch;

    //Costruttori
    public RespondAckRequest(String message, String tokenMatch) {
        this.message = message;
        this.tokenMatch = tokenMatch;
    }

    //Getter
    public String getMessage() {
        return message;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }

    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);    }


}
