package mvc.model.objects.toolcards;

import mvc.model.objects.*;

import java.rmi.RemoteException;

public class TenagliaRotelle extends ToolCard {

    //Costruttori
    public TenagliaRotelle() {
        super("tenaglia a rotelle", "dopo il tuo primo turno scegli subito un altro dado; salta il tuo secondo turno in questo round", GameConstants.RED);

    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {

    }
}
