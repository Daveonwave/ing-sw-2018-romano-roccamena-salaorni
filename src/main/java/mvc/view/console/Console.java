package mvc.view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Console {
    //Funzioni base di visualizzazione console

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    //Costruttori
    private Console(){}

    //Ottiene stringa centrata
    public static String centeredStringBorder(String text, int width, String pad) {
        String result = "";
        for (int i=0; i<(width-text.length())/2; i++) {
            result += pad;
        }

        return result;
    }

    //Pulisce schermo
    public static void clearScreen() {
        LOGGER.log(Level.FINE, "\033[H\033[2J");
    }

    //Colore scrittura
    public static void setColor(String color) {
        LOGGER.log(Level.FINE, color);
    }
    public static void resetColor() {
        setColor(ConsoleColors.RESET);
    }

    //Lettura
    public static String readLine() throws IOException {
       return in.readLine();
    }

    //Scrittura
    public static void newLine() {
        println("");
    }
    public static void println(String text) {
        LOGGER.fine(text);
    }
    public static void printlnCentered(String text, int width, String pad) {
        String border = centeredStringBorder(text, width, pad);

        LOGGER.fine(border + text + border);

    }

}
