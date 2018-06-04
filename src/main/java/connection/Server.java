package connection;

import connection.rmi.RmiServer;
import connection.sockets.ServerHandler;
import connection.sockets.SocketServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class Server {
    //Server dell'applicazione

    //Lancia il server RMI o socket dell'applicazione
    public void launchServer() throws AlreadyBoundException {
        RmiServer rmiServer = new RmiServer();

        try {
            rmiServer.runRmiServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            SocketServer.getIstance().runSocketServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
