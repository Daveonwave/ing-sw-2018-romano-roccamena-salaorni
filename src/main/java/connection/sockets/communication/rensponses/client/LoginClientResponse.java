package connection.sockets.communication.rensponses.client;

import connection.sockets.communication.handlers.ClientResponseHandler;

public class LoginClientResponse implements ClientResponse {

    private final String tokenUser;

    //Costruttori
    public LoginClientResponse(String userToken) {
        this.tokenUser = userToken;
    }

    //Setter/Getter
    public String getTokenUser(){return this.tokenUser;}

    @Override
    public void handleAction(ClientResponseHandler handler) {handler.handleAction(this);}
}
