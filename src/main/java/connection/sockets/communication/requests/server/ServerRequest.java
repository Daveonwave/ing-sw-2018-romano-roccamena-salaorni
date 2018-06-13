package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;

public interface ServerRequest {

    //Richiesta del server

    ServerResponse handleAction (ServerRequestHandler handler);
}
