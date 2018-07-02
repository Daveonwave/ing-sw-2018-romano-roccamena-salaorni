package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Generic response to a server sendRequest
 */
public interface ServerResponse extends Serializable {
    //Risposta server

    /**
     * Handle he response thanks to a specific handler
     * @param handler ServerResponseHandler
     * @throws RemoteException
     */
    void handleAction (ServerResponseHandler handler) throws RemoteException;

}
