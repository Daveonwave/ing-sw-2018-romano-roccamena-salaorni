package config;

/**
 * Configuration object containing network connections ports
 */
public class PortsConfig {
    //Configurazioni sulle porte delle connessioni

    private int rmiPort;
    private int socketPort;

    //Costruttori
    public PortsConfig(int rmiPort, int socketPort) {
        this.rmiPort = rmiPort;
        this.socketPort = socketPort;
    }

    //Setter/Getter
    public void setRmiPort(int rmiPort) {
        this.rmiPort = rmiPort;
    }
    public void setSocketPort(int socketPort) {
        this.socketPort = socketPort;
    }

    public int getRmiPort() {
        return rmiPort;
    }
    public int getSocketPort() {
        return socketPort;
    }
}
