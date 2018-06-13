package connection.sockets.communication;

import connection.sockets.communication.handlers.ClientResponseHandler;
import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.client.*;
import connection.sockets.communication.rensponses.server.*;
import connection.sockets.communication.requests.server.*;

public class ClientActionHandler implements ClientResponseHandler, ServerRequestHandler {
    //Gestore delle richieste che arrivano al client e delle risposte dal server


    //////////////////////////////
    ///// GESTIONE RICHIESTE /////
    //////////////////////////////

    //Richieste osservazione multiplayer
    public ServerResponse handleAction(OnPlayerLeaveRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnPlayerRejoinRequest request) {
        return null;
    }

    public ServerResponse handleAction(OnMatchStartRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnTurnStartRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnTurnEndRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnPlaceDieRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnChooseWindowRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnUseToolCardRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnGetPointsRequest request) {
        return null;
    }
    public ServerResponse handleAction(OnMatchEndRequest request) {
        return null;
    }

    //Richieste del controller
    public ServerResponse handleAction(RespondErrorRequest request) {
        return null;
    }
    public ServerResponse handleAction(RespondAckRequest request) {
        return null;
    }


    /////////////////////////////
    ///// GESTIONE RISPOSTE /////
    /////////////////////////////

    //Risposte alle richieste operazione utente
    public String handleAction(LoginResponse response) {
        return response.getTokenUser();
    }
    public void handleAction(LogoutResponse response) {
    }

    //Risposte ad attività multiplayer
    public void handleAction(JoinMatchResponse joinMatchResponse) {

    }
    public void handleAction(RejoinMatchResponse rejoinMatchResponse) {

    }
    public void handleAction(LeaveMatchResponse leaveMatchResponse) {

    }
    public void handleAction(CancelJoinMatchResponse cancelJoinMatchResponse) {

    }

    //Risposte  a comandi multiplayer
    public void handleAction(ChooseWindowResponse chooseWindowResponse) {

    }
    public void handleAction(PlaceDieResponse placeDieResponse) {

    }
    public void handleAction(EndTurnResponse endTurnResponse) {

    }
    public void handleAction(UseToolCardResponse useToolCardResponse) {

    }

}


