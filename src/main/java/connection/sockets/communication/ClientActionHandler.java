package connection.sockets.communication;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.server.*;
import mvc.view.AppView;

import java.rmi.RemoteException;

/**
 * Client side handler. It handles server requests and client responses
 */
public class ClientActionHandler implements ClientResponseHandler, ServerRequestHandler {

    private AppView view;

    /**
     * ClientActionHandler constructor
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnPlayerLeaveMatchRequest
     * @return OnPlayerLeaveMatchResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnPlayerLeaveRejoinRequest
     * @return OnPlayerLeaveRejoinResponse
     * Server observation method
     */
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

    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnMatchStartRequest
     * @return OnMatchStartResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnTurnStartRequest
     * @return OnTurnStartResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnTurnEndRequest
     * @return OnTurnEndResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnPLaceDieRequest
     * @return OnPlaceDieResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnChooseWindowRequest
     * @return OnChooseWindowResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnUseToolCardRequest
     * @return OnUseToolCardResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnGetPointsRequest
     * @return OnGetPointsResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request OnMatchEndRequest
     * @return OnMatchEndResponse
     * Server observation method
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request RespondErrorRequest
     * @return RespondErrorResponse
     * Controller requests (ack/error)
     */
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
    /**
     * Handles the request from server calling the view and forwarding the request. If the request is completed
     * correctly, returns the relative response to the server setting exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request RespondAckRequest
     * @return RespondAckResponse
     * Controller requests (ack/error)
     */
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
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response LoginResponse
     * @return String (userToken)
     * @throws RemoteException
     * Responses to request of user operation
     */
    public String handleAction(LoginResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
        return response.getTokenUser();
    }
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response LogoutResponse
     * * @throws RemoteException
     * Responses to request of user operation
     */
    public void handleAction(LogoutResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }

    //Risposte ad attività multiplayer
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response JoinMatchResponse
     * * @throws RemoteException
     * Responses to request of user activity
     */
    public void handleAction(JoinMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response RejoinMatchResponse
     * * @throws RemoteException
     * Responses to request of user activity
     */
    public void handleAction(RejoinMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response LeaveMatchResponse
     * * @throws RemoteException
     * Responses to request of user activity
     */
    public void handleAction(LeaveMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response CancelJoinMatchResponse
     * * @throws RemoteException
     * Responses to request of user activity
     */
    public void handleAction(CancelJoinMatchResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }

    //Risposte  a comandi multiplayer
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response ChooseWindowResponse
     * * @throws RemoteException
     * Responses to request of user command
     */
    public void handleAction(ChooseWindowResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response PlaceDieResponse
     * * @throws RemoteException
     * Responses to request of user command
     */
    public void handleAction(PlaceDieResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response EndTurnResponse
     * * @throws RemoteException
     * Responses to request of user command
     */
    public void handleAction(EndTurnResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }
    /**
     * Handles the response received from controllerProxy, then checks if there is the exceptionFlag set true and,
     * if it is, throws the wrapped exception
     * @param response UseToolCardResponse
     * * @throws RemoteException
     * Responses to request of user command
     */
    public void handleAction(UseToolCardResponse response) throws RemoteException {

        if(response.isException()) {
            throw response.getException();
        }
    }

}


