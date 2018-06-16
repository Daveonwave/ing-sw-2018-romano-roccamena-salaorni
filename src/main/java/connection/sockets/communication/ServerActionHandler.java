package connection.sockets.communication;

import connection.sockets.ViewProxy;
import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.client.*;
import mvc.exceptions.AppControllerException;
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
           ((LoginResponse) response).setException(false);
       } catch (RemoteException e){
           response = new LoginResponse(null);
           ((LoginResponse) response).setException(true);
       }
       return response;
    }
    public ClientResponse handleAction(LogoutRequest request) {
        ClientResponse response = new LogoutResponse();

        try{
            controller.logout(request.getTokenUser());
            ((LogoutResponse) response).setException(false);
        } catch (RemoteException e){
            ((LogoutResponse) response).setException(true);
        }

        return response;
    }

    //Richieste attività multiplayer
    public ClientResponse handleAction(JoinMatchRequest request) {
        ClientResponse response = new JoinMatchResponse();

        try{
            controller.joinMatch(request.getTokenUser());
            ((JoinMatchResponse) response).setException(false);
        } catch (RemoteException e){
            ((JoinMatchResponse) response).setException(true);
        }

        return response;
    }
    public ClientResponse handleAction(CancelJoinMatchRequest request) {
        ClientResponse response = new CancelJoinMatchResponse();

        try{
            controller.cancelJoinMatch(request.getTokenUser());
            ((CancelJoinMatchResponse) response).setException(false);
        } catch(RemoteException e){
            ((CancelJoinMatchResponse) response).setException(true);
        }

        return response;
    }
    public ClientResponse handleAction(LeaveMatchRequest request) {
        ClientResponse response = new LeaveMatchResponse();

        try{
            controller.leaveMatch(request.getTokenUser(), request.getTokenMatch());
            ((LeaveMatchResponse) response).setException(false);
        } catch(RemoteException e){
            ((LeaveMatchResponse) response).setException(true);
        }

        return response;
    }
    public ClientResponse handleAction(RejoinMatchRequest request) {
        ClientResponse response = new RejoinMatchResponse();

        try{
            controller.rejoinMatch(request.getTokenUser(), request.getTokenMatch());
            ((RejoinMatchResponse) response).setException(false);
        } catch(RemoteException e){
            ((RejoinMatchResponse) response).setException(true);
        }

        return response;
    }

    //Richiesta comandi multiplayer
    public ClientResponse handleAction(ChooseWindowRequest request) {
        ClientResponse response = new ChooseWindowResponse();

        try{
            controller.chooseWindow(request.getTokenUser(), request.getTokenMatch(), request.getWindow());
            ((ChooseWindowResponse) response).setException(false);
        } catch(RemoteException e){
            ((ChooseWindowResponse) response).setException(true);
        }

        return response;
    }
    public ClientResponse handleAction(PlaceDieRequest request) {
        ClientResponse response =  new PlaceDieResponse();

        try{
            controller.placeDie(request.getTokenUser(), request.getTokenMatch(), request.getCell(), request.getDie());
            ((PlaceDieResponse) response).setException(false);
        } catch(RemoteException e){
            ((PlaceDieResponse) response).setException(true);
        }

        return response;
    }
    public ClientResponse handleAction(UseToolCardRequest request) {
        ClientResponse response = new UseToolCardResponse();

        try{
            controller.useToolCard(request.getTokenUser(), request.getTokenMatch(), request.getInput(), request.getToolCard());
            ((UseToolCardResponse) response).setException(false);
        } catch(RemoteException e){
            ((UseToolCardResponse) response).setException(true);
        }

        return response;
    }
    public ClientResponse handleAction(EndTurnRequest request) {
        ClientResponse response = new EndTurnResponse();

        try{
            controller.endTurn(request.getUserToken(), request.getUserMatch());
            ((EndTurnResponse) response).setException(false);
        } catch(RemoteException e){
            ((EndTurnResponse) response).setException(true);
        }

        return response;
    }


    /////////////////////////////
    ///// GESTIONE RISPOSTE /////
    /////////////////////////////

    //Risposte a richieste su osservazione multiplayer
    public void handleAction(OnPlayerLeaveResponse response) {
        if(response.isException()){
            //throw new AppViewException("errore");
        } else{

        }

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

    //Risposte a richieste del controllore
    public void handleAction(RespondAckResponse response) {

    }
    public void handleAction(RespondErrorResponse response) {

    }

}

