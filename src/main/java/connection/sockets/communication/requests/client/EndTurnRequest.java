package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.rmi.RemoteException;

/**
 * Request of end the current turn
 */
public class EndTurnRequest extends ClientRequest {

    private final String userToken;
    private final String userMatch;

    /**
     * Costructor of the specific request
     * @param userToken id of the user
     * @param userMatch id of the match
     * @param idAction id of the request
     */
    public EndTurnRequest(String userToken, String userMatch, int idAction) {
        super(idAction);
        this.userToken = userToken;
        this.userMatch = userMatch;
    }

    //Getter
    public String getUserToken() {
        return userToken;
    }
    public String getUserMatch() {
        return userMatch;
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
