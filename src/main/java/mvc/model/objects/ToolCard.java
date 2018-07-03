package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Tool card object
 */
public abstract class ToolCard extends ColorCard {
    //Carta strumento

    private int favorTokens;

    //Costruttori
    /**
     * Create new tool card
     * @param name Card name
     * @param description Card description
     * @param color Card color
     */
    public ToolCard(String name, String description, DieColor color) {
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

    /**
     * Use tool card in a multiplayer match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public abstract void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException;
    /**
     * Use tool card in a singleplayer match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public abstract void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException;
}
