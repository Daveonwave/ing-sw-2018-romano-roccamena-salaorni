package mvc.model.objects;

import java.io.Serializable;

/**
 * Restriction of a game cell to die place action
 */
public interface CellRestriction extends Serializable {
    //Restrizione di una cella di una finestra

    //Verifica uguaglianze
    /**
     * Assert equality f two restrictions
     * @param cellRestriction Cell restriction instance
     * @return
     */
    boolean sameCellRestriction(CellRestriction cellRestriction);

    //Verifica che un dado rispetti la restrizione
    /**
     * Assert if a die is compatible with the restriction
     * @param die Die instance
     * @return
     */
    boolean canPlaceDie(Die die);
}
