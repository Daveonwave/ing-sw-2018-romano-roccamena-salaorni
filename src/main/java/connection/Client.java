package connection;

import config.AddressConfig;
import config.ConfigLoader;
import config.PortsConfig;
import connection.rmi.RmiClient;
import connection.sockets.clientside.SocketClient;
import mvc.stubs.AppControllerStub;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Client launcher
 */
public class Client {
    private RmiClient rmiClient;
    private SocketClient socketClient;

    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    //Getter
    public SocketClient getSocketClient() {
        return socketClient;
    }

    /**
     * Depending on the value of rmiConnectionChosen, launches the Rmi or socket client.
     * @param rmiConnectionChosen if true it means that has been chosen a Rmi connection, socket if false.
     * @throws NotBoundException
     * @throws IOException
     */
    public void launchClient(boolean rmiConnectionChosen) throws NotBoundException, IOException {
        //Carica configurazioni
        Map<String, String> loadedConfig;
        AddressConfig addressConfig;
        PortsConfig portsConfig;

        try {
            loadedConfig = ConfigLoader.loadServerConfig();

            addressConfig = new AddressConfig(loadedConfig.get("server address"));
            portsConfig = new PortsConfig(Integer.parseInt(loadedConfig.get("rmi port")), Integer.parseInt(loadedConfig.get("socket port")));

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "[ERRORE]: lauch connection settings failed");
            return;
        }

        //Lancia il client
        if (rmiConnectionChosen){
            this.rmiClient = new RmiClient();
            this.rmiClient.runRmiClient(addressConfig, portsConfig);
        }
        else {
            this.socketClient = new SocketClient();
            socketClient.init(addressConfig, portsConfig);
        }
    }

    /**
     * Gets the controller of the Rmi connection, because AppView needs it.
     * @return AppControllerStub
     */
    public AppControllerStub getRmiController() {
        return rmiClient.getController();
    }
    /**
     * Gets the controller of the socket connection, because AppView needs it.
     * @return AppControllerStub
     */
    public AppControllerStub getSocketController(){
        return socketClient.getController();
    }

}
