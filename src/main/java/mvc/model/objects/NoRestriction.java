package mvc.model.objects;

import mvc.model.objects.Die;
import mvc.model.objects.CellRestriction;

public class NoRestriction extends CellRestriction {
    //Nessuna restrizione di cella

    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return true;
    }
}
