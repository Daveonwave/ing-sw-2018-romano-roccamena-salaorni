package views;

import mvc.controller.AppController;
import mvc.view.console.ConsoleView;
import org.junit.Test;

public class ConsoleTest {

    //Test
    @Test
    public void cliTest() {

    }

    public static void main(String[] args) throws Exception {
        ConsoleView consoleGame = new ConsoleView(new AppController());

        consoleGame.show();
    }

}