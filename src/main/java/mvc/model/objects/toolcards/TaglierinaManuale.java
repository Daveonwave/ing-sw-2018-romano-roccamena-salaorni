package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.ToolCardInput;

import java.awt.*;
import java.rmi.RemoteException;

public class TaglierinaManuale extends ToolCard {

    //Costruttori
    public TaglierinaManuale(String name, String description, int favorTokens, Color color) {
        super("taglierina manuale", "muovi fino a due dadi, dello stesso colore di un solo dado del tracciato dei round, rispettando tutte le restrizioni", favorTokens, color);
    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
    }
}
