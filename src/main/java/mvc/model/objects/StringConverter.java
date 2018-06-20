package mvc.model.objects;

import java.awt.*;

public final class StringConverter {
    //Convertitore di oggetti in stringa

    public static String getColorString(Color color) {
        String out = "";

        if (color.equals(GameConstants.PURPLE)) {
            out = "P";
        }
        else if (color.equals(GameConstants.RED)) {
            out = "R";
        }
        else if (color.equals(GameConstants.BLUE)) {
            out = "B";
        }
        else if (color.equals(GameConstants.YELLOW)) {
            out = "Y";
        }
        else if (color.equals(GameConstants.GREEN)) {
            out = "G";
        }
        else {
            out = "X";
        }

        return out;
    }
}
