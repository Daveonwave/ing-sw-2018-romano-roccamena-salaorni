package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.rmi.RemoteException;

public class TamponeDiamantato extends ToolCard{

    //Costruttori
    public TamponeDiamantato() {
        super("tampone diamantato", "dopo aver scelto un dado giralo sulla faccia opposta", GameConstants.GREEN);

    }

    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());

        //Esegue effetto
        die.invertShade();

        //Aggiorna effetti
        player.getToolCardEffect().setChoosenDie(die);
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
