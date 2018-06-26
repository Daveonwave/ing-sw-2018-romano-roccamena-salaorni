package config;

/**
 * Configuration object containing network address reference
 */
public class AddressConfig {
    //Configurazione su indirizzo server

    private String address;

    //Costruttori
    public AddressConfig(String address) {
        this.address = address;
    }

    //Setter/Getter
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
