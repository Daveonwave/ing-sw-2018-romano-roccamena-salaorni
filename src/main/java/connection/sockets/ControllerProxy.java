package connection.sockets;

import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.ClientActionHandler;
import connection.sockets.communication.requests.client.*;
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

    //Richieste operazioni utente
    public String login(String name, AppViewStub view) throws RemoteException {
        this.view = (AppView) view;
        client.request(new LoginRequest(name, null));
        return clientActionHandler.handleAction((LoginResponse) client.getResponse());
    }
    public void logout(String tokenUser) throws RemoteException {
        client.request(new LogoutRequest(tokenUser));
        clientActionHandler.handleAction((LogoutResponse) client.getResponse());
    }

    //Richieste attività multiplayer
    public void joinMatch(String tokenUser) throws RemoteException {
        client.request(new JoinMatchRequest(tokenUser));
        clientActionHandler.handleAction((JoinMatchResponse) client.getResponse());
    }
    public void cancelJoinMatch(String tokenUser) throws RemoteException {
        client.request(new CancelJoinMatchRequest(tokenUser));
        clientActionHandler.handleAction((CancelJoinMatchResponse) client.getResponse());
    }
    public void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {
        client.request(new LeaveMatchRequest(tokenUser, tokenMatch));
        clientActionHandler.handleAction((LeaveMatchResponse) client.getResponse());
    }
    public void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException {
        client.request(new RejoinMatchRequest(tokenUser, tokenMatch));
        clientActionHandler.handleAction((RejoinMatchResponse) client.getResponse());
    }

    //Richieste comando multiplayer
    public void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {
        client.request(new ChooseWindowRequest(tokenUser, tokenMatch, window));
        clientActionHandler.handleAction((ChooseWindowResponse) client.getResponse());
    }
    public void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {
        client.request(new PlaceDieRequest(tokenUser, tokenMatch, cell, die));
        clientActionHandler.handleAction((PlaceDieResponse) client.getResponse());
    }
    public void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException {
        client.request(new UseToolCardRequest(tokenUser, tokenMatch, input, toolCard));
        clientActionHandler.handleAction((UseToolCardResponse) client.getResponse());
    }
    public void endTurn(String tokenUser, String tokenMatch) throws RemoteException {
        client.request(new EndTurnRequest(tokenUser, tokenMatch));
        clientActionHandler.handleAction((EndTurnResponse) client.getResponse());
    }

}
