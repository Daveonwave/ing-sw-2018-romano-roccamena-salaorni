package mvc.model.objects;

import java.awt.Color;
import java.util.Random;

public class Die {
    //Dado del gioco

    private Color color;
    private int shade;
    private Random random;

    //Costruttori
    public Die(Color color, int shade) {
        this.color = color;
        this.shade = shade;
        this.random = new Random();
    }
    public Die(Color color) {
        this(color, 0);
        roll();
    }

    //Setter/Getter
    public void setColor(Color color) {
        this.color = color;
    }
    public void setShade(int shade) {
        this.shade = shade;
    }

    public Color getColor() {
        return color;
    }
    public int getShade() {
        return shade;
    }

    //Verifica uguaglianze
    public boolean sameColor(Die die) {
        if (die == null)
            return false;
        if (die.getColor() == null)
            return false;

        return  die.getColor().equals(color);
    }
    public boolean sameShade(Die die) {
        if (die == null)
            return false;

        return  die.getShade() == shade;
    }
    public boolean sameDie(Die die) {
        return  sameColor(die) && sameShade(die);
    }

    //Operazioni su dado
    public void roll() {
        this.shade = random.nextInt(6) + 1;
    }
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
}
