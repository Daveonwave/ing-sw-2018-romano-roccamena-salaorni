package mvc.model.objects;

import mvc.creators.MatchCreator;
import mvc.exceptions.AppModelException;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

public class MatchDice {
    //Dadi di una partita

    private int playersCount;
    private List<Die> diceBag;
    private List<Die> draftPool;
    private Random random;

    //Costruttori
    public MatchDice(int playersCount, List<Die> diceBag, List<Die> draftPool) {
        this.playersCount = playersCount;
        this.diceBag = diceBag;
        this.draftPool = draftPool;
        this.random = new Random();
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
    public boolean samePlayersCount(MatchDice matchDice) {
        return matchDice.getPlayersCount() == playersCount;
    }
    public boolean sameDiceBag(MatchDice matchDice) {
        if (matchDice == null)
            return false;
        if (matchDice.getDiceBag() == null)
            return false;


        if (diceBag.size() != matchDice.getDiceBag().size())
            return false;

        for (int i=0; i<diceBag.size(); i++)
            if (!diceBag.get(i).sameDie(matchDice.getDiceBag().get(i)))
                return false;

        return true;
    }
    public boolean sameDraftPool(MatchDice matchDice) {
        if (matchDice == null)
            return false;
        if (matchDice.getDraftPool() == null)
            return false;

        if (draftPool.size() != matchDice.getDraftPool().size())
            return false;

        for (int i=0; i<draftPool.size(); i++)
            if (!draftPool.get(i).sameDie(matchDice.getDraftPool().get(i)))
                return false;

        return true;
    }
    public boolean sameMatchDice(MatchDice matchDice) {
        return samePlayersCount(matchDice) && sameDiceBag(matchDice) && sameDraftPool(matchDice);
    }

    //Ottiene dado da riserva dadi
    public synchronized Die retrieveDieFromDraftPool(Die die) throws RemoteException {
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
    public Die extractDieFromBag() {
        int index = random.nextInt(draftPool.size());

        draftPool.remove(index);

        return draftPool.get(index);
    }
    //Estrae nuova draft pool dal sacco di dadi
    public void extractDraftPoolFromBag() {
        boolean singlePlayer = this.playersCount == 1;

        MatchCreator matchCreator = new MatchCreator(singlePlayer);

        this.draftPool.addAll(matchCreator.createDraftPool(diceBag, playersCount));
    }

    //Toglie dalla draft pool tutti i dadi
    public void clearDraftPool() {
        this.draftPool.clear();
    }
}
