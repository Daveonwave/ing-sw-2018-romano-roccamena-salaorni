package mvc.model;

import mvc.stubs.MultiPlayerObserver;
import mvc.model.objects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MatchModel {
    //Decoratore a subject model di una partita multiplayer

    private MultiPlayerMatch match;
    private List<MultiPlayerObserver> matchObserver;

    //Costruttori
    public MatchModel(MultiPlayerMatch match) {
        this.match = new MultiPlayerMatch(match);
        this.matchObserver = new ArrayList<MultiPlayerObserver>();
    }

    //Setter/Getter
    public void setMatch(MultiPlayerMatch match) {
        this.match = match;
    }

    public MultiPlayerMatch getMatch() {
        return this.match;
    }

    //Attacco, distacco degli osservatori
    public synchronized void attachMatchObserver(MultiPlayerObserver matchObserver) {
        this.matchObserver.add(matchObserver);
    }
    public synchronized void detachMatchObserver(MultiPlayerObserver matchObserver) {
        this.matchObserver.remove(matchObserver);
    }

    //Notifica osservatori
    public synchronized void notifyPlayerLeave(String tokenMatch) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onPlayerLeave(tokenMatch, match);
    }
    public synchronized void notifyPlayerRejoin(String tokenMatch) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onPlayerRejoin(tokenMatch, match);
    }

    public synchronized void notifyMatchStart(String tokenMatch) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onMatchStart(tokenMatch, match);
    }
    public synchronized void notifyTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onTurnStart(tokenMatch, match);
    }
    public synchronized void notifyTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onTurnEnd(tokenMatch, match);
    }
    public synchronized void notifyChooseWindows(String tokenMatch) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onChooseWindows(tokenMatch, match);
    }
    public synchronized void notifyPlaceDie(String tokenMatch, Cell cell, Die die) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onPlaceDie(tokenMatch, match, cell, die);
    }
    public synchronized void notifyUseTool(String tokenMatch, ToolCard toolCard) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onUseTool(tokenMatch, match, toolCard);
    }
    public synchronized void notifyGetPoints(String tokenMatch, Player player, PlayerPoints points) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onGetPoints(tokenMatch, match, player, points);
    }
    public synchronized void notifyMatchEnd(String tokenMatch) throws RemoteException {
        for (MultiPlayerObserver observer : matchObserver)
            observer.onMatchEnd(tokenMatch, match);
    }
}
