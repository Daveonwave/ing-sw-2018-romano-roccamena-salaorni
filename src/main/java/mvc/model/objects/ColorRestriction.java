package mvc.model.objects;

import game.components.base.DieColor;

public class ColorRestriction extends CellRestriction {
    //Restrizione di colore

    private DieColor dieColor;

    //Costruttori
    public ColorRestriction(DieColor dieColor) {
        this.dieColor = dieColor;
    }

    //Setter/Getter
    public void setDieColor(DieColor dieColor) {
        this.dieColor = dieColor;
    }

    public DieColor getDieColor() {
        return dieColor;
    }

    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return dieColor.equals(die.getColor());
    }
}
