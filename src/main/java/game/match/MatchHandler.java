package game.match;

import game.components.*;
import game.match.args.MatchEvent;
import game.match.args.MatchInput;
import game.match.args.MatchOutput;
import service.call.CallableController;
import service.ServiceException;

public abstract class MatchHandler {
    //Gestore di una partita

    //Update di funzionalità di una partita
    public abstract MatchOutput updateMatch(MatchInput input) throws MatchException;
    public abstract MatchOutput updateStartWindows(MatchInput input) throws MatchException;
    public abstract MatchOutput updateChooseWindow(MatchInput input) throws MatchException;
    public abstract MatchOutput updateAssignObjective(MatchInput input) throws MatchException;
    public abstract MatchOutput updatePlaceDie(MatchInput input) throws MatchException;
    public abstract MatchOutput updateUseTool(MatchInput input) throws MatchException;
    public abstract MatchOutput updateGetPoints(MatchInput input) throws MatchException;
}
