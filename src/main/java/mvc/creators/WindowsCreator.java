package mvc.creators;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.util.ArrayList;
import java.util.List;

public class WindowsCreator {
    //Creatore di finestre di gioco

    public static List<Window> createWindows() {
        List<Window> windows = new ArrayList<Window>(24);

        //Crea finestre
        for (int i = 0; i < 23; i++) {
            Window window = new Window(null, 0, "");
            windows.add(window);
        }

        //Crea celle per ogni finestra
        for(int i = 0; i < windows.size(); i++){
            Cell[][] cells = new Cell[4][5];

            switch (i) {
                case 0:
                    windows.get(i).setName("bellesguard");
                    windows.get(i).setDifficulty(3);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 0);
                    cells[0][1] = new Cell(null, new ShadeRestriction(6), 0, 1);
                    cells[0][2] = new Cell(null, new NoRestriction(), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(3), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new ShadeRestriction(5), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(6), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(2), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(4), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(1), 3, 3);
                    cells[3][4] = new Cell(null, new ColorRestriction(DieColor.GREEN), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 1:
                    windows.get(i).setName("fractal drops");
                    windows.get(i).setDifficulty(3);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new ShadeRestriction(4), 0, 1);
                    cells[0][2] = new Cell(null, new NoRestriction(), 0, 2);
                    cells[0][3] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(6), 0, 4);
                    cells[1][0] = new Cell(null, new ColorRestriction(DieColor.RED), 1, 0);
                    cells[1][1] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(2), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 3);
                    cells[2][4] = new Cell(null, new ShadeRestriction(1), 2, 4);
                    cells[3][0] = new Cell(null, new ColorRestriction(DieColor.BLUE), 3, 0);
                    cells[3][1] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 2:
                    windows.get(i).setName("luz celestial");
                    windows.get(i).setDifficulty(3);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ColorRestriction(DieColor.RED), 0, 2);
                    cells[0][3] = new Cell(null, new ShadeRestriction(5), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 1, 1);
                    cells[1][2] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[1][3] = new Cell(null, new ColorRestriction(DieColor.GREEN), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(3), 1, 4);
                    cells[2][0] = new Cell(null, new ShadeRestriction(6), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.BLUE), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(2), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 3:
                    windows.get(i).setName("sun catcher");
                    windows.get(i).setDifficulty(3);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(2), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 1, 1);
                    cells[1][2] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[1][3] = new Cell(null, new ColorRestriction(DieColor.RED), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(5), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new ColorRestriction(DieColor.GREEN), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(3), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 4:
                    windows.get(i).setName("aurora sagradis");
                    windows.get(i).setDifficulty(4);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(DieColor.RED), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 4);
                    cells[1][0] = new Cell(null, new ShadeRestriction(4), 1, 0);
                    cells[1][1] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(3), 1, 2);
                    cells[1][3] = new Cell(null, new ColorRestriction(DieColor.GREEN), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new ShadeRestriction(1), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(5), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(6), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 5:
                    windows.get(i).setName("chromatic splendor");
                    windows.get(i).setDifficulty(4);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ColorRestriction(DieColor.GREEN), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new ShadeRestriction(2), 1, 0);
                    cells[1][1] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(5), 1, 2);
                    cells[1][3] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(1), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(3), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(1), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(6), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(4), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 6:
                    windows.get(i).setName("kaleidoscopic dream");
                    windows.get(i).setDifficulty(4);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 1);
                    cells[0][2] = new Cell(null, new NoRestriction(), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(1), 0, 4);
                    cells[1][0] = new Cell(null, new ColorRestriction(DieColor.GREEN), 1, 0);
                    cells[1][1] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(5), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(4), 1, 4);
                    cells[2][0] = new Cell(null, new ShadeRestriction(3), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 2);
                    cells[2][3] = new Cell(null, new NoRestriction(), 2, 3);
                    cells[2][4] = new Cell(null, new ColorRestriction(DieColor.GREEN), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(2), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new ColorRestriction(DieColor.BLUE), 3, 3);
                    cells[3][4] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 7:
                    windows.get(i).setName("via lux");
                    windows.get(i).setDifficulty(4);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(6), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(1), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(5), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 1, 4);
                    cells[2][0] = new Cell(null, new ShadeRestriction(3), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 1);
                    cells[2][2] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(4), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 3, 3);
                    cells[3][4] = new Cell(null, new ColorRestriction(DieColor.RED), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 8:
                    windows.get(i).setName("aurorae magnificus");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(5), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.GREEN), 0, 1);
                    cells[0][2] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 2);
                    cells[0][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(2), 0, 4);
                    cells[1][0] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 0);
                    cells[1][1] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[1][2] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 1, 4);
                    cells[2][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(6), 2, 2);
                    cells[2][3] = new Cell(null, new NoRestriction(), 2, 3);
                    cells[2][4] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(1), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new ColorRestriction(DieColor.GREEN), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(4), 3, 4);

                    windows.get(i).setCells(cells);
                    break;

                case 9:
                    windows.get(i).setName("battlo");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(6), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(5), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 2);
                    cells[1][3] = new Cell(null, new ShadeRestriction(4), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new ShadeRestriction(3), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.GREEN), 2, 1);
                    cells[2][2] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 3);
                    cells[2][4] = new Cell(null, new ShadeRestriction(2), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(1), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(4), 3, 1);
                    cells[3][2] = new Cell(null, new ColorRestriction(DieColor.RED), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(5), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(3), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 10:
                    windows.get(i).setName("comitas");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(2), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(6), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 1, 1);
                    cells[1][2] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[1][3] = new Cell(null, new ShadeRestriction(5), 1, 3);
                    cells[1][4] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 3);
                    cells[2][4] = new Cell(null, new ShadeRestriction(5), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(1), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(2), 3, 1);
                    cells[3][2] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;

                case 11:
                    windows.get(i).setName("firelight");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(3), 0, 0);
                    cells[0][1] = new Cell(null, new ShadeRestriction(4), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(1), 0, 2);
                    cells[0][3] = new Cell(null, new ShadeRestriction(5), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(6), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(2), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 3);
                    cells[2][4] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(5), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 2);
                    cells[3][3] = new Cell(null, new ColorRestriction(DieColor.RED), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(6), 3, 4);

                    windows.get(i).setCells(cells);
                    break;

                case 12:
                    windows.get(i).setName("firmitas");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 0, 0);
                    cells[0][1] = new Cell(null, new ShadeRestriction(6), 0, 1);
                    cells[0][2] = new Cell(null, new NoRestriction(), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(3), 0, 4);
                    cells[1][0] = new Cell(null, new ShadeRestriction(5), 1, 0);
                    cells[1][1] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(3), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new ShadeRestriction(2), 2, 1);
                    cells[2][2] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(1), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(1), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(5), 3, 2);
                    cells[3][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(4), 3, 4);

                    windows.get(i).setCells(cells);
                    break;

                case 13:
                    windows.get(i).setName("fulgor del cielo");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 1);
                    cells[0][2] = new Cell(null, new ColorRestriction(DieColor.RED), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(5), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 4);
                    cells[2][0] = new Cell(null, new ColorRestriction(DieColor.BLUE), 2, 0);
                    cells[2][1] = new Cell(null, new ShadeRestriction(2), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 3);
                    cells[2][4] = new Cell(null, new ShadeRestriction(5), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(6), 3, 0);
                    cells[3][1] = new Cell(null, new ColorRestriction(DieColor.RED), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(3), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(1), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 14:
                    windows.get(i).setName("gravitas");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(1), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(3), 0, 2);
                    cells[0][3] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(2), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new ShadeRestriction(6), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(4), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new ColorRestriction(DieColor.BLUE), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(5), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(2), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(1), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 15:
                    windows.get(i).setName("industria");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(1), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.RED), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(3), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(6), 0, 4);
                    cells[1][0] = new Cell(null, new ShadeRestriction(5), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.RED), 1, 2);
                    cells[1][3] = new Cell(null, new ShadeRestriction(2), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(5), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 3);
                    cells[2][4] = new Cell(null, new ShadeRestriction(1), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 3, 3);
                    cells[3][4] = new Cell(null, new ColorRestriction(DieColor.RED), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 16:
                    windows.get(i).setName("lux astram");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new ShadeRestriction(1), 0, 1);
                    cells[0][2] = new Cell(null, new ColorRestriction(DieColor.GREEN), 0, 2);
                    cells[0][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(4), 0, 4);
                    cells[1][0] = new Cell(null, new ShadeRestriction(6), 1, 0);
                    cells[1][1] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(2), 1, 2);
                    cells[1][3] = new Cell(null, new ShadeRestriction(5), 1, 3);
                    cells[1][4] = new Cell(null, new ColorRestriction(DieColor.GREEN), 1, 4);
                    cells[2][0] = new Cell(null, new ShadeRestriction(1), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.GREEN), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(5), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(3), 2, 3);
                    cells[2][4] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 17:
                    windows.get(i).setName("ripples of light");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new NoRestriction(), 0, 2);
                    cells[0][3] = new Cell(null, new ColorRestriction(DieColor.RED), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(5), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 2);
                    cells[1][3] = new Cell(null, new ShadeRestriction(4), 1, 3);
                    cells[1][4] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(3), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 3);
                    cells[2][4] = new Cell(null, new ShadeRestriction(6), 2, 4);
                    cells[3][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(2), 3, 1);
                    cells[3][2] = new Cell(null, new ColorRestriction(DieColor.GREEN), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(1), 3, 3);
                    cells[3][4] = new Cell(null, new ColorRestriction(DieColor.RED), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 18:
                    windows.get(i).setName("shadow thief");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(6), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 0, 1);
                    cells[0][2] = new Cell(null, new NoRestriction(), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(5), 0, 4);
                    cells[1][0] = new Cell(null, new ShadeRestriction(5), 1, 0);
                    cells[1][1] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 0);
                    cells[2][1] = new Cell(null, new ShadeRestriction(6), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 0);
                    cells[3][1] = new Cell(null, new ColorRestriction(DieColor.RED), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(5), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(4), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(3), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 19:
                    windows.get(i).setName("virtus");
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(4), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(2), 0, 2);
                    cells[0][3] = new Cell(null, new ShadeRestriction(5), 0, 3);
                    cells[0][4] = new Cell(null, new ColorRestriction(DieColor.GREEN), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(6), 1, 2);
                    cells[1][3] = new Cell(null, new ColorRestriction(DieColor.GREEN), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new ShadeRestriction(3), 2, 1);
                    cells[2][2] = new Cell(null, new ColorRestriction(DieColor.GREEN), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(4), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new ShadeRestriction(5), 3, 0);
                    cells[3][1] = new Cell(null, new ColorRestriction(DieColor.GREEN), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(1), 3, 2);
                    cells[3][3] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 20:
                    windows.get(i).setName("symphony of light");
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(2), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(5), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(1), 0, 4);
                    cells[1][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(6), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 2);
                    cells[1][3] = new Cell(null, new ShadeRestriction(2), 1, 3);
                    cells[1][4] = new Cell(null, new ColorRestriction(DieColor.RED), 1, 4);
                    cells[2][0] = new Cell(null, new NoRestriction(), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(4), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.GREEN), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(3), 3, 1);
                    cells[3][2] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(5), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 21:
                    windows.get(i).setName("water of life");
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(6), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 0, 1);
                    cells[0][2] = new Cell(null, new NoRestriction(), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(1), 0, 4);
                    cells[1][0] = new Cell(null, new NoRestriction(), 1, 0);
                    cells[1][1] = new Cell(null, new ShadeRestriction(5), 1, 1);
                    cells[1][2] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[2][0] = new Cell(null, new ShadeRestriction(4), 2, 0);
                    cells[2][1] = new Cell(null, new ColorRestriction(DieColor.RED), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(2), 2, 2);
                    cells[2][3] = new Cell(null, new ColorRestriction(DieColor.BLUE), 2, 3);
                    cells[2][4] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[3][0] = new Cell(null, new ColorRestriction(DieColor.GREEN), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(6), 3, 1);
                    cells[3][2] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 3, 3);
                    cells[3][4] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 22:
                    windows.get(i).setName("lux mundi");
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 0, 0);
                    cells[0][1] = new Cell(null, new NoRestriction(), 0, 1);
                    cells[0][2] = new Cell(null, new ShadeRestriction(1), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new NoRestriction(), 0, 4);
                    cells[1][0] = new Cell(null, new ShadeRestriction(1), 1, 0);
                    cells[1][1] = new Cell(null, new ColorRestriction(DieColor.GREEN), 1, 1);
                    cells[1][2] = new Cell(null, new ShadeRestriction(3), 1, 2);
                    cells[1][3] = new Cell(null, new ColorRestriction(DieColor.BLUE), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 1, 4);
                    cells[2][0] = new Cell(null, new ColorRestriction(DieColor.BLUE), 2, 0);
                    cells[2][1] = new Cell(null, new ShadeRestriction(5), 2, 1);
                    cells[2][2] = new Cell(null, new ShadeRestriction(4), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(6), 2, 3);
                    cells[2][4] = new Cell(null, new ColorRestriction(DieColor.GREEN), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new ColorRestriction(DieColor.BLUE), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(5), 3, 2);
                    cells[3][3] = new Cell(null, new ColorRestriction(DieColor.GREEN), 3, 3);
                    cells[3][4] = new Cell(null, new NoRestriction(), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
                case 23:
                    windows.get(i).setName("sun's glory");
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(1), 0, 0);
                    cells[0][1] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 0, 1);
                    cells[0][2] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 0, 2);
                    cells[0][3] = new Cell(null, new NoRestriction(), 0, 3);
                    cells[0][4] = new Cell(null, new ShadeRestriction(4), 0, 4);
                    cells[1][0] = new Cell(null, new ColorRestriction(DieColor.PURPLE), 1, 0);
                    cells[1][1] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 1, 1);
                    cells[1][2] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[1][3] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[1][4] = new Cell(null, new ShadeRestriction(6), 1, 4);
                    cells[2][0] = new Cell(null, new ColorRestriction(DieColor.YELLOW), 2, 0);
                    cells[2][1] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[2][2] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[2][3] = new Cell(null, new ShadeRestriction(5), 2, 3);
                    cells[2][4] = new Cell(null, new ShadeRestriction(3), 2, 4);
                    cells[3][0] = new Cell(null, new NoRestriction(), 3, 0);
                    cells[3][1] = new Cell(null, new ShadeRestriction(5), 3, 1);
                    cells[3][2] = new Cell(null, new ShadeRestriction(4), 3, 2);
                    cells[3][3] = new Cell(null, new ShadeRestriction(2), 3, 3);
                    cells[3][4] = new Cell(null, new ShadeRestriction(1), 3, 4);

                    windows.get(i).setCells(cells);
                    break;
            }
        }

        return windows;
    }

}
