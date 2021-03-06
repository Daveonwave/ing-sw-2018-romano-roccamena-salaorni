package mvc.model.objects.toolcards;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class PennelloPerEglomise extends ToolCard {

    /**
     * Class constructo
     */
    public PennelloPerEglomise() {
        super("pennello per eglomise", "Muovi un qualunque tuo dado piazzato ignorando le restrizioni di colore, rispettando tutte le altre restrizioni", DieColor.BLUE);

    }

    /**
     * Handle the effect of the specific tool card
     * @param player player that uses it
     * @param input cell or die pre-selected
     * @throws RemoteException
     */
    private void cardEffect(Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Cell origin = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell destination = player.getWindow().retrieveCell(input.getDestinationCell1());

        //Esegue il movimento
        player.getWindow().moveDie(origin, destination, false, false, true, false);

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
