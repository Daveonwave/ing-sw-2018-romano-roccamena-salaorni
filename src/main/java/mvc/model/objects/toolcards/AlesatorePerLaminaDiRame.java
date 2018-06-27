package mvc.model.objects.toolcards;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class AlesatorePerLaminaDiRame extends ToolCard{

    //Costruttori
    public AlesatorePerLaminaDiRame() {
        super("alesatore per lamina di rame", "Muovi un qualunque tuo dado piazzato ignorando le restrizione di valore, rispettando tutte le altre restrizioni", DieColor.RED);
    }

    private void cardEffect(Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Window window = player.getWindow();
        Cell origin = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell destination = player.getWindow().retrieveCell(input.getDestinationCell1());

        //Esegue il movimento
        window.moveDie(origin, destination, false, false, false, true);

    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getPlayer(), input);
    }
}