package connection.sockets;

import connection.Server;
import connection.ServerInfo;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer implements Closeable {
    //Server Socket

    private static SocketServer singletonServer;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private boolean isReady;

    //Costruttori - Singleton
    private SocketServer() {
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
    public void init() throws IOException {
        serverSocket = new ServerSocket(ServerInfo.SOCKET_PORT);
        this.isReady = true;
        System.out.println(">>> Server socket in ascolto sulla porta: " + ServerInfo.SOCKET_PORT + "...");
    }

    //Apre la connessione con il client
    public Socket acceptConnection() throws IOException{
        Socket accepted = serverSocket.accept();
        System.out.println(">>> Aperta connessione con: " + accepted.getRemoteSocketAddress());
        return accepted;
    }

    //Inizializza e fa funzionare il server
    public void runSocketServer() throws IOException{
        this.init();

        while(isReady) {
            try{
                final Socket socket = acceptConnection();
                threadPool.submit(new ConnectionHandler(socket));

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
