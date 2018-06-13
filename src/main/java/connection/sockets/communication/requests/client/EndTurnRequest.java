package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.rmi.RemoteException;

public class EndTurnRequest implements ClientRequest {

    private final String userToken;
    private final String userMatch;

    //Costruttori
    public EndTurnRequest(String userToken, String userMatch) {
        this.userToken = userToken;
        this.userMatch = userMatch;
    }

    //Getter
    public String getUserToken() {
        return userToken;
    }
    public String getUserMatch() {
        return userMatch;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException {
        return handler.handleAction(this);
    }
}
