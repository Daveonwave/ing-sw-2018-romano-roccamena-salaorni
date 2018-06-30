package util;

import java.util.Random;

/**
 * Handler of the Random object of the application
 */
public class RandomHandler {
    //Creatore e fornitore di oggetti random

    private static Random random;

    /**
     * Private constructor
     */
    private RandomHandler(){}

    /**
     * Create a Random object given a seed
     * @param seed Seed value
     */
    public static synchronized void createRandom(long seed) {
        random = new Random(seed);
    }

    /**
     * Create a Random object given default initialization
     */
    public static synchronized void createRandom() {
        random = new Random();
    }

    /**
     * Retrieve Random object created
     * @return random object
     */
    public static synchronized Random retrieveRandom() {
        return random;
    }

}
