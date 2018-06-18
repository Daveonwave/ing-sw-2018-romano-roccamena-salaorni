package connection.sockets.communication;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.server.*;
import mvc.exceptions.AppControllerException;
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
            ((OnPlayerLeaveResponse) response).setException(false);
        } catch (RemoteException e) {
            ((OnPlayerLeaveResponse) response).setException(true);
        }

        return  response;
    }
    public ServerResponse handleAction(OnPlayerRejoinRequest request) {
        ServerResponse response = new OnPlayerRejoinResponse();

        try{
            view.onPlayerRejoin(request.getTokenMatch(), request.getMatch());
            ((OnPlayerRejoinResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnPlayerRejoinResponse) response).setException(true);
        }

        return response;
    }

    public ServerResponse handleAction(OnMatchStartRequest request) {
        ServerResponse response = new OnMatchStartResponse();

        try{
            view.onMatchStart(request.getTokenMatch(), request.getMatch());
            ((OnMatchStartResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnMatchStartResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(OnTurnStartRequest request) {
        ServerResponse response = new OnTurnStartResponse();

        try{
            view.onTurnStart(request.getTokenMatch(), request.getMatch());
            ((OnTurnStartResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnTurnStartResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(OnTurnEndRequest request) {
        ServerResponse response = new OnTurnEndResponse();

        try{
            view.onTurnEnd(request.getTokenMatch(), request.getMatch());
            ((OnTurnEndResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnTurnEndResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(OnPlaceDieRequest request) {
        ServerResponse response = new OnPlaceDieResponse();

        try{
            view.onPlaceDie(request.getTokenMatch(), request.getMatch(), request.getCell(),request.getDie());
            ((OnPlaceDieResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnPlaceDieResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(OnChooseWindowRequest request) {
        ServerResponse response = new OnChooseWindowResponse();

        try{
            view.onChooseWindows(request.getTokenMatch(), request.getMatch());
            ((OnChooseWindowResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnChooseWindowResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(OnUseToolCardRequest request) {
        ServerResponse response = new OnUseToolCardResponse();

        try{
            view.onUseTool(request.getTokenMatch(), request.getMatch(), request.getToolCard());
            ((OnUseToolCardResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnUseToolCardResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(OnGetPointsRequest request) {
        ServerResponse response = new OnGetPointsResponse();

        try{
            view.onGetPoints(request.getTokenMatch(),request.getMatch(), request.getPlayer(), request.getPoints());
            ((OnGetPointsResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnGetPointsResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(OnMatchEndRequest request) {
        ServerResponse response = new OnMatchEndResponse();

        try{
            view.onMatchEnd(request.getTokenMatch(),request.getMatch());
            ((OnMatchEndResponse) response).setException(false);
        } catch(RemoteException e){
            ((OnMatchEndResponse) response).setException(true);
        }

        return response;
    }

    //Richieste del controller
    public ServerResponse handleAction(RespondErrorRequest request) {
        ServerResponse response = new RespondErrorResponse();

        try{
            view.respondError(request.getMessage(), request.getTokenMatch());
            ((RespondErrorResponse) response).setException(false);
        } catch(RemoteException e){
            ((RespondErrorResponse) response).setException(true);
        }

        return response;
    }
    public ServerResponse handleAction(RespondAckRequest request) {
        ServerResponse response = new RespondAckResponse();

        try{
            view.respondAck(request.getMessage(), request.getTokenMatch());
            ((RespondAckResponse) response).setException(false);
        } catch(RemoteException e){
            ((RespondAckResponse) response).setException(true);
        }

        return response;
    }


    /////////////////////////////
    ///// GESTIONE RISPOSTE /////
    /////////////////////////////

    //Risposte alle richieste operazione utente
    public String handleAction(LoginResponse response) {

        if(response.isException()) {
            //throw new AppControllerException("Richiesta fallita");
            return null;
        }
        return response.getTokenUser();
    }
    public void handleAction(LogoutResponse response) {
       //if(response.isException())
            //throw new AppControllerException("Richiesta fallita!");
    }

    //Risposte ad attività multiplayer
    public void handleAction(JoinMatchResponse joinMatchResponse) {

    }
    public void handleAction(RejoinMatchResponse rejoinMatchResponse) {

    }
    public void handleAction(LeaveMatchResponse leaveMatchResponse) {

    }
    public void handleAction(CancelJoinMatchResponse cancelJoinMatchResponse) {

    }

    //Risposte  a comandi multiplayer
    public void handleAction(ChooseWindowResponse chooseWindowResponse) {

    }
    public void handleAction(PlaceDieResponse placeDieResponse) {

    }
    public void handleAction(EndTurnResponse endTurnResponse) {

    }
    public void handleAction(UseToolCardResponse useToolCardResponse) {

    }

}


