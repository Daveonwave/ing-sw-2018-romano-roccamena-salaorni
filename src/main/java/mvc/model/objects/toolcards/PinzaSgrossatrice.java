package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class PinzaSgrossatrice extends ToolCard {

    /**
     * Class constructor
     */
    public PinzaSgrossatrice() {
        super("pinza sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di uno", DieColor.PURPLE);
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
        boolean increaseShade = input.getIncreaseShade();

        //Controllo correttezza uso
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        if ((die.getShade() == 1 && !input.getIncreaseShade()) || (die.getShade() == 6 && input.getIncreaseShade()))
            throw new MatchException("non puoi usare questa carta per quel dado");

        //Esegue effetto
        if (increaseShade)
            die.setShade(die.getShade()+1);
        else
            die.setShade(die.getShade()-1);

        //Assegna la scelta del dado
        player.getToolCardEffect().setChosenDie(die);
    }

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
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
