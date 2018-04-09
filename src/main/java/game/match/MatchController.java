package game.match;

import game.components.*;
import game.match.args.MatchEvent;
import game.match.args.MatchInput;
import game.match.args.MatchOutput;
import service.ServiceController;
import service.ServiceException;

public abstract class MatchController extends ServiceController<MatchEvent, MatchInput, MatchOutput, Match> {
    //Controllore di una partita

    //Creatori
    public MatchController(Match match) {
        super(match);
    }

    //Gestione ricezione eventi di una partita
    public MatchOutput handleEvent(MatchEvent event, MatchInput input) throws ServiceException {
        //Gestione per ogni valore dell'evento
        switch (event) {
            case START_WINDOWS:
                return updateStartWindows(input);
            case CHOOSE_WINDOW:
                return updateChooseWindow(input);
            case ASSIGN_OBJECTIVE:
                return updateAssignObjective(input);
            case PLACE_DIE:
                return updatePlaceDie(input);
            case USE_TOOL:
                return updateUseTool(input);
            case GET_POINTS:
                return updateGetPoints(input);
            default:
                throw new MatchException("invalid match event");
        }
    }

    //Update di funzionalità di una partita
    public abstract MatchOutput updateStartWindows(MatchInput input) throws MatchException;
    public abstract MatchOutput updateChooseWindow(MatchInput input) throws MatchException;
    public abstract MatchOutput updateAssignObjective(MatchInput input) throws MatchException;
    public abstract MatchOutput updatePlaceDie(MatchInput input) throws MatchException;
    public abstract MatchOutput updateUseTool(MatchInput input) throws MatchException;
    public abstract MatchOutput updateGetPoints(MatchInput input) throws MatchException;
}
