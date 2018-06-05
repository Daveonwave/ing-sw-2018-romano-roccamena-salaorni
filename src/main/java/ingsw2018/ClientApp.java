package ingsw2018;

import connection.Client;
import mvc.controller.AppController;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import mvc.view.console.ConsoleView;
import mvc.view.others.BaseConsoleView;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientApp {
    //Client dell'applicazione

    public static void main( String[] args ) throws Exception {
        Client client = new Client();

        client.launchClient(true);
    }
}
