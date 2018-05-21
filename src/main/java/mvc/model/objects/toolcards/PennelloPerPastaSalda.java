package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.rmi.RemoteException;

public class PennelloPerPastaSalda extends ToolCard {

    //Costruttori
    public PennelloPerPastaSalda() {
        super("pennello per pasta salda", "dopo aver scelto un dado, tira nuovamente quel dado; se non puoi piazzarlo, riponilo nella riserva", 0, GameConstants.PURPLE);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());

        //Controlla giocatore non ha gia piazzato questo turno
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        //Rilancia il dado
        die.roll();

        //Controlla se il dado è piazzabile
        if (player.getWindow().getValidCells(die).size() == 0) {
            player.setTurnDiePlaced(true);
            throw new MatchException("dado riposto in riserva");
        }

        //Aggiorna effetti
        player.getToolCardEffect().setChoosenDie(die);
    }
}
