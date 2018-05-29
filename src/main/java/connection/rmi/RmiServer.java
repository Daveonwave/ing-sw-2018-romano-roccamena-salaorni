package connection.rmi;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    //Server RMI

    //Lancia server RMI
    public void runRmiServer() throws RemoteException, AlreadyBoundException {
        System.out.println("Costruendo l'implementazione server...");
        RmiController appController = new RmiController();

        System.out.println("Bindando l'implementazione server al registro...");
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("app_controller", appController);

        System.out.println("Aspettando invocazioni dal client...");
    }
}
