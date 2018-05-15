package mvc.view.console;

import java.io.*;

public final class Console {
    //Funzioni base di visualizzazione console

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

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
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Colore scrittura
    public static void setColor(String color) {
        System.out.println(color);
        System.out.flush();
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
        System.out.println(text);
        System.out.flush();
    }
    public static void printlnCentered(String text, int width, String pad) {
        String border = centeredStringBorder(text, width, pad);

        System.out.println(border + text + border);

        System.out.flush();
    }

}
