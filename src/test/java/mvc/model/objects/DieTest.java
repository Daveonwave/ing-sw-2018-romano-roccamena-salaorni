package mvc.model.objects;

import mvc.model.objects.enums.DieColor;
import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {

    Die die = new Die(DieColor.PURPLE, 5);

    @Test
    public void sameElements(){
        Die dieNull = null;

        assertFalse(die.sameDie(dieNull));
        assertFalse(die.sameColor(dieNull));
        assertFalse(die.sameShade(dieNull));

        assertFalse(die.sameDie(new Die(null, 2)));
        assertFalse(die.sameShade(new Die(DieColor.YELLOW, 2)));
        assertFalse(die.sameColor(new Die(DieColor.RED, 2)));

        dieNull = new Die (null, 0);
        dieNull.setColor(DieColor.GREEN);
        //Die dieNew = new Die(DieColor.BLUE);

        assertNotEquals(dieNull.getColor(), die.getColor());
        assertTrue(die.sameColor(new Die(DieColor.PURPLE, 5)));
    }


    @Test
    public void invertShade() {
        Die dieInvert = new Die(null, 1);
        dieInvert.invertShade();
        assertEquals(6, dieInvert.getShade());

        dieInvert.setShade(2);
        dieInvert.invertShade();
        assertEquals(5, dieInvert.getShade());

        dieInvert.setShade(3);
        dieInvert.invertShade();
        assertEquals(4, dieInvert.getShade());

        dieInvert.setShade(4);
        dieInvert.invertShade();
        assertEquals(3, dieInvert.getShade());

        dieInvert.setShade(5);
        dieInvert.invertShade();
        assertEquals(2, dieInvert.getShade());

        dieInvert.setShade(6);
        dieInvert.invertShade();
        assertEquals(1, dieInvert.getShade());

    }
}