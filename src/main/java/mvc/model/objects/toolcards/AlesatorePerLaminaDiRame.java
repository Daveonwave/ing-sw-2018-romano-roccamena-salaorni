package mvc.model.objects.toolcards;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class AlesatorePerLaminaDiRame extends ToolCard{

    /**
     * Class constructor
     */
    public AlesatorePerLaminaDiRame() {
        super("alesatore per lamina di rame", "Muovi un qualunque tuo dado piazzato ignorando le restrizione di valore, rispettando tutte le altre restrizioni", DieColor.RED);
    }

    /**
     * Reproduce the effect of the specific tool card
     * @param player player that uses it
     * @param input die or cell pre-selected
     * @throws RemoteException
     */
    private void cardEffect(Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Window window = player.getWindow();
        Cell origin = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell destination = player.getWindow().retrieveCell(input.getDestinationCell1());

        //Esegue il movimento
        window.moveDie(origin, destination, false, false, false, true);

    }

    //Usa carta strumento
    /**
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getTurnPlayer(), input);
    }
    /**
     * Use this specific tool card in a single player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getPlayer(), input);
    }
}