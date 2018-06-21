package connection.rmi;

import mvc.controller.AppController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class bind in the RMI communication
 */
public class RmiController extends AppController {
    //Implementazione RMI del controller

    /**
     * RMI controller constructor
     * @throws RemoteException
     */
    public RmiController() throws RemoteException {
        super();

        UnicastRemoteObject.exportObject(this, 0);
    }
}