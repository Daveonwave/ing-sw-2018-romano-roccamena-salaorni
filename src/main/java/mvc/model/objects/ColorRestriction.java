package mvc.model.objects;

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

    //Verifica uguaglianza
    public boolean sameCellRestriction(CellRestriction cellRestriction) {
        if (!(cellRestriction instanceof ColorRestriction))
            return false;

        ColorRestriction converted = (ColorRestriction) cellRestriction;

        return converted.getColor().equals(color);
    }

    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return color.equals(die.getColor());
    }

    @Override
    public String toString() {
        return StringConverter.getColorString(color);
    }
}
