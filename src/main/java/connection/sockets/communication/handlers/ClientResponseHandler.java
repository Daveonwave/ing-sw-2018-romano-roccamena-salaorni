package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.*;

import java.rmi.RemoteException;

public interface ClientResponseHandler {
    //Gestore di risposte lato client

    //Risposta a richieste su utente
    String handleAction(LoginResponse response) throws RemoteException;
    void handleAction(LogoutResponse response) throws RemoteException;

    //Risposta ad attività  multiplayer
    void handleAction(JoinMatchResponse joinMatchResponse) throws RemoteException;
    void handleAction(RejoinMatchResponse rejoinMatchResponse) throws RemoteException;
    void handleAction(LeaveMatchResponse leaveMatchResponse) throws RemoteException;
    void handleAction(CancelJoinMatchResponse cancelJoinMatchResponse) throws RemoteException;

    //Risposta a comandi multiplayer
    void handleAction(ChooseWindowResponse chooseWindowResponse) throws RemoteException;
    void handleAction(PlaceDieResponse placeDieResponse) throws RemoteException;
    void handleAction(EndTurnResponse endTurnResponse) throws RemoteException;
    void handleAction(UseToolCardResponse useToolCardResponse) throws RemoteException;


}
