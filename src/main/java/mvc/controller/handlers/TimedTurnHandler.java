package mvc.controller.handlers;

import mvc.controller.AppController;
import mvc.controller.TimedSubcontroller;
import mvc.model.MatchModel;
import mvc.model.objects.MultiPlayerMatch;

public class TimedTurnHandler extends TimedSubcontroller {
    //Gestore evento scadenza tempo per giocare un turno

    private MultiPlayerMatch match;
    private String tokenMatch;
    private MatchModel matchModel;

    //Costruttori
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
    public synchronized void onTimedTask() throws Exception {
        //Termina turno
        getController().finishTurn(match, tokenMatch, matchModel, match.getTurnPlayer());
    }
    public synchronized void onTimedTaskException(Exception e) {
        //Non fa nulla
    }
}
