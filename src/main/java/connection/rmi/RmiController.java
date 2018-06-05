package connection.rmi;

import connection.ServerInfo;
import mvc.controller.AppController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiController extends AppController {
    //Implementazione RMI del controller

    //Costruttori
    public RmiController() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }
}