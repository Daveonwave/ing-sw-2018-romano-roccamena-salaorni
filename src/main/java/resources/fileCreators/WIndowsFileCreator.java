package resources.fileCreators;

import mvc.model.objects.*;
import mvc.model.objects.Window;
import mvc.model.objects.enums.WindowPattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WIndowsFileCreator {

    public static void main(String[] args) {

        List<Window> windows = new ArrayList<Window>();

        for(int i = 0; i < 24; i++){
            Window window = new Window(null, null, 0);
            windows.add(window);
        }

        for(int i = 0; i < windows.size(); i++){
            switch (i){
                case 0:
                    windows.get(i).setWindowPattern(WindowPattern.BELLESGUARD);
                    windows.get(i).setDifficulty(3);
                    Cell[][] cells = new Cell[4][5];

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 1);
                    cells[0][1] = new Cell(null, new ShadeRestriction(6), 1, 2);
                    cells[0][2] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(3), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(Color.BLUE), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new ShadeRestriction(5), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(6), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(2), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(4), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(1), 4, 4);
                    cells[3][4] = new Cell(null, new ColorRestriction(Color.GREEN), 4, 5);

                    windows.get(i).setCells(cells);

            }
        }

    }
}
