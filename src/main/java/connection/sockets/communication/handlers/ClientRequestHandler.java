package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.requests.client.*;

import java.rmi.RemoteException;

public interface ClientRequestHandler {
    //Gestore di richieste lato client

    //Richiesta operazioni utente
    ClientResponse handleAction(LoginRequest request) throws RemoteException;
    ClientResponse handleAction(LogoutRequest request) throws RemoteException;

    //Richiesta attività multiplayer
    ClientResponse handleAction(JoinMatchRequest request) throws RemoteException;
    ClientResponse handleAction(RejoinMatchRequest request) throws RemoteException;
    ClientResponse handleAction(LeaveMatchRequest request) throws RemoteException;
    ClientResponse handleAction(CancelJoinMatchRequest request) throws RemoteException;

    //Richiesta comandi multiplayer
    ClientResponse handleAction(ChooseWindowRequest request) throws RemoteException;
    ClientResponse handleAction(PlaceDieRequest request) throws RemoteException;
    ClientResponse handleAction(UseToolCardRequest request) throws RemoteException;
    ClientResponse handleAction(EndTurnRequest request) throws RemoteException;
}
