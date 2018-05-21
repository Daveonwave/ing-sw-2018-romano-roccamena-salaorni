package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.awt.Color;
import java.rmi.RemoteException;

public class Lathekin extends ToolCard {

    //Costruttori
    public Lathekin() {
        super("lathekin", "muovi esattamente due dadi rispettando tutte le restrizioni", 0, GameConstants.YELLOW);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();
        Window window = player.getWindow();
        Cell origin1 = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell origin2 = player.getWindow().retrieveCell(input.getOriginCell2());
        Cell destination1 = player.getWindow().retrieveCell(input.getDestinationCell1());
        Cell destination2 = player.getWindow().retrieveCell(input.getDestinationCell2());

        //Esegue gli scambi
        window.moveDie(origin1, destination1);
        try {
            window.moveDie(origin2, destination2);
        } catch (MatchException e) {
            window.moveDie(destination1, origin1);
            throw e;
        }
    }
}