package connection.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOSupport {
    //Classe utile per mandare e ricevere oggetti - nome da cambiare

    //Serializza e manda l'oggetto al client
    public static void sendToClient(ObjectOutputStream outputStream, Response response) {

        try {
            outputStream.writeObject(response);
            outputStream.flush();
            System.out.println("risposta serializzata e scritta!");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Serializza e manda l'oggetto al server
    public static void sendToServer(ObjectOutputStream outputStream, Request request) {

        try {
            outputStream.writeObject(request);
            outputStream.flush();
            System.out.println("richiesta serializzata e scritta!");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Riceve dal client e deserializza l'oggetto
    public static Request receiveFromClient(ObjectInputStream inputStream) {
        Request request = null;

        try {
            request = (Request) inputStream.readObject();
            System.out.println("richiesta letta e deserializzata!");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return request;
    }

    //Riceve dal server e deserializza l'oggetto
    public static Response receiveFromServer(ObjectInputStream inputStream) {
        Response response = null;

        try {
            response = (Response) inputStream.readObject();
            System.out.println("risposta letta e deserializzata!");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return response;
    }
}
