package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;

import java.rmi.RemoteException;

public interface ServerResponse {
    //Risposta server

    void handleAction (ServerResponseHandler handler) throws RemoteException;

}
