package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

import java.rmi.RemoteException;

/**
 * Response on a usage of tool card action
 */
public class UseToolCardResponse extends ClientResponse {

    /**
     * Specific client response constructor
     * @param idAction to make the action unambiguous
     */
    public UseToolCardResponse(int idAction) {
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
