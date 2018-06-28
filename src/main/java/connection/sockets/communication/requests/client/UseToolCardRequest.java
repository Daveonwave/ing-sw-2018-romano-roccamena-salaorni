package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.model.objects.ToolCard;
import mvc.model.objects.ToolCardInput;

import java.rmi.RemoteException;

/**
 * Request for the usege of a tool card
 */
public class UseToolCardRequest extends ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;
    private final ToolCardInput input;
    private final ToolCard toolCard;

    /**
     * Constructor of the specific client sendRequest
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @param input input of the tool card
     * @param toolCard selected tool card
     * @param idAction id of the sendRequest
     */
    public UseToolCardRequest(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard, int idAction) {
        super(idAction);
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
        this.input = input;
        this.toolCard = toolCard;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }
    public ToolCardInput getInput() {
        return input;
    }
    public ToolCard getToolCard() {
        return toolCard;
    }

    /**
     * Handle a specific client sendRequest
     * @param handler ClientRequestHandler in this case
     * @return the response to client
     * @throws RemoteException
     */
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException {
        return handler.handleAction(this);
    }
}
