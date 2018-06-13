package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;

public interface ServerResponse {
    //Risposta server

    void handleAction (ServerResponseHandler handler);

}
