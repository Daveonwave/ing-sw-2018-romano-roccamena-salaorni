package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.server.*;

public interface ServerResponseHandler {
    //Gestore di risposte lato client

    //Risposte a richieste osservazione multiplayer
    void handleAction(OnPlayerLeaveResponse response);
    void handleAction(OnPlayerRejoinResponse response);
    void handleAction(OnMatchStartResponse response);
    void handleAction(OnTurnStartResponse response);
    void handleAction(OnTurnEndResponse response);
    void handleAction(OnChooseWindowResponse response);
    void handleAction(OnPlaceDieResponse response);
    void handleAction(OnUseToolCardResponse response);
    void handleAction(OnGetPointsResponse response);
    void handleAction(OnMatchEndResponse response);

    //Risposte a richieste del controllore
    void handleAction(RespondAckResponse response);
    void handleAction(RespondErrorResponse response);

}
