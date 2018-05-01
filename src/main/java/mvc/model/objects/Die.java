package mvc.model.objects;

import java.awt.*;

public class Die {
    //Dado del gioco

    private Color color;
    private int shade;

    //Costruttori
    public Die(Color color, int shade) {
        this.color = color;
        this.shade = shade;
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
}
