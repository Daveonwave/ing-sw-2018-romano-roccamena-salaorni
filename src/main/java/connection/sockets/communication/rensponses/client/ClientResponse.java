package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ClientResponse extends Serializable {
    //Risposta al client

    void handleAction (ClientResponseHandler handler) throws RemoteException;
}
