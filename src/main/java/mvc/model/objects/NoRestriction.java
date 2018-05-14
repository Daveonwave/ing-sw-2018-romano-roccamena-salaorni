package mvc.model.objects;

import mvc.model.objects.Die;
import mvc.model.objects.CellRestriction;

public class NoRestriction extends CellRestriction {
    //Nessuna restrizione di cella

    //Verifica uguaglianza
    public boolean sameCellRestriction(CellRestriction cellRestriction) {
        NoRestriction converted = (NoRestriction) cellRestriction;
        if (converted == null)
            return false;

        return true;
    }


    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return true;
    }
}
