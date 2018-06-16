package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.rensponses.client.EndTurnResponse;
import mvc.model.objects.MultiPlayerMatch;

public class OnMatchStartResponse extends ExceptionResponse implements ServerResponse {

    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);

    }
}
