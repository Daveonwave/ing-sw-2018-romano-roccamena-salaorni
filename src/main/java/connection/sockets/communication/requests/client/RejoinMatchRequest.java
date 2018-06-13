package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.rmi.RemoteException;

public class RejoinMatchRequest implements ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;

    //Costruttori
    public RejoinMatchRequest(String tokenUser, String tokenMatch) {
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException {
        return handler.handleAction(this);
    }
}
