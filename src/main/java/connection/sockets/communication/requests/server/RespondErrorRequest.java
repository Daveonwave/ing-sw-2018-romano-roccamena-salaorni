package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;

/**
 * Request of error
 */
public class RespondErrorRequest implements ServerRequest {

    private final String message;
    private final String tokenMatch;

    /**
     * Constructor of the specific request
     * @param message error message sent to client
     * @param tokenMatch id of the match
     */
    public RespondErrorRequest(String message, String tokenMatch) {
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
