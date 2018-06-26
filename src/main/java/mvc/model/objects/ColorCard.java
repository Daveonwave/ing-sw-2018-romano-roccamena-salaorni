package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

/**
 * Colored card extension of a game card
 */
public class ColorCard extends Card {
    //Carta colorata

    private DieColor color;

    //Costruttori
    /**
     * Create new colored card
     * @param name Name of the card
     * @param description Description of the card
     * @param color Color of the card
     */
    public ColorCard(String name, String description, DieColor color) {
        super(name, description);
        this.color = color;
    }

    //Setter/Getter
    public DieColor getColor() {
        return color;
    }

    public void setColor(DieColor color) {
        this.color = color;
    }
}
