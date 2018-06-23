package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class TaglierinaManuale extends ToolCard {

    //Costruttori
    public TaglierinaManuale() {
        super("taglierina manuale", "muovi fino a due dadi, dello stesso colore di un solo dado del tracciato dei round, rispettando tutte le restrizioni", DieColor.BLUE);
    }

    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Window window = player.getWindow();
        Die die = input.getRoundTrackDie();
        Cell origin1 = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell origin2 = player.getWindow().retrieveCell(input.getOriginCell2());
        Cell destination1 = player.getWindow().retrieveCell(input.getDestinationCell1());
        Cell destination2 = player.getWindow().retrieveCell(input.getDestinationCell2());

        //Controlla correttezza uso
        die = match.getRoundTrack().retrieveDie(input.getRoundTrackRound(), die);

        if (!origin1.getDie().sameColor(die) || !origin2.getDie().sameColor(die))
            throw new MatchException("colore dadi non corrispondente");

        //Esegue gli scambi
        window.moveDie(origin1, destination1);
        try {
            window.moveDie(origin2, destination2);
        } catch (MatchException e) {
            window.moveDie(destination1, origin1);
            throw e;
        }
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
