package mvc.model.objects;

import mvc.exceptions.MatchException;
import mvc.model.objects.GameConstants;

import java.io.Serializable;

public abstract class TurnHandler implements Serializable {
    //Gestore base di turni di partite

    protected int round;

    protected boolean roundFirstTurn;
    protected boolean roundLastTurn;

    protected boolean firstTurnWave;

    //Costruttori
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
    public boolean isRoundFirstTurn() {
        return roundFirstTurn;
    }
    public boolean isRoundLastTurn() {
        return roundLastTurn;
    }

    //Proprietà di round e turni
    public boolean isFirstTurn() {
        return round == 1 && roundFirstTurn;
    }
    public boolean isLastTurn() {
        return round == GameConstants.ROUNDS_COUNT && roundLastTurn;
    }

    public boolean isStarted() {
        return round > 0;
    }
    public boolean isEnded() {
        return round > GameConstants.ROUNDS_COUNT;
    }

    public boolean isFirstTurnWave() {
        return firstTurnWave;
    }

    //Round successivo
    public abstract void nextRound();
    //Inizia calcolo dei 10 round e relativi turni
    public void startRounds() {
        this.roundLastTurn = false;
        this.roundFirstTurn = true;
        this.firstTurnWave = true;
        nextRound();
    }
    //Turno successivo
    public abstract void nextTurn() throws MatchException;
}
