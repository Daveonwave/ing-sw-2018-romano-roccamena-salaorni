package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.PlayerPoints;

/**
 * Observation sendRequest on getting points action
 */
public class OnGetPointsRequest implements ServerRequest {

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final Player player;
    private final PlayerPoints points;

    /**
     * Costructor of the specific sendRequest
     * @param tokenMatch id of the match
     * @param match object match
     * @param player current player
     * @param points player points
     */
    public OnGetPointsRequest(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) {
        this.tokenMatch = tokenMatch;
        this.match = match;
        this.player = player;
        this.points = points;
    }

    //Getter
    public String getTokenMatch() {
        return tokenMatch;
    }
    public MultiPlayerMatch getMatch() {
        return match;
    }
    public Player getPlayer() {
        return player;
    }
    public PlayerPoints getPoints() {
        return points;
    }

    /**
     * Handle the specific server sendRequest
     * @param handler ServerRequestHandler
     * @return the related response
     */
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
