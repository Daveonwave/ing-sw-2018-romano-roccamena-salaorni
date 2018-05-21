package mvc.model.objects;

import javafx.scene.paint.Color;

public abstract class PrivateObjectiveCard extends ColorCard {
    //Carta obiettivo privato

    //Costruttori
    public PrivateObjectiveCard(String name, String description, java.awt.Color color) {
        super(name, description, color);
    }

    //Calcolo punteggio
    public abstract int getPoints(Window window);
}
