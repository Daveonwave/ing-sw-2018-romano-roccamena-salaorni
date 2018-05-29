package connection;

import connection.rmi.RmiClient;
import mvc.stubs.AppControllerStub;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    //Client dell'applicazione

    private RmiClient rmiClient;
    private AppControllerStub controller;

    //Lancia il client RMI o socket dell'applicazione
    public void launchClient(boolean rmiConnectionChoosen) throws RemoteException, NotBoundException {
        if (rmiConnectionChoosen){

            this.rmiClient = new RmiClient();
            this.rmiClient.runRmiClient();
        }
        else {
            //SocketClient client = new SocketClient();
            //client.runSocketClient();
        }
    }

    //Getter
    public AppControllerStub getController() {
        return rmiClient.getController();
    }
}
