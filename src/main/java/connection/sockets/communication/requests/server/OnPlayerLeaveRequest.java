package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.Player;

/**
 * Observation sendRequest on the leave of a player
 */
public class OnPlayerLeaveRequest implements ServerRequest {

    private final String tokenMatch;
    private final Player player;

    /**
     * Constructor of the specific sendRequest
     * @param tokenMatch id of the match
     * @param player interested player
     */
    public OnPlayerLeaveRequest(String tokenMatch, Player player) {
        this.tokenMatch = tokenMatch;
        this.player = player;
    }

    //Getter
    public String getTokenMatch() {
        return tokenMatch;
    }
    public Player getPlayer() {
        return player;
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
