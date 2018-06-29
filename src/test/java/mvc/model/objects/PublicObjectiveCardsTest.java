package mvc.model.objects;

import mvc.model.objects.enums.DieColor;
import mvc.model.objects.publicobjectivescards.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PublicObjectiveCardsTest {
    Cell[][] cells = new Cell[4][5];

    @Test
    public void getPointsTest() {
        cells[0][0] = new Cell(new Die(DieColor.BLUE,2), new ColorRestriction(DieColor.BLUE), 0, 0);
        cells[0][1] = new Cell(new Die(DieColor.GREEN,6), new ShadeRestriction(6), 0, 1);
        cells[0][2] = new Cell(new Die(DieColor.PURPLE,5), new NoRestriction(), 0, 2);
        cells[0][3] = new Cell(new Die(DieColor.RED, 4), new NoRestriction(), 0, 3);
        cells[0][4] = new Cell(new Die(DieColor.YELLOW,1), new ColorRestriction(DieColor.YELLOW), 0, 4);
        cells[1][0] = new Cell(new Die(DieColor.GREEN,4), new NoRestriction(), 1, 0);
        cells[1][1] = new Cell(null, new ShadeRestriction(3), 1, 1);
        cells[1][2] = new Cell(new Die(DieColor.BLUE,4), new ColorRestriction(DieColor.BLUE), 1, 2);
        cells[1][3] = new Cell(new Die(DieColor.PURPLE,3), new NoRestriction(), 1, 3);
        cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
        cells[2][0] = new Cell(new Die(DieColor.PURPLE,3), new NoRestriction(), 2, 0);
        cells[2][1] = new Cell(new Die(DieColor.GREEN,5), new ShadeRestriction(5), 2, 1);
        cells[2][2] = new Cell(new Die(DieColor.YELLOW,6), new ShadeRestriction(6), 2, 2);
        cells[2][3] = new Cell(null, new ShadeRestriction(2), 2, 3);
        cells[2][4] = new Cell(new Die(DieColor.PURPLE,1), new NoRestriction(), 2, 4);
        cells[3][0] = new Cell(new Die(DieColor.YELLOW,5), new NoRestriction(), 3, 0);
        cells[3][1] = new Cell(new Die(DieColor.BLUE,4), new ShadeRestriction(4), 3, 1);
        cells[3][2] = new Cell(new Die(DieColor.RED,2), new NoRestriction(), 3, 2);
        cells[3][3] = new Cell(new Die(DieColor.PURPLE,1), new ShadeRestriction(1), 3, 3);
        cells[3][4] = new Cell(new Die(DieColor.GREEN,6), new ColorRestriction(DieColor.GREEN), 3, 4);
        Window window = new Window(cells,3,"bellesguard");
        assertEquals(9,new ColorDiagonals().getPoints(window));
        assertEquals(8, new ColorVariety().getPoints(window));
        assertEquals(10, new ShadeVariety().getPoints(window));
        assertEquals(4, new LightShades().getPoints(window));
        assertEquals(4,new MediumShades().getPoints(window));
        assertEquals(6,new DeepShades().getPoints(window));
        assertEquals(10, new ColumnColorVariety().getPoints(window));
        assertEquals(8, new ColumnShadeVariety().getPoints(window));
        assertEquals(12, new RowColorVariety().getPoints(window));
        assertEquals(10,new RowShadeVariety().getPoints(window));
    }
}