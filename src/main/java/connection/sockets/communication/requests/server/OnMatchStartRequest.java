package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;

public class OnMatchStartRequest implements ServerRequest {

    private final String tokenMatch;
    private final MultiPlayerMatch match;

    //Costruttori
    public OnMatchStartRequest(String tokenMatch, MultiPlayerMatch match) {
        this.tokenMatch = tokenMatch;
        this.match = match;
    }


    @Override
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
