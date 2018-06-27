package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.io.Serializable;

/**
 * Match handler of turns and rounds
 */
public abstract class TurnHandler implements Serializable {
    //Gestore base di turni di partite

    protected int round;

    protected boolean roundFirstTurn;
    protected boolean roundLastTurn;

    protected boolean firstTurnWave;

    //Costruttori
    /**
     * Create new turn handler
     */
    public TurnHandler() {
        this.round = 0;
        this.roundLastTurn = false;
        this.roundFirstTurn = false;
        this.firstTurnWave = false;
    }

    //Setter/Getter
    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return this.round;
    }

    //Proprietà di round e turni
    /**
     * Asserts current turn is round first turn
     * @return
     */
    public boolean isRoundFirstTurn() {
        return roundFirstTurn;
    }
    /**
     * Asserts current turn is round last turn
     * @return
     */
    public boolean isRoundLastTurn() {
        return roundLastTurn;
    }

    //Proprietà di round e turni
    /**
     * Asserts if current turn is match first turn
     * @return
     */
    public boolean isFirstTurn() {
        return round == 1 && roundFirstTurn;
    }
    /**
     * Asserts if current turn is match last turn
     * @return
     */
    public boolean isLastTurn() {
        return round == GameConstants.ROUNDS_COUNT && roundLastTurn;
    }

    /**
     * Asserts if match rounds stage has started
     * @return
     */
    public boolean isStarted() {
        return round > 0;
    }
    /**
     * Asserts if match rounds stage has ended
     * @return
     */
    public boolean isEnded() {
        return round > GameConstants.ROUNDS_COUNT;
    }

    /**
     * Assert if current turn is in round first turn wave
     * @return
     */
    public boolean isFirstTurnWave() {
        return firstTurnWave;
    }

    //Round successivo
    /**
     * Go to next round
     */
    public abstract void nextRound();
    //Inizia calcolo dei 10 round e relativi turni
    /**
     * Start rounds stage
     */
    public void startRounds() {
        this.roundLastTurn = false;
        this.roundFirstTurn = true;
        this.firstTurnWave = true;
        nextRound();
    }
    //Turno successivo

    /**
     * Go to next turn
     * @throws MatchException Invalid action requested
     */
    public abstract void nextTurn() throws MatchException;
}
