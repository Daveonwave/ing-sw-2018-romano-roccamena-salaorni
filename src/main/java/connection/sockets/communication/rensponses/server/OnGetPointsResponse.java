package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.handlers.ClientResponseHandler;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.PlayerPoints;

public class OnGetPointsResponse extends ExceptionResponse implements ServerResponse {

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final Player player;
    private final PlayerPoints points;

    //Costruttori
    public OnGetPointsResponse(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) {
        this.tokenMatch = tokenMatch;
        this.match = match;
        this.player = player;
        this.points = points;
    }


    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);;
    }
}
