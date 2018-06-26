package mvc.controller.handlers;

import mvc.controller.AppController;
import mvc.controller.TimedSubcontroller;
import mvc.model.MatchModel;
import mvc.model.objects.MultiPlayerMatch;

/**
 * Timed match turn feature handler
 */
public class TimedTurnHandler extends TimedSubcontroller {
    //Gestore evento scadenza tempo per giocare un turno

    private MultiPlayerMatch match;
    private String tokenMatch;
    private MatchModel matchModel;

    //Costruttori

    /**
     * Create new handler
     * @param controller Parent controller
     * @param delay Delay of timer
     * @param match Parent match
     * @param tokenMatch Token of parent match
     * @param matchModel Model of parent match
     */
    public TimedTurnHandler(AppController controller, int delay, MultiPlayerMatch match, String tokenMatch, MatchModel matchModel) {
        super(controller, delay);
        this.match = match;
        this.tokenMatch = tokenMatch;
        this.matchModel = matchModel;
    }

    //Setter/Getter
    public void setMatch(MultiPlayerMatch match) {
        this.match = match;
    }
    public void setTokenMatch(String tokenMatch) {
        this.tokenMatch = tokenMatch;
    }
    public void setMatchModel(MatchModel matchModel) {
        this.matchModel = matchModel;
    }

    public MultiPlayerMatch getMatch() {
        return match;
    }
    public String getTokenMatch() {
        return tokenMatch;
    }
    public MatchModel getMatchModel() {
        return matchModel;
    }

    //Eventi task
    /**
     * Execute parent controller's end turn routine
     * @throws Exception Internal exception
     */
    public synchronized void onTimedTask() throws Exception {
        //Termina turno
        getController().finishTurn(match, tokenMatch, matchModel, match.getTurnPlayer());
    }
    /**
     * No exception handling are used
     * @param e Exception raised
     */
    public synchronized void onTimedTaskException(Exception e) {
        //Non fa nulla
    }
}
