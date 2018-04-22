package game.components.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum WindowPattern {
    //24 window pattern

    BELLESGUARD(3),
    FRACTAL_DROPS(3),
    LUZ_CELESTIAL(3),
    SUN_CATCHER(3),
    AURORA_SAGRADIS(4),
    CHROMATIC_SPLENDOR(4),
    KALEIDOSCOPIC_DREAM(4),
    VIA_LUX(4),
    AURORAE_MAGNIFICUS(5),
    BATTLO(5),
    COMITAS(5),
    FIRELIGHT(5),
    FIRMITAS(5),
    FULGOR_DEL_CIELO(5),
    GRAVITAS(5),
    INDUSTRIA(5),
    LUX_ASTRAM(5),
    RIPPLE_OF_LIGHT(5),
    SHADOW_THIEF(5),
    VIRTUS(5),
    SYMPHONY_OF_LIGHT(6),
    WATER_OF_LIGHT(6),
    LUX_MUNDI(6),
    SUNSS_GLORY(6);

    private final int difficulty;
    public static final List<WindowPattern> WINDOW_PATTERNS = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int WINDOW_PATTERNS_SIZE = WINDOW_PATTERNS.size();
    private static final Random RANDOM_WINDOW_PATTERN = new Random();

    //Costruttore
    WindowPattern(int difficulty) {
        this.difficulty =difficulty;
    }

    //sceglie un window pattern random
    public static final WindowPattern randomWindowPattern(){
        return WINDOW_PATTERNS.get(RANDOM_WINDOW_PATTERN.nextInt(WINDOW_PATTERNS_SIZE));
    }

}

