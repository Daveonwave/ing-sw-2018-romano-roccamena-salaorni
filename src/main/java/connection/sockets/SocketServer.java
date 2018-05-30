package connection.sockets;

import connection.ServerInfo;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer implements Closeable {
    //Server Socket

    private int port;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private boolean isReady;

    //Costruttori
    public SocketServer() {
        this.port = ServerInfo.SOCKET_PORT;
        threadPool = Executors.newCachedThreadPool();
        this.isReady = false;
    }

    //Crea il server
    public void init() throws IOException{
        serverSocket = new ServerSocket(port);
        this.isReady = true;
        System.out.println(">>> Server socket in ascolto sulla porta: " + port + "...");
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
                threadPool.submit(new ServerHandler(socket));
            } catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
        threadPool.shutdown();
    }

    //Chiude la connessione del server
    public void close() throws IOException {
        serverSocket.close();
    }
}
