package mvc.model.objects;

public abstract class PublicObjectiveCard extends Card {
    //Carta obiettivo pubblico

    //Costruttori
    public PublicObjectiveCard(String name, String description) {
        super(name, description);
    }

    //Calcolo punteggio
    public abstract int getPoints(Window window);
}