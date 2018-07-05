package mvc.model.objects.toolcards;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class TamponeDiamantato extends ToolCard{

    /**
     * Class constructor
     */
    public TamponeDiamantato() {
        super("tampone diamantato", "Dopo aver scelto un dado giralo sulla faccia opposta", DieColor.GREEN);

    }

    /**
     * Handle the effect of the specific tool card
     * @param match object match
     * @param player player that uses it
     * @param input cell or die pre-selected
     * @throws RemoteException
     */
    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChosenDie());

        //Esegue effetto
        die.invertShade();

        //Aggiorna effetti
        player.getToolCardEffect().setChosenDie(die);
    }

    //Usa carta strumento
    /**
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    /**
     * Use this specific tool card in a single player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
