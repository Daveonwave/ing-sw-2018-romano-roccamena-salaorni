package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;

/**
 * Observation request on the rejoining of a player
 */
public class OnPlayerRejoinRequest implements ServerRequest {

    private final String tokenMatch;
    private final Player player;

    /**
     * Constructor of the specific request
     * @param tokenMatch id of the match
     * @param player subject player
     */
    public OnPlayerRejoinRequest(String tokenMatch, Player player) {
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
     * Handle the specific server request
     * @param handler ServerRequestHandler
     * @return the related response
     */
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
