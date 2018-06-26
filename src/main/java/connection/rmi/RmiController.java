package connection.rmi;

import config.TimerConfig;
import mvc.controller.AppController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class bind in the RMI communication
 */
public class RmiController extends AppController {
    //Implementazione RMI del controller

    public RmiController(TimerConfig timerConfig) throws RemoteException {
        super(timerConfig);

        UnicastRemoteObject.exportObject(this, 0);
    }
}