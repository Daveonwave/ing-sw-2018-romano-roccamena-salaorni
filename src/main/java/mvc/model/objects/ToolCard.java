package mvc.model.objects;

import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

import java.util.List;

public abstract class ToolCard extends Card{
    //Carta strumento

    private int favorTokens;
    private DieColor dieColor;

    //Costruttori
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

    //Usa carta strumento
    public abstract void useToolCard(Match match, Player player, List<Die> dice);

}
