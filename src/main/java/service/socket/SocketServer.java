package service.socket;

import service.ServiceException;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class SocketServer<E extends Serializable, I extends Serializable, O extends Serializable>{
    //Generico server socket astratto di servizi

    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private int port;

    //Costruttore
    public SocketServer(int port) {
        this.serverSocket = null;
        this.executorService = Executors.newCachedThreadPool();
        this.port = port;
    }

    //Setter/Getter
    protected void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    //Connessione
    public void start() throws ServiceException {
        try {
            //Avvia server
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new ServiceException("can't start server");
        }
    }
    public void close() throws ServiceException {
        try {
            //Chiude server
            serverSocket.close();
            serverSocket = null;
        } catch (IOException e) {
            throw new ServiceException("failed closing server");
        }
    }
    public void accept(SocketController socketController) throws ServiceException {
        try {
            //Accetta un client
            socketController.setClient(serverSocket.accept());
            //Avvia controllo associato al client
            executorService.submit(socketController);
        } catch (Exception e) {
            throw new ServiceException("failed accepting connection and starting socket controller");
        }
    }
}
