package mvc.model.objects;

import mvc.exceptions.MatchException;

public class SinglePlayerTurnHandler extends TurnHandler {

    //Round successivo
    public void nextRound() {
        //Aumenta round
        round += 1;

        //Imposta segnali
        roundFirstTurn = true;
        roundLastTurn = false;

        firstTurnWave = true;
    }
    //Turno successivo
    public void nextTurn() throws MatchException {
        //Controlla se nuovo round
        if (roundLastTurn)
            nextRound();
        else {
            //Cambia turno
            roundFirstTurn = false;
            roundLastTurn = true;

            firstTurnWave = true;
        }
    }
}
