package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.server.*;

import java.rmi.RemoteException;

public interface ServerRequestHandler {
    //Gestore di richiesta lato server

       //Richieste osservazione multiplayer
    ServerResponse handleAction(OnMatchStartRequest request);
    ServerResponse handleAction(OnPlayerLeaveRequest request);
    ServerResponse handleAction(OnPlayerRejoinRequest request);
    ServerResponse handleAction(OnTurnStartRequest request);
    ServerResponse handleAction(OnTurnEndRequest request);
    ServerResponse handleAction(OnPlaceDieRequest request);
    ServerResponse handleAction(OnChooseWindowRequest request);
    ServerResponse handleAction(OnUseToolCardRequest request);
    ServerResponse handleAction(OnGetPointsRequest request);
    ServerResponse handleAction(OnMatchEndRequest request);

    //Richieste del controllore
    ServerResponse handleAction(RespondErrorRequest request);
    ServerResponse handleAction(RespondAckRequest request);


}