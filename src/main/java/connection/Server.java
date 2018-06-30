package connection;

import config.ConfigLoader;
import config.PortsConfig;
import config.TimerConfig;
import connection.rmi.RmiServer;
import connection.sockets.serverside.SocketServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server launcher in the double modality: RMI and SOCKET.
 */
public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    /**
     * Launches Rmi server and socket server.
     * @throws AlreadyBoundException Controller RMI binding error occured
     */
    public void launchServer() throws AlreadyBoundException {
        RmiServer rmiServer = new RmiServer();

        //Carica configurazioni
        Map<String, String> loadedConfig;
        TimerConfig timerConfig;
        PortsConfig portsConfig;

        try {
            loadedConfig = ConfigLoader.loadServerConfig();

            timerConfig = new TimerConfig(Integer.parseInt(loadedConfig.get("join wait time")), Integer.parseInt(loadedConfig.get("turn max time")));
            portsConfig = new PortsConfig(Integer.parseInt(loadedConfig.get("rmi port")), Integer.parseInt(loadedConfig.get("socket port")));

        } catch (Exception e) {
            LOGGER.log(Level.FINE, "[ERROR]: load server config failed");
            return;
        }

        //Lancia server RMI
        try {
            rmiServer.runRmiServer(timerConfig, portsConfig);
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING, "[ERROR]:launch server RMI failed");
            return;
        }
        //Lancia server socket
        try {
            SocketServer.getInstance().runSocketServer(timerConfig, portsConfig);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "[ERROR]:launch server SOCKET failed");
        }
    }
}
