package mvc.view.console.printer;

import mvc.model.objects.Cell;
import mvc.model.objects.CellRestriction;
import mvc.model.objects.Die;
import mvc.model.objects.Window;
import mvc.model.objects.enums.DieColor;
import mvc.view.console.Console;
import mvc.view.console.ConsoleColors;
import mvc.view.console.ConsoleView;

import java.awt.*;
import java.io.Serializable;

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
        if (color.equals(DieColor.PURPLE)) {
            Console.setColor(ConsoleColors.PURPLE);
        }
        else if (color.equals(DieColor.RED)) {
            Console.setColor(ConsoleColors.RED);
        }
        else if (color.equals(DieColor.BLUE)) {
            Console.setColor(ConsoleColors.BLUE);
        }
        else if (color.equals(DieColor.YELLOW)) {
            Console.setColor(ConsoleColors.YELLOW);
        }
        else if (color.equals(DieColor.GREEN)) {
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
