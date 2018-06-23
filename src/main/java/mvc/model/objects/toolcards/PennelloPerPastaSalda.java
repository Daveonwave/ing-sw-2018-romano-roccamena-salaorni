package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class PennelloPerPastaSalda extends ToolCard {

    //Costruttori
    public PennelloPerPastaSalda() {
        super("pennello per pasta salda", "dopo aver scelto un dado, tira nuovamente quel dado; se non puoi piazzarlo, riponilo nella riserva", DieColor.PURPLE);

    }

    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());

        //Controlla giocatore non ha gia piazzato questo turno
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        //Rilancia il dado
        die.roll();

        //Controlla se il dado è piazzabile
        if (player.getWindow().getValidCells(die).size() == 0) {
            player.setTurnDiePlaced(true);
            throw new MatchException("non puoi piu piazzare il dado");
        }

        //Aggiorna effetti
        player.getToolCardEffect().setChoosenDie(die);
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
