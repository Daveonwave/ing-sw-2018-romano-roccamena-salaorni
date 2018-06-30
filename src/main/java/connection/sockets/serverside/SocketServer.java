package connection.sockets.serverside;

import config.PortsConfig;
import config.TimerConfig;
import mvc.controller.AppController;
import mvc.stubs.AppControllerStub;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kernel class of the server
 */
public class SocketServer implements Closeable {
    //Server Socket

    private static SocketServer singletonServer;

    private ServerActionHandler serverActionHandler;

    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    private boolean isReady;

    private static final Logger LOGGER = Logger.getLogger(SocketServer.class.getName());

    /**
     * Constructor singleton
     */
    private SocketServer() {
        this.serverActionHandler = null;
        this.serverSocket = null;
        threadPool = Executors.newCachedThreadPool();
        this.isReady = false;
    }

    //Setter/Getter
    public void setReady(boolean ready) {
        isReady = ready;
    }
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public boolean isReady() {
        return isReady;
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Return the singleton instance of the server
     * @return server instance
     */
    public static SocketServer getInstance() {
        if(singletonServer == null ){
            singletonServer = new SocketServer();
        }

        return singletonServer;
    }

    /**
     * Create the server
     * @param portsConfig configuration of the socket port
     * @throws IOException
     */
    public void init(PortsConfig portsConfig) throws IOException {
        serverSocket = new ServerSocket(portsConfig.getSocketPort());
        this.isReady = true;
        LOGGER.info("[SOCKET SERVER READY : PORT " + portsConfig.getSocketPort() + "]");
    }

    /**
     * Accept and set the connection with the client
     * @return return the socket used for the connection
     * @throws IOException
     */
    private Socket acceptConnection() throws IOException{
        Socket accepted = serverSocket.accept();
        LOGGER.info("[SOCKET] Aperta connessione con: " + accepted.getRemoteSocketAddress());
        return accepted;
    }

    /**
     * Initialize the connection and all the instances related with the server
     * @param timerConfig configuration of the timer
     * @param portsConfig configuration of the ports
     * @throws IOException
     */
    public void runSocketServer(TimerConfig timerConfig, PortsConfig portsConfig) throws IOException{
        this.init(portsConfig);

        AppControllerStub controller = new AppController(timerConfig);

        while(isReady) {
            try{
                final Socket socket = acceptConnection();

                ServerTransmitter serverTransmitter = new ServerTransmitter(socket, null);
                ViewProxy viewProxy = new ViewProxy(serverTransmitter);
                serverActionHandler = new ServerActionHandler(viewProxy, controller);
                viewProxy.setServerResponseHandler(serverActionHandler);

                serverTransmitter.setClientRequestHandler(serverActionHandler);
                serverTransmitter.setRunning(true);

                threadPool.submit(serverTransmitter);

            } catch (IOException e){
                LOGGER.log(Level.WARNING, "[ERROR]: initialize server connection failed");
                break;
            }
        }
    }

    /**
     * Close the connsectio
     * @throws IOException
     */
    public void close() throws IOException {
        serverSocket.close();
        threadPool.shutdown();
    }

}
