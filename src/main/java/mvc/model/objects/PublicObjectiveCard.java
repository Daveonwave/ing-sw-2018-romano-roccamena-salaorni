package mvc.model.objects;

/**
 * Abstract public objective card
 */
public abstract class PublicObjectiveCard extends Card {
    //Carta obiettivo pubblico

    //Costruttori
    /**
     * Create new public objective card
     * @param name Name of the card
     * @param description Description of the card
     */
    public PublicObjectiveCard(String name, String description) {
        super(name, description);
    }

    //Calcolo punteggio
    /**
     * Points calculation algorithm
     * @param window Window instance
     * @return
     */
    public abstract int getPoints(Window window);
}