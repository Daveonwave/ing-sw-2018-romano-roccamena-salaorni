package mvc.model.objects;


public class TurnHandler {
    //Gestore dell'ordine dei giocatori di round e turni

    private final int playersCount;
    private int firstPlayerIndex;
    private int turnPlayerIndex;
    private int round;

    private boolean roundFirstTurn;
    private boolean roundLastTurn;

    //Costruttori
    public TurnHandler(int playersCount, int firstPlayerIndex) {
        this.playersCount = playersCount;
        this.firstPlayerIndex = firstPlayerIndex;
        this.turnPlayerIndex = firstPlayerIndex;
        this.round = 0;
        this.roundLastTurn = false;
        this.roundFirstTurn = true;
    }

    //Getter e osservatori
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
        return round == 10 && turnPlayerIndex == firstPlayerIndex;
    }

    public boolean isStarted() {
        return round > 0;
    }
    public boolean isEnded() {
        return round > 10;
    }

    //Calcola index player successivo
    private int playerIndexShift(int playerIndex) {
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

        //Se non è il primo round calcola il nuovo primo giocatore
        if (round != 1)
            firstPlayerIndex = playerIndexShift(firstPlayerIndex);

        //Il giocatore corrente è il primo
        turnPlayerIndex = firstPlayerIndex;
    }

    //Inizia calcolo dei 10 round e relativi turni
    public void startRounds() {
        nextRound();
    }
    //Turno successivo
    public void nextTurn() {
        //Imposta segnali
        if (roundFirstTurn)
            roundFirstTurn = false;

        //Assegna il giocatore successivo
        turnPlayerIndex = playerIndexShift(turnPlayerIndex);

        //Imposta segnale di ultimo turno del round
        roundLastTurn = turnPlayerIndex == firstPlayerIndex && !roundFirstTurn;

        //Se è l'ultimo passa al prossimo round
        if (roundLastTurn) {
            nextRound();
        }
    }
}
