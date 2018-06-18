package connection;

import connection.rmi.RmiClient;
import connection.sockets.ControllerProxy;
import connection.sockets.SocketClient;
import gui.GuiApp;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;

public class Client implements Serializable {
    //Client dell'applicazione

    private RmiClient rmiClient;
    private SocketClient socketClient;

    //Costruttori
    public Client() {}

    //Lancia il client RMI o socket dell'applicazione
    public void launchClient(boolean rmiConnectionChoosen) throws NotBoundException, IOException {
        if (rmiConnectionChoosen){

            this.rmiClient = new RmiClient();
            this.rmiClient.runRmiClient();
        }
        else {
            this.socketClient = new SocketClient();
            socketClient.init();
        }
    }

    //Getter
    public AppControllerStub getController() {
        return rmiClient.getController();
    }
    public SocketClient getSocketClient() {
        return socketClient;
    }
}
