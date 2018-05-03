package mvc.model.objects;

import game.components.base.DieColor;

import java.awt.*;

public class ColorRestriction extends CellRestriction {
    //Restrizione di colore

    private Color color;

    //Costruttori
    public ColorRestriction(Color color) {
        this.color = color;
    }

    //Setter/Getter
    public void setDieColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return color.equals(die.getColor());
    }
}
