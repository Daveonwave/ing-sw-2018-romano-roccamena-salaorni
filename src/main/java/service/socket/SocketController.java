package service.socket;

import service.ServiceException;
import service.ServiceUpdater;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public abstract class SocketController <E extends Serializable, I extends Serializable, O extends Serializable> implements ServiceUpdater<E, I, O>, Runnable {
    //Generico controllore astratto di servizi che è soggetto a richieste attraverso socket dall'utilizzatore

    private final SocketServer socketServer;
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    //Costruttori
    public SocketController(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    //Setter/Getter
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    //Avvio controllore
    public void run() {
        try {
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());

            //Ciclo di gestione del client
            while (socket.isConnected()) {
                synchronized (socketServer) {
                    E event = (E) inStream.readObject();
                    I input = (I) inStream.readObject();

                    outStream.writeObject(updateRequest(event, input));
                    outStream.flush();
                }
            }
        } catch (Exception e) {
           runExceptionHandling(e);
        }
    }
    protected abstract void runExceptionHandling(Exception e);

    //Update
    public abstract O updateRequest(E event, I input) throws ServiceException;
}
