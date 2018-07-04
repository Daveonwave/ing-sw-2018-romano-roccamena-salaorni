package mvc.model.objects;

/**
 * No cell restriction implementation
 */
public class NoRestriction implements CellRestriction {
    //Nessuna restrizione di cella

    //Verifica uguaglianza
    public boolean sameCellRestriction(CellRestriction cellRestriction) {
        if (!(cellRestriction instanceof NoRestriction))
            return false;

        return true;
    }


    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return true;
    }

    @Override
    public String toString() {
        return " ";
    }
}
