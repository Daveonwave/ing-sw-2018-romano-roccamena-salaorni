package connection.sockets;

import connection.ServerInfo;
import connection.sockets.communication.requests.client.ClientRequest;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {
    //Client Socket

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ViewProxy viewProxy;

    //Costruttori
    public SocketClient(){}

    //Setter/Getter
    public ObjectInputStream getIn() {
        return in;
    }
    public ObjectOutputStream getOut() {
        return out;
    }

    //Inizializza il client
    public void init() throws IOException{
        socket = new Socket(ServerInfo.SERVER_ADDRESS, ServerInfo.SOCKET_PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
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
