package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.util.ArrayList;
import java.util.List;

public class TurnHandler {
    //Gestore dell'ordine dei giocatori di round e turni

    private final int playersCount;

    private int firstPlayerIndex;
    private int turnPlayerIndex;

    private int round;

    private int currentTurnIndex;
    private List<Integer> roundTurnsIndices;

    private boolean roundFirstTurn;
    private boolean roundLastTurn;

    private boolean firstTurnWave;

    //Costruttori
    public TurnHandler(int playersCount, int firstPlayerIndex) {
        this.playersCount = playersCount;
        this.firstPlayerIndex = firstPlayerIndex;
        this.turnPlayerIndex = firstPlayerIndex;
        this.round = 0;
        this.roundTurnsIndices = new ArrayList<Integer>();
        this.roundLastTurn = false;
        this.roundFirstTurn = false;
        this.firstTurnWave = false;
    }

    //Getter
    public int getPlayersCount() {
        return playersCount;
    }
    public int getFirstPlayerIndex() {
        return firstPlayerIndex;
    }
    public int getTurnPlayerIndex() {
        return turnPlayerIndex;
    }
    public int getRound() {
        return round;
    }

    //Proprietà di round e turni
    public boolean isRoundFirstTurn() {
        return roundFirstTurn;
    }
    public boolean isRoundLastTurn() {
        return roundLastTurn;
    }

    public boolean isFirstTurn() {
        return round == 1 && turnPlayerIndex == firstPlayerIndex;
    }
    public boolean isLastTurn() {
        return round == GameConstants.ROUNDS_COUNT && turnPlayerIndex == firstPlayerIndex;
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

    //Shift indici
    private int leftIndexShift(int playerIndex) {
        if (playerIndex == 0)
            return playersCount - 1;
        else
            return playerIndex - 1;
    }
    private int rightIndexShift(int playerIndex) {
        if (playerIndex == playersCount - 1)
            return 0;
        else
            return playerIndex + 1;
    }

    //Round successivo
    private void nextRound() {
        //Aumenta round
        round += 1;

        //Imposta segnali
        roundFirstTurn = true;
        roundLastTurn = false;

        firstTurnWave = true;

        //Se non è il primo round calcola il nuovo primo giocatore
        if (round != 1)
            firstPlayerIndex = rightIndexShift(firstPlayerIndex);

        //Crea ordine di gioco del nuovo round
        int current = firstPlayerIndex;

        roundTurnsIndices.clear();
        for (int i=0; i<playersCount; i++) {
            roundTurnsIndices.add(current);

            if (i!=playersCount-1)
                current = rightIndexShift(current);
        }
        for (int i=0; i<playersCount; i++) {
            roundTurnsIndices.add(current);

            current = leftIndexShift(current);
        }

        //Il giocatore corrente è il primo
        currentTurnIndex = 0;
        turnPlayerIndex = firstPlayerIndex;
    }
    //Inizia calcolo dei 10 round e relativi turni
    public void startRounds() {
        this.roundLastTurn = false;
        this.roundFirstTurn = true;
        this.firstTurnWave = true;
        nextRound();
    }
    //Turno successivo
    public void nextTurn() throws MatchException {
        //Controllo partita non finita
        if (isEnded())
            throw new MatchException("la partita è finita");

        //Imposta segnali
        roundFirstTurn = false;

        //Se è l'ultimo passa al prossimo round
        if (roundLastTurn) {
            nextRound();
        } else {
            //Assegna il giocatore successivo
            currentTurnIndex += 1;
            turnPlayerIndex = roundTurnsIndices.get(currentTurnIndex);

            //Imposta segnali
            roundLastTurn = currentTurnIndex == roundTurnsIndices.size()-1;
            firstTurnWave = currentTurnIndex <=roundTurnsIndices.size()/2-1;
        }
    }
}
