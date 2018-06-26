package mvc.model.objects.toolcards;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class DiluentePerPastaSalda extends ToolCard {

    //Costruttori
    public DiluentePerPastaSalda() {
        super("Diluente per Pasta Salda", "Dopo aver scelto un dado, riponilo del sacco di dadi ed estrai un altro dado; scegli il valore del nuovo dado e piazzalo rispettando tutte le restrizioni", DieColor.PURPLE);
    }

    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());

        //Toglie il dado e lo rimette nel sacco
        match.getMatchDice().getDraftPool().remove(die);
        match.getMatchDice().getDiceBag().add(die);

        //Estrae nuovo dado, seleziona valore scelto e viene impostato come dado scelto
        die = match.getMatchDice().extractDieFromBag();
        die.setShade(input.getChoosenShade());
        match.getMatchDice().getDraftPool().add(die);

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
