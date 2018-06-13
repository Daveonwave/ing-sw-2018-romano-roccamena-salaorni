package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.rmi.RemoteException;

public class JoinMatchRequest implements ClientRequest {

    private final String tokenUser;

    //Costruttori
    public JoinMatchRequest(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException {
        return handler.handleAction(this);
    }
}
