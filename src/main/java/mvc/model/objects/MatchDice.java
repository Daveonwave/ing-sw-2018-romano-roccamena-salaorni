package mvc.model.objects;

import mvc.creators.MatchCreator;
import mvc.exceptions.AppModelException;
import mvc.exceptions.MatchException;
import util.RandomHandler;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Representation of a game's match dice set
 */
public class MatchDice implements Serializable {
    //Dadi di una partita

    private int playersCount;
    private List<Die> diceBag;
    private List<Die> draftPool;

    //Costruttori
    public MatchDice(int playersCount, List<Die> diceBag, List<Die> draftPool) {
        this.playersCount = playersCount;
        this.diceBag = diceBag;
        this.draftPool = draftPool;
    }

    //Setter/Getter
    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }
    public void setDiceBag(List<Die> diceBag) {
        this.diceBag = diceBag;
    }
    public void setDraftPool(List<Die> draftPool) {
        this.draftPool = draftPool;
    }

    public int getPlayersCount() {
        return playersCount;
    }
    public List<Die> getDiceBag() {
        return diceBag;
    }
    public List<Die> getDraftPool() {
        return draftPool;
    }

    //Verifica uguaglianze

    /**
     * Assert if two match dice refers to matches with same players count
     * @param matchDice Match dice instance
     * @return
     */
    public boolean samePlayersCount(MatchDice matchDice) {
        return matchDice.getPlayersCount() == playersCount;
    }
    /**
     * Assert equality of object's dice bags
     * @param matchDice Match dice instance
     * @return
     */
    public boolean sameDiceBag(MatchDice matchDice) {
        if (matchDice == null)
            return false;

        List<Die> otherDiceBag = matchDice.getDiceBag();

        if (otherDiceBag == null)
            return false;

        if (diceBag.size() != otherDiceBag.size())
            return false;

        for (int i=0; i<diceBag.size(); i++)
            if (!diceBag.get(i).sameDie(otherDiceBag.get(i)))
                return false;

        return true;
    }
    /**
     * Assert equality of object's draft pools
     * @param matchDice Match dice instance
     * @return
     */
    public boolean sameDraftPool(MatchDice matchDice) {
        if (matchDice == null)
            return false;

        List<Die> otherDraftPool = matchDice.getDraftPool();

        if (otherDraftPool == null)
            return false;

        if (draftPool.size() != otherDraftPool.size())
            return false;

        for (int i=0; i<draftPool.size(); i++)
            if (!draftPool.get(i).sameDie(otherDraftPool.get(i)))
                return false;

        return true;
    }
    /**
     * Assert equality of two match dice
     * @param matchDice Match dice instance
     * @return
     */
    public boolean sameMatchDice(MatchDice matchDice) {
        return samePlayersCount(matchDice) && sameDiceBag(matchDice) && sameDraftPool(matchDice);
    }

    //Verifica che il dado sia contenuto
    /**
     * Assert if die bag contains a die
     * @param die Die instance
     * @return
     */
    public boolean containsDieInBag(Die die) {
        for (Die d : diceBag) {
            if (d==null ^ die==null)
                return false;
            if (d==null && die==null)
                return true;

            if (d.sameDie(die))
                return true;
        }

        return false;
    }

    /**
     * Assert if draft pool contains a die
     * @param die
     * @return
     */
    public boolean containsDieInPool(Die die) {
        for (Die d : draftPool) {
            if (d==null ^ die==null)
                return false;
            if (d==null && die==null)
                return true;

            if (d.sameDie(die))
                return true;
        }

        return false;
    }

    //Ottiene dado da riserva dadi
    /**
     * Obtain a draft pool's die using another equal die instance
     * @param die
     * @return
     * @throws RemoteException MatchException if invalid die requested
     */
    public Die retrieveDieFromDraftPool(Die die) throws RemoteException {
        if (die==null)
            throw new MatchException("dado non valido");

        Die result = null;
        for (Die d : draftPool) {
            if (die.sameDie(d)) {
                result = d;
                break;
            }
        }
        if (result == null)
            throw new AppModelException("dado non valido");

        return result;
    }

    //Estrae un dado dal sacco di dadi
    /**
     * Extract one die from the bag
     * @return
     */
    public Die extractDieFromBag() {
        int index = RandomHandler.retrieveRandom().nextInt(diceBag.size());

        Die result = diceBag.get(index);
        diceBag.remove(index);

        return result;
    }
    //Estrae nuova draft pool dal sacco di dadi
    /**
     * Extract draft pool's dice from the bag
     */
    public void extractDraftPoolFromBag() {
        boolean singlePlayer = this.playersCount == 1;

        MatchCreator matchCreator = new MatchCreator(singlePlayer);

        this.draftPool.addAll(matchCreator.createDraftPool(diceBag, playersCount));
    }

    //Toglie dalla draft pool tutti i dadi
    /**
     * Removes all dice in the draft pool
     */
    public void clearDraftPool() {
        this.draftPool.clear();
    }
}
