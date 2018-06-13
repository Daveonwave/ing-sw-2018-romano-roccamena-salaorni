package connection.sockets;

import connection.sockets.communication.requests.client.ClientRequest;
import connection.sockets.communication.rensponses.client.ClientResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOSupport {
    //Classe utile per mandare e ricevere oggetti - nome da cambiare

    //Serializza e manda l'oggetto al server
    public static void sendToServer(ObjectOutputStream outputStream, ClientRequest clientRequest) {

        try {
            outputStream.writeObject(clientRequest);
            outputStream.flush();
            System.out.println("richiesta serializzata e scritta!");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Serializza e manda l'oggetto al client
    public static void sendToClient(ObjectOutputStream outputStream, ClientResponse clientResponse) {

        try {
            outputStream.writeObject(clientResponse);
            outputStream.flush();
            System.out.println("[SOCKET] Riposta serializzata e scritta");

        } catch(IOException e){
            e.printStackTrace();
        }
    }


    //Riceve dal server e deserializza l'oggetto
    public static ClientResponse receiveFromServer(ObjectInputStream inputStream) {
        ClientResponse clientResponse = null;

        try {
            clientResponse = (ClientResponse) inputStream.readObject();
            System.out.println("risposta letta e deserializzata!");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return clientResponse;
    }

    //Riceve dal client e deserializza l'oggetto
    public static ClientRequest receiveFromClient(ObjectInputStream inputStream) {
        ClientRequest clientRequest = null;

        try {
            clientRequest = (ClientRequest) inputStream.readObject();
            System.out.println("richiesta letta e deserializzata!");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return clientRequest;
    }
}
