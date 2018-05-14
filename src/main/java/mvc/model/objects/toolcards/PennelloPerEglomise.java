package mvc.model.objects.toolcards;

import mvc.model.objects.*;

import java.awt.*;
import java.rmi.RemoteException;

public class PennelloPerEglomise extends ToolCard {

    //Costruttori
    public PennelloPerEglomise(String name, String description, int favorTokens, Color color) {
        super("pennello per eglomise", "muovi un qualunque tuo dado piazzato ignorando le restrizioni di colore, rispettando tutte le altre restrizioni", favorTokens, color);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();
        Cell origin = match.getTurnPlayer().getWindow().retrieveCell(input.getOriginCell1());
        Cell destination = match.getTurnPlayer().getWindow().retrieveCell(input.getDestinationCell2());

        //Esegue il movimento
        player.getWindow().moveDie(origin, destination, false, false, false, true);
    }
}
