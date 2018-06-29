package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

import java.rmi.RemoteException;

/**
 * Response of leave match
 */
public class LeaveMatchResponse extends ClientResponse {

    /**
     * Specific client response constructor
     * @param idAction to make the action unambiguous
     */
    public LeaveMatchResponse(int idAction) {
        super(idAction);
    }

    /**
     * Handle this specific kind of response
     * @param handler ServerActionHandler in this case
     * @throws RemoteException
     */
    public void handleAction(ClientResponseHandler handler) throws RemoteException {
        handler.handleAction(this);
    }
}
