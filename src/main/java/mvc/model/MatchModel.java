package mvc.model;

import mvc.model.objects.*;
import mvc.stubs.MultiplayerObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Multiplayer subject implementation of main match properties
 */
public class MatchModel {
    //Decoratore a subject model di una partita multiplayer

    private MultiPlayerMatch match;
    private List<MultiplayerObserver> matchObservers;

    //Costruttori
    /**
     * Create new multiplayer model
     * @param match Match instance
     */
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
    /**
     * Attach new observer to the match
     * @param matchObserver Match observer instance
     */
    public synchronized void attachMatchObserver(MultiplayerObserver matchObserver) {
        this.matchObservers.add(matchObserver);
    }
    /**
     * Free an observer from observing the match
     * @param matchObserver Match observer instance
     */
    public synchronized void detachMatchObserver(MultiplayerObserver matchObserver) {
        this.matchObservers.remove(matchObserver);
    }

    //Notifica osservatori

    /**
     * Notify player leave event to observers
     * @param tokenMatch Token of the match
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyPlayerLeave(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onPlayerLeave(tokenMatch, match);
    }
    /**
     * Notify player rejoined a match to observers
     * @param tokenMatch Token of the match
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyPlayerRejoin(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onPlayerRejoin(tokenMatch, match);
    }
    /**
     * Notify match started to observers
     * @param tokenMatch Token of the match
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyMatchStart(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onMatchStart(tokenMatch, match);
    }
    /**
     * Notify turn started to observers
     * @param tokenMatch Token of the match
     * @param match Match state
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onTurnStart(tokenMatch, match);
    }
    /**
     * Notify match ended to observers
     * @param tokenMatch Token of the match
     * @param match Match state
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onTurnEnd(tokenMatch, match);
    }
    /**
     * Notify choose window stage has started to observers
     * @param tokenMatch Token of the match
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyChooseWindows(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onChooseWindows(tokenMatch, match);
    }
    /**
     * Notify a die has been placed to observers
     * @param tokenMatch Token of the match
     * @param cell Subject cell
     * @param die Die placed
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyPlaceDie(String tokenMatch, Cell cell, Die die) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onPlaceDie(tokenMatch, match, cell, die);
    }
    /**
     * Notify a card ha been used to observers
     * @param tokenMatch Token of the match
     * @param toolCard Tool card used
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyUseTool(String tokenMatch, ToolCard toolCard) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onUseTool(tokenMatch, match, toolCard);
    }
    /**
     * Notify points of players to observers
     * @param tokenMatch Token of the match
     * @param player Player of the match
     * @param points Points of the player
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyGetPoints(String tokenMatch, Player player, PlayerPoints points) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onGetPoints(tokenMatch, match, player, points);
    }
    /**
     * Notify match ended to observers
     * @param tokenMatch Token of the match
     * @throws RemoteException Connection error occured
     */
    public synchronized void notifyMatchEnd(String tokenMatch) throws RemoteException {
        for (MultiplayerObserver observer : matchObservers)
            observer.onMatchEnd(tokenMatch, match);
    }
}
