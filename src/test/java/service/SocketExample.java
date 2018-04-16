package service;

import service.socket.SocketController;
import service.socket.SocketServer;
import service.socket.SocketUser;

import java.io.Serializable;

public class SocketExample {

    private final static String ipServer = "127.0.0.1";
    private final static int port = 2222;

    //Eventi, input e output del servizio
    private enum ExampleEvent {
        PRINT_1, PRINT_2;
    }
    private class ExampleInput implements Serializable {
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
    private class ExampleOutput implements Serializable {
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
    private class ExampleServer extends SocketServer<ExampleEvent, ExampleInput, ExampleOutput> {

        public ExampleServer() {
            super(port);
        }
    }
    private class ExampleUser extends SocketUser<ExampleEvent, ExampleInput, ExampleOutput> {

        public ExampleUser() throws ServiceException {
            super(ipServer, port);
        }
    }
    private class ExampleController extends SocketController<ExampleEvent, ExampleInput, ExampleOutput> {

        public ExampleController(SocketServer socketServer) {
            super(socketServer);
        }

        protected void runExceptionHandling(Exception e) {
            System.out.println("Controller run launched exception: " + e.getMessage());
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

    public static void main(String[] args) {
        
    }

}
