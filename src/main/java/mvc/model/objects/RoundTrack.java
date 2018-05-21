package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.rmi.RemoteException;
import java.util.List;

public class RoundTrack {
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
        List<Die> roundDiceArg = roundTrack.getDiceStack().get(round-1);

        if (roundDice.size() != roundDiceArg.size())
            return false;

        for (int i=0; i<roundDiceArg.size(); i++) {
            if (!roundDice.get(i).sameDie(roundDiceArg.get(i)))
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