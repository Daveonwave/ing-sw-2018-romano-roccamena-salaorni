package connection.sockets;

import connection.ServerInfo;
import connection.sockets.actionshandling.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {
    //Client Socket

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    //Costruttori
    public SocketClient(){}

    //Inizializza il client
    public void init() throws IOException{
        socket = new Socket(ServerInfo.SERVER_ADDRESS, ServerInfo.SOCKET_PORT);
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    //Chiude il client
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

    //Ottiene risposta del server
    public Object getResponse(){
        return IOSupport.receive(inputStream);
    }

    //Invia richiesta al server
    public void request(Request request){
        IOSupport.send(outputStream, request);
    }
}
