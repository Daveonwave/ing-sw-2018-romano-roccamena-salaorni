package mvc.view.console.printer;

import mvc.model.objects.*;
import mvc.view.console.Console;
import mvc.view.console.ConsoleColors;
import mvc.view.console.ConsoleView;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectPrinter implements Serializable {
    //Printer base di oggetti del gioco

    private ConsoleView consoleView;

    //Costruttori
    public ObjectPrinter(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    //Setter/Getter
    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public void setConsoleView(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    //Setta il colore della print da colore
    public void consoleSetColor(Color color) {
        if (color.equals(GameConstants.PURPLE)) {
            Console.setColor(ConsoleColors.PURPLE);
        }
        else if (color.equals(GameConstants.RED)) {
            Console.setColor(ConsoleColors.RED);
        }
        else if (color.equals(GameConstants.BLUE)) {
            Console.setColor(ConsoleColors.BLUE);
        }
        else if (color.equals(GameConstants.YELLOW)) {
            Console.setColor(ConsoleColors.YELLOW);
        }
        else if (color.equals(GameConstants.GREEN)) {
            Console.setColor(ConsoleColors.GREEN);
        }
        else {
            Console.setColor(ConsoleColors.RED_UNDERLINED);
        }
    }

    public void printWindowRestictions(Window window) {
        for (int row=0; row<window.getCells().length; row++) {
            String rowString = "|";

            for (Cell cell : window.getCells()[row]) {
                CellRestriction restriction = cell.getCellRestriction();

                rowString += restriction.toString();

                rowString += "|";
            }

            Console.printlnCentered(rowString, consoleView.WIDTH, " ");
        }
    }
    public void printWindowDice(Window window) {
        for (int row=0; row<window.getCells().length; row++) {
            String rowString = "|";

            for (Cell cell : window.getCells()[row]) {
                Die die = cell.getDie();
                if (die==null)
                    rowString += "  ";
                else
                    rowString += die.toString();

                rowString += "|";
            }

            Console.printlnCentered(rowString, consoleView.WIDTH, " ");
        }
    }
}
