package connection.rmi;

import config.AddressConfig;
import config.PortsConfig;
import connection.ServerInfo;
import mvc.stubs.AppControllerStub;

import java.io.IOException;
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
     * Launches client, sets policy, server address and port
     * @throws RemoteException
     * @throws NotBoundException
     */
    public void runRmiClient(AddressConfig addressConfig, PortsConfig portsConfig) throws RemoteException, NotBoundException {
        //Setta impostazioni sicurezza
        System.setProperty("java.security.policy", "client.policy");
        System.setSecurityManager(new SecurityManager());

        try {
            //Ottiene registro rmi server
            Registry registry = LocateRegistry.getRegistry(addressConfig.getAddress(), portsConfig.getRmiPort());

            //Scarica stub controller dal server
            this.controller = (AppControllerStub) registry.lookup(ServerInfo.REMOTE_OBJECT_NAME);
        } catch(RemoteException e){
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }
    }

    //Getter
    public AppControllerStub getController() {
        return controller;
    }
}
