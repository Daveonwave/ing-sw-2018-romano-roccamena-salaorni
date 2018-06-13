package connection.sockets.communication.requests.client;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.handlers.ClientRequestHandler;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ClientRequest extends Serializable {
    //Richiesta del client

    ClientResponse handleAction (ClientRequestHandler handler) throws RemoteException;

}
