package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.client.*;
import mvc.exceptions.AppControllerException;

public interface ClientResponseHandler {
    //Gestore di risposte lato client

    //Risposta a richieste su utente
    String handleAction(LoginResponse response) throws AppControllerException;
    void handleAction(LogoutResponse response);

    //Risposta ad attività  multiplayer
    void handleAction(JoinMatchResponse joinMatchResponse);
    void handleAction(RejoinMatchResponse rejoinMatchResponse);
    void handleAction(LeaveMatchResponse leaveMatchResponse);
    void handleAction(CancelJoinMatchResponse cancelJoinMatchResponse);

    //Risposta a comandi multiplayer
    void handleAction(ChooseWindowResponse chooseWindowResponse);
    void handleAction(PlaceDieResponse placeDieResponse);
    void handleAction(EndTurnResponse endTurnResponse);
    void handleAction(UseToolCardResponse useToolCardResponse);


}
