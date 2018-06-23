package connection;

import connection.rmi.RmiClient;
import connection.sockets.SocketClient;
import mvc.stubs.AppControllerStub;

import java.io.IOException;
import java.rmi.NotBoundException;

/**
 * Client Launcher
 */
public class Client {

    private RmiClient rmiClient;
    private SocketClient socketClient;

    //Getter
    public SocketClient getSocketClient() {
        return socketClient;
    }

    /**
     * Depending on the value of rmiConnectionChosen, launches the Rmi or socket client.
     * @param rsiConnectionChosen if true it means that has been chosen a Rmi connection, socket if false.
     * @throws NotBoundException
     * @throws IOException
     */
    public void launchClient(boolean rsiConnectionChosen) throws NotBoundException, IOException {
        if (rsiConnectionChosen){

            this.rmiClient = new RmiClient();
            this.rmiClient.runRmiClient();
        }
        else {
            this.socketClient = new SocketClient();
            socketClient.init();
        }
    }

    /**
     * Gets the controller of the Rmi connection, because AppView needs it.
     * @return AppControllerStub
     */
    public AppControllerStub getRmiController() {
        return rmiClient.getController();
    }
    /**
     * Gets the controller of the socket connection, because AppView needs it.
     * @return AppControllerStub
     */
    public AppControllerStub getSocketController(){
        return socketClient.getController();
    }

}
