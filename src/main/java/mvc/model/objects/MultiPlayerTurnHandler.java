package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.util.ArrayList;
import java.util.List;

public class MultiPlayerTurnHandler extends TurnHandler {
    //Gestore dell'ordine dei giocatori di round e turni di partite multiplayer

    private final int playersCount;

    private int firstPlayerIndex;
    private int turnPlayerIndex;

    private int currentTurnIndex;
    private List<Integer> roundTurnsIndices;

    //Costruttori
    public MultiPlayerTurnHandler(int playersCount, int firstPlayerIndex) {
        super();
        this.playersCount = playersCount;
        this.firstPlayerIndex = firstPlayerIndex;
        this.turnPlayerIndex = firstPlayerIndex;
        this.roundTurnsIndices = new ArrayList<Integer>();
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
    public void nextRound() {
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
