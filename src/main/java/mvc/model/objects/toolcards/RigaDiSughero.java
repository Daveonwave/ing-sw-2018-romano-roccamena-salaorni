package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class RigaDiSughero extends ToolCard {

    /**
     * Class constructor
     */
    public RigaDiSughero() {
        super("riga di sughero", "Dopo aver scelto un dado, piazzalo in una cella che non sia adiacente ad un altro, rispettando tutte le altre restrizioni", DieColor.YELLOW);
    }

    /**
     * Handle the effect of the specific tool card
     * @param player player that uses it
     * @throws RemoteException
     */
    private void cardEffect(Player player) throws RemoteException {
        //Controllo correttezza uso
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        //Esegue effetto carta
        player.getToolCardEffect().setIgnoreIsolatedRestriction(true);
    }

    //Usa carta strumento
    /**
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getTurnPlayer());
    }
    /**
     * Use this specific tool card in a single player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getPlayer());
    }
}

