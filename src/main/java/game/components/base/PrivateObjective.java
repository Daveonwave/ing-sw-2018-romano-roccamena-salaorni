package game.components.base;

import java.util.*;

public enum PrivateObjective {
    //Obiettivi privati per ogni giocatore

    SHADES_OF_RED ("Private Sum of values on red dice", DieColor.RED),
    SHADES_OF_YELLOW ("Private Sum of values on yellow dice", DieColor.YELLOW),
    SHADES_OF_GREEN ("Private Sum of values on green dice", DieColor.GREEN),
    SHADES_OF_BLUE ("Private Sum of values on blue dice", DieColor.BLUE),
    SHADES_OF_PURPLE ("Private Sum of values on purple dice", DieColor.PURPLE);

    private String description;

    //Costruttore
    PrivateObjective(String description, DieColor color) {
        this.description = description;
    }

    //Getter
    public String getDescription() {
        return description;
    }

    public static final List<PrivateObjective> PRIVATE_OBJECTIVES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int PRIVATE_OBJECTIVES_SIZE = PRIVATE_OBJECTIVES.size();
    private static final Random RANDOM_PRIVATE_OBJECTIVE = new Random();

    //sceglie un obiettivo privato random
    public static final PrivateObjective randomPrivateObjective(){
        return PRIVATE_OBJECTIVES.get(RANDOM_PRIVATE_OBJECTIVE.nextInt(PRIVATE_OBJECTIVES_SIZE));
    }

/*
    public static void main(String[] args) {
        List<PrivateObjective> carteEstratte = new ArrayList<PrivateObjective>();
        int numberOfPlayers = 4;
        int count = 1;

        System.out.println(PrivateObjective.PRIVATE_OBJECTIVES_SIZE);

        while(count <= numberOfPlayers) {
            PrivateObjective p = randomPrivateObjective();
            if (!carteEstratte.contains(p)){
                carteEstratte.add(p);
                count++;
            }

        }

        for (PrivateObjective p : carteEstratte) {
            System.out.println(p + ": " + p.getDescription());

        }

    }*/
}
