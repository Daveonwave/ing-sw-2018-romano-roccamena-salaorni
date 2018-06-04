package connection.sockets;

import connection.ServerInfo;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    //Client Socket

    private String ip;
    private int port;

    //Costruttori
    public SocketClient(){
        this.ip = ServerInfo.SERVER_ADDRESS;
        this.port = ServerInfo.SOCKET_PORT;
    }

    //Setter/Getter
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }


    public void runSocketClient() throws IOException {
        Socket socket = new Socket(this.getIp(), this.getPort());
        ClientHandler clientHandler = new ClientHandler(socket);

    }
}
