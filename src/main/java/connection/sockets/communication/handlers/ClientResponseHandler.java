package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.*;

import java.rmi.RemoteException;

/**
 * Handler of the response for the client
 */
public interface ClientResponseHandler {
    //Gestore di risposte lato client

    //Risposta a richieste su utente
    /**
     * Handle the specific response.
     * @param response login action
     * @return a string with the token of the user
     * @throws RemoteException
     */
    String handleAction(LoginResponse response) throws RemoteException;
    /**
     * Handle the specific response.
     * @param response logout action
     * @throws RemoteException
     */
    void handleAction(LogoutResponse response) throws RemoteException;

    //Risposta ad attività  multiplayer
    /**
     * Handle the specific response.
     * @param joinMatchResponse join match action
     * @throws RemoteException
     */
    void handleAction(JoinMatchResponse joinMatchResponse) throws RemoteException;
    /**
     * Handle the specific response.
     * @param rejoinMatchResponse rejoin match action
     * @throws RemoteException
     */
    void handleAction(RejoinMatchResponse rejoinMatchResponse) throws RemoteException;
    /**
     * Handle the specific response.
     * @param leaveMatchResponse leave match action
     * @throws RemoteException
     */
    void handleAction(LeaveMatchResponse leaveMatchResponse) throws RemoteException;
    /**
     * Handle the specific response.
     * @param cancelJoinMatchResponse cancel join match action
     * @throws RemoteException
     */
    void handleAction(CancelJoinMatchResponse cancelJoinMatchResponse) throws RemoteException;

    //Risposta a comandi multiplayer
    /**
     * Handle the specific response.
     * @param chooseWindowResponse choose window action
     * @throws RemoteException
     */
    void handleAction(ChooseWindowResponse chooseWindowResponse) throws RemoteException;
    /**
     * Handle the specific response.
     * @param placeDieResponse place die action
     * @throws RemoteException
     */
    void handleAction(PlaceDieResponse placeDieResponse) throws RemoteException;
    /**
     * Handle the specific response.
     * @param endTurnResponse end turn action
     * @throws RemoteException
     */
    void handleAction(EndTurnResponse endTurnResponse) throws RemoteException;
    /**
     * Handle the specific response.
     * @param useToolCardResponse use tool card action
     * @throws RemoteException
     */
    void handleAction(UseToolCardResponse useToolCardResponse) throws RemoteException;


}
