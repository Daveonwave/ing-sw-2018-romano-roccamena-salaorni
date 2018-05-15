package mvc.model.objects.toolcards;

import mvc.model.objects.*;

import java.rmi.RemoteException;

public class AlesatorePerLaminaDiRame extends ToolCard{

    //Costruttori
    public AlesatorePerLaminaDiRame() {
        super("alesatore per lamina di rame", "muovi un qualunque tuo dado piazzato ignorando le restrizione di valore, rispettando tutte le altre restrizioni", 0, GameConstants.RED);
    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();
        Window window = player.getWindow();
        Cell origin = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell destination = player.getWindow().retrieveCell(input.getDestinationCell2());

        //Esegue il movimento
        window.moveDie(origin, destination, false, false, false, true);
    }
}