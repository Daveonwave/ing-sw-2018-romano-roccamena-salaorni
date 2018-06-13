package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;
import mvc.model.objects.MultiPlayerMatch;

public class OnPlaceDieRequest implements ServerRequest{

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final Cell cell;
    private final Die die;

    //Costruttori
    public OnPlaceDieRequest(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) {
        this.tokenMatch = tokenMatch;
        this.match = match;
        this.cell = cell;
        this.die = die;
    }

    @Override
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
