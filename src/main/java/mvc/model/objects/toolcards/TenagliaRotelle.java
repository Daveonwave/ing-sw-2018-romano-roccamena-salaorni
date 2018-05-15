package mvc.model.objects.toolcards;

import mvc.model.objects.GameConstants;
import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.ToolCardInput;

import java.awt.*;
import java.rmi.RemoteException;

public class TenagliaRotelle extends ToolCard {

    //Costruttori
    public TenagliaRotelle() {
        super("tenaglia a rotelle", "dopo il tuo primo turno scegli subito un altro dado; salta il tuo secondo turno in questo round", 0, GameConstants.RED);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
    }
}
