package connection.rmi;

import connection.ServerInfo;
import mvc.stubs.AppControllerStub;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    //Client tramite RMI

    private AppControllerStub  controller;

    //Lancia client RMI
    public void runRmiClient() throws RemoteException, NotBoundException {
        System.setProperty("java.security.policy", "client.policy");
        System.setSecurityManager(new SecurityManager());

        Registry registry = LocateRegistry.getRegistry(ServerInfo.SERVER_ADDRESS, ServerInfo.PORT);

        System.out.println("RMI registry bindings: ");
        String[] e = registry.list();

        for (int i = 0; i < e.length; i++){
            System.out.println(e[i]);
        }

        String remoteObjectName = ServerInfo.REMOTE_OBJECT_NAME;
        this.controller = (AppControllerStub) registry.lookup(remoteObjectName);
    }

    //Getter
    public AppControllerStub getController() {
        return controller;
    }
}
