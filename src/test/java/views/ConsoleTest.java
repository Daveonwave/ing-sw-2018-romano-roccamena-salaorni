package views;

import mvc.controller.AppController;
import mvc.view.console.ConsoleView;
import objects.BaseTest;
import org.junit.Test;

public class ConsoleTest extends BaseTest {
    //Test su view console

    //Test
    @Test
    public void cliTest() {

    }

    public static void main(String[] args) throws Exception {
        ConsoleView consoleGame = new ConsoleView(new AppController());

        consoleGame.show();
    }

}