package game.components;

import game.components.base.IdentifyStrategy;

public class Match implements IdentifyStrategy<Match> {
    //Componenti di una partita

    private Player[] players;
    private Player roundPlayer;
    private int round;
    private PrivateObjectiveCard[] objectiveCards;
    private ToolCard[] toolCards;
    private Die[] draftPool;
    private Die[][] roundTrack;

    //Creatori
    public Match(Player[] players, Player roundPlayer, int round, PrivateObjectiveCard[] objectiveCards, ToolCard[] toolCards, Die[] draftPool, Die[][] roundTrack) {
        this.players = players;
        this.roundPlayer = roundPlayer;
        this.round = round;
        this.objectiveCards = objectiveCards;
        this.toolCards = toolCards;
        this.draftPool = draftPool;
        this.roundTrack = roundTrack;
    }

    //Setter/Getter
    public void setPlayers(Player[] players) {
        this.players = players;
    }
    public void setRoundPlayer(Player roundPlayer) {
        this.roundPlayer = roundPlayer;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public void setObjectiveCards(PrivateObjectiveCard[] objectiveCards) {
        this.objectiveCards = objectiveCards;
    }
    public void setToolCards(ToolCard[] toolCards) {
        this.toolCards = toolCards;
    }
    public void setDraftPool(Die[] draft_pool) {
        this.draftPool = draft_pool;
    }
    public void setRoundTrack(Die[][] roundTrack) {
        this.roundTrack = roundTrack;
    }

    public Player[] getPlayers() {
        return players;
    }
    public Player getRoundPlayer() {
        return roundPlayer;
    }
    public int getRound() {
        return round;
    }
    public PrivateObjectiveCard[] getObjectiveCards() {
        return objectiveCards;
    }
    public ToolCard[] getToolCards() {
        return toolCards;
    }
    public Die[] getDraftPool() {
        return draftPool;
    }
    public Die[][] getRoundTrack() {
        return roundTrack;
    }

    //Identificazione
    //TODO: implementazione
    public boolean isSame(Match obj) {
        return true;
    }


}
