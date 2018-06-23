package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

public class ColorCard extends Card {
    //Carta colorata

    private DieColor color;

    //Costruttori
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
