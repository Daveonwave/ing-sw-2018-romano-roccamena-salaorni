package mvc.model.objects;

import mvc.model.objects.Die;
import mvc.model.objects.CellRestriction;

public class NoRestriction extends CellRestriction {
    //Nessuna restrizione di cella

    //Verifica uguaglianza
    public boolean sameCellRestriction(CellRestriction cellRestriction) {
        if (!(cellRestriction instanceof NoRestriction))
            return false;

        NoRestriction converted = (NoRestriction) cellRestriction;

        return true;
    }


    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return true;
    }
}
