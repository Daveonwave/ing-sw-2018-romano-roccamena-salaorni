package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class RoundTrack implements Serializable {
    //Tracciato dei round di una partita

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

    //Verifica uguglianze
    public boolean sameRoundDice(RoundTrack roundTrack, int round) {
        List<Die> roundDice = diceStack.get(round-1);
        List<Die> otherRoundDice = roundTrack.getDiceStack().get(round-1);

        if (otherRoundDice==null)
            return false;

        if (roundDice.size() != otherRoundDice.size())
            return false;

        for (int i=0; i<otherRoundDice.size(); i++) {
            if (!roundDice.get(i).sameDie(otherRoundDice.get(i)))
                return false;
        }

        return true;
    }
    public boolean sameRoundTrack(RoundTrack roundTrack) {
        for(int i=0; i<10; i++)
            if (!sameRoundDice(roundTrack, i))
                return false;

        return true;
    }

    //Verifica che i dadi siano contenuti
    public boolean containsDice(int round, List<Die> dice) {
        for (Die d1 : diceStack.get(round-1)) {
            for (Die d2 : dice) {
                if (d1 != null && d2 != null)
                    if (d1.sameDie(d2))
                        return true;
            }
        }

        return false;
    }
    public boolean containsDie(int round, Die die) {
        for (Die d1 : diceStack.get(round-1)) {
            if (die != null && d1 != null)
                if (die.sameDie(d1))
                    return true;
        }

        return false;
    }

    //Ottiene i dadi di un round del tracciato
    public List<Die> retrieveDice(int round) {
        return diceStack.get(round-1);
    }
    //Ottiene uno dei dadi di un round del tracciato
    public Die retrieveDie(int round, Die die) throws RemoteException {
        List<Die> dice = retrieveDice(round-1);

        boolean found = false;
        for (Die d : dice) {
            if (d.sameDie(die)) {
                die = d;
                found = true;
                break;
            }
        }
        if (!found)
            throw new MatchException("dado non valido");

        return die;
    }
    //Sposta dadi della draft pool di un turno nella posizione corrispondente dela round track
    public void moveDice(MatchDice matchDice, int round) {
        List<Die> selected = diceStack.get(round-1);

        for (Die d : matchDice.getDraftPool())
            selected.add(d);

        matchDice.clearDraftPool();
    }
}
