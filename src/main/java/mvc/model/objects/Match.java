package mvc.model.objects;

import java.util.List;

public class Match {
    //Componenti di una partita

    private List<Player> players;
    private Player roundPlayer;
    private int round;
    private List<ObjectiveCard> objectiveCards;
    private List<ToolCard> toolCards;
    private List<Die> draftPool;
    private List<List<Die>> roundTrack;

    //Creatori
    public Match(List<Player> players, Player roundPlayer, int round, List<ObjectiveCard> objectiveCards, List<ToolCard> toolCards, List<Die> draftPool, List<List<Die>> roundTrack) {
        this.players = players;
        this.roundPlayer = roundPlayer;
        this.round = round;
        this.objectiveCards = objectiveCards;
        this.toolCards = toolCards;
        this.draftPool = draftPool;
        this.roundTrack = roundTrack;
    }

    //Setter/Getter
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setRoundPlayer(Player roundPlayer) {
        this.roundPlayer = roundPlayer;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public void setObjectiveCards(List<ObjectiveCard> objectiveCards) {
        this.objectiveCards = objectiveCards;
    }
    public void setToolCards(List<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }
    public void setDraftPool(List<Die> draft_pool) {
        this.draftPool = draft_pool;
    }
    public void setRoundTrack(List<List<Die>> roundTrack) {
        this.roundTrack = roundTrack;
    }

    public List<Player> getPlayers() {
        return players;
    }
    public Player getRoundPlayer() {
        return roundPlayer;
    }
    public int getRound() {
        return round;
    }
    public List<ObjectiveCard> getObjectiveCards() {
        return objectiveCards;
    }
    public List<ToolCard> getToolCards() {
        return toolCards;
    }
    public List<Die> getDraftPool() {
        return draftPool;
    }
    public List<List<Die>> getRoundTrack() {
        return roundTrack;
    }
}
