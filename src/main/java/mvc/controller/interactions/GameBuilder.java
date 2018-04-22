package mvc.controller.interactions;

import mvc.model.objects.*;
import mvc.model.objects.enums.*;

import java.util.*;

public class GameBuilder {
    //Builder di componenti del gioco

    private static final int SP_PRIVATE_OBJECTIVES_COUNT = 2;
    private static final int SP_PUBLIC_OBJECTIVES_COUNT = 2;
    private static final int MP_PUBLIC_OBJECTIVES_COUNT = 3;
    private static final int MP_TOOL_CARDS_COUNT = 3;
    private static final int SINGLE_COLOR_DICE_COUNT = 18;

    private boolean singlePlayer;
    private Random random;

    //Costruttori
    public GameBuilder(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        this.random = new Random();
    }

    //Creazione obiettivi pubblici
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

    //////////////////////
    //TODO: arrivati qui//
    //////////////////////

    //Creazione strumenti
    public ArrayList<Tool> createTools(){
        MP_TOOL_CARDS_COUNT = new ArrayList<Tool>();
        int countExtracted = 1;

        while(countExtracted < MP_TOOL_CARDS_COUNT){
            Tool extractedCard = Tool.randomTool();

            if(!MP_TOOL_CARDS_COUNT.contains(extractedCard)){
                MP_TOOL_CARDS_COUNT.add(extractedCard);
                countExtracted++;
            }
        }
        return MP_TOOL_CARDS_COUNT;
    }

    //Creazione pattern finestre
    public ArrayList<WindowPattern> createWindowPatterns(int numberOfPlayers){
        activeWindowPatterns = new ArrayList<WindowPattern>();
        int countExtracted = 1;

        while(countExtracted <= numberOfPlayers * 4){
            WindowPattern extractedWindowPattern = WindowPattern.randomWindowPattern();

            if(!activeWindowPatterns.contains(extractedWindowPattern)){
                activeWindowPatterns.add(extractedWindowPattern);
                countExtracted++;
            }
        }
        return activeWindowPatterns;
    }

    //Creazione sacco di dadi
    public ArrayList<Die> createDiceBag(){
        diceBag = new ArrayList<Die>();

        for(int i = 1; i <= SINGLE_COLOR_DICE_COUNT; i++){
            diceBag.add(new Die(DieColor.YELLOW, Shade.randomShade()));
            diceBag.add(new Die(DieColor.RED, Shade.randomShade()));
            diceBag.add(new Die(DieColor.GREEN, Shade.randomShade()));
            diceBag.add(new Die(DieColor.BLUE, Shade.randomShade()));
            diceBag.add(new Die(DieColor.PURPLE, Shade.randomShade()));
        }
        Collections.shuffle(diceBag);

        return diceBag;
    }




    //Prova temporanea
    public static void main(String[] args) {

        GameBuilder gamePreparation = new GameBuilder();
        ArrayList<Die> diceBag = gamePreparation.createDiceBag();

        for(Die dado : diceBag){
            System.out.println(dado.getColor().toString() + " " + dado.getShade());
        }


    }
}
