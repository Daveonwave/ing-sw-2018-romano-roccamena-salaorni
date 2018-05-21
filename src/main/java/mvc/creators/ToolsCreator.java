package mvc.creators;

import mvc.model.objects.ToolCard;
import mvc.model.objects.toolcards.*;

import java.util.ArrayList;
import java.util.List;

public class ToolsCreator {
    //Creatore carte strumento gioco

    public static List<ToolCard> createToolCards() {
        List<ToolCard> toolCards = new ArrayList<ToolCard>();

        //Crea ogni carta strumento
        toolCards.add(new AlesatorePerLaminaDiRame());
        toolCards.add(new DiluentePerPastaCalda());
        toolCards.add(new Lathekin());
        toolCards.add(new Martelletto());
        toolCards.add(new PennelloPerEglomise());
        toolCards.add(new PennelloPerPastaSalda());
        toolCards.add(new PinzaSgrossatrice());
        toolCards.add(new RigaDiSughero());
        toolCards.add(new TaglierinaCircolare());
        toolCards.add(new TaglierinaManuale());
        toolCards.add(new TamponeDiamantato());
        toolCards.add(new TenagliaRotelle());

        return toolCards;
    }
}
