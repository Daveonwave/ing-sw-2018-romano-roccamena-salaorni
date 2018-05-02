package mvc.model.objects;

import java.util.ArrayList;
import java.util.List;

public class RoundTrack {
    //Round track di una partita

    List<List<Die>> dice;

    //Costruttori
    public RoundTrack(List<List<Die>> dice) {
        this.dice = dice;
    }

    //Setter/Getter
    public void setDice(List<List<Die>> dice) {
        this.dice = dice;
    }

    public List<List<Die>> getDice() {
        return dice;
    }

    //Ottiene i dadi di un turno della round track
    public List<Die> retrieveDice(int round) {
        return dice.get(round-1);
    }
    //Sposta dadi della draft pool di un turno nella posizione corrispondente dela round track
    public void moveDice(MatchDice matchDice, int round) {
        List<Die> selected = dice.get(round-1);

        for (Die d : matchDice.getDraftPool())
            selected.add(d);

        matchDice.clearDraftPool();
    }
}
