package resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.FileHandler;
import mvc.model.objects.*;
import mvc.model.objects.Window;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResourceRetriever {
    //Ottiene gli oggetti da file

    //Costruttori
    public ResourceRetriever() {}

    //Crea gli oggetti di una partita
    public List<Window> createWindows() {
        List<Window> windows = new ArrayList<Window>(24);

        //Crea finestre
        for (int i = 0; i < 23; i++) {
            Window window = new Window(null, 0);
            windows.add(window);
        }

        //Crea celle per ogni finestra
        for(int i = 0; i < windows.size(); i++){
            Cell[][] cells = new Cell[4][5];

            switch (i) {
                case 0:
                    windows.get(i).setDifficulty(3);

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
                    break;
                case 1:
                    windows.get(i).setDifficulty(3);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new ShadeRestriction(4), 1, 2);
                    cells[0][2] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[0][3] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(6), 1, 5);
                    cells[1][0] = new Cell(null, new ColorRestriction(Color.RED), 2, 1);
                    cells[1][1] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(2), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new ColorRestriction(Color.RED), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 3, 4);
                    cells[2][4] = new Cell(null, new ShadeRestriction(1), 3, 5);
                    cells[3][0] = new Cell(null, new ColorRestriction(Color.BLUE), 4, 1);
                    cells[3][1] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new NoRestriction(), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 2:
                    windows.get(i).setDifficulty(3);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ColorRestriction(Color.RED), 1, 3);
                    cells[0][3] = new Cell(null, new ShadeRestriction(5), 1, 4);
                    cells[0][4] = new Cell(null, new NoRestriction(), 1, 5);
                    cells[1][0] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 2, 2);
                    cells[1][2] = new Cell(null, new NoRestriction(), 2, 3);
                    cells[1][3] = new Cell(null, new ColorRestriction(Color.GREEN), 2, 4);
                    cells[1][4] = new Cell(null, new ShadeRestriction(3), 2, 5);
                    cells[2][0] = new Cell(null, new ShadeRestriction(6), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.BLUE), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(2), 4, 3);
                    cells[3][3] = new Cell(null, new NoRestriction(), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 3:
                    windows.get(i).setDifficulty(3);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(2), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 2, 2);
                    cells[1][2] = new Cell(null, new NoRestriction(), 2, 3);
                    cells[1][3] = new Cell(null, new ColorRestriction(Color.RED), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(5), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new ColorRestriction(Color.GREEN), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(3), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new NoRestriction(), 4, 4);
                    cells[3][4] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 4:
                    windows.get(i).setDifficulty(4);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(Color.RED), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 5);
                    cells[1][0] = new Cell(null, new ShadeRestriction(4), 2, 1);
                    cells[1][1] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(3), 2, 3);
                    cells[1][3] = new Cell(null, new ColorRestriction(Color.GREEN), 2, 4);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new ShadeRestriction(1), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(5), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new NoRestriction(), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(6), 4, 3);
                    cells[3][3] = new Cell(null, new NoRestriction(), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 5:
                    windows.get(i).setDifficulty(4);

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
                    break;
                case 6:
                    windows.get(i).setDifficulty(4);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 2);
                    cells[0][2] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(1), 1, 5);
                    cells[1][0] = new Cell(null, new ColorRestriction(Color.GREEN), 2, 1);
                    cells[1][1] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(5), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new ShadeRestriction(4), 2, 5);
                    cells[2][0] = new Cell(null, new ShadeRestriction(3), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new ColorRestriction(Color.RED), 3, 3);
                    cells[2][3] = new Cell(null, new NoRestriction(), 3, 4);
                    cells[2][4] = new Cell(null, new ColorRestriction(Color.GREEN), 3, 5);
                    cells[3][0] = new Cell(null, new ShadeRestriction(2), 4, 1);
                    cells[3][1] = new Cell(null, new NoRestriction(), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new ColorRestriction(Color.BLUE), 4, 4);
                    cells[3][4] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 7:
                    windows.get(i).setDifficulty(4);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(6), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new NoRestriction(), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(1), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(5), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 2, 5);
                    cells[2][0] = new Cell(null, new ShadeRestriction(3), 3, 1);
                    cells[2][1] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 2);
                    cells[2][2] = new Cell(null, new ColorRestriction(Color.RED), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new NoRestriction(), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(4), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 4, 4);
                    cells[3][4] = new Cell(null, new ColorRestriction(Color.RED), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 8:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(5), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(Color.GREEN), 1, 2);
                    cells[0][2] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 3);
                    cells[0][3] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(2), 1, 5);
                    cells[1][0] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 1);
                    cells[1][1] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[1][2] = new Cell(null, new NoRestriction(), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new ColorRestriction(Color.YELLOW), 2, 5);
                    cells[2][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(6), 3, 3);
                    cells[2][3] = new Cell(null, new NoRestriction(), 3, 4);
                    cells[2][4] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 3, 5);
                    cells[3][0] = new Cell(null, new ShadeRestriction(1), 4, 1);
                    cells[3][1] = new Cell(null, new NoRestriction(), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new ColorRestriction(Color.GREEN), 4, 4);
                    cells[3][4] = new Cell(null, new ShadeRestriction(4), 4, 5);

                    windows.get(i).setCells(cells);
                    break;

                case 9:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(6), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new NoRestriction(), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(5), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(Color.BLUE), 2, 3);
                    cells[1][3] = new Cell(null, new ShadeRestriction(4), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new ShadeRestriction(3), 3, 1);
                    cells[2][1] = new Cell(null, new ColorRestriction(Color.GREEN), 3, 2);
                    cells[2][2] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 3, 4);
                    cells[2][4] = new Cell(null, new ShadeRestriction(2), 3, 5);
                    cells[3][0] = new Cell(null, new ShadeRestriction(1), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(4), 4, 2);
                    cells[3][2] = new Cell(null, new ColorRestriction(Color.RED), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(5), 4, 4);
                    cells[3][4] = new Cell(null, new ShadeRestriction(3), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 10:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(2), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(6), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 2, 2);
                    cells[1][2] = new Cell(null, new NoRestriction(), 2, 3);
                    cells[1][3] = new Cell(null, new ShadeRestriction(5), 2, 4);
                    cells[1][4] = new Cell(null, new ColorRestriction(Color.YELLOW), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 4);
                    cells[2][4] = new Cell(null, new ShadeRestriction(5), 3, 5);
                    cells[3][0] = new Cell(null, new ShadeRestriction(1), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(2), 4, 2);
                    cells[3][2] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;

                case 11:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(3), 1, 1);
                    cells[0][1] = new Cell(null, new ShadeRestriction(4), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(1), 1, 3);
                    cells[0][3] = new Cell(null, new ShadeRestriction(5), 1, 4);
                    cells[0][4] = new Cell(null, new NoRestriction(), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(6), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(2), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new ColorRestriction(Color.YELLOW), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 4);
                    cells[2][4] = new Cell(null, new ColorRestriction(Color.RED), 3, 5);
                    cells[3][0] = new Cell(null, new ShadeRestriction(5), 4, 1);
                    cells[3][1] = new Cell(null, new NoRestriction(), 4, 2);
                    cells[3][2] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 3);
                    cells[3][3] = new Cell(null, new ColorRestriction(Color.RED), 4, 4);
                    cells[3][4] = new Cell(null, new ShadeRestriction(6), 4, 5);

                    windows.get(i).setCells(cells);
                    break;

                case 12:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 1, 1);
                    cells[0][1] = new Cell(null, new ShadeRestriction(6), 1, 2);
                    cells[0][2] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(3), 1, 5);
                    cells[1][0] = new Cell(null, new ShadeRestriction(5), 2, 1);
                    cells[1][1] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(3), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new ShadeRestriction(2), 3, 2);
                    cells[2][2] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(1), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(1), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(5), 4, 3);
                    cells[3][3] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 4, 4);
                    cells[3][4] = new Cell(null, new ShadeRestriction(4), 4, 5);

                    windows.get(i).setCells(cells);
                    break;

                case 13:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 2);
                    cells[0][2] = new Cell(null, new ColorRestriction(Color.RED), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new NoRestriction(), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(5), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new ColorRestriction(Color.BLUE), 2, 5);
                    cells[2][0] = new Cell(null, new ColorRestriction(Color.BLUE), 3, 1);
                    cells[2][1] = new Cell(null, new ShadeRestriction(2), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.RED), 3, 4);
                    cells[2][4] = new Cell(null, new ShadeRestriction(5), 3, 5);
                    cells[3][0] = new Cell(null, new ShadeRestriction(6), 4, 1);
                    cells[3][1] = new Cell(null, new ColorRestriction(Color.RED), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(3), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(1), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 14:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(1), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(3), 1, 3);
                    cells[0][3] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 4);
                    cells[0][4] = new Cell(null, new NoRestriction(), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(2), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(Color.BLUE), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new ShadeRestriction(6), 3, 1);
                    cells[2][1] = new Cell(null, new ColorRestriction(Color.BLUE), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(4), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new ColorRestriction(Color.BLUE), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(5), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(2), 4, 3);
                    cells[3][3] = new Cell(null, new NoRestriction(), 4, 4);
                    cells[3][4] = new Cell(null, new ShadeRestriction(1), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 15:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(1), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(Color.RED), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(3), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(6), 1, 5);
                    cells[1][0] = new Cell(null, new ShadeRestriction(5), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(4), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(Color.RED), 2, 3);
                    cells[1][3] = new Cell(null, new ShadeRestriction(2), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(5), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.RED), 3, 4);
                    cells[2][4] = new Cell(null, new ShadeRestriction(1), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new NoRestriction(), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 4, 4);
                    cells[3][4] = new Cell(null, new ColorRestriction(Color.RED), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 16:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new ShadeRestriction(1), 1, 2);
                    cells[0][2] = new Cell(null, new ColorRestriction(Color.GREEN), 1, 3);
                    cells[0][3] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(4), 1, 5);
                    cells[1][0] = new Cell(null, new ShadeRestriction(6), 2, 1);
                    cells[1][1] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(2), 2, 3);
                    cells[1][3] = new Cell(null, new ShadeRestriction(5), 2, 4);
                    cells[1][4] = new Cell(null, new ColorRestriction(Color.GREEN), 2, 5);
                    cells[2][0] = new Cell(null, new ShadeRestriction(1), 3, 1);
                    cells[2][1] = new Cell(null, new ColorRestriction(Color.GREEN), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(5), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(3), 3, 4);
                    cells[2][4] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new NoRestriction(), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new NoRestriction(), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 17:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[0][3] = new Cell(null, new ColorRestriction(Color.RED), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(5), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 3);
                    cells[1][3] = new Cell(null, new ShadeRestriction(4), 2, 4);
                    cells[1][4] = new Cell(null, new ColorRestriction(Color.BLUE), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new ColorRestriction(Color.BLUE), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(3), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 4);
                    cells[2][4] = new Cell(null, new ShadeRestriction(6), 3, 5);
                    cells[3][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(2), 4, 2);
                    cells[3][2] = new Cell(null, new ColorRestriction(Color.GREEN), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(1), 4, 4);
                    cells[3][4] = new Cell(null, new ColorRestriction(Color.RED), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 18:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(6), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 1, 2);
                    cells[0][2] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(5), 1, 5);
                    cells[1][0] = new Cell(null, new ShadeRestriction(5), 2, 1);
                    cells[1][1] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new ColorRestriction(Color.RED), 3, 1);
                    cells[2][1] = new Cell(null, new ShadeRestriction(6), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 1);
                    cells[3][1] = new Cell(null, new ColorRestriction(Color.RED), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(5), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(4), 4, 4);
                    cells[3][4] = new Cell(null, new ShadeRestriction(3), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 19:
                    windows.get(i).setDifficulty(5);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(4), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(2), 1, 3);
                    cells[0][3] = new Cell(null, new ShadeRestriction(5), 1, 4);
                    cells[0][4] = new Cell(null, new ColorRestriction(Color.GREEN), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new NoRestriction(), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(6), 2, 3);
                    cells[1][3] = new Cell(null, new ColorRestriction(Color.GREEN), 2, 4);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new ShadeRestriction(3), 3, 2);
                    cells[2][2] = new Cell(null, new ColorRestriction(Color.GREEN), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(4), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new ShadeRestriction(5), 4, 1);
                    cells[3][1] = new Cell(null, new ColorRestriction(Color.GREEN), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(1), 4, 3);
                    cells[3][3] = new Cell(null, new NoRestriction(), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 20:
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(2), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(5), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(1), 1, 5);
                    cells[1][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(6), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 3);
                    cells[1][3] = new Cell(null, new ShadeRestriction(2), 2, 4);
                    cells[1][4] = new Cell(null, new ColorRestriction(Color.RED), 2, 5);
                    cells[2][0] = new Cell(null, new NoRestriction(), 3, 1);
                    cells[2][1] = new Cell(null, new ColorRestriction(Color.BLUE), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(4), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.GREEN), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(3), 4, 2);
                    cells[3][2] = new Cell(null, new NoRestriction(), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(4), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 21:
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(6), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(Color.BLUE), 1, 2);
                    cells[0][2] = new Cell(null, new NoRestriction(), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(1), 1, 5);
                    cells[1][0] = new Cell(null, new NoRestriction(), 2, 1);
                    cells[1][1] = new Cell(null, new ShadeRestriction(5), 2, 2);
                    cells[1][2] = new Cell(null, new ColorRestriction(Color.BLUE), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new NoRestriction(), 2, 5);
                    cells[2][0] = new Cell(null, new ShadeRestriction(4), 3, 1);
                    cells[2][1] = new Cell(null, new ColorRestriction(Color.RED), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(2), 3, 3);
                    cells[2][3] = new Cell(null, new ColorRestriction(Color.BLUE), 3, 4);
                    cells[2][4] = new Cell(null, new NoRestriction(), 3, 5);
                    cells[3][0] = new Cell(null, new ColorRestriction(Color.GREEN), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(6), 4, 2);
                    cells[3][2] = new Cell(null, new ColorRestriction(Color.YELLOW), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(3), 4, 4);
                    cells[3][4] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 22:
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new NoRestriction(), 1, 1);
                    cells[0][1] = new Cell(null, new NoRestriction(), 1, 2);
                    cells[0][2] = new Cell(null, new ShadeRestriction(1), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new NoRestriction(), 1, 5);
                    cells[1][0] = new Cell(null, new ShadeRestriction(1), 2, 1);
                    cells[1][1] = new Cell(null, new ColorRestriction(Color.GREEN), 2, 2);
                    cells[1][2] = new Cell(null, new ShadeRestriction(3), 2, 3);
                    cells[1][3] = new Cell(null, new ColorRestriction(Color.BLUE), 2, 4);
                    cells[1][4] = new Cell(null, new ShadeRestriction(2), 2, 5);
                    cells[2][0] = new Cell(null, new ColorRestriction(Color.BLUE), 3, 1);
                    cells[2][1] = new Cell(null, new ShadeRestriction(5), 3, 2);
                    cells[2][2] = new Cell(null, new ShadeRestriction(4), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(6), 3, 4);
                    cells[2][4] = new Cell(null, new ColorRestriction(Color.GREEN), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new ColorRestriction(Color.BLUE), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(5), 4, 3);
                    cells[3][3] = new Cell(null, new ColorRestriction(Color.GREEN), 4, 4);
                    cells[3][4] = new Cell(null, new NoRestriction(), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                case 23:
                    windows.get(i).setDifficulty(6);

                    //Creazione celle finestra
                    cells[0][0] = new Cell(null, new ShadeRestriction(1), 1, 1);
                    cells[0][1] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 1, 2);
                    cells[0][2] = new Cell(null, new ColorRestriction(Color.YELLOW), 1, 3);
                    cells[0][3] = new Cell(null, new NoRestriction(), 1, 4);
                    cells[0][4] = new Cell(null, new ShadeRestriction(4), 1, 5);
                    cells[1][0] = new Cell(null, new ColorRestriction(new Color(148, 0, 211)), 2, 1);
                    cells[1][1] = new Cell(null, new ColorRestriction(Color.YELLOW), 2, 2);
                    cells[1][2] = new Cell(null, new NoRestriction(), 2, 3);
                    cells[1][3] = new Cell(null, new NoRestriction(), 2, 4);
                    cells[1][4] = new Cell(null, new ShadeRestriction(6), 2, 5);
                    cells[2][0] = new Cell(null, new ColorRestriction(Color.YELLOW), 3, 1);
                    cells[2][1] = new Cell(null, new NoRestriction(), 3, 2);
                    cells[2][2] = new Cell(null, new NoRestriction(), 3, 3);
                    cells[2][3] = new Cell(null, new ShadeRestriction(5), 3, 4);
                    cells[2][4] = new Cell(null, new ShadeRestriction(3), 3, 5);
                    cells[3][0] = new Cell(null, new NoRestriction(), 4, 1);
                    cells[3][1] = new Cell(null, new ShadeRestriction(5), 4, 2);
                    cells[3][2] = new Cell(null, new ShadeRestriction(4), 4, 3);
                    cells[3][3] = new Cell(null, new ShadeRestriction(2), 4, 4);
                    cells[3][4] = new Cell(null, new ShadeRestriction(1), 4, 5);

                    windows.get(i).setCells(cells);
                    break;
                }
            }

            return windows;
    }

    //Ottiene da file gli oggetti di una partita
    public List<PrivateObjectiveCard> retrievePrivateObjectiveCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PRIVATE_OBJECTIVE_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        List<PrivateObjectiveCard> result = gson.fromJson(jsonFile, founderListType);

        return result;

    }
    public List<ToolCard> retrieveToolCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<ToolCard>>(){}.getType();
        List<ToolCard> result = gson.fromJson(jsonFile, founderListType);

        return result;

    }
    public List<Window> retrieveWindows(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<Window>>(){}.getType();
        List<Window> result = gson.fromJson(jsonFile, founderListType);

        return result;
    }

}
