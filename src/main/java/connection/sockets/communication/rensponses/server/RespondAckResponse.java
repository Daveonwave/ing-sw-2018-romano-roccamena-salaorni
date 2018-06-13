package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

public class RespondAckResponse extends ExceptionResponse implements ServerResponse {

    private final String message;

    public RespondAckResponse(String message) {
        this.message = message;
    }

    @Override
    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);
    }
}
