package mvc.model.objects;
import java.awt.*;

public class ColorCard extends Card {
    //Carta colorata

    private Color color;

    //Costruttori
    public ColorCard(String name, String description, Color color) {
        super(name, description);
        this.color = color;
    }

    //Setter/Getter
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
