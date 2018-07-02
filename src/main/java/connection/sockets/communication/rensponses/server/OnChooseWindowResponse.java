package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

import java.rmi.RemoteException;

/**
 * Response of choose window observation sendRequest
 */
public class OnChooseWindowResponse extends ExceptionResponse implements ServerResponse {

    /**
     * Handle he response thanks to a specific handler
     * @param handler ServerResponseHandler
     * @throws RemoteException
     */
    public void handleAction(ServerResponseHandler handler) throws RemoteException {
        handler.handleAction(this);
    }
}
