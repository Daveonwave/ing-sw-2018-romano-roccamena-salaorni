package connection;

import connection.rmi.RmiServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class Server {
    //Server dell'applicazione

    //Lancia il server RMI o socket dell'applicazione
    public void launchServer() throws AlreadyBoundException {
        RmiServer rmiServer = new RmiServer();
        //SocketServer socketServer = new SocketServer();

        try {
            rmiServer.runRmiServer();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }

        //socketServer.runSocketServer();
    }
}
