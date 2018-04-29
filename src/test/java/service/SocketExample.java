package service;

import org.junit.Test;
import service.socket.SocketController;
import service.socket.SocketServer;
import service.socket.SocketUser;

import java.io.Serializable;
import java.net.ServerSocket;

public class SocketExample {

    private final static String ipServer = "192.168.43.203";
    private final static int port = 1555;

    //Eventi, input e output del servizio
    private enum ExampleEvent {
        PRINT_1, PRINT_2;
    }
    private static class ExampleInput implements Serializable {
        private String text;

        private ExampleInput(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    private static class ExampleOutput implements Serializable {
        private String text;

        private ExampleOutput(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    //Unità costituenti del servizio
    private static class ExampleServer extends SocketServer<ExampleEvent, ExampleInput, ExampleOutput> {

        public ExampleServer() {
            super(port);
        }
    }
    private static class ExampleUser extends SocketUser<ExampleEvent, ExampleInput, ExampleOutput> {

        public ExampleUser() throws ServiceException {
            super(ipServer, port);
        }
    }
    private static class ExampleController extends SocketController<ExampleEvent, ExampleInput, ExampleOutput> {

        public ExampleController(SocketServer socketServer) {
            super(socketServer);
        }

        protected void runExceptionHandling(Exception e) {
            System.out.println("MatchController run launched exception: " + e.getMessage());
        }
        //Algoritmo di controllo
        public ExampleOutput updateRequest(ExampleEvent event, ExampleInput input) throws ServiceException {
            switch (event) {
                case PRINT_1:
                    System.out.println("PRINT1: " + input.getText());
                    return new ExampleOutput("print1 done");
                case PRINT_2:
                    System.out.println("PRINT2: " + input.getText());
                    return new ExampleOutput("print2 done");
                default:
                    System.out.println("ERROR: unknown event received");
                    return new ExampleOutput("print function unknown");
            }
        }
    }


    @Test
    public void startServer() throws ServiceException{
        try {
            ExampleServer server = new ExampleServer();
            server.start();
            System.out.println(">>> Server is listening on port: " + server.getPort());


            do{
                System.out.println("Waiting for connection...");
                server.accept(new ExampleController(server));
                System.out.println(">>> Server is connected!");
            }
            while(true);
        }
        catch (ServiceException e){
            e.printStackTrace();
        }
    }


    @Test
    public void startClient(){
        try{
            ExampleUser client = new ExampleUser();

            System.out.println(">>> Client is connecting...");
            client.connect();
            System.out.println(">>> Client connected!");

            client.notifyRequest(ExampleEvent.PRINT_1, new ExampleInput("dave usa print 1"));
            client.notifyRequest(ExampleEvent.PRINT_2, new ExampleInput("dave usa print 2"));
        }
        catch(ServiceException e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {

        try{
            ExampleUser client = new ExampleUser();

            System.out.println("Connecting to server...");
            client.connect();
            System.out.println(">>> Connected to server!\n");

            ExampleOutput out = client.notifyRequest(ExampleEvent.PRINT_1, new ExampleInput("dave usa print 1"));
            System.out.println(out.getText());


            out = client.notifyRequest(ExampleEvent.PRINT_2, new ExampleInput("dave usa print 2"));
            System.out.println(out.getText());




            client.disconnect();
        }
        catch(ServiceException e){
            e.printStackTrace();
        }
    }

}
