package mvc.model.objects;

import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

public abstract class ToolCard extends Card{
    //Carta strumento

    private int favorTokens;
    private DieColor dieColor;

    //Costruttore
    public ToolCard(String name, String description, int favorTokens, DieColor dieColor) {
        super(name, description);
        this.favorTokens = favorTokens;
        this.dieColor = dieColor;
    }

    //Setter/Getter

    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }
    public void setDieColor(DieColor dieColor) {
        this.dieColor = dieColor;
    }

    public int getFavorTokens() {
        return favorTokens;
    }
    public DieColor getDieColor() {
        return dieColor;
    }

    //metodo che fa funzionare la carta tool

    public abstract void useToolCard(Match match);

}
