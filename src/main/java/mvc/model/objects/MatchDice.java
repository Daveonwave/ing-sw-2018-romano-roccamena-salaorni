package mvc.model.objects;

import mvc.builders.MatchBuilder;

import java.util.ArrayList;
import java.util.List;

public class MatchDice {
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

    //TODO:implementazione
    //Estrae nuova draft pool dal sacco di dadi
    public void extractNewDraftPool() {

    }
    //Toglie dalla draft pool tutti i dadi
    public void clearDraftPool() {
        this.draftPool.clear();
    }
}
