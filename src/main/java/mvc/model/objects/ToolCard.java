package mvc.model.objects;



import java.awt.*;

public abstract class ToolCard extends Card{
    //Carta strumento

    private Match match;
    private int favorTokens;
    private Color color;
    //Costruttori
   public ToolCard(String name, String description, int favorTokens, Color color) {
        super(name, description);
        this.match = match;
        this.favorTokens = favorTokens;
        this.color = color;
    }

    //Setter/Getter
    public void setMatch(Match match) {
        this.match = match;
    }
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }
    public void setDieColor(Color color) {
        this.color = color;
    }

    public Match getMatch() {
        return match;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
    public Color getColor() {
        return color;
    }

    //Usa carta strumento
    public abstract void useToolCard(Match newMatch, Player player);
}
