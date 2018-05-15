package mvc.model.objects;

import java.util.List;

public class RoundTrack {
    //Round track di una partita

    List<List<Die>> diceStack;

    //Costruttori


    public RoundTrack(List<List<Die>> diceStack) {
        this.diceStack = diceStack;
    }

    //Setter/Getter
    public void setDiceStack(List<List<Die>> diceStack) {
        this.diceStack = diceStack;
    }

    public List<List<Die>> getDiceStack() {
        return diceStack;
    }


    //Ottiene i dadi di un turno della round track
    public List<Die> retrieveDice(int round) {
        return diceStack.get(round-1);
    }
    //Sposta dadi della draft pool di un turno nella posizione corrispondente dela round track
    public void moveDice(MatchDice matchDice, int round) {
        List<Die> selected = diceStack.get(round-1);

        for (Die d : matchDice.getDraftPool())
            selected.add(d);

        matchDice.clearDraftPool();
    }
}
