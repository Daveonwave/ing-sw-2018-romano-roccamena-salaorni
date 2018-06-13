package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.stubs.AppViewStub;

public class LoginRequest implements ClientRequest {

    private final String name;
    private final AppViewStub view;

    //Costruttori
    public LoginRequest(String name, AppViewStub view) {
        this.name = name;
        this.view = view;
    }

    //Setter/Getter
    public String getName(){return this.name;}
    public AppViewStub getView() {
        return view;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) {
        return handler.handleAction(this);
    }
}
