package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;

import java.io.Serializable;

/**
 * Generic sendRequest of the server
 */
public interface ServerRequest extends Serializable {

    //Richiesta del server
    /**
     * Handle the specific server sendRequest
     * @param handler ServerRequestHandler
     * @return the related response
     */
    ServerResponse handleAction (ServerRequestHandler handler);
}
