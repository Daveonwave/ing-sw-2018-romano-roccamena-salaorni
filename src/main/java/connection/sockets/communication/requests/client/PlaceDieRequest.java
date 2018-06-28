package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;

import java.rmi.RemoteException;

/**
 * Request for place die
 */
public class PlaceDieRequest extends ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;
    private final Cell cell;
    private final Die die;

    /**
     * Constructor of the specific sendRequest
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @param cell cell selected
     * @param die die selected
     * @param idAction id of the sendRequest
     */
    public PlaceDieRequest(String tokenUser, String tokenMatch, Cell cell, Die die, int idAction) {
        super(idAction);
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
        this.cell = cell;
        this.die = die;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }
    public Cell getCell() {
        return cell;
    }
    public Die getDie() {
        return die;
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
