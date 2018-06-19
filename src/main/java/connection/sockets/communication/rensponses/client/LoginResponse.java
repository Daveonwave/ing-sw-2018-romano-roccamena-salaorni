package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.rensponses.ExceptionResponse;

import java.rmi.RemoteException;

public class LoginResponse extends ExceptionResponse implements ClientResponse {

    private final String tokenUser;

    //Costruttori
    public LoginResponse(String userToken) {
        this.tokenUser = userToken;
    }

    //Setter/Getter
    public String getTokenUser(){return this.tokenUser;}

    @Override
    public void handleAction(ClientResponseHandler handler) throws RemoteException {handler.handleAction(this);}
}
