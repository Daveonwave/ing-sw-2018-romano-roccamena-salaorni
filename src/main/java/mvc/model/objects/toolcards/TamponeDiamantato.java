package mvc.model.objects.toolcards;

import mvc.model.objects.*;

import java.rmi.RemoteException;

public class TamponeDiamantato extends ToolCard{

    //Costruttori
    public TamponeDiamantato() {
        super("tampone diamantato", "dopo aver scelto un dado giralo sulla faccia opposta", GameConstants.GREEN);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());

        //Esegue effetto
        die.invertShade();
    }
}
