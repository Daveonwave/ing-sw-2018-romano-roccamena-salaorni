package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.ToolCard;

/**
 * Observation sendRequest on the usage of a tool card
 */
public class OnUseToolCardRequest implements ServerRequest {

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final ToolCard toolCard;

    /**
     * Constructor of the specific sendRequest
     * @param tokenMatch id of the match
     * @param match object match
     * @param toolCard selected tool card
     */
    public OnUseToolCardRequest(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) {
        this.tokenMatch = tokenMatch;
        this.match = match;
        this.toolCard = toolCard;
    }

    //Getter
    public String getTokenMatch() {
        return tokenMatch;
    }
    public MultiPlayerMatch getMatch() {
        return match;
    }
    public ToolCard getToolCard() {
        return toolCard;
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
