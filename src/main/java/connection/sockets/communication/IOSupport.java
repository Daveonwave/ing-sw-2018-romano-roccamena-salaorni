package connection.sockets.communication;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.client.ClientRequest;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOSupport {
    //Classe utile per mandare e ricevere oggetti - nome da cambiare

    private static final Logger LOGGER = Logger.getLogger(IOSupport.class.getName());

    //Costruttori
    private IOSupport(){}

    //Invio richiesta al server
    public static synchronized void requestToServer(ObjectOutputStream outputStream, ClientRequest request){

        try {
            outputStream.writeObject(request);
            outputStream.flush();
            LOGGER.log(Level.INFO, "[SOCKET]: invio richiesta al server");

        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: send request to server failed");
        }
    }
    //Invio richiesta al client
    public static synchronized void requestToClient(ObjectOutputStream outputStream, ServerRequest request) {

        try {
            outputStream.writeObject(request);
            outputStream.flush();
            LOGGER.log(Level.INFO,"[SOCKET]: invio richiesta al client");

        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: send request to client failed");
        }
    }

    //Invio risposta al server
    public static synchronized void responseToServer(ObjectOutputStream outputStream, ServerResponse response) {

        try {
            outputStream.writeObject(response);
            outputStream.flush();
            LOGGER.log(Level.INFO,"[SOCKET]: invio risposta al server");

        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: send response to server failed");
        }
    }
    //Invio risposta al client
    public static synchronized void responseToClient(ObjectOutputStream outputStream, ClientResponse response) {

        try {
            outputStream.writeObject(response);
            outputStream.flush();
            LOGGER.log(Level.INFO,"[SOCKET]: invio risposta al client");

        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: send response to client failed");
        }
    }

    //Ricezione richiesta del server
    public static synchronized ServerRequest requestFromServer(ObjectInputStream inputStream) {
        ServerRequest request = null;

        try {
            request = (ServerRequest) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione richiesta dal server");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving request from server failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received request from server doesn't match with any class");
        }
        return request;
    }
    //Ricezione richiesta del client
    public static synchronized ClientRequest requestFromClient(ObjectInputStream inputStream) {
        ClientRequest request = null;

        try {
            request = (ClientRequest) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione richiesta dal client");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving request from client failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received request from client doesn't match with any class");
        }
        return request;
    }

    //Ricezione risposta al client
    public static synchronized ClientResponse responseFromServer(ObjectInputStream inputStream) {
        ClientResponse response = null;

        try {
            response = (ClientResponse) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione risposta dal server");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving response from server failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received response from server doesn't match with any class");
        }
        return response;
    }
    //Ricezione risposta al server
    public static synchronized ServerResponse responseFromClient(ObjectInputStream inputStream) {
        ServerResponse response = null;

        try {
            response = (ServerResponse) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione risposta dal client");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving response from client failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received response from client doesn't match with any class");
        }
        return response;
    }

    public static synchronized Object fromServer(ObjectInputStream inputStream){
        Object response = null;

        try {
            response = inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione oggetto generico dal server");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving object from server failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received object from server doesn't match with any class");
        }
        return response;
    }
}
