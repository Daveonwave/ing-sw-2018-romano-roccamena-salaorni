package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.rmi.RemoteException;

/**
 * Request of end the current turn
 */
public class EndTurnRequest extends ClientRequest {

    private final String userToken;
    private final String tokenMatch;

    /**
     * Costructor of the specific sendRequest
     * @param userToken id of the user
     * @param tokenMatch id of the match
     * @param idAction id of the sendRequest
     */
    public EndTurnRequest(String userToken, String tokenMatch, int idAction) {
        super(idAction);
        this.userToken = userToken;
        this.tokenMatch = tokenMatch;
    }

    //Getter
    public String getUserToken() {
        return userToken;
    }
    public String getTokenMatch() {
        return tokenMatch;
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
