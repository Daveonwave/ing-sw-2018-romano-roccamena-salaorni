package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.rensponses.server.*;

public interface ClientResponseHandler {
    //Gestore di risposte lato client

    //Risposta a richieste su utente
    String handleAction(LoginClientResponse response);
    void handleAction(LogoutClientResponse response);

    //Risposta a richieste su multiplayer
    void handleAction(JoinMatchResponse joinMatchResponse);
    void handleAction(RejoinMatchResponse rejoinMatchResponse);
    void handleAction(LeaveMatchResponse leaveMatchResponse);
    void handleAction(CancelJoinMatchResponse cancelJoinMatchResponse);
    void handleAction(ChooseWindowResponse chooseWindowResponse);
    void handleAction(PlaceDieResponse placeDieResponse);
    void handleAction(EndTurnResponse endTurnResponse);
    void handleAction(UseToolCardResponse useToolCardResponse);


}
