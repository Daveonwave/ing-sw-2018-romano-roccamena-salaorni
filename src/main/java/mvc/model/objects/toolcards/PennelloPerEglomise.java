package mvc.model.objects.toolcards;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class PennelloPerEglomise extends ToolCard {

    //Costruttori
    public PennelloPerEglomise() {
        super("Pennello per Eglomise", "Muovi un qualunque tuo dado piazzato ignorando le restrizioni di colore, rispettando tutte le altre restrizioni", DieColor.BLUE);

    }

    private void cardEffect(Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Cell origin = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell destination = player.getWindow().retrieveCell(input.getDestinationCell1());

        //Esegue il movimento
        player.getWindow().moveDie(origin, destination, false, false, true, false);

    }


    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getPlayer(), input);
    }
}
