package connection.sockets;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.client.ClientRequest;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOSupport {
    //Classe utile per mandare e ricevere oggetti - nome da cambiare

    //Invio richiesta al server
    public synchronized static void requestToServer(ObjectOutputStream outputStream, ClientRequest request) {

        try {
            outputStream.writeObject(request);
            outputStream.flush();
            System.out.println("[SOCKET] Invio richiesta al server");

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    //Invio richiesta al client
    public synchronized static void requestToClient(ObjectOutputStream outputStream, ServerRequest request) {

        try {
            outputStream.writeObject(request);
            outputStream.flush();
            System.out.println("[SOCKET] Invio richiesta al client");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Invio risposta al server
    public synchronized static void responseToServer(ObjectOutputStream outputStream, ServerResponse response) {

        try {
            outputStream.writeObject(response);
            outputStream.flush();
            System.out.println("[SOCKET] Invio risposta al server");

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    //Invio risposta al client
    public synchronized static void responseToClient(ObjectOutputStream outputStream, ClientResponse response) {

        try {
            outputStream.writeObject(response);
            outputStream.flush();
            System.out.println("[SOCKET] Invio risposta al client");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Ricezione richiesta del server
    public synchronized static ServerRequest requestFromServer(ObjectInputStream inputStream) {
        ServerRequest request = null;

        try {
            request = (ServerRequest) inputStream.readObject();
            System.out.println("[SOCKET] Ricezione richiesta del server");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return request;
    }
    //Ricezione richiesta del client
    public synchronized static ClientRequest requestFromClient(ObjectInputStream inputStream) {
        ClientRequest request = null;

        try {
            request = (ClientRequest) inputStream.readObject();
            System.out.println("[SOCKET] Ricezione richiesta del client");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return request;
    }

    //Ricezione risposta al client
    public synchronized static ClientResponse responseFromServer(ObjectInputStream inputStream) {
        ClientResponse response = null;

        try {
            response = (ClientResponse) inputStream.readObject();
            System.out.println("[SOCKET] Ricezione risposta dal server");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return response;
    }
    //Ricezione risposta al server
    public synchronized static ServerResponse responseFromClient(ObjectInputStream inputStream) {
        ServerResponse response = null;

        try {
            response = (ServerResponse) inputStream.readObject();
            System.out.println("[SOCKET] Ricezione risposta dal client");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return response;
    }

}
