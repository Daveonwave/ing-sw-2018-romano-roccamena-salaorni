package connection.sockets.communication;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.server.*;
import mvc.view.AppView;

import java.rmi.RemoteException;

public class ClientActionHandler implements ClientResponseHandler, ServerRequestHandler {
    //Gestore delle richieste che arrivano al client e delle risposte dal server

    private AppView view;

    //Costruttori
    public ClientActionHandler() {
        view = null;
    }

    //Setter/Getter
    public AppView getView() {
        return view;
    }

    public void setView(AppView view) {
        this.view = view;
    }

    //////////////////////////////
    ///// GESTIONE RICHIESTE /////
    //////////////////////////////

    //Richieste osservazione multiplayer
    public ServerResponse handleAction(OnPlayerLeaveRequest request) {
        ServerResponse response = new OnPlayerLeaveResponse();

        try{
            view.onPlayerLeave(request.getTokenMatch(), request.getMatch());
            ((OnPlayerLeaveResponse) response).setExceptionFlag(false);
        } catch (RemoteException e) {
            ((OnPlayerLeaveResponse) response).setExceptionFlag(true);
            ((OnPlayerLeaveResponse) response).setException(e);
        }

        return  response;
    }
    public ServerResponse handleAction(OnPlayerRejoinRequest request) {
        ServerResponse response = new OnPlayerRejoinResponse();

        try{
            view.onPlayerRejoin(request.getTokenMatch(), request.getMatch());
            ((OnPlayerRejoinResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnPlayerRejoinResponse) response).setExceptionFlag(true);
            ((OnPlayerRejoinResponse) response).setException(e);
        }

        return response;
    }

    public ServerResponse handleAction(OnMatchStartRequest request) {
        ServerResponse response = new OnMatchStartResponse();

        try{
            view.onMatchStart(request.getTokenMatch(), request.getMatch());
            ((OnMatchStartResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnMatchStartResponse) response).setExceptionFlag(true);
            ((OnMatchStartResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(OnTurnStartRequest request) {
        ServerResponse response = new OnTurnStartResponse();

        try{
            view.onTurnStart(request.getTokenMatch(), request.getMatch());
            ((OnTurnStartResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnTurnStartResponse) response).setExceptionFlag(true);
            ((OnTurnStartResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(OnTurnEndRequest request) {
        ServerResponse response = new OnTurnEndResponse();

        try{
            view.onTurnEnd(request.getTokenMatch(), request.getMatch());
            ((OnTurnEndResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnTurnEndResponse) response).setExceptionFlag(true);
            ((OnTurnEndResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(OnPlaceDieRequest request) {
        ServerResponse response = new OnPlaceDieResponse();

        try{
            view.onPlaceDie(request.getTokenMatch(), request.getMatch(), request.getCell(),request.getDie());
            ((OnPlaceDieResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnPlaceDieResponse) response).setExceptionFlag(true);
            ((OnPlaceDieResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(OnChooseWindowRequest request) {
        ServerResponse response = new OnChooseWindowResponse();

        try{
            view.onChooseWindows(request.getTokenMatch(), request.getMatch());
            ((OnChooseWindowResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnChooseWindowResponse) response).setExceptionFlag(true);
            ((OnChooseWindowResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(OnUseToolCardRequest request) {
        ServerResponse response = new OnUseToolCardResponse();

        try{
            view.onUseTool(request.getTokenMatch(), request.getMatch(), request.getToolCard());
            ((OnUseToolCardResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnUseToolCardResponse) response).setExceptionFlag(true);
            ((OnUseToolCardResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(OnGetPointsRequest request) {
        ServerResponse response = new OnGetPointsResponse();

        try{
            view.onGetPoints(request.getTokenMatch(),request.getMatch(), request.getPlayer(), request.getPoints());
            ((OnGetPointsResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnGetPointsResponse) response).setExceptionFlag(true);
            ((OnGetPointsResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(OnMatchEndRequest request) {
        ServerResponse response = new OnMatchEndResponse();

        try{
            view.onMatchEnd(request.getTokenMatch(),request.getMatch());
            ((OnMatchEndResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((OnMatchEndResponse) response).setExceptionFlag(true);
            ((OnMatchEndResponse) response).setException(e);
        }

        return response;
    }

    //Richieste del controller
    public ServerResponse handleAction(RespondErrorRequest request) {
        ServerResponse response = new RespondErrorResponse();

        try{
            view.respondError(request.getMessage(), request.getTokenMatch());
            ((RespondErrorResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((RespondErrorResponse) response).setExceptionFlag(true);
            ((RespondErrorResponse) response).setException(e);
        }

        return response;
    }
    public ServerResponse handleAction(RespondAckRequest request) {
        ServerResponse response = new RespondAckResponse();

        try{
            view.respondAck(request.getMessage(), request.getTokenMatch());
            ((RespondAckResponse) response).setExceptionFlag(false);
        } catch(RemoteException e){
            ((RespondAckResponse) response).setExceptionFlag(true);
            ((RespondAckResponse) response).setException(e);
        }

        return response;
    }


    /////////////////////////////
    ///// GESTIONE RISPOSTE /////
    /////////////////////////////

    //Risposte alle richieste operazione utente
    public String handleAction(LoginResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
        return response.getTokenUser();
    }
    public void handleAction(LogoutResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }

    //Risposte ad attività multiplayer
    public void handleAction(JoinMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    public void handleAction(RejoinMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    public void handleAction(LeaveMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    public void handleAction(CancelJoinMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }

    //Risposte  a comandi multiplayer
    public void handleAction(ChooseWindowResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    public void handleAction(PlaceDieResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    public void handleAction(EndTurnResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    public void handleAction(UseToolCardResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }

}


