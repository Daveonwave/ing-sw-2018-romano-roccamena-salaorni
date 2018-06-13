package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

public class LogoutClientResponse implements ClientResponse {

    @Override
    public void handleAction(ClientResponseHandler handler) { handler.handleAction(this);}
}
