package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.server.*;

import java.rmi.RemoteException;

public interface ServerResponseHandler {
    //Gestore di risposte lato client

    //Risposte a richieste osservazione multiplayer
    void handleAction(OnPlayerLeaveResponse response) throws RemoteException;
    void handleAction(OnPlayerRejoinResponse response) throws RemoteException;

    void handleAction(OnMatchStartResponse response) throws RemoteException;
    void handleAction(OnChooseWindowResponse response) throws RemoteException;
    void handleAction(OnTurnStartResponse response) throws RemoteException;
    void handleAction(OnTurnEndResponse response) throws RemoteException;
    void handleAction(OnPlaceDieResponse response) throws RemoteException;
    void handleAction(OnUseToolCardResponse response) throws RemoteException;
    void handleAction(OnGetPointsResponse response) throws RemoteException;
    void handleAction(OnMatchEndResponse response) throws RemoteException;

    //Risposte a richieste del controllore
    void handleAction(RespondAckResponse response) throws RemoteException;
    void handleAction(RespondErrorResponse response) throws RemoteException;

}
