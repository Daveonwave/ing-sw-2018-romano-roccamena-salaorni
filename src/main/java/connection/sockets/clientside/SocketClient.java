package connection.sockets.clientside;

import config.AddressConfig;
import config.PortsConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Kernel class of the client
 */
public class SocketClient {
    //Client Socket

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ControllerProxy controllerProxy;
    private ClientTransmitter clientTransmitter;
    private ClientActionHandler clientActionHandler;

    private ResponseRegistry responseRegistry;

    //Getter
    public ObjectInputStream getIn() {
        return in;
    }
    public ObjectOutputStream getOut() {
        return out;
    }
    public ControllerProxy getController(){
        return controllerProxy;
    }
    public ClientTransmitter getClientTransmitter() {
        return clientTransmitter;
    }
    public ClientActionHandler getClientActionHandler() {
        return clientActionHandler;
    }
    public ResponseRegistry getResponseRegistry() {
        return responseRegistry;
    }

    /**
     * Initialize the client and create all the related instances
     * @param addressConfig configuration of the ip address
     * @param portsConfig configuration of the socket port
     * @throws IOException
     */
    public void init(AddressConfig addressConfig, PortsConfig portsConfig) throws IOException{
        socket = new Socket(addressConfig.getAddress(), portsConfig.getSocketPort());
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        clientActionHandler = new ClientActionHandler();
        responseRegistry = new ResponseRegistry();
        clientTransmitter = new ClientTransmitter(this, clientActionHandler);
        controllerProxy = new ControllerProxy(this, clientActionHandler);

        clientTransmitter.setRunning(true);
        new Thread(clientTransmitter).start();
    }

    //Chiude il client
    /**
     * Close the connection and the IO streams
     * @throws IOException
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
        clientTransmitter.setRunning(false);
    }


}
