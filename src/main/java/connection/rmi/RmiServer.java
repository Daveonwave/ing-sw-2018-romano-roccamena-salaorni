package connection.rmi;


import connection.ServerInfo;
import mvc.stubs.AppControllerStub;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    //Server RMI

    //Lancia server RMI
    public void runRmiServer() throws RemoteException, AlreadyBoundException {
        //Setta impostazioni sicurezza
        System.setProperty("java.security.policy", "server.policy");
        System.setSecurityManager(new SecurityManager());

        //Crea controller dell'applicazione
        AppControllerStub appController = new RmiController();

        //Crea registro rmi e carica controller
        Registry registry = LocateRegistry.createRegistry(ServerInfo.RMI_PORT);
        registry.bind(ServerInfo.REMOTE_OBJECT_NAME, appController);

        System.out.println("[RMI SERVER READY : PORT " + ServerInfo.RMI_PORT + "]");
    }

}
