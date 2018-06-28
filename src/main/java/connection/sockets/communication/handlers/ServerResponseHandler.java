package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.server.*;

import java.rmi.RemoteException;

/**
 * Handler of the responses for server
 */
public interface ServerResponseHandler {
    //Gestore di risposte lato client

    //Risposte a richieste osservazione multiplayer
    /**
     * Handle the specific response of an observation action.
     * @param response on player leave action
     * @throws RemoteException
     */
    void handleAction(OnPlayerLeaveResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on player rejoin action
     * @throws RemoteException
     */
    void handleAction(OnPlayerRejoinResponse response) throws RemoteException;

    /**
     * Handle the specific response of an observation action.
     * @param response on match start action
     * @throws RemoteException
     */
    void handleAction(OnMatchStartResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on the choice of window action
     * @throws RemoteException
     */
    void handleAction(OnChooseWindowResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on turn start action
     * @throws RemoteException
     */
    void handleAction(OnTurnStartResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on turn end action
     * @throws RemoteException
     */
    void handleAction(OnTurnEndResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on the placement of a die action
     * @throws RemoteException
     */
    void handleAction(OnPlaceDieResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on the usage of a tool card action
     * @throws RemoteException
     */
    void handleAction(OnUseToolCardResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on the calculation of player points action
     * @throws RemoteException
     */
    void handleAction(OnGetPointsResponse response) throws RemoteException;
    /**
     * Handle the specific response of an observation action.
     * @param response on the end of match action
     * @throws RemoteException
     */
    void handleAction(OnMatchEndResponse response) throws RemoteException;

    //Risposte a richieste del controllore
    /**
     * Handle the specific controller response for server.
     * @param response respond ack
     * @throws RemoteException
     */
    void handleAction(RespondAckResponse response) throws RemoteException;
    /**
     * Handle the specific controller response for server.
     * @param response respond error
     * @throws RemoteException
     */
    void handleAction(RespondErrorResponse response) throws RemoteException;

}
