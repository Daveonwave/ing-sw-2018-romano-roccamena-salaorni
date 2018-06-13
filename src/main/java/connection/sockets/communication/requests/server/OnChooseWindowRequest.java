package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;

public class OnChooseWindowRequest implements ServerRequest {

    private final String tokenMatch;
    private final MultiPlayerMatch match;

    //Costruttori
    public OnChooseWindowRequest(String tokenMatch, MultiPlayerMatch match) {
        this.tokenMatch = tokenMatch;
        this.match = match;
    }


    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
