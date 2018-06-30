package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

/**
 * Abstract private objective card
 */
public abstract class PrivateObjectiveCard extends ColorCard {
    //Carta obiettivo privato

    //Costruttori
    /**
     * Create new private objective card
     * @param name Name of the card
     * @param description Description of the card
     * @param color Color of the card
     */
    public PrivateObjectiveCard(String name, String description, DieColor color) {
        super(name, description, color);
    }

    //Calcolo punteggio
    /**
     * Points calculation algorithm
     * @param window Window instance
     * @return
     */
    public abstract int getPoints(Window window);
}
