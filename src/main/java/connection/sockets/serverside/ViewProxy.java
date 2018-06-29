package connection.sockets.serverside;

import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.server.*;
import connection.sockets.serverside.ServerTransmitter;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;

/**
 * Proxy of the view server side
 */
public class ViewProxy implements AppViewStub {
    //Proxy della view utilizzato lato server

    private transient ServerTransmitter serverTransmitter;
    private transient ServerResponseHandler serverResponseHandler;

    /**
     * Class constructor
     * @param serverTransmitter used to send request to client
     */
    public ViewProxy(ServerTransmitter serverTransmitter) {
        this.serverTransmitter = serverTransmitter;
        this.serverResponseHandler = null;
    }

    //Getter/Setter
    public AppControllerStub getController() throws RemoteException {
        return null;
    }

    public void setServerResponseHandler(ServerResponseHandler serverResponseHandler) {
        this.serverResponseHandler = serverResponseHandler;
    }

    //Metodi da eliminare ?? ////////////////////////////////////////////////////
    public String login(String userToken) throws RemoteException {return null;}
    public void logout() throws RemoteException{}
    /////////////////////////////////////////////////////////////////////////////

    //Richieste osservazione attività multiplayer
    /**
     * Observation method on activity of the player when he leaves the match
     * @param tokenMatch id of the match
     * @param match object match
     * @throws RemoteException
     */
    public void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnPlayerLeaveRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnPlayerLeaveResponse) serverTransmitter.getResponse());
    }
    /**
     * Observation method on activity of player when he rejoins the match
     * @param tokenMatch id of the match
     * @param match object match
     * @throws RemoteException
     */
    public void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnPlayerRejoinRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnPlayerRejoinResponse) serverTransmitter.getResponse());
    }

    //Richieste osservazione comandi multiplayer
    /**
     * Observation method on the state of the match when the match starts
     * @param tokenMatch id of the match
     * @param match object match
     * @throws RemoteException
     */
    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnMatchStartRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnMatchStartResponse) serverTransmitter.getResponse());
    }
    /**
     * Observation method on the state of the match when there is the choice of windows
     * @param tokenMatch id of the match
     * @param match object match
     * @throws RemoteException
     */
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnChooseWindowRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnChooseWindowResponse) serverTransmitter.getResponse());
    }
    /**
     * Observation method on the state of the match when the turn starts
     * @param tokenMatch id of the match
     * @param match object match
     * @throws RemoteException
     */
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnTurnStartRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnTurnStartResponse)serverTransmitter.getResponse());
    }
    /**
     * Observation method on the state of the match when the turn ends
     * @param tokenMatch id of the match
     * @param match object match
     * @throws RemoteException
     */
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnTurnEndRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnTurnEndResponse) serverTransmitter.getResponse());
    }
    /**
     * Observation method on the state of the match when the player must place a die
     * @param tokenMatch id of the match
     * @param match object match
     * @param cell selected cell
     * @param die selected die
     * @throws RemoteException
     */
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        serverTransmitter.request(new OnPlaceDieRequest(tokenMatch, match, cell, die));
        serverResponseHandler.handleAction((OnPlaceDieResponse) serverTransmitter.getResponse());
    }
    /**
     * Observation method on the state of the match when the player uses a tool card
     * @param tokenMatch id of the match
     * @param match object match
     * @param toolCard selected tool card
     * @throws RemoteException
     */
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        serverTransmitter.request(new OnUseToolCardRequest(tokenMatch, match, toolCard));
        serverResponseHandler.handleAction((OnUseToolCardResponse) serverTransmitter.getResponse());
    }
    /**
     * Observation method on the state of the match when is necessary to calculate the scores
     * @param tokenMatch id of the match
     * @param match object match
     * @param player current player
     * @param points player score
     * @throws RemoteException
     */
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {
        serverTransmitter.request(new OnGetPointsRequest(tokenMatch, match, player, points));
        serverResponseHandler.handleAction((OnGetPointsResponse) serverTransmitter.getResponse());
    }
    /**
     * Observation method on the state of the match when the match ends
     * @param tokenMatch id of the match
     * @param match object match
     * @throws RemoteException
     */
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        serverTransmitter.request(new OnMatchEndRequest(tokenMatch, match));
        serverResponseHandler.handleAction((OnMatchEndResponse) serverTransmitter.getResponse());
    }

    //Metodi di acknowledgement o error
    /**
     * Acknowledgement notice method
     * @param message message to print
     * @param tokenMatch id of the match
     * @throws RemoteException
     */
    public void respondError(String message, String tokenMatch) throws RemoteException {
        serverTransmitter.request(new RespondErrorRequest(message, tokenMatch));
        serverResponseHandler.handleAction((RespondErrorResponse) serverTransmitter.getResponse());
    }
    /**
     * Error notice method
     * @param message message to print
     * @param tokenMatch id of the match
     * @throws RemoteException
     */
    public void respondAck(String message, String tokenMatch) throws RemoteException {
        serverTransmitter.request(new RespondAckRequest(message, tokenMatch));
        serverResponseHandler.handleAction((RespondAckResponse) serverTransmitter.getResponse());
    }
}
