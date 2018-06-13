package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.requests.client.*;

import java.rmi.RemoteException;

public interface ClientRequestHandler {
    //Gestore di richieste lato client

    //Richieste operazioni utente
    ClientResponse handleAction(LoginRequest request);
    ClientResponse handleAction(LogoutRequest request);

    //Richieste operazioni multiplayer
    ClientResponse handleAction(JoinMatchRequest request);
    ClientResponse handleAction(CancelJoinMatchRequest request);
    ClientResponse handleAction(LeaveMatchRequest request);
    ClientResponse handleAction(RejoinMatchRequest request);
    ClientResponse handleAction(ChooseWindowRequest request);
    ClientResponse handleAction(PlaceDieRequest request);
    ClientResponse handleAction(UseToolCardRequest request);
    ClientResponse handleAction(EndTurnRequest request);
}
