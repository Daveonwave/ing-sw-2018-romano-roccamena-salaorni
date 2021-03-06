package mvc.creators;

import mvc.model.objects.ToolCard;
import mvc.model.objects.toolcards.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator of model's tool cards
 */
public class ToolsCreator {
    //Creatore carte strumento gioco

    /**
     * Private constructor
     */
    private ToolsCreator(){}

    /**
     * Create all tool cards
     * @return
     */
    public static List<ToolCard> createToolCards() {
        List<ToolCard> cards = new ArrayList<>();

        //Crea ogni carta strumento
        cards.add(new AlesatorePerLaminaDiRame());
        cards.add(new DiluentePerPastaSalda());
        cards.add(new Lathekin());
        cards.add(new Martelletto());
        cards.add(new PennelloPerEglomise());
        cards.add(new PennelloPerPastaSalda());
        cards.add(new PinzaSgrossatrice());
        cards.add(new RigaDiSughero());
        cards.add(new TaglierinaCircolare());
        cards.add(new TaglierinaManuale());
        cards.add(new TamponeDiamantato());
        cards.add(new TenagliaRotelle());

        return cards;
    }
}
