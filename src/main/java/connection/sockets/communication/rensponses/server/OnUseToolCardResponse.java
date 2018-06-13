package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.handlers.ClientResponseHandler;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.ToolCard;

public class OnUseToolCardResponse extends ExceptionResponse implements ServerResponse {

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final ToolCard toolCard;

    //Costruttori
    public OnUseToolCardResponse(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) {
        this.match = match;
        this.tokenMatch = tokenMatch;
        this.toolCard = toolCard;
    }


    public void handleAction(ServerResponseHandler handler) {
        handler.handleAction(this);
    }
}
