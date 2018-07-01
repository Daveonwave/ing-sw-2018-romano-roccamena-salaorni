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

    /**
     * Class constructor
     * @param timerConfig receives the timers that control the times of action during the match
     * @throws RemoteException
     */
    public RmiController(TimerConfig timerConfig) throws RemoteException {
        super(timerConfig);

        UnicastRemoteObject.exportObject(this, 0);
    }
}