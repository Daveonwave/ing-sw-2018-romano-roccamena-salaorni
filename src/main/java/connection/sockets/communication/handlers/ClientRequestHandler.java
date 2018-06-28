package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.requests.client.*;

import java.rmi.RemoteException;

/**
 * Handler of the sendRequest from client
 */
public interface ClientRequestHandler {

    //Richiesta operazioni utente

    /**
     * Handles the specific sendRequest.
     * @param request LoginRequest
     * @return ClientResponse (LoginResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(LoginRequest request) throws RemoteException;
    /**
     * Handles the specific sendRequest.
     * @param request LogoutRequest
     * @return ClientResponse (LogoutResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(LogoutRequest request) throws RemoteException;

    //Richiesta attività multiplayer
    /**
     * Handles the specific sendRequest.
     * @param request JoinMatchRequest
     * @return ClientResponse (JoinMatchResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(JoinMatchRequest request) throws RemoteException;
    /**
     * Handles the specific sendRequest.
     * @param request RejoinMatchRequest
     * @return ClientResponse (RejoinMatchResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(RejoinMatchRequest request) throws RemoteException;
    /**
     * Handles the specific sendRequest.
     * @param request LeaveMatchRequest
     * @return ClientResponse (LeaveMatchResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(LeaveMatchRequest request) throws RemoteException;
    /**
     * Handles the specific sendRequest.
     * @param request CancelJoinMatchRequest
     * @return ClientResponse (CancelJoinMatchResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(CancelJoinMatchRequest request) throws RemoteException;

    //Richiesta comandi multiplayer
    /**
     * Handles the specific sendRequest.
     * @param request ChooseWindowRequest
     * @return ClientResponse (ChooseWindowResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(ChooseWindowRequest request) throws RemoteException;
    /**
     * Handles the specific sendRequest.
     * @param request PlaceDieRequest
     * @return ClientResponse (PlaceDieResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(PlaceDieRequest request) throws RemoteException;
    /**
     * Handles the specific sendRequest.
     * @param request UseToolCardRequest
     * @return ClientResponse (UseToolCardResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(UseToolCardRequest request) throws RemoteException;
    /**
     * Handles the specific sendRequest.
     * @param request EndTurnRequest
     * @return ClientResponse (EndTurnResponse)
     * @throws RemoteException
     */
    ClientResponse handleAction(EndTurnRequest request) throws RemoteException;
}
