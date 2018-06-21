package connection.rmi;

import connection.ServerInfo;
import mvc.stubs.AppControllerStub;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI client
 */
public class RmiClient implements Serializable {

    private AppControllerStub  controller;

    /**
     * Launches client, setting policy, server address and port
     * @throws RemoteException
     * @throws NotBoundException
     */
    public void runRmiClient() throws RemoteException, NotBoundException {
        //Setta impostazioni sicurezza
        System.setProperty("java.security.policy", "client.policy");
        System.setSecurityManager(new SecurityManager());

        //Ottiene registro rmi server
        Registry registry = LocateRegistry.getRegistry(ServerInfo.SERVER_ADDRESS, ServerInfo.RMI_PORT);

        //Scarica stub controller dal server
        this.controller = (AppControllerStub) registry.lookup(ServerInfo.REMOTE_OBJECT_NAME);

    }

    //Getter
    public AppControllerStub getController() {
        return controller;
    }
}
