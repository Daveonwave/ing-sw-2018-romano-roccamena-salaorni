package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

public class RespondErrorResponse extends ExceptionResponse implements ServerResponse {

    private final String message;

    public RespondErrorResponse(String message) {
        this.message = message;
    }

    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);
    }
}
