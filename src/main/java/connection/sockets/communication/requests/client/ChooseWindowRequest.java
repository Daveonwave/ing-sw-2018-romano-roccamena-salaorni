package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.model.objects.Window;

import java.rmi.RemoteException;

/**
 * Request during the choice of windows
 */
public class ChooseWindowRequest extends ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;
    private final Window window;

    /**
     * Constructor of a specific client sendRequest
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @param window window chosen
     * @param idAction id of the sendRequest
     */
    public ChooseWindowRequest(String tokenUser, String tokenMatch, Window window, int idAction) {
        super(idAction);
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
        this.window = window;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }
    public Window getWindow() {
        return window;
    }

    /**
     * Handle a specific client sendRequest
     * @param handler ClientRequestHandler in this case
     * @return the response to client
     * @throws RemoteException
     */
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException { return handler.handleAction(this);}

}
