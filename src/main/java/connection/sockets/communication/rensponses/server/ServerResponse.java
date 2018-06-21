package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ServerResponse extends Serializable {
    //Risposta server

    void handleAction (ServerResponseHandler handler) throws RemoteException;

}
