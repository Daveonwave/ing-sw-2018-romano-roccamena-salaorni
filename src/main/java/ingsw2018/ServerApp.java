package ingsw2018;

import connection.Server;

import java.rmi.AlreadyBoundException;

/**
 * Server application main
 */
public class ServerApp {
    //Server dell'applicazione

    public static void main( String[] args ) throws AlreadyBoundException {
        Server server = new Server();

        server.launchServer();
    }
}
