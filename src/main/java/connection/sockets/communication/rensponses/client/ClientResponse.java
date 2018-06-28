package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Generic client response
 */
public abstract class ClientResponse extends ExceptionResponse implements Serializable {
    //Risposta al client

    private int idAction;

    /**
     * Generic client response constructor
     * @param idAction to make the action unambiguous
     */
    protected ClientResponse(int idAction) {
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
     * Handle the response thanks to a specific handler
     * @param handler ServerActionHandler in this case
     * @throws RemoteException
     */
    public abstract void handleAction(ClientResponseHandler handler) throws RemoteException;
}
