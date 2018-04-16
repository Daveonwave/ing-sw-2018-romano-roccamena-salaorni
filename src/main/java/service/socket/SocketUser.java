package service.socket;

import service.ServiceException;
import service.ServiceNotifier;
import service.SyncHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public abstract class SocketUser<E extends Serializable, I extends Serializable, O extends Serializable> extends SyncHandler<E, I, O> implements ServiceNotifier<E, I, O> {
    //Generico utilizzatore astratto di servizi che esegue una richiesta attraverso socket al controllore

    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private String ip;
    private int port;

    //Costruttori
    public SocketUser(String ip, int port) throws ServiceException {
        super(new Object());
        this.socket = null;
        this.inStream = null;
        this.outStream = null;
        this.ip = ip;
        this.port = port;
    }

    //Setter/Getter
    protected void setIp(String ip) {
        this.ip = ip;
    }
    protected void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }

    //Connessione
    public void connect() throws ServiceException {
        try {
            //Apre connessione
            socket = new Socket(ip, port);

            //Salva stream
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new ServiceException("can't connect with server");
        }
    }
    public void disconnect() throws ServiceException {
        try {
            //Chiude gli stream
            inStream.close();
            inStream = null;
            outStream.close();
            outStream = null;

            //Chiude connessione
            socket.close();
            socket = null;
        } catch (IOException e) {
            throw new ServiceException("can't disconnect with server");
        }

    }

    //Notify
    public O notifyRequest(E event, I input) throws ServiceException {
        return raiseTask(event, input);
    }
    protected O handleTask(E event, I input) throws ServiceException {
        try {
            outStream.writeObject(event);
            outStream.flush();
            outStream.writeObject(input);
            outStream.flush();

            return (O) inStream.readObject();
        } catch (IOException e) {
            throw new ServiceException("failed writing to server");
        } catch (ClassNotFoundException e) {
            throw new ServiceException("failed reading from server");
        }
    }
}
