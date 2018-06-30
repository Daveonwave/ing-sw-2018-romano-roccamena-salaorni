package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;

/**
 * Request of acknowledgement
 */
public class RespondAckRequest implements ServerRequest {

    private final String message;
    private final String tokenMatch;

    /**
     * Constructor of a specific request
     * @param message message sent to client
     * @param tokenMatch id of the match
     */
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

    /**
     * Handle the specific server request
     * @param handler ServerRequestHandler
     * @return the related response
     */
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);    }


}
