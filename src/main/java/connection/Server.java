package connection;

import connection.rmi.RmiServer;
import connection.sockets.SocketServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 * Server launcher in the double modality: RMI and SOCKET.
 */
public class Server {

    /**
     * Launches Rmi server and socket server.
     * @throws AlreadyBoundException
     */
    public void launchServer() throws AlreadyBoundException {
        RmiServer rmiServer = new RmiServer();

        //Lancia server RMI
        try {
            rmiServer.runRmiServer();
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        //Lancia server socket
        try {
            SocketServer.getIstance().runSocketServer();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
