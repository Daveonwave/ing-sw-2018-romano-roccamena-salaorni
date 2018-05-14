package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.awt.Color;
import java.rmi.RemoteException;

public class AlesatorePerLaminaDiRame extends ToolCard{

    //Costruttori
    public AlesatorePerLaminaDiRame(String name, String description, int favorTokens, Color color) {
        super("alesatore per lamina di rame", "muovi un qualunque tuo dado piazzato ignorando le restrizione di valore, rispettando tutte le altre restrizioni", favorTokens, color);
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