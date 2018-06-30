package mvc.model.objects;

import mvc.creators.MatchCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MatchDiceTest {

    MatchCreator matchCreator = new MatchCreator(false);
    List<Die> diceBag = matchCreator.createDiceBag();
    List<Die> draftPool = matchCreator.createDraftPool(diceBag, 3);
    MatchDice matchDice = new MatchDice(3, diceBag, draftPool);

    @Test
    public void sameElements(){
        MatchDice matchDiceWrong = new MatchDice(0, null, null);
        matchDiceWrong.setPlayersCount(2);
        assertFalse(matchDice.sameMatchDice(matchDiceWrong));
    }

    @Test
    public void samePlayersCount() {

    }

    @Test
    public void sameDiceBag() {
    }

    @Test
    public void sameDraftPool() {
    }

    @Test
    public void sameMatchDice() {
    }

    @Test
    public void containsDieInBag() {
    }

    @Test
    public void containsDieInPool() {
    }

    @Test
    public void retrieveDieFromDraftPool() {
    }

    @Test
    public void extractDieFromBag() {
    }

    @Test
    public void extractDraftPoolFromBag() {
    }

    @Test
    public void clearDraftPool() {
    }
}