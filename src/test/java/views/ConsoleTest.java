package views;

import connection.rmi.RmiClient;
import mvc.controller.AppController;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import mvc.view.console.ConsoleView;
import base.BaseTest;
import mvc.view.others.BaseConsoleView;
import org.junit.Test;

public class ConsoleTest extends BaseTest {
    //Test su view console

    private static void startLocal() throws Exception {
        ConsoleView consoleGame = new ConsoleView(new AppController());

        consoleGame.show();
    }
    private static void startRemote() throws Exception {
        RmiClient client = new RmiClient();

        client.runRmiClient();

        AppControllerStub controller =  client.getController();
        ConsoleView consoleGame = new ConsoleView(client.getController());

        consoleGame.show();
    }

    //Test
    @Test
    public void cliTest() {

    }

    public static void main(String[] args) throws Exception {
        startLocal();
    }

}