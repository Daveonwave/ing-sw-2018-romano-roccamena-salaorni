package mvc.model.objects;

import java.awt.*;

public abstract class ToolCard extends Card{
    //Carta strumento

    private int favorTokens;
    private Color color;

    //Costruttori
    public ToolCard(String name, String description, int favorTokens, Color color) {
        super(name, description);
        this.favorTokens = favorTokens;
        this.color = color;
    }

    //Setter/Getter
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }
    public void setDieColor(Color dieColor) {
        this.color = dieColor;
    }

    public int getFavorTokens() {
        return favorTokens;
    }
    public Color getDieColor() {
        return color;
    }

    //Usa carta strumento
    public abstract void useToolCard(Match match, Player player);

}
