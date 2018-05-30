package connection.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable {
    //Gestore dei client nel server

    private SocketServer server;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private final static String EXIT = "exit";

    //Costruttori
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            server.runSocketServer();

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());


            while(!receiveObject().equals(EXIT)){

                //receiveObject();
                //sendObject();

            }

        } catch(IOException e){
            e.printStackTrace();

        } finally {
            try{
                inputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                outputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                server.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //Serializza e manda l'oggetto
    public Object sendObject() {
        Object obj = null;

        try {
            outputStream.writeObject(obj);
            outputStream.flush();
            System.out.println("oggetto scritto!");

        } catch(IOException e){
            e.printStackTrace();
        }
        return obj;
    }

    //Riceve e deserializza l'oggetto
    public Object receiveObject() {
        Object obj = null;

        try {
            obj = inputStream.readObject();
            System.out.println("oggetto letto!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return obj;
    }
}
