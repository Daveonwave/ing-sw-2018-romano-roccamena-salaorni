package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Round track component of a match
 */
public class RoundTrack implements Serializable {
    //Tracciato dei round di una partita

    private List<List<Die>> diceStack;

    //Costruttori
    /**
     * Create new round track
     * @param diceStack
     */
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
    /**
     * Asserts equality of round track dice for a specified round
     * @param roundTrack Round track instance
     * @param round Round
     * @return
     */
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
    /**
     * Asserts equality of two round track objects
     * @param roundTrack Round track instance
     * @return
     */
    public boolean sameRoundTrack(RoundTrack roundTrack) {
        for(int i=0; i<10; i++)
            if (!sameRoundDice(roundTrack, i))
                return false;

        return true;
    }

    //Verifica che i dadi siano contenuti
    /**
     * Asserts round track contains specified dice on a given round
     * @param round Round
     * @param dice Dice instances
     * @return
     */
    public boolean containsDice(int round, List<Die> dice) {
        for (Die d1 : diceStack.get(round-1)) {
            for (Die d2 : dice) {
                if ((d1 != null && d2 != null) && d1.sameDie(d2))
                    return true;
            }
        }

        return false;
    }
    /**
     * Asserts round track contains specified die on a given round
     * @param round Round
     * @param die Die intance
     * @return
     */
    public boolean containsDie(int round, Die die) {
        for (Die d1 : diceStack.get(round-1)) {
            if ((die != null && d1 != null) && die.sameDie(d1))
                return true;
        }

        return false;
    }

    //Ottiene i dadi di un round del tracciato
    /**
     * Reetrieve dice instances for a given round
     * @param round Round
     * @return
     */
    public List<Die> retrieveDice(int round) {
        return diceStack.get(round-1);
    }
    //Ottiene uno dei dadi di un round del tracciato
    /**
     * Retrieve given die for a specified round by die state
     * @param round Round
     * @param die Die instance
     * @return
     * @throws RemoteException MatchException if die was not found
     */
    public Die retrieveDie(int round, Die die) throws RemoteException {
        List<Die> dice = retrieveDice(round);

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
    /**
     * Move draft pool dice in round track for a given round
     * @param matchDice Match dice instance
     * @param round Round
     */
    public void moveDice(MatchDice matchDice, int round) {
        List<Die> selected = diceStack.get(round-1);

        for (Die d : matchDice.getDraftPool())
            selected.add(d);

        matchDice.clearDraftPool();
    }
}
