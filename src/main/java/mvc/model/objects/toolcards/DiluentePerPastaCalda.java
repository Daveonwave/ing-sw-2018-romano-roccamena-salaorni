package mvc.model.objects.toolcards;

import mvc.model.objects.*;

import java.rmi.RemoteException;

public class DiluentePerPastaCalda extends ToolCard {

    //Costruttori
    public DiluentePerPastaCalda() {
        super("diluente per pasta calda", "dopo aver scelto un dado, riponilo del sacco di dadi ed estrai un altro dado; scegli il valore del nuovo dado e piazzalo rispettando tutte le restrizioni", GameConstants.PURPLE);
    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();
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
}
