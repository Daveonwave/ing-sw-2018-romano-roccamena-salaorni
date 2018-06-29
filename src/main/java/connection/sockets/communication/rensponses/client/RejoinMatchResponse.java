package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

import java.rmi.RemoteException;

/**
 * Response on a rejoin match action
 */
public class RejoinMatchResponse extends ClientResponse {

    /**
     * Specific client response constructor
     * @param idAction to make the action unambiguous
     */
    public RejoinMatchResponse(int idAction) {
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
