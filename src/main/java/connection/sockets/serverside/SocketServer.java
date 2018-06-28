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

public class SocketServer implements Closeable {
    //Server Socket

    private static SocketServer singletonServer;

    private ServerActionHandler serverActionHandler;

    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    private boolean isReady;

    //Costruttori - Singleton
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

    //Restituisce il singleton del server
    public static SocketServer getIstance() {
        if(singletonServer == null ){
            singletonServer = new SocketServer();
        }

        return singletonServer;
    }

    //Crea il server
    public void init(PortsConfig portsConfig) throws IOException {
        serverSocket = new ServerSocket(portsConfig.getSocketPort());
        this.isReady = true;
        System.out.println("[SOCKET SERVER READY : PORT " + portsConfig.getSocketPort() + "]");
    }

    //Apre la connessione con il client
    private Socket acceptConnection() throws IOException{
        Socket accepted = serverSocket.accept();
        System.out.println("[SOCKET] Aperta connessione con: " + accepted.getRemoteSocketAddress());
        return accepted;
    }

    //Inizializza e fa funzionare il server
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
                e.printStackTrace();
                break;
            }
        }
    }

    //Chiude la connessione del server
    public void close() throws IOException {
        serverSocket.close();
        threadPool.shutdown();
    }

}
