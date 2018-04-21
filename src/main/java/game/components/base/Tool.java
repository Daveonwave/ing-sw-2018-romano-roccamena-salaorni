package game.components.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Tool {
    //Strumenti di una partita

    //TODO: aggiungere descrizione delle carte, numero e simbolo singlePlayer
    GROZING_PLIERS, EGLOMISE_BRUSH, COPPER_FOIL_BURNISHER, LATHEKIN, LENS_CUTTER, FLUX_BRUSH,
    GLAZING_HAMMER, RUNNING_PLIERS, CORK_BACKED_STRAIGHTEDGE, GRIDING_STONE, FLUX_REMOVER, TAP_WHEEL;

    public static final List<Tool> TOOLS = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int TOOLS_SIZE = TOOLS.size();
    private static final Random RANDOM_TOOL = new Random();

    //sceglie una carta tool random
    public static final Tool randomTool(){
        return TOOLS.get(RANDOM_TOOL.nextInt(TOOLS_SIZE));
    }
}
