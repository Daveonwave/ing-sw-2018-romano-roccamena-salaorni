package mvc.builders;

import mvc.model.objects.*;
import mvc.model.objects.enums.*;

import java.util.*;

public class
MatchBuilder {
    //Builder di componenti del gioco

    private static final int SP_PRIVATE_OBJECTIVES_COUNT = 2;
    private static final int SP_PUBLIC_OBJECTIVES_COUNT = 2;
    private static final int SP_TOOL_CARDS_COUNT = 12;
    private static final int MP_PUBLIC_OBJECTIVES_COUNT = 3;
    private static final int MP_TOOL_CARDS_COUNT = 3;
    private static final int SINGLE_COLOR_DICE_COUNT = 18;

    private boolean singlePlayer;
    private Random random;

    //Costruttori
    public MatchBuilder(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        this.random = new Random();
    }

    //Creazione obiettivi pubblici TODO: capire se fare in json con serializzazione
    public PublicObjectiveCard createPublicObjectivecard(PublicObjective objective) {
        PublicObjectiveCard result = new PublicObjectiveCard("", "", objective);

        switch (objective) {
            case COLOR_DIAGONALS:
                result.setName("color diagonals");
                result.setDescription("obtain points on diagonal");
                break;
            case LIGHT_SHADES:
                result.setName("light sahdes");
                result.setDescription("obtain points on light shades");
                break;
        }

        return result;
    }
    public List<PublicObjective> createPublicObjectives(){
        List<PublicObjective> result = new ArrayList<PublicObjective>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = SP_PUBLIC_OBJECTIVES_COUNT;
        } else {
            count = MP_PUBLIC_OBJECTIVES_COUNT;
        }

        while(countExtracted <= count){
            List<PublicObjective> publicObjectivesList = Collections.unmodifiableList(Arrays.asList(PublicObjective.values()));
            PublicObjective extracted = publicObjectivesList.get(random.nextInt(publicObjectivesList.size()));

            if(!result.contains(extracted)){
                PublicObjectiveCard card;

                result.add(extracted);
                countExtracted++;
            }
        }

        return result;
    }

    //Creazione obiettivi privati
    public List<PrivateObjective> createPrivateObjectives(int numberOfPlayers){
        List<PrivateObjective> result = new ArrayList<PrivateObjective>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = SP_PRIVATE_OBJECTIVES_COUNT;
        } else {
            count = numberOfPlayers;
        }

        while(countExtracted <= count){
            List<PrivateObjective> privateObjectivesList = Collections.unmodifiableList(Arrays.asList(PrivateObjective.values()));
            PrivateObjective extracted = privateObjectivesList.get(random.nextInt(privateObjectivesList.size()));

            if(!result.contains(extracted)){
                result.add(extracted);
                countExtracted++;
            }
        }
        return result;
    }

    //Creazione strumenti
    public List<Tool> createTools(int difficultyLevelSP){
        List<Tool> result = new ArrayList<Tool>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = difficultyLevelSP; //da 1 a 5 a seconda della difficoltà a cui si vuole giocare
        } else {
            count = MP_TOOL_CARDS_COUNT;
        }

        while(countExtracted <= count){
            List<Tool> toolsList = Collections.unmodifiableList(Arrays.asList(Tool.values()));
            Tool extracted = toolsList.get(random.nextInt(toolsList.size()));

            if(!result.contains(extracted)){
                result.add(extracted);
                countExtracted++;
            }
        }
        return result;
    }

    //Creazione pattern finestre
    public List<WindowPattern> createWindowPatterns(int numberOfPlayers){
        List<WindowPattern> result = new ArrayList<WindowPattern>();
        int countExtracted = 1;

        while(countExtracted <= numberOfPlayers * 4){
            List<WindowPattern> windowPatternList = Collections.unmodifiableList(Arrays.asList(WindowPattern.values()));
            WindowPattern extracted = windowPatternList.get(random.nextInt(windowPatternList.size()));

            if(!result.contains(extracted)){
                result.add(extracted);
                countExtracted++;
            }
        }
        return result;
    }

    //Creazione sacco di dadi.
    //NOTA random number generator: "rand.nextInt((max - min) + 1) + min"
    public List<Die> createDiceBag(){
        List<Die> diceBag = new ArrayList<Die>();

        for(int i = 1; i <= SINGLE_COLOR_DICE_COUNT; i++){
            diceBag.add(new Die(DieColor.YELLOW, random.nextInt(6)+1,0,0));
            diceBag.add(new Die(DieColor.RED, random.nextInt(6)+1,0,0));
            diceBag.add(new Die(DieColor.GREEN, random.nextInt(6)+1,0,0));
            diceBag.add(new Die(DieColor.BLUE, random.nextInt(6)+1,0,0));
            diceBag.add(new Die(DieColor.PURPLE, random.nextInt(6)+10,0,0));

        }
        Collections.shuffle(diceBag);

        return diceBag;
    }

    //-------------------------------- SPECIFICA PER IMPLEMENTAZIONE -----------------------------------//

    //TODO: implementazione
    //Creazione nuovo match multiplayer nello stato iniziale
    public static Match createMultiPlayer(List<User> users) {
        //Comprende costruzione di un MatchDice e RoundTrack

        return null;
    }
    //TODO: implementazione
    //Creazione nuovo match singleplayer nello stato iniziale
    public static Match createSinglePlayer() {
        return null;
    }

}
