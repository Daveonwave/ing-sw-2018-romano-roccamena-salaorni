package connection.sockets;

import mvc.controller.AppController;



public class ServerHandler {
    //Gestore del server
    private ConnectionHandler connectionHandler;
    private AppController controller;

    //Costruttori
    public ServerHandler(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.controller = new AppController();
    }


}
