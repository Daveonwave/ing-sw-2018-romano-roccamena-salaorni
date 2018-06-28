package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

import java.rmi.RemoteException;

/**
 * Response of cancel while joining match
 */
public class CancelJoinMatchResponse extends ClientResponse {

    /**
     * Specific client response constructor
     * @param idAction to make the action unambiguous
     */
    public CancelJoinMatchResponse(int idAction) {
        super(idAction);
    }

    /**
     * Handle the specific response
     * @param handler ServerActionHandler in this case
     * @throws RemoteException
     */
     public void handleAction(ClientResponseHandler handler) throws RemoteException {
        handler.handleAction(this);
    }
}
