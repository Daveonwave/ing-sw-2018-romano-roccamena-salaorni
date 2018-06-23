package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

public class ColorRestriction implements CellRestriction {
    //Restrizione di colore

    private DieColor color;

    //Costruttori
    public ColorRestriction(DieColor color) {
        this.color = color;
    }

    //Setter/Getter
    public void setColor(DieColor color) {
        this.color = color;
    }

    public DieColor getColor() {
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
