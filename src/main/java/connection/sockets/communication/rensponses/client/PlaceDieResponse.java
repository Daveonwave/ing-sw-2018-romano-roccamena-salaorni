package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

public class PlaceDieResponse extends ExceptionResponse implements ClientResponse {

    @Override
    public void handleAction(ClientResponseHandler handler) {
        handler.handleAction(this);
    }
}
