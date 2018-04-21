package game.components.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PublicObjective {
    //Obiettivi pubblici per le fineste

    //TODO: aggiungere descrizione delle carte e punti vittori rispettivi

    COLOR_DIAGONALS, LIGHT_SHADES, MEDIUM_SHADES, DEEP_SHADES, COLUMN_SHADE_VARIETY, COLOR_VARIETY, SHADE_VARIETY,
    ROW_SHADE_VARIETY, COLUMN_COLOR_VARIETY, ROW_COLOR_VARIETY;

    public static final List<PublicObjective> PUBLIC_OBJECTIVES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int PUBLIC_OBJECTIVES_SIZE = PUBLIC_OBJECTIVES.size();
    private static final Random RANDOM_PUBLIC_OBJECTIVE = new Random();

    //sceglie un obiettivo pubblico random
    public static final PublicObjective randomPublicObjective(){
        return PUBLIC_OBJECTIVES.get(RANDOM_PUBLIC_OBJECTIVE.nextInt(PUBLIC_OBJECTIVES_SIZE));
    }
}
