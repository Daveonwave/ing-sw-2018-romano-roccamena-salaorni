package mvc.model.objects;

import java.io.Serializable;

public abstract class CellRestriction implements Serializable {
    //Restrizione di una cella di una finestra

    //Verifica uguaglianze
    public abstract boolean sameCellRestriction(CellRestriction cellRestriction);

    //Verifica che un dado rispetti la restrizione
    public abstract boolean canPlaceDie(Die die);
}
