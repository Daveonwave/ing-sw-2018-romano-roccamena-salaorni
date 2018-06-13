package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.PlayerPoints;

public class OnGetPointsRequest implements ServerRequest {

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final Player player;
    private final PlayerPoints points;

    //Costruttori
    public OnGetPointsRequest(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) {
        this.tokenMatch = tokenMatch;
        this.match = match;
        this.player = player;
        this.points = points;
    }

    @Override
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
