package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;

public class PlaceDieRequest implements ClientRequest {

    private final String tokenUser;
    private final String tokenMatch;
    private final Cell cell;
    private final Die die;

    //Costruttori
    public PlaceDieRequest(String tokenUser, String tokenMatch, Cell cell, Die die) {
        this.tokenUser = tokenUser;
        this.tokenMatch = tokenMatch;
        this.cell = cell;
        this.die = die;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) {
        return handler.handleAction(this);
    }
}
