package connection.sockets.communication.rensponses.server;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

import java.rmi.RemoteException;

public class OnTurnEndResponse extends ExceptionResponse implements ServerResponse {

    public void handleAction(ServerResponseHandler handler) throws RemoteException {
        handler.handleAction(this);
    }
}
