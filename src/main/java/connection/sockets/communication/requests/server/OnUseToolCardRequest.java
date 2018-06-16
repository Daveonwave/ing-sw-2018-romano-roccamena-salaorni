package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.ToolCard;

public class OnUseToolCardRequest implements ServerRequest {

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final ToolCard toolCard;

    //costruttori
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

    @Override
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
