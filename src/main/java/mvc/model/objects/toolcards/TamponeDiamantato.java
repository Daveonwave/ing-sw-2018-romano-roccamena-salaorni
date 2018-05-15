package mvc.model.objects.toolcards;

import mvc.model.objects.GameConstants;
import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.ToolCardInput;

import java.awt.*;
import java.rmi.RemoteException;

public class TamponeDiamantato extends ToolCard{

    //Costruttori
    public TamponeDiamantato() {
        super("tampone diamantato", "dopo aver scelto un dado giralo sulla faccia opposta", 0, GameConstants.GREEN);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
    }
}
