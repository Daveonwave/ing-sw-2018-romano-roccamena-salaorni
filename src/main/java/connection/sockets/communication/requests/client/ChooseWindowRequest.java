package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.model.objects.Window;

import java.rmi.RemoteException;

public class ChooseWindowRequest implements ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;
    private final Window window;

    //Costruttori
    public ChooseWindowRequest(String tokenUser, String tokenMatch, Window window) {
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
        this.window = window;
    }

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }
    public Window getWindow() {
        return window;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException { return handler.handleAction(this);}

}
