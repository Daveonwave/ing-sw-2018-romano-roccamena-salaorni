package mvc.model.objects;

import java.awt.*;
import java.rmi.RemoteException;

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
    public void setDieColor(Color color) {
        this.color = color;
    }

    public int getFavorTokens() {
        return favorTokens;
    }
    public Color getColor() {
        return color;
    }

    //Usa carta strumento
    public abstract void useToolCard(Match match, ToolCardInput input) throws RemoteException;
}
