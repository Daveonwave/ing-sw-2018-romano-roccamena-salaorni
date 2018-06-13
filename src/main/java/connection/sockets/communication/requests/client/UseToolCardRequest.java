package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.model.objects.ToolCard;
import mvc.model.objects.ToolCardInput;

public class UseToolCardRequest implements ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;
    private final ToolCardInput input;
    private final ToolCard toolCard;

    //Costruttori
    public UseToolCardRequest(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) {
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
        this.input = input;
        this.toolCard = toolCard;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) {
        return handler.handleAction(this);
    }
}
