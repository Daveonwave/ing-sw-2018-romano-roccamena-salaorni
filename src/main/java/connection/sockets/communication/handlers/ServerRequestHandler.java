package connection.sockets.communication.handlers;

import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.server.*;

/**
 * Handler of the request of server
 */
public interface ServerRequestHandler {
    //Gestore di richiesta lato server

    //Richieste osservazione multiplayer

    /**
     * Handle the specific observation request.
     * @param request on player leave action
     * @return response for server
     */
    ServerResponse handleAction(OnPlayerLeaveRequest request);
    /**
     * Handle the specific observation request.
     * @param request on player rejoin match action
     * @return response for server
     */
    ServerResponse handleAction(OnPlayerRejoinRequest request);

    /**
     * Handle the specific observation request.
     * @param request on match start action
     * @return response for server
     */
    ServerResponse handleAction(OnMatchStartRequest request);
    /**
     * Handle the specific observation request.
     * @param request on choice of window action
     * @return response for server
     */
    ServerResponse handleAction(OnChooseWindowRequest request);
    /**
     * Handle the specific observation request.
     * @param request on turn start action
     * @return response for server
     */
    ServerResponse handleAction(OnTurnStartRequest request);
    /**
     * Handle the specific observation request.
     * @param request on turn end action
     * @return response for server
     */
    ServerResponse handleAction(OnTurnEndRequest request);
    /**
     * Handle the specific observation request.
     * @param request on place die action
     * @return response for server
     */
    ServerResponse handleAction(OnPlaceDieRequest request);
    /**
     * Handle the specific observation request.
     * @param request on usage of a tool card action
     * @return response for server
     */
    ServerResponse handleAction(OnUseToolCardRequest request);
    /**
     * Handle the specific observation request.
     * @param request on calculation of player points action
     * @return response for server
     */
    ServerResponse handleAction(OnGetPointsRequest request);
    /**
     * Handle the specific observation request.
     * @param request on end of match action
     * @return response for server
     */
    ServerResponse handleAction(OnMatchEndRequest request);

    //Richieste del controllore
    /**
     * Handle the specific controller request
     * @param request respond ack
     * @return response for server
     */
    ServerResponse handleAction(RespondErrorRequest request);
    /**
     * Handle the specific controller request
     * @param request respond error
     * @return response for server
     */
    ServerResponse handleAction(RespondAckRequest request);


}