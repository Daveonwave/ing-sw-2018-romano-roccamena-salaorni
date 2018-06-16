package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

public class RespondAckResponse extends ExceptionResponse implements ServerResponse {

    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);
    }
}
