package connection.sockets.communication.requests.server;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;
import mvc.model.objects.MultiPlayerMatch;

/**
 * Observation request on the placement of a die
 */
public class OnPlaceDieRequest implements ServerRequest{

    private final String tokenMatch;
    private final MultiPlayerMatch match;
    private final Cell cell;
    private final Die die;

    /**
     * Constructor of the specific request
     * @param tokenMatch id of the match
     * @param match object match
     * @param cell selected cell
     * @param die selected die
     */
    public OnPlaceDieRequest(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) {
        this.tokenMatch = tokenMatch;
        this.match = match;
        this.cell = cell;
        this.die = die;
    }

    //Getter
    public String getTokenMatch() {
        return tokenMatch;
    }
    public MultiPlayerMatch getMatch() {
        return match;
    }
    public Cell getCell() {
        return cell;
    }
    public Die getDie() {
        return die;
    }

    /**
     * Handle the specific server request
     * @param handler ServerRequestHandler
     * @return the related response
     */
    public ServerResponse handleAction(ServerRequestHandler handler) {
        return handler.handleAction(this);
    }
}
