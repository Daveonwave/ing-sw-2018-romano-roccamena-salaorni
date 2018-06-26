package connection.sockets;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.server.*;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;


public class ViewProxy implements AppViewStub {
    //Proxy della view utilizzato lato server

    private ServerTransmitter serverTransmitter;
    private ServerResponseHandler serverResponseHandler;

    //Costruttori
    public ViewProxy(ServerTransmitter serverTransmitter) {
        this.serverTransmitter = serverTransmitter;
        this.serverResponseHandler = null;
    }

    //Setter
    public void setServerResponseHandler(ServerResponseHandler serverResponseHandler) {
        this.serverResponseHandler = serverResponseHandler;
    }

    public AppControllerStub getController() throws RemoteException {
        return null;
    }


    //Metodi da eliminare ??
    public String login(String userToken) throws RemoteException {
        return null;
    }
    public void logout() throws RemoteException {

    }

    //Richieste osservazione attività multiplayer
    public void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnPlayerLeaveRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnPlayerLeaveResponse) serverTransmitter.getResponse());
    }
    public void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnPlayerRejoinRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnPlayerRejoinResponse) serverTransmitter.getResponse());
    }

    //Richieste osservazione comandi multiplayer
    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnMatchStartRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnMatchStartResponse) serverTransmitter.getResponse());
    }
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnChooseWindowRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnChooseWindowResponse) serverTransmitter.getResponse());
    }
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnTurnStartRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnTurnStartResponse)serverTransmitter.getResponse());
    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnTurnEndRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnTurnEndResponse) serverTransmitter.getResponse());
    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        serverTransmitter.request(new OnPlaceDieRequest(tokenMatch, match, cell, die));
        serverResponseHandler.handleAction((OnPlaceDieResponse) serverTransmitter.getResponse());
    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        serverTransmitter.request(new OnUseToolCardRequest(tokenMatch, match, toolCard));
        serverResponseHandler.handleAction((OnUseToolCardResponse) serverTransmitter.getResponse());
    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {
        serverTransmitter.request(new OnGetPointsRequest(tokenMatch, match, player, points));
        serverResponseHandler.handleAction((OnGetPointsResponse) serverTransmitter.getResponse());
    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnMatchEndRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnMatchEndResponse) serverTransmitter.getResponse());
    }

    //Metodi di acknowledgement o error
    public void respondError(String message, String tokenMatch) throws RemoteException {
        serverTransmitter.request(new RespondErrorRequest(message, tokenMatch));
        serverResponseHandler.handleAction((RespondErrorResponse) serverTransmitter.getResponse());
    }
    public void respondAck(String message, String tokenMatch) throws RemoteException {
        serverTransmitter.request(new RespondAckRequest(message, tokenMatch));
        serverResponseHandler.handleAction((RespondAckResponse) serverTransmitter.getResponse());
    }
}
