package config;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Load configuration parameters from files in a specified format
 */
public final class ConfigLoader {
    //Caricatore di parametri di configurazioni

    public final String COMMENTS_START = "//";
    public final String PARAM_SEPARATOR = "=";

    public static final String CLIENT_CONFIG_PATH = "src/main/java/config/client.txt";
    public static final String SERVER_CONFIG_PATH = "src/main/java/config/server.txt";

    /**
     * Load configuration parameters from file
     * @param path Config file path
     * @return Map name->value of each parameter
     * @throws IOException If file can't be acessed properly
     * @throws IllegalArgumentException Invalid file format
     */
    private Map<String, String> loadConfigParams(String path) throws IOException, IllegalArgumentException {
        Map<String, String> output = new HashMap<String, String>();

        //Apre stream di input
        FileReader inStream = new FileReader(path);
        Scanner scanner = new Scanner(inStream);

        //Legge e interpreta ogni riga
        while (scanner.hasNext()) {

            String line = scanner.nextLine();

            //Se non è riga di commento
            if (!line.startsWith(COMMENTS_START) && !line.equals("")) {

                //Splitta il parametro in nome e valore
                String[] splitted = line.split(PARAM_SEPARATOR);

                if (splitted.length != 2) {
                    throw new IllegalArgumentException("invalid config file");
                }

                String name = splitted[0];
                String value = splitted[1];

                //Aggiunge parametro
                output.put(name, value);
            }
        }

        //Chiude stream ingresso
        scanner.close();
        inStream.close();

        return output;
    }

    /**
     * Load client configuration parameters
     * @return Map name->value of each parameter
     * @throws IOException If file can't be acessed properly
     * @throws IllegalArgumentException Invalid file format
     */
    public static Map<String, String> loadClientConfig() throws IOException, IllegalArgumentException {
        ConfigLoader loader = new ConfigLoader();

        return loader.loadConfigParams(CLIENT_CONFIG_PATH);
    }
    /**
     * Load server configuration parameters
     * @return Map name->value of each parameter
     * @throws IOException If file can't be acessed properly
     * @throws IllegalArgumentException Invalid file format
     */
    public static Map<String, String> loadServerConfig() throws IOException, IllegalArgumentException {
        ConfigLoader loader = new ConfigLoader();

        return loader.loadConfigParams(SERVER_CONFIG_PATH);
    }
}
