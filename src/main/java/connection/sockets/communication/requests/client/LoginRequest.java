package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;

/**
 * Request of login
 */
public class LoginRequest extends ClientRequest {

    private final String name;
    private final AppViewStub view;

    /**
     * Constructor of a specific client sendRequest
     * @param name name of the user
     * @param view view related with user
     * @param idAction id of the sendRequest
     */
    public LoginRequest(String name, AppViewStub view, int idAction) {
        super(idAction);
        this.name = name;
        this.view = view;
    }

    //Setter/Getter
    public String getName(){return this.name;}
    public AppViewStub getView() {
        return view;
    }

    /**
     * Handle a specific client sendRequest
     * @param handler ClientRequestHandler in this case
     * @return the response to client
     * @throws RemoteException
     */
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException {
        return handler.handleAction(this);
    }
}
