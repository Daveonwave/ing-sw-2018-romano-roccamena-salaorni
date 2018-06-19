package mvc.model.objects;

import java.io.Serializable;

public interface CellRestriction extends Serializable {
    //Restrizione di una cella di una finestra

    //Verifica uguaglianze
    boolean sameCellRestriction(CellRestriction cellRestriction);

    //Verifica che un dado rispetti la restrizione
    boolean canPlaceDie(Die die);
}
