package ingsw2018;

import connection.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientApp {
    //Client dell'applicazione

    public static void main( String[] args ) throws RemoteException, NotBoundException {
        Client client = new Client();

        client.launchClient(true);
    }
}
