package connection.sockets;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.ClientActionHandler;
import connection.sockets.communication.rensponses.client.LoginResponse;
import connection.sockets.communication.requests.client.JoinMatchRequest;
import connection.sockets.communication.requests.client.LoginRequest;
import connection.sockets.communication.requests.client.LogoutRequest;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import mvc.view.AppView;

import java.rmi.RemoteException;

public class ControllerProxy implements AppControllerStub {
    //Proxy del controller utilizzato lato client

    private final SocketClient client;
    private ClientActionHandler clientActionHandler;
    private AppView view;

    //Costruttori
    public ControllerProxy(SocketClient client, AppView view) {
        this.client = client;
        this.clientActionHandler = new ClientActionHandler(view);
        this.view = view;
    }

    //Setter/Getter
    public SocketClient getClient() {
        return client;
    }


    public String login(String name, AppViewStub view) throws RemoteException {
        this.view = (AppView) view;
        client.request(new LoginRequest(name, null));
        return clientActionHandler.handleAction((LoginResponse) client.getResponse());
    }
    public void logout(String tokenUser) throws RemoteException {

    }
    public void joinMatch(String tokenUser) throws RemoteException {
    }
    public void cancelJoinMatch(String tokenUser) throws RemoteException {

    }
    public void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {

    }
    public void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException {

    }
    public void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {

    }
    public void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {

    }
    public void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException {

    }
    public void endTurn(String tokenUser, String tokenMatch) throws RemoteException {

    }


}
