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
        System.setProperty("java.security.policy", "server.policy");
        System.setSecurityManager(new SecurityManager());

        System.out.println("Costruendo l'implementazione server...");
        AppControllerStub appController = new RmiController();

        System.out.println("Bindando l'implementazione server al registro...");
        Registry registry = LocateRegistry.getRegistry(ServerInfo.PORT);
        registry.bind(ServerInfo.REMOTE_OBJECT_NAME, appController);

        System.out.println("Aspettando invocazioni dal client...");
    }
}
