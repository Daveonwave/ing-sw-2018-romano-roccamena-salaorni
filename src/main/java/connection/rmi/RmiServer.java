package connection.rmi;


import config.PortsConfig;
import config.TimerConfig;
import connection.ServerInfo;
import mvc.stubs.AppControllerStub;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * RMI server
 */
public class RmiServer {

    private static final Logger LOGGER = Logger.getLogger(RmiServer.class.getName());

    /**
     * Lauches RMI server, setting policy and listening port. The it makes the bind of RMI controller.
     * @throws RemoteException
     * @throws AlreadyBoundException
     */
    public void runRmiServer(TimerConfig timerConfig, PortsConfig portsConfig) throws RemoteException, AlreadyBoundException {
        //Setta impostazioni sicurezza
        System.setProperty("java.security.policy", "server.policy");
        System.setSecurityManager(new SecurityManager());
        System.setProperty("java.rmi.server.hostname", "localhost");

        //Crea controller dell'applicazione
        AppControllerStub appController = new RmiController(timerConfig);

        //Crea registro rmi e carica controller
        try {
            Registry registry = LocateRegistry.createRegistry(portsConfig.getRmiPort());
            registry.bind(ServerInfo.REMOTE_OBJECT_NAME, appController);

            LOGGER.log(Level.INFO, "[RMI SERVER READY : PORT " + portsConfig.getRmiPort() + "]");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
