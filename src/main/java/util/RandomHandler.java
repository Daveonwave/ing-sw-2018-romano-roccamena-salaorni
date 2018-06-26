package util;

import java.util.Random;

/**
 * Handler of the Random object of the application
 */
public class RandomHandler {
    //Creatore e fornitore di oggetti random

    private static Random random;

    /**
     * Create a Random object given a seed
     * @param seed Seed value
     */
    public synchronized static void createRandom(long seed) {
        random = new Random(seed);
    }

    /**
     * Create a Random object given default initialization
     */
    public synchronized static void createRandom() {
        random = new Random();
    }

    /**
     * Retrieve Random object created
     * @return
     */
    public synchronized static Random retrieveRandom() {
        return random;
    }

}
