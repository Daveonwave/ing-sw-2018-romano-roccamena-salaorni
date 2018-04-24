package mvc.model;

import mvc.MatchObserver;
import mvc.model.objects.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MatchModel extends Match {
    //Model del match

    private List<MatchObserver> matchObserver;

    //Costruttori
    public MatchModel(List<Player> players, Player roundPlayer, int round, Map<String, List<Window>> startWindows, List<PublicObjectiveCard> objectiveCards, List<ToolCard> toolCards, List<Die> draftPool, List<List<Die>> roundTrack) {
        super(players, roundPlayer, round, startWindows, objectiveCards, toolCards, draftPool, roundTrack);
        this.matchObserver = new ArrayList<MatchObserver>();
    }
    public MatchModel(Match match) {
        this(match.getPlayers(), match.getTurnPlayer(), match.getRound(), match.getStartWindows(), match.getPublicObjectiveCards(), match.getToolCards(), match.getDraftPool(), match.getRoundTrack());
    }

    //Attacco, distacco degli osservatori
    public synchronized void attachMatchObserver(MatchObserver matchObserver) {
        this.matchObserver.add(matchObserver);
    }
    public synchronized void detachMatchObserver(MatchObserver matchObserver) {
        this.matchObserver.remove(matchObserver);
    }

    //Notifica osservatori
    public synchronized void notifyMatchStart(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onMatchStart(tokenMatch, match);
    }
    public synchronized void notifyChooseWindows(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onChooseWindows(tokenMatch, match);
    }
    public synchronized void notifyFirstRoundStart(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onFirstRoundStart(tokenMatch, match);
    }
    public synchronized void notifyRoundStart(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onRoundStart(tokenMatch, match);
    }
    public synchronized void notifyPlayerTurnStart(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onPlayerTurnStart(tokenMatch, match);
    }
    public synchronized void notifyPlaceDie(String tokenMatch, Match match, Player player, Die die) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onPlaceDie(tokenMatch, match, player, die);
    }
    public synchronized void notifyUseTool(String tokenMatch, Match match, Player player, ToolCard toolCard) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onUseTool(tokenMatch, match, player, toolCard);
    }
    public synchronized void notifyPlayerTurnEnd(String tokenMatch, Match match, Player player) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onPlayerTurnEnd(tokenMatch, match, player);
    }
    public synchronized void notifyRoundEnd(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onRoundEnd(tokenMatch, match);
    }
    public synchronized void notifyLastRoundEnd(String tokenMatch, Match match) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onLastRoundEnd(tokenMatch, match);
    }
    public synchronized void notifyGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException {
        for (MatchObserver observer : matchObserver)
            observer.onGetPoints(tokenMatch, match, player, points);
    }
}
