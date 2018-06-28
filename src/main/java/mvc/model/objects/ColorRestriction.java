package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

/**
 * Color restriction of a cell
 */
public class    ColorRestriction implements CellRestriction {
    //Restrizione di colore

    private DieColor color;

    //Costruttori
    /**
     * Create new color restriction
     * @param color Color of the restriction
     */
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
    /**
     * Assert equality of restrictions within two cells
     * @param cellRestriction Cell restriction instance
     * @return
     */
    public boolean sameCellRestriction(CellRestriction cellRestriction) {
        if (!(cellRestriction instanceof ColorRestriction))
            return false;

        ColorRestriction converted = (ColorRestriction) cellRestriction;

        return converted.getColor().equals(color);
    }

    //Verifica che un dado rispetti la restrizione
    /**
     * Assert if a die is compatible with the restriction
     * @param die Die instance
     * @return
     */
    public boolean canPlaceDie(Die die) {
        return color.equals(die.getColor());
    }

    @Override
    public String toString() {
        return StringConverter.getColorString(color);
    }
}
