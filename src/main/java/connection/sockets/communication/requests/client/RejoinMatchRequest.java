package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.rmi.RemoteException;

/**
 * Request of rejoin match
 */
public class RejoinMatchRequest extends ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;

    /**
     * Constructor of the specific client request
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @param idAction id of the request
     */
    public RejoinMatchRequest(String tokenUser, String tokenMatch, int idAction) {
        super(idAction);
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }

    /**
     * Handle a specific client request
     * @param handler ClientRequestHandler in this case
     * @return the response to client
     * @throws RemoteException
     */
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException {
        return handler.handleAction(this);
    }
}
