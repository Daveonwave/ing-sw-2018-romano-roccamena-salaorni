package connection.sockets.communication;

import connection.sockets.ViewProxy;
import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.client.LoginClientResponse;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.client.*;
import mvc.stubs.AppControllerStub;

import java.rmi.RemoteException;

public class ServerActionHandler implements ClientRequestHandler, ServerResponseHandler {
    //Gestore delle richieste che arrivano al server e delle risposte dal client

    private ViewProxy viewProxy;
    private AppControllerStub controller;

    //Costruttori
    public ServerActionHandler(ViewProxy viewProxy, AppControllerStub controller) {

        this.viewProxy = viewProxy;
        this.controller = controller;
    }

    //Richieste operazioni utente
    public ClientResponse handleAction(LoginRequest request) {
       // String token = controller.login(request.getName(), viewProxy);

       // return new LoginClientResponse(token);
        return null;
    }
    public ClientResponse handleAction(LogoutRequest request) {
        return null;
    }

    //Richieste operazioni multiplayer
    public ClientResponse handleAction(JoinMatchRequest request) {
        return null;
    }
    public ClientResponse handleAction(CancelJoinMatchRequest request) {
        return null;
    }
    public ClientResponse handleAction(LeaveMatchRequest request) {
        return null;
    }
    public ClientResponse handleAction(RejoinMatchRequest request) {
        return null;
    }
    public ClientResponse handleAction(ChooseWindowRequest request) {
        return null;
    }
    public ClientResponse handleAction(PlaceDieRequest request) {
        return null;
    }
    public ClientResponse handleAction(UseToolCardRequest request) {
        return null;
    }
    public ClientResponse handleAction(EndTurnRequest request) {
        return null;
    }


    //Risposte a richieste del controllore
    public void handleAction(RespondAckResponse response) {

    }
    public void handleAction(RespondErrorResponse response) {

    }

    //Risposte a richieste su osservazione multiplayer
    public void handleAction(OnPlayerLeaveResponse response) {

    }
    public void handleAction(OnPlayerRejoinResponse response) {

    }
    public void handleAction(OnMatchStartResponse response) {

    }
    public void handleAction(OnTurnStartResponse response) {

    }
    public void handleAction(OnTurnEndResponse response) {

    }
    public void handleAction(OnChooseWindowResponse response) {

    }
    public void handleAction(OnPlaceDieResponse response) {

    }
    public void handleAction(OnUseToolCardResponse response) {

    }
    public void handleAction(OnGetPointsResponse response) {

    }
    public void handleAction(OnMatchEndResponse response) {

    }

}

