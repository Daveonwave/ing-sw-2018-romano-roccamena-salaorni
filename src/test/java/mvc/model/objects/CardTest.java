package mvc.model.objects;

import mvc.model.objects.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void sameCardTest(){
        Card card = new Card("cardTest", "descriptionTest");
        assertFalse(card.getName(), card.sameCard(new Card("cardFalse", "descriptionFalse")));
        assertTrue(card.getName(), card.sameCard(new Card("cardTest", "descriptionTest")));
    }
}