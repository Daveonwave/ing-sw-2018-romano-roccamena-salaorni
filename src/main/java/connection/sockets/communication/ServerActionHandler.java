package connection.sockets.communication;

import connection.sockets.ViewProxy;
import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.client.*;
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

    //////////////////////////////
    ///// GESTIONE RICHIESTE /////
    //////////////////////////////

    //Richieste operazioni utente
    public ClientResponse handleAction(LoginRequest request) {
       ClientResponse response;

       try{
           String token = controller.login(request.getName(), viewProxy);
           response = new LoginResponse(token);
           ((LoginResponse) response).setExceptionFlag(false);
       } catch (RemoteException e){
           response = new LoginResponse(null);
           ((LoginResponse) response).setExceptionFlag(true);
           ((LoginResponse) response).setException(e);
       }
       return response;
    }
    public ClientResponse handleAction(LogoutRequest request) {
        ClientResponse response = new LogoutResponse();

        try{
            controller.logout(request.getTokenUser());
            ((LogoutResponse) response).setExceptionFlag(false);
        } catch (RemoteException e){
            ((LogoutResponse) response).setExceptionFlag(true);
            ((LogoutResponse) response).setException(e);
        }

        return response;
    }

    //Richieste attività multiplayer
    public ClientResponse handleAction(JoinMatchRequest request) {
        ClientResponse response = new JoinMatchResponse();

        try{
            controller.joinMatch(request.getTokenUser());
            ((JoinMatchResponse) response).setExceptionFlag(false);
        } catch (RemoteException e){
            ((JoinMatchResponse) response).setExceptionFlag(true);
            ((JoinMatchResponse) response).setException(e);
        }

        return response;
    }
    public ClientResponse handleAction(CancelJoinMatchRequest request) {
        ClientResponse response = new CancelJoinMatchResponse();

        try{
            controller.cancelJoinMatch(request.getTokenUser());
            ((CancelJoinMatchResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((CancelJoinMatchResponse) response).setExceptionFlag(true);
            ((CancelJoinMatchResponse) response).setException(e);
        }

        return response;
    }
    public ClientResponse handleAction(LeaveMatchRequest request) {
        ClientResponse response = new LeaveMatchResponse();

        try{
            controller.leaveMatch(request.getTokenUser(), request.getTokenMatch());
            ((LeaveMatchResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((LeaveMatchResponse) response).setExceptionFlag(true);
            ((LeaveMatchResponse) response).setException(e);
        }

        return response;
    }
    public ClientResponse handleAction(RejoinMatchRequest request) {
        ClientResponse response = new RejoinMatchResponse();

        try{
            controller.rejoinMatch(request.getTokenUser(), request.getTokenMatch());
            ((RejoinMatchResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((RejoinMatchResponse) response).setExceptionFlag(true);
            ((RejoinMatchResponse) response).setException(e);
        }

        return response;
    }

    //Richiesta comandi multiplayer
    public ClientResponse handleAction(ChooseWindowRequest request) {
        ClientResponse response = new ChooseWindowResponse();

        try{
            controller.chooseWindow(request.getTokenUser(), request.getTokenMatch(), request.getWindow());
            ((ChooseWindowResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((ChooseWindowResponse) response).setExceptionFlag(true);
            ((ChooseWindowResponse) response).setException(e);
        }

        return response;
    }
    public ClientResponse handleAction(PlaceDieRequest request) {
        ClientResponse response =  new PlaceDieResponse();

        try{
            controller.placeDie(request.getTokenUser(), request.getTokenMatch(), request.getCell(), request.getDie());
            ((PlaceDieResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((PlaceDieResponse) response).setExceptionFlag(true);
            ((PlaceDieResponse) response).setException(e);
        }

        return response;
    }
    public ClientResponse handleAction(UseToolCardRequest request) {
        ClientResponse response = new UseToolCardResponse();

        try{
            controller.useToolCard(request.getTokenUser(), request.getTokenMatch(), request.getInput(), request.getToolCard());
            ((UseToolCardResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((UseToolCardResponse) response).setExceptionFlag(true);
            ((UseToolCardResponse) response).setException(e);
        }

        return response;
    }
    public ClientResponse handleAction(EndTurnRequest request) {
        ClientResponse response = new EndTurnResponse();

        try{
            controller.endTurn(request.getUserToken(), request.getUserMatch());
            ((EndTurnResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((EndTurnResponse) response).setExceptionFlag(true);
            ((EndTurnResponse) response).setException(e);
        }

        return response;
    }


    /////////////////////////////
    ///// GESTIONE RISPOSTE /////
    /////////////////////////////

    //Risposte a richieste su osservazione multiplayer
    public void handleAction(OnPlayerLeaveResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnPlayerRejoinResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }

    public void handleAction(OnMatchStartResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnTurnStartResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnTurnEndResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnChooseWindowResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnPlaceDieResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnUseToolCardResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnGetPointsResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(OnMatchEndResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }

    //Risposte a richieste del controllore
    public void handleAction(RespondAckResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }
    public void handleAction(RespondErrorResponse response) throws RemoteException {
        if(response.isException())
            throw response.getException();
    }

}

