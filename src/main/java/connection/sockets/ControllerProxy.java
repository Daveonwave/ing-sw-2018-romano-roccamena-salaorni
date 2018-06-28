package connection.sockets;

import connection.sockets.communication.ClientActionHandler;
import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.requests.client.*;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;

public class ControllerProxy implements AppControllerStub {
    //Proxy del controller utilizzato lato client

    private final SocketClient client;
    private ClientActionHandler clientActionHandler;
    private static int idCounter;

    //Costruttori
    public ControllerProxy(SocketClient client, ClientActionHandler clientActionHandler) {
        this.client = client;
        this.clientActionHandler = clientActionHandler;
        idCounter = 0;
    }

    //Setter/Getter
    public SocketClient getClient() {
        return client;
    }

    //Get the id of a request and increment the counter to have unique id
    private int getAndUpdateIdCounter(){
        idCounter++;
        return idCounter - 1;
    }

    //Richieste operazioni utente
    public String login(String name, AppViewStub view) throws RemoteException {
        client.request(new LoginRequest(name, null, getAndUpdateIdCounter()));
        return clientActionHandler.handleAction((LoginResponse) client.getResponse());
    }
    public void logout(String tokenUser) throws RemoteException {
        client.request(new LogoutRequest(tokenUser, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((LogoutResponse) client.getResponse());
    }

    //Richieste attività multiplayer
    public void joinMatch(String tokenUser) throws RemoteException {
        client.request(new JoinMatchRequest(tokenUser, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((JoinMatchResponse) client.getResponse());
    }
    public void cancelJoinMatch(String tokenUser) throws RemoteException {
        client.request(new CancelJoinMatchRequest(tokenUser, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((CancelJoinMatchResponse) client.getResponse());
    }
    public void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {
        client.request(new LeaveMatchRequest(tokenUser, tokenMatch, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((LeaveMatchResponse) client.getResponse());
    }
    public void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException {
        client.request(new RejoinMatchRequest(tokenUser, tokenMatch, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((RejoinMatchResponse) client.getResponse());
    }

    //Richieste comando multiplayer
    public void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {
        client.request(new ChooseWindowRequest(tokenUser, tokenMatch, window, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((ChooseWindowResponse) client.getResponse());
    }
    public void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {
        client.request(new PlaceDieRequest(tokenUser, tokenMatch, cell, die, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((PlaceDieResponse) client.getResponse());
    }
    public void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException {
        client.request(new UseToolCardRequest(tokenUser, tokenMatch, input, toolCard, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((UseToolCardResponse) client.getResponse());
    }
    public void endTurn(String tokenUser, String tokenMatch) throws RemoteException {
        client.request(new EndTurnRequest(tokenUser, tokenMatch, getAndUpdateIdCounter()));
        clientActionHandler.handleAction((EndTurnResponse) client.getResponse());
    }

}
