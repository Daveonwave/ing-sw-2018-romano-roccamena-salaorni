package connection.sockets;


import connection.sockets.communication.handlers.ClientRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *  Receiver of the client requests and responsibile of their sorting
 */
public class ServerTransmitter implements Runnable {

    private Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ClientRequestHandler clientRequestHandler;

    private boolean isRunning;

    /**
     * Class constructor
     *
     * @param socket communication gate
     * @param clientRequestHandler handler of the client request
     * @throws IOException
     */
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
     * Handles the comunication between client and server. It is always listening for some request from the client
     * and, when it receives one, send it to the clientRequestHandle to obtain a response (excption or not).
     */
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

    /**
     * Closes communication with client
     */
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

    /**
     * Receives a response to a request
     * @return response
     */
    public ServerResponse getResponse(){
        return IOSupport.responseFromClient(in);
    }

    /**
     * Make a request
     * @param request send request to be write on the outStream
     */
    public void request(ServerRequest request){
        IOSupport.requestToClient(out, request);
    }
}
