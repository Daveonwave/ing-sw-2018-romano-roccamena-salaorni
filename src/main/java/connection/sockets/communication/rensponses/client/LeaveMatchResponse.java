package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

public class LeaveMatchResponse implements ClientResponse {

    @Override
    public void handleAction(ClientResponseHandler handler) {
        handler.handleAction(this);
    }
}
