package connection;

import connection.rmi.RmiClient;
import connection.sockets.ControllerProxy;
import connection.sockets.SocketClient;
import mvc.stubs.AppControllerStub;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;

public class Client implements Serializable {
    //Client dell'applicazione

    private RmiClient rmiClient;
    private AppControllerStub controller;

    //Lancia il client RMI o socket dell'applicazione
    public void launchClient(boolean rmiConnectionChoosen) throws NotBoundException, IOException {
        if (rmiConnectionChoosen){

            this.rmiClient = new RmiClient();
            this.rmiClient.runRmiClient();
        }
        else {
            SocketClient client = new SocketClient();
            client.init();
            ControllerProxy handler = new ControllerProxy(client);
            handler.run();

            client.close();
        }
    }

    //Getter
    public AppControllerStub getController() {
        return rmiClient.getController();
    }


}
