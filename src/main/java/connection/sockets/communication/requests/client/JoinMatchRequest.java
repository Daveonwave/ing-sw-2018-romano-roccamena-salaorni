package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.rmi.RemoteException;

/**
 * Request of join match
 */
public class JoinMatchRequest extends ClientRequest {

    private final String tokenUser;

    /**
     * Constructor of a specific client request
     * @param tokenUser id of the user
     * @param idAction id of the request
     */
    public JoinMatchRequest(String tokenUser, int idAction) {
        super(idAction);
        this.tokenUser = tokenUser;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
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
