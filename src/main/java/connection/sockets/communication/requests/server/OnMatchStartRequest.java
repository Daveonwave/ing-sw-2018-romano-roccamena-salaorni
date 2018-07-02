package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;

/**
 * Observation sendRequest on the start of the match
 */
public class OnMatchStartRequest implements ServerRequest {

    private final String tokenMatch;
    private final MultiPlayerMatch match;

    /**
     * Costructor of the specific sendRequest
     * @param tokenMatch id of the match
     * @param match object match
     */
    public OnMatchStartRequest(String tokenMatch, MultiPlayerMatch match) {
        this.tokenMatch = tokenMatch;
        this.match = match;
    }

    //Getter
    public String getTokenMatch() {
        return tokenMatch;
    }
    public MultiPlayerMatch getMatch() {
        return match;
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
