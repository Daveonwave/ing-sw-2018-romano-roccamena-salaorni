package mvc.model.objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test (expected = NullPointerException.class)
    public void sameCardTest(){
        Card card = new Card("cardTest", "descriptionTest");
        assertFalse(card.getName(), card.sameCard(new Card("cardFalse", "descriptionFalse")));
        assertTrue(card.getName(), card.sameCard(new Card("cardTest", "descriptionTest")));

        Card cardNull = null;

        assertFalse(card.sameCard(cardNull));
        cardNull = new Card(null, null);
        cardNull.setDescription(card.getDescription());

        assertNotEquals(true, card.sameCard(cardNull));

        cardNull.setName(card.getName());

        assertTrue(card.sameCard(cardNull));


    }
}