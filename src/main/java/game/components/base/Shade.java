package game.components.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Shade{
    //Shades di un dado, dunque facce che possono uscire dal RollDice

    UNO(1), DUE(2), TRE(3), QUATTRO(4), CINQUE(5), SEI(6);

    private final int sideDieValue;
    public static final List<Shade> SHADES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int SHADES_SIZE = SHADES.size();
    public static final Random RANDOM_SHADE = new Random();

    //Costruttore
    Shade(int sideDieValue){
        this.sideDieValue = sideDieValue;
    }

    //Setter/Getter
    public int getSideDieValue() {
        return sideDieValue;
    }

    //sceglie un sfumatura("shade") random
    public static final Shade randomShade(){
        return SHADES.get(RANDOM_SHADE.nextInt(SHADES_SIZE));
    }



}
