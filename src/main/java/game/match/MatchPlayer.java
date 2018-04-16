package game.match;

import game.components.*;
import game.match.args.MatchEvent;
import game.match.args.MatchInput;
import game.match.args.MatchOutput;
import service.ServiceException;
import service.CallerUser;

public abstract class MatchPlayer extends CallerUser<MatchEvent, MatchInput, MatchOutput> {
    //Giocatore di una partita

    //Costruttori
    public MatchPlayer(MatchController match_controller) {
        super(match_controller);
    }

    //Richieste di funzionalità di una partita
    public void retrieveStartWindows(Player player) throws ServiceException {
        notifyRequest(MatchEvent.START_WINDOWS, new MatchInput(null, player, null, null, null, null));
    }
    public void chooseWindow(Player player, Window window) throws ServiceException {
        notifyRequest(MatchEvent.CHOOSE_WINDOW, new MatchInput(null, player, null, null, window, null));
    }
    public void retrieveObjectiveCard(Player player) throws ServiceException {
        notifyRequest(MatchEvent.ASSIGN_OBJECTIVE, new MatchInput(null, player, null, null, null, null));
    }
    public void placeDie(Player player, Die die, Cell cell) throws ServiceException {
        notifyRequest(MatchEvent.PLACE_DIE, new MatchInput(null, player, die, cell, null, null));
    }
    public void useTool(Player player, ToolCard toolCard, Match match) throws ServiceException {
        notifyRequest(MatchEvent.USE_TOOL, new MatchInput(match, player, null, null, null, toolCard));
    }
    public void retrievePoints(Player player) throws ServiceException{
        notifyRequest(MatchEvent.GET_POINTS, new MatchInput(null, player, null, null, null, null));
    }
}
