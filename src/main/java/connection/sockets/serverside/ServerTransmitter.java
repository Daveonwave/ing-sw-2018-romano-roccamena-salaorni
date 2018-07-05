package connection.sockets.serverside;


import connection.sockets.communication.ServerReader;
import connection.sockets.communication.ServerWriter;
import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Receiver of the client requests and responsibile of their sorting
 */
public class ServerTransmitter implements Runnable {

    private Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ClientRequestHandler clientRequestHandler;

    private ServerReader serverReader;
    private ServerWriter serverWriter;

    private boolean isRunning;

    private static final Logger LOGGER = Logger.getLogger(ServerTransmitter.class.getName());

    /**
     * Class constructor
     * @param socket communication gate
     * @param clientRequestHandler handler of the client sendRequest
     * @throws IOException
     */
    public ServerTransmitter(Socket socket, ClientRequestHandler clientRequestHandler) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.clientRequestHandler = clientRequestHandler;
        serverReader = new ServerReader();
        serverWriter = new ServerWriter();
    }

    //Setter/Getter
    public void setClientRequestHandler(ClientRequestHandler clientRequestHandler) {
        this.clientRequestHandler = clientRequestHandler;
    }
    public void setRunning(boolean bool) {
        isRunning = bool;
    }

    public ClientRequestHandler getRequestHandler() {
        return this.clientRequestHandler;    }
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Stops run() method.
     */
    public void stop(){
        setRunning(false);
    }

    /**
     * Handles the communication between client and server. It is always listening for some sendRequest from the client
     * and, when it receives one, send it to the clientRequestHandle to obtain a response (exception or not).
     */
    public void run() {
        try{
            while(isRunning){
                ClientResponse clientResponse = serverReader.requestFromClient(in).handleAction(clientRequestHandler);

                if(clientResponse != null)
                    serverWriter.responseToClient(out, clientResponse);
            }
        } catch(Exception e){
            LOGGER.log(Level.WARNING, "[ERROR]: gestione della richiesta del client fallita");
        }
        close();
    }

    /**
     * Closes communication with client
     */
    private void close(){

        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "[ERROR]: input stream closure failed");
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "[ERROR]: output stream closure failed");
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "[ERROR]: socket port closure failed");
        }
    }

    /**
     * Receives a response to a sent sendRequest
     * @return response
     */
    public ServerResponse getResponse(){
        return serverReader.responseFromClient(in);
    }
    /**
     * Make a sendRequest
     * @param request send sendRequest to be write on the outStream
     */
    public void sendRequest(ServerRequest request){
        serverWriter.requestToClient(out, request);
    }
}
