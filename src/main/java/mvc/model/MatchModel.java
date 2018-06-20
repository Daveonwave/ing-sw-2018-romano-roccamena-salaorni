package mvc.model;

import mvc.model.objects.*;
import mvc.stubs.MultiplayerObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MatchModel {
    //Decoratore a subject model di una partita multiplayer

    private MultiPlayerMatch match;
    private List<MultiplayerObserver> matchObservers;

    //Costruttori
    public MatchModel(MultiPlayerMatch match) {
        this.match = new MultiPlayerMatch(match);
        this.matchObservers = new ArrayList<MultiplayerObserver>();
    }

    //Setter/Getter
    public void setMatch(MultiPlayerMatch match) {
        this.match = match;
    }

    public MultiPlayerMatch getMatch() {
        return this.match;
    }

    //Attacco, distacco degli osservatori
    public synchronized void attachMatchObserver(MultiplayerObserver matchObserver) {
        this.matchObservers.add(matchObserver);
    }
    public synchronized void detachMatchObserver(MultiplayerObserver matchObserver) {
        this.matchObservers.remove(matchObserver);
    }

    //Notifica osservatori
    public synchronized void notifyPlayerLeave(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onPlayerLeave(tokenMatch, match);
    }
    public synchronized void notifyPlayerRejoin(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onPlayerRejoin(tokenMatch, match);
    }

    public synchronized void notifyMatchStart(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onMatchStart(tokenMatch, match);
    }
    public synchronized void notifyTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onTurnStart(tokenMatch, match);
    }
    public synchronized void notifyTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onTurnEnd(tokenMatch, match);
    }
    public synchronized void notifyChooseWindows(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onChooseWindows(tokenMatch, match);
    }
    public synchronized void notifyPlaceDie(String tokenMatch, Cell cell, Die die) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onPlaceDie(tokenMatch, match, cell, die);
    }
    public synchronized void notifyUseTool(String tokenMatch, ToolCard toolCard) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onUseTool(tokenMatch, match, toolCard);
    }
    public synchronized void notifyGetPoints(String tokenMatch, Player player, PlayerPoints points) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onGetPoints(tokenMatch, match, player, points);
    }
    public synchronized void notifyMatchEnd(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onMatchEnd(tokenMatch, match);
    }
}
