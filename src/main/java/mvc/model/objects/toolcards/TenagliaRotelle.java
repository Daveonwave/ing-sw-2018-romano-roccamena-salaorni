package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.ToolCardInput;

import java.awt.*;
import java.rmi.RemoteException;

public class TenagliaRotelle extends ToolCard {

    //Costruttori
    public TenagliaRotelle(String name, String description, int favorTokens, Color color) {
        super("tenaglia a rotelle", "dopo il tuo primo turno scegli subito un altro dado; salta il tuo secondo turno in questo round", favorTokens, color);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
    }
}
