package mvc.model;

import mvc.MatchObserver;
import mvc.model.objects.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public synchronized void notifyChooseWindows(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onChooseWindows(tokenMatch, match);
    }
    public synchronized void notifyFirstRoundStart(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onFirstRoundStart(tokenMatch, match);
    }
    public synchronized void notifyRoundStart(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onRoundStart(tokenMatch, match);
    }
    public synchronized void notifyPlayerTurnStart(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onPlayerTurnStart(tokenMatch, match);
    }
    public synchronized void notifyPlaceDie(String tokenMatch, Player player, Cell cell, Die die) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onPlaceDie(tokenMatch, match, player, cell, die);
    }
    public synchronized void notifyUseTool(String tokenMatch, Player player, ToolCard toolCard) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onUseTool(tokenMatch, match, player, toolCard);
    }
    public synchronized void notifyPlayerTurnEnd(String tokenMatch, Player player) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onPlayerTurnEnd(tokenMatch, match, player);
    }
    public synchronized void notifyRoundEnd(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onRoundEnd(tokenMatch, match);
    }
    public synchronized void notifyLastRoundEnd(String tokenMatch) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onLastRoundEnd(tokenMatch, match);
    }
    public synchronized void notifyGetPoints(String tokenMatch, Player player, PlayerPoints points) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onGetPoints(tokenMatch, match, player, points);
    }
}
