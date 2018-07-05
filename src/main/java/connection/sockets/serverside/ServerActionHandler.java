package connection.sockets.serverside;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.handlers.ServerResponseHandler;
import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.client.*;
import mvc.stubs.AppControllerStub;

import java.rmi.RemoteException;

/**
 * Server side handler. It handles client requests and server responses.
 */
public class ServerActionHandler implements ClientRequestHandler, ServerResponseHandler {

    private ViewProxy viewProxy;
    private AppControllerStub controller;

    /**
     * ServerActionHandler constructor.
     * @param viewProxy
     * @param controller
     */
    public ServerActionHandler(ViewProxy viewProxy, AppControllerStub controller) {

        this.viewProxy = viewProxy;
        this.controller = controller;
    }

    //////////////////////////////
    ///// GESTIONE RICHIESTE /////
    //////////////////////////////

    //Richieste operazioni utente
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request LoginRequest
     * @return LoginResponse
     * Client operation method
     */
    public ClientResponse handleAction(LoginRequest request) {
       ClientResponse response;

       try{
           String token = controller.login(request.getName(), viewProxy);
           response = new LoginResponse(token, request.getIdAction());
           response.setExceptionFlag(false);
       } catch (RemoteException e){
           response = new LoginResponse(null, request.getIdAction());
           response.setExceptionFlag(true);
           response.setException(e);
       }
       return response;
    }
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request LogoutRequest
     * @return LogoutResponse
     * Client operation method
     */
    public ClientResponse handleAction(LogoutRequest request) {
        ClientResponse response = new LogoutResponse(request.getIdAction());

        try{
            controller.logout(request.getTokenUser());
            response.setExceptionFlag(false);
        } catch (RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }

    //Richieste attività multiplayer
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request JoinMatchRequest
     * @return JoinMatchResponse
     * Client activity method
     */
    public ClientResponse handleAction(JoinMatchRequest request) {
        ClientResponse response = new JoinMatchResponse(request.getIdAction());

        try{
            controller.joinMatch(request.getTokenUser());
            response.setExceptionFlag(false);
        } catch (RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request CancelJoinRequest
     * @return CancelJoinResponse
     * Client activity method
     */
    public ClientResponse handleAction(CancelJoinMatchRequest request) {
        ClientResponse response = new CancelJoinMatchResponse(request.getIdAction());

        try{
            controller.cancelJoinMatch(request.getTokenUser());
            response.setExceptionFlag(false);
        } catch(RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request LeaveMatchRequest
     * @return LeaveMatchResponse
     * Client activity method
     */
    public ClientResponse handleAction(LeaveMatchRequest request) {
        ClientResponse response = new LeaveMatchResponse(request.getIdAction());

        try{
            controller.leaveMatch(request.getTokenUser(), request.getTokenMatch());
            response.setExceptionFlag(false);
        } catch(RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request RejoinMatchRequest
     * @return RejoinMatchResponse
     * Client activity method
     */
    public ClientResponse handleAction(RejoinMatchRequest request) {
        ClientResponse response = new RejoinMatchResponse(request.getIdAction());

        try{
            controller.rejoinMatch(request.getTokenUser(), request.getTokenMatch());
            response.setExceptionFlag(false);
        } catch(RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }

    //Richiesta comandi multiplayer
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request ChooseWindowRequest
     * @return ChooseWindowResponse
     * Client command method
     */
    public ClientResponse handleAction(ChooseWindowRequest request) {
        ClientResponse response = new ChooseWindowResponse(request.getIdAction());

        try{
            controller.chooseWindow(request.getTokenUser(), request.getTokenMatch(), request.getWindow());
            response.setExceptionFlag(false);
        } catch(RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request PlaceDieRequest
     * @return PlaceDieResponse
     * Client command method
     */
    public ClientResponse handleAction(PlaceDieRequest request) {
        ClientResponse response =  new PlaceDieResponse(request.getIdAction());

        try{
            controller.placeDie(request.getTokenUser(), request.getTokenMatch(), request.getCell(), request.getDie());
            response.setExceptionFlag(false);
        } catch(RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request UseToolCardRequest
     * @return UseToolCardResponse
     * Client command method
     */
    public ClientResponse handleAction(UseToolCardRequest request) {
        ClientResponse response = new UseToolCardResponse(request.getIdAction());

        try{
            controller.useToolCard(request.getTokenUser(), request.getTokenMatch(), request.getInput(), request.getToolCard());
            response.setExceptionFlag(false);
        } catch(RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }
    /**
     * Handles the sendRequest from client calling the controller and forwarding the sendRequest. If the sendRequest is completed
     * correctly, returns the relative response to the client setting the exceptionFlag false, otherwise set the
     * response exceptionFlag true and wraps the exception inside the response.
     * @param request EndTurnRequest
     * @return EndTurnResponse
     * Client command method
     */
    public ClientResponse handleAction(EndTurnRequest request) {
        ClientResponse response = new EndTurnResponse(request.getIdAction());

        try{
            controller.endTurn(request.getUserToken(), request.getTokenMatch());
            response.setExceptionFlag(false);
        } catch(RemoteException e){
            response.setExceptionFlag(true);
            response.setException(e);
        }

        return response;
    }


    /////////////////////////////
    ///// GESTIONE RISPOSTE /////
    /////////////////////////////

    //Risposte a richieste su osservazione multiplayer
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnPlayerLeaveResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnPlayerLeaveResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnPlayerRejoinResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnPlayerRejoinResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }

    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnMatchStartResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnMatchStartResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnTurnStartResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnTurnStartResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnTurnEndResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnTurnEndResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnChooseWindowResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnChooseWindowResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnPlaceDieResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnPlaceDieResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnUseToolCardResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnUseToolCardResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnGetPointsResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnGetPointsResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response OnMatchEndResponse
     * * @throws RemoteException
     * Responses to observation requests.
     */
    public void handleAction(OnMatchEndResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }

    //Risposte a richieste del controllore
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response RespondAckResponse
     * * @throws RemoteException
     * Responses to requests of the controller.
     */
    public void handleAction(RespondAckResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }
    /**
     * Handles the response received from viewProxy, then checks if the exceptionFlag is set true and,
     * if it is, throws the wrapped exception.
     * @param response RespondErrorResponse
     * * @throws RemoteException
     * Responses to requests of the controller.
     */
    public void handleAction(RespondErrorResponse response) throws RemoteException {
        if(response.isExceptionFlag())
            throw response.getException();
    }

}

