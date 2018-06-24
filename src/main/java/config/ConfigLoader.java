package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Load configurations parameters in a specified format from files
 */
public final class ConfigLoader {
    //Caricatore di parametri di configurazioni

    public final static String COMMENTS_START = "//";
    public final static String PARAM_SEPARATOR = "=";

    public static Map<String, String> loadParams(String path) throws FileNotFoundException, IllegalArgumentException {
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

        return output;
    }
}
