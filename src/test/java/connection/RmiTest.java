package connection;

import mvc.view.others.BaseConsoleView;
import connection.rmi.RmiClient;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiTest {
    //Test su dinamica RMI

    @Test
    public void rmiExample() throws RemoteException, NotBoundException {
        RmiClient client = new RmiClient();

        client.runRmiClient();

        AppControllerStub controller =  client.getController();
        AppViewStub view = new BaseConsoleView(controller);

        view.login("dave il supremo");
    }
}
