package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

import java.rmi.RemoteException;

/**
 * Response on login action
 */
public class LoginResponse extends ClientResponse {

    private final String tokenUser;

    /**
     * Specific client response constructor
     * @param userToken token assigned to player
     * @param idAction to make the action unambiguous
     */
    public LoginResponse(String userToken, int idAction) {
        super(idAction);
        this.tokenUser = userToken;
    }

    //Setter/Getter
    public String getTokenUser(){return this.tokenUser;}

    /**
     * Handle the specific kind of response
     * @param handler ServerActionHandler in this case
     * @throws RemoteException
     */
    public void handleAction(ClientResponseHandler handler) throws RemoteException {handler.handleAction(this);}
}
