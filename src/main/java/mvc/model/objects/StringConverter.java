package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

public final class StringConverter {
    //Convertitore di oggetti in stringa

    private StringConverter(){}

    public static String getColorString(DieColor color) {
        String out = "";

        if (color.equals(DieColor.PURPLE)) {
            out = "P";
        }
        else if (color.equals(DieColor.RED)) {
            out = "R";
        }
        else if (color.equals(DieColor.BLUE)) {
            out = "B";
        }
        else if (color.equals(DieColor.YELLOW)) {
            out = "Y";
        }
        else if (color.equals(DieColor.GREEN)) {
            out = "G";
        }
        else {
            out = "X";
        }

        return out;
    }
}
