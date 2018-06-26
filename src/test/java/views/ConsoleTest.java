package views;

import config.AddressConfig;
import config.PortsConfig;
import config.TimerConfig;
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
        ConsoleView consoleGame = new ConsoleView(new AppController(new TimerConfig(30000, 30000)));

        consoleGame.show();
    }
    private static void startRemote() throws Exception {
        RmiClient client = new RmiClient();

        client.runRmiClient(new AddressConfig("localhost"), new PortsConfig(1099, 12345));

        AppControllerStub controller =  client.getController();
        ConsoleView consoleGame = new ConsoleView(client.getController());

        consoleGame.show();
    }

    //Test
    @Test
    public void cliTest() {

    }

    public static void main(String[] args) throws Exception {
        startRemote();
    }

}