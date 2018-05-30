package mvc.model.objects;

import java.awt.*;
import java.rmi.RemoteException;

public abstract class ToolCard extends ColorCard {
    //Carta strumento

    private int favorTokens;

    //Costruttori
    public ToolCard(String name, String description, Color color) {
        super(name, description, color);
    }

    //Setter/Getter
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    //Usa carta strumento
    public abstract void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException;
    public abstract void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException;
}
