package connection.sockets.clientside;

import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.requests.client.*;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;

/**
 * Proxy of the controller client side
 */
public class ControllerProxy implements AppControllerStub {
    //Proxy del controller utilizzato lato client

    private final transient SocketClient client;
    private transient ClientActionHandler clientActionHandler;

    private static int idRequestCounter = 0;

    /**
     * Class Constructor
     * @param client the gate of the connection
     * @param clientActionHandler handler of the requests and responses
     */
    public ControllerProxy(SocketClient client, ClientActionHandler clientActionHandler) {
        this.client = client;
        this.clientActionHandler = clientActionHandler;
    }

    //Setter/Getter
    public SocketClient getClient() {
        return client;
    }

    //Get the id of a sendRequest and increment the counter to have unique id
    /**
     * Id generator to map all the requests and the relative response
     * @return a new unique id
     */
    private static int getAndUpdateIdCounter(){
        idRequestCounter ++;
        return idRequestCounter - 1;
    }

    //Richieste operazioni utente
    /**
     * Login action
     * @param name name insert by the user
     * @param view view associated with the user
     * @return a univocal id for each user
     * @throws RemoteException
     */
    public String login(String name, AppViewStub view) throws RemoteException {
        ClientRequest request = new LoginRequest(name, null, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        return clientActionHandler.handleAction((LoginResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }
    /**
     * Logout action
     * @param tokenUser id of the user
     * @throws RemoteException
     */
    public void logout(String tokenUser) throws RemoteException {
        ClientRequest request = new LogoutRequest(tokenUser, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((LogoutResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }

    //Richieste attività multiplayer
    /**
     * Join Match action
     * @param tokenUser id of the user
     * @throws RemoteException
     */
    public void joinMatch(String tokenUser) throws RemoteException {
        ClientRequest request = new JoinMatchRequest(tokenUser, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((JoinMatchResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }
    /**
     * Abort the join match action
     * @param tokenUser id of the user
     * @throws RemoteException
     */
    public void cancelJoinMatch(String tokenUser) throws RemoteException {
        ClientRequest request = new CancelJoinMatchRequest(tokenUser, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((CancelJoinMatchResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }
    /**
     * Leave match action
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @throws RemoteException
     */
    public void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {
        ClientRequest request = new LeaveMatchRequest(tokenUser, tokenMatch, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((LeaveMatchResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }
    /**
     * Rejoin Match action
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @throws RemoteException
     */
    public void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException {
        ClientRequest request = new RejoinMatchRequest(tokenUser, tokenMatch, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((RejoinMatchResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }

    //Richieste comando multiplayer
    /**
     * Choose window action
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @param window selected window
     * @throws RemoteException
     */
    public void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {
        ClientRequest request = new ChooseWindowRequest(tokenUser, tokenMatch, window, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((ChooseWindowResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }
    /**
     * Place die action
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @param cell selected cell
     * @param die selected die
     * @throws RemoteException
     */
    public void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {
        ClientRequest request = new PlaceDieRequest(tokenUser, tokenMatch, cell, die, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((PlaceDieResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }
    /**
     * Use tool card action
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @param input selected input
     * @param toolCard selected tool card
     * @throws RemoteException
     */
    public void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException {
        ClientRequest request = new UseToolCardRequest(tokenUser, tokenMatch, input, toolCard, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((UseToolCardResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }
    /**
     * End turn action
     * @param tokenUser id of the user
     * @param tokenMatch id of the match
     * @throws RemoteException
     */
    public void endTurn(String tokenUser, String tokenMatch) throws RemoteException {
        ClientRequest request = new EndTurnRequest(tokenUser, tokenMatch, getAndUpdateIdCounter());
        client.getClientTransmitter().sendRequest(request);
        clientActionHandler.handleAction((EndTurnResponse) client.getClientTransmitter().getResponse(request.getIdAction()));
    }

}
