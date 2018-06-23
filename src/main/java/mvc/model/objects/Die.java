package mvc.model.objects;

import mvc.model.objects.enums.DieColor;
import util.RandomHandler;

import java.io.Serializable;

/**
 * Die object of the game
 */
public class Die implements Serializable {
    //Dado del gioco

    private DieColor color;
    private int shade;

    //Costruttori
    public Die(DieColor color, int shade) {
        this.color = color;
        this.shade = shade;
    }
    public Die(DieColor color) {
        this(color, 0);
        roll();
    }

    //Setter/Getter
    public void setColor(DieColor color) {
        this.color = color;
    }
    public void setShade(int shade) {
        this.shade = shade;
    }

    public DieColor getColor() {
        return color;
    }
    public int getShade() {
        return shade;
    }

    //Verifica uguaglianze
    /**
     * Assert color equality of two dice
     * @param die Die instance
     * @return
     */
    public boolean sameColor(Die die) {
        if (die == null)
            return false;

        DieColor otherDie = die.getColor();

        if (color == null && otherDie == null)
            return true;
        if (color == null ^ otherDie == null)
            return false;

        return  otherDie.equals(color);
    }

    /**
     * Assert shade equality of two dice
     * @param die Die instance
     * @return
     */
    public boolean sameShade(Die die) {
        if (die == null)
            return false;

        return  die.getShade() == shade;
    }

    /**
     * Assert dice equality
     * @param die Die instance
     * @return
     */
    public boolean sameDie(Die die) {
        return  sameColor(die) && sameShade(die);
    }

    //Operazioni su dado
    /**
     * Roll the die, randomizing its shade between 0 and 1
     */
    public void roll() {
        int newShade = RandomHandler.retrieveRandom().nextInt(6) + 1;

        this.shade = newShade;
    }

    /**
     * Invert the shade of the die, as if you flip die face
     */
    public void invertShade() {
        switch (shade) {
            case 1:
                this.shade = 6;
                break;
            case 2:
                this.shade = 5;
                break;
            case 3:
                this.shade = 4;
                break;
            case 4:
                this.shade = 3;
                break;
            case 5:
                this.shade = 2;
                break;
            case 6:
                this.shade = 1;
                break;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(shade) + StringConverter.getColorString(color);
    }
}
