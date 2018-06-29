package mvc.model.objects;

import mvc.exceptions.MatchException;
import mvc.model.objects.enums.DieColor;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class CellTest {

    Cell cell = new Cell(new Die(DieColor.BLUE, 2), new ColorRestriction(DieColor.BLUE), 2, 3);

    //Test dado uguale
    @Test
    public void sameDieTest(){
        Cell cellNull = null;
        assertFalse(cell.sameCell(cellNull));

        cellNull = new Cell(null, null, 3, 1);
        cellNull.setDie(new Die(DieColor.BLUE, 2));
        cellNull.setCellRestriction(null);
        assertFalse(cell.sameCell(cellNull));

        Cell cellDiff = new Cell(new Die(DieColor.YELLOW, 3), new ShadeRestriction(3), 2, 3);
        assertNotEquals(cell, cell.sameCell(cellDiff));
        assertNotEquals(cell.getDie(), cellDiff.getDie());
        assertNotEquals(cell.getCellRestriction(), cellDiff.getCellRestriction());
        assertEquals(cell.getColumn(), cellDiff.getColumn());
        assertEquals(cell.getRow(), cellDiff.getRow());
    }

    //Test stessa posizione
    @Test
    public void samePositionTest(){
        Cell otherCell = null;
        assertFalse(cell.samePosition(otherCell));

        otherCell= new Cell(null, null, 2, 4);
        assertFalse(cell.samePosition(otherCell));

        otherCell.setColumn(3);
        assertTrue(cell.samePosition(otherCell));
    }

    //Test rispetto bordi
    @Test
    public void BorderTest(){
        assertNotEquals(true, cell.isNorthBorder());
        assertNotEquals(true, cell.isSouthBorder());
        assertNotEquals(true, cell.isEastBorder());
        assertNotEquals(true, cell.isWestBorder());
        assertNotEquals(true, cell.isInBorder());

        Cell borderCell = new Cell(null, null, 0, 0);
        assertTrue(borderCell.isWestBorder());
        assertTrue(borderCell.isNorthBorder());
        assertTrue(borderCell.isInBorder());
    }

    //Test nessuna restrizione di cell
    @Test
    public void noCellRestrictionTest(){
        assertTrue(cell.noCellRestriction(new Die(DieColor.BLUE, 1), false, false));
        assertTrue(cell.noCellRestriction(new Die(DieColor.BLUE, 1), false, true));
        assertTrue(cell.noCellRestriction(new Die(DieColor.BLUE, 1), true, false));
    }

    //Test di piazzamento
    @Test (expected = MatchException.class)
    public void placeDieTest() throws RemoteException {
        Die die = null;
        cell.placeDie(die, false, false);
        assertNotEquals(cell.getDie(), die);

        die = new Die(DieColor.BLUE, 3);
        cell.placeDie(die);
        assertEquals(cell.getDie(), die);

        Die dieDiff = new Die(DieColor.RED, 5);
        cell.placeDie(dieDiff, false, true);
        assertNotEquals(cell.getDie(), dieDiff);
    }

    //Test di mossa dado
    @Test (expected = MatchException.class)
    public void moveDiceTest() throws RemoteException{
        Cell movedCell = new Cell(null, new ColorRestriction(DieColor.RED), 1, 1);
        cell.moveDie(movedCell, false, false);
        assertNotEquals(null, cell.getDie());

        Cell movedCell2 = new Cell(new Die(DieColor.RED, 4), new ColorRestriction(DieColor.RED), 1, 1);
        cell.moveDie(movedCell2, false, false);
        assertNotEquals(null, cell.getDie());

        cell.moveDie(movedCell, true, true);
        assertEquals(null, cell.getDie());
    }

    //Test di scambio dado
    @Test (expected = MatchException.class)
    public void swapDiceTest() throws RemoteException{
        Die swapDie = new Die(DieColor.RED, 4);
        Cell swapCell= new Cell(swapDie, new ColorRestriction(DieColor.RED), 4, 3);
        cell.swapDice(swapCell, false, false);
        assertNotEquals(cell.getDie(), swapDie);

        cell.swapDice(swapCell, true, true);
        assertEquals(cell.getDie(),swapDie);
    }
}