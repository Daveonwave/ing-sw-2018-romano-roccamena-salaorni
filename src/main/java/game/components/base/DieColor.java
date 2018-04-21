package game.components.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum DieColor {
    //Colori di un dado

    YELLOW, RED, GREEN, PURPLE, BLUE;

    public static final List<DieColor> COLORS = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int COLORS_SIZE = COLORS.size();
    private static final Random RANDOM_COLOR = new Random();

    //sceglie un colore random
    public static final DieColor randomColor(){
        return COLORS.get(RANDOM_COLOR.nextInt(COLORS_SIZE));
    }


}
