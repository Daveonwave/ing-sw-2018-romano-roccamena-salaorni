package connection.sockets;

import config.AddressConfig;
import config.PortsConfig;
import connection.ServerInfo;
import connection.sockets.communication.ClientActionHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.requests.client.ClientRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {
    //Client Socket

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ControllerProxy controllerProxy;
    private ClientActionHandler clientActionHandler;

    //Getter
    public ObjectInputStream getIn() {
        return in;
    }
    public ObjectOutputStream getOut() {
        return out;
    }
    public ClientActionHandler getClientActionHandler() {
        return clientActionHandler;
    }
    public ControllerProxy getController(){
        return controllerProxy;
    }

    //Inizializza il client
    public void init(AddressConfig addressConfig, PortsConfig portsConfig) throws IOException{
        socket = new Socket(addressConfig.getAddress(), portsConfig.getSocketPort());
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        clientActionHandler = new ClientActionHandler();
        controllerProxy = new ControllerProxy(this, clientActionHandler);
    }

    //Chiude il client
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    //Ottiene risposta del server
    public ClientResponse getResponse(){
        return IOSupport.responseFromServer(in);
    }

    //Invia richiesta al server
    public void request(ClientRequest request){
        IOSupport.requestToServer(out, request);
    }
}
