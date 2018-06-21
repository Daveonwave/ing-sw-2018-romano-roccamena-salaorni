package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;

import java.io.Serializable;

public interface ServerRequest extends Serializable {

    //Richiesta del server

    ServerResponse handleAction (ServerRequestHandler handler);
}
