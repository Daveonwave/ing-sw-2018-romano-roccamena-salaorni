package connection.sockets;


import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerTransmitter implements Runnable {
    //Gestore dello smistamento delle richieste del client

    private Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ClientRequestHandler clientRequestHandler;

    private boolean isRunning;


    //Costruttori
    public ServerTransmitter(Socket socket, ClientRequestHandler clientRequestHandler) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.clientRequestHandler = clientRequestHandler;
    }

    //Setter/Getter
    public void setClientRequestHandler(ClientRequestHandler clientRequestHandler) {
        this.clientRequestHandler = clientRequestHandler;
    }
    public void setRunning(boolean bool) {
        isRunning = bool;
    }

    public ClientRequestHandler getRequestHandler(ClientRequestHandler clientRequestHandler) {
        return this.clientRequestHandler;    }
    public boolean isRunning() {
        return isRunning;
    }

    //Gestisce la comunicazione da view a server a lato server
    public void run() {
        try{
            while(isRunning){
                ClientResponse clientResponse = IOSupport.requestFromClient(in).handleAction(clientRequestHandler);

                if(clientResponse != null)
                    IOSupport.responseToClient(out, clientResponse);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        close();
    }

    //Stop comunicazione
    public void stop(){
        setRunning(false);
    }

    //Chiude la comunicazione
    private void close(){

        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Riceve risposta ad una richiesta
    public ServerResponse getResponse(){
        return IOSupport.responseFromClient(in);
    }

    public void request(ServerRequest request){
        IOSupport.requestToClient(out, request);
    }
}
