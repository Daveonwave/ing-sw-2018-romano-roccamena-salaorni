package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Generic client request
 */
public abstract class ClientRequest implements Serializable {
    //Richiesta del client

    private int idAction;

    /**
     * Generic client request constructor
     * @param idAction
     */
    protected ClientRequest(int idAction) {
        this.idAction = idAction;
    }

    //Getter/Setter
    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    /**
     * Handle the request thanks to a specific handler
     * @param handler ClientRequestHandler in this case
     * @return a response for client
     * @throws RemoteException
     */
    public abstract ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException;

}
