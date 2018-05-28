package mvc.model.objects.toolcards;

import mvc.model.objects.*;

import java.rmi.RemoteException;

public class PennelloPerEglomise extends ToolCard {

    //Costruttori
    public PennelloPerEglomise() {
        super("pennello per eglomise", "muovi un qualunque tuo dado piazzato ignorando le restrizioni di colore, rispettando tutte le altre restrizioni", GameConstants.BLUE);

    }

    private void cardEffect(Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Cell origin = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell destination = player.getWindow().retrieveCell(input.getDestinationCell2());

        //Esegue il movimento
        player.getWindow().moveDie(origin, destination, false, false, false, true);

    }


    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getPlayer(), input);
    }
}
