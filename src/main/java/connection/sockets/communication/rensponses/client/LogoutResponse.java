package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

import java.rmi.RemoteException;

public class LogoutResponse extends ExceptionResponse implements ClientResponse {

    @Override
    public void handleAction(ClientResponseHandler handler) throws RemoteException { handler.handleAction(this);}
}
