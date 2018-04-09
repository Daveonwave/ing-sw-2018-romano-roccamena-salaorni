package game.components;

import game.components.base.IdentifyStrategy;
import game.components.base.DieColor;

public class Die implements IdentifyStrategy<Die> {
    //Dado del gioco

    private DieColor color;
    private int shade;

    //Creatori
    public Die(DieColor color, int shade) {
        this.color = color;
        this.shade = shade;
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

    //Identificazione
    public boolean isSame(Die obj) {
        return color == obj.color && shade == obj.shade;
    }
}
