package views;

import mvc.controller.AppController;
import mvc.view.console.ConsoleView;

public class ConsoleTest {

    public static void main(String[] args) throws Exception {
        ConsoleView consoleGame = new ConsoleView(new AppController());

        consoleGame.show();
    }

}