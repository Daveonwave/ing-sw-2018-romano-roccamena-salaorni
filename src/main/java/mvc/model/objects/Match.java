package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Generic match object of the game
 */
public abstract class Match implements Serializable {
    //Partita base

    protected MatchState matchState;
    protected TurnHandler turnHandler;
    protected List<PublicObjectiveCard> publicObjectiveCards;
    protected List<ToolCard> toolCards;
    protected MatchDice matchDice;
    protected RoundTrack roundTrack;

    //Costruttori
    /**
     * Create new match
     * @param objectiveCards Objective cards of the match
     * @param toolCards Tool cards of the match
     * @param matchDice Dice of the match
     * @param roundTrack Round track of the match
     * @param turnHandler Turn handler of the match
     */
    public Match(List<PublicObjectiveCard> objectiveCards, List<ToolCard> toolCards, MatchDice matchDice, RoundTrack roundTrack, TurnHandler turnHandler) {
        this.matchState = MatchState.CREATED;
        this.publicObjectiveCards = objectiveCards;
        this.toolCards = toolCards;
        this.matchDice = matchDice;
        this.roundTrack = roundTrack;
        this.turnHandler = turnHandler;
    }

    //Setter/Getter
    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }
    public void setTurnHandler(TurnHandler turnHandler) {
        this.turnHandler = turnHandler;
    }
    public void setPublicObjectiveCards(List<PublicObjectiveCard> publicObjectiveCards) {
        this.publicObjectiveCards = publicObjectiveCards;
    }
    public void setToolCards(List<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }
    public void setMatchDice(MatchDice matchDice) {
        this.matchDice = matchDice;
    }
    public void setRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
    }

    public MatchState getMatchState() {
        return matchState;
    }
    public TurnHandler getTurnHandler() {
        return turnHandler;
    }
    public List<PublicObjectiveCard> getPublicObjectiveCards() {
        return publicObjectiveCards;
    }
    public List<ToolCard> getToolCards() {
        return toolCards;
    }
    public MatchDice getMatchDice() {
        return matchDice;
    }
    public RoundTrack getRoundTrack() {
        return roundTrack;
    }

    //Inizia partita
    /**
     * Begin the match object
     * @throws RemoteException MatchException if match has already started
     */
    public void beginMatch() throws RemoteException {
        //Controllo stato corretto della partita
        if (matchState != MatchState.CREATED)
            throw new MatchException("la partita è gia iniziata");


        //Aggiorna lo stato prossimo
        matchState = MatchState.CHOOSE_WINDOWS;
    }
}
