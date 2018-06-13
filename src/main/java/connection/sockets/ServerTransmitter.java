package connection.sockets;


import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerTransmitter implements Runnable {
    //Gestore della comunicazione del server con i client

    private Socket socket;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private ClientRequestHandler clientRequestHandler;

    private boolean isRunning;


    //Costruttori
    public ServerTransmitter(Socket socket, ClientRequestHandler clientRequestHandler) throws IOException {
        this.socket = socket;
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.clientRequestHandler = clientRequestHandler;
    }

    //Setter/Getter
    public void setClientRequestHandler(ClientRequestHandler clientRequestHandler) {
        this.clientRequestHandler = clientRequestHandler;
    }
    public void setRunning() {
        isRunning = true;
    }

    public ClientRequestHandler getRequestHandler(ClientRequestHandler clientRequestHandler) {
        return this.clientRequestHandler;    }
    public boolean isRunning() {
        return isRunning;
    }

    //Gestisce la comunicazione da view a server a lato server
    @Override
    public void run() {
        try{
            while(isRunning){
                ClientResponse clientResponse = IOSupport.receiveFromClient(inputStream).handleAction(clientRequestHandler);

                if(clientResponse != null)
                    IOSupport.sendToClient(outputStream, clientResponse);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        close();
    }

    //Chiude la comunicazione
    private void close(){
        isRunning = false;

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (outputStream != null) {
            try {
                outputStream.close();
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

}
