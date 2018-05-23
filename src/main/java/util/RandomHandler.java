package util;

import java.util.Random;

public class RandomHandler {
    //Creatore e fornitore di oggetti random

    private static Random random;

    //Crea nuovo random
    public synchronized static void createRandom(long seed) {
        random = new Random(seed);
    }
    public synchronized static void createRandom() {
        random = new Random();
    }

    //Ottiene random
    public synchronized static Random retrieveRandom() {
        return random;
    }

}
