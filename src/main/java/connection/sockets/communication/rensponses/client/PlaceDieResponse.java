package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

import java.rmi.RemoteException;

/**
 * Response on a place die action
 */
public class PlaceDieResponse extends ClientResponse {

    /**
     * Specific client response constructor
     * @param idAction to make the action unambiguous
     */
    public PlaceDieResponse(int idAction) {
        super(idAction);
    }

    /**
     * Handle the specific kind of response
     * @param handler ServerActionHandler in this case
     * @throws RemoteException
     */
    public void handleAction(ClientResponseHandler handler) throws RemoteException {
        handler.handleAction(this);
    }
}
