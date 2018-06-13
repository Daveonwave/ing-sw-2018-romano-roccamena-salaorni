package connection.sockets.communication.requests.client;

import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;

import java.rmi.RemoteException;

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

    //Getter
    public String getTokenUser() {
        return tokenUser;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }
    public Cell getCell() {
        return cell;
    }
    public Die getDie() {
        return die;
    }

    @Override
    public ClientResponse handleAction(ClientRequestHandler handler) throws RemoteException {
        return handler.handleAction(this);
    }
}
