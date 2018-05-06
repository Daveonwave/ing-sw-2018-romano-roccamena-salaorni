package mvc.model;

import mvc.stubs.MatchObserver;
import mvc.model.objects.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MatchModel {
    //Decoratore a subject model di una partita

    private Match match;
    private List<MatchObserver> matchObserver;

    //Costruttori
    public MatchModel(Match match) {
        this.match = new Match(match);
        this.matchObserver = new ArrayList<MatchObserver>();
    }

    //Setter/Getter
    public void setMatch(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return this.match;
    }

    //Attacco, distacco degli osservatori
    public synchronized void attachMatchObserver(MatchObserver matchObserver) {
        this.matchObserver.add(matchObserver);
    }
    public synchronized void detachMatchObserver(MatchObserver matchObserver) {
        this.matchObserver.remove(matchObserver);
    }

    //Notifica osservatori
    public synchronized void notifyMatchStart(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onMatchStart(tokenMatch, match);
    }
    public synchronized void notifyTurnStart(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onTurnStart(tokenMatch, match);
    }
    public synchronized void notifyTurnEnd(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onTurnEnd(tokenMatch, match);
    }
    public synchronized void notifyChooseWindows(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onChooseWindows(tokenMatch, match);
    }
    public synchronized void notifyPlaceDie(String tokenMatch, Cell cell, Die die) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onPlaceDie(tokenMatch, match, cell, die);
    }
    public synchronized void notifyUseTool(String tokenMatch, ToolCard toolCard) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onUseTool(tokenMatch, match, toolCard);
    }
    public synchronized void notifyGetPoints(String tokenMatch, Player player, PlayerPoints points) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onGetPoints(tokenMatch, match, player, points);
    }
    public synchronized void notifyMatchEnd(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onMatchEnd(tokenMatch, match);
    }
}
