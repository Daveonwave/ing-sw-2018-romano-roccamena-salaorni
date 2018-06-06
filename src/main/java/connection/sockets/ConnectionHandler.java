package connection.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    //Gestito da un thread separato

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private boolean isRunning;
    private final ServerHandler serverHandler;


    //Costruttori
    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.serverHandler = new ServerHandler(this);
    }

    //Setter/Getter
    public void setRunning() {
        isRunning = true;
    }

    @Override
    //Fa funzionare la comunicazione
    public void run() {
        try{
            while(isRunning){
                Response response = IOSupport.receiveFromClient(inputStream).handle(serverHandler);
                if(response != null)
                    IOSupport.sendToClient(outputStream, response);
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
