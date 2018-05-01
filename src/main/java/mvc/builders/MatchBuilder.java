package mvc.builders;

import mvc.model.objects.*;
import mvc.model.objects.Window;
import mvc.model.objects.enums.*;
import resources.ResourcesRetriver;

import java.awt.*;
import java.util.*;
import java.util.List;

public class
MatchBuilder {
    //Builder di componenti del gioco

    private static final int SP_PRIVATE_OBJECTIVES_COUNT = 2;
    private static final int SP_PUBLIC_OBJECTIVES_COUNT = 2;
    private static final int SP_TOOL_CARDS_COUNT = 12;
    private static final int MP_PUBLIC_OBJECTIVES_COUNT = 3;
    private static final int MP_TOOL_CARDS_COUNT = 3;
    private static final int SINGLE_COLOR_DICE_COUNT = 18;
    private static final int WINDOWS_FOR_PLAYER = 4;

    private boolean singlePlayer;
    private Random random;

    //Costruttori
    public MatchBuilder(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        this.random = new Random();
    }

    //Creazione obiettivi pubblici
    public List<PublicObjectiveCard> createPublicObjectives() {
        List<PublicObjective> extractedPublicObjectives = new ArrayList<PublicObjective>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = SP_PUBLIC_OBJECTIVES_COUNT;
        } else {
            count = MP_PUBLIC_OBJECTIVES_COUNT;
        }

        while (countExtracted <= count) {
            List<PublicObjective> publicObjectivesList = Collections.unmodifiableList(Arrays.asList(PublicObjective.values()));
            PublicObjective extracted = publicObjectivesList.get(random.nextInt(publicObjectivesList.size()));

            if (!extractedPublicObjectives.contains(extracted)) {
                PublicObjectiveCard card;

                extractedPublicObjectives.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(extractedPublicObjectives);
        List<PublicObjectiveCard> result = ResourcesRetriver.retrivePublicObjectiveCards(extractedPublicObjectives);

        return result;
    }

    //Creazione obiettivi privati
    public List<PrivateObjectiveCard> createPrivateObjectives(int numberOfPlayers) {
        List<PrivateObjective> extractedPrivateObjectives = new ArrayList<PrivateObjective>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = SP_PRIVATE_OBJECTIVES_COUNT;
        } else {
            count = numberOfPlayers;
        }

        while (countExtracted <= count) {
            List<PrivateObjective> privateObjectivesList = Collections.unmodifiableList(Arrays.asList(PrivateObjective.values()));
            PrivateObjective extracted = privateObjectivesList.get(random.nextInt(privateObjectivesList.size()));

            if (!extractedPrivateObjectives.contains(extracted)) {
                extractedPrivateObjectives.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(extractedPrivateObjectives);
        List<PrivateObjectiveCard> result = ResourcesRetriver.retrivePrivateObjectiveCards(extractedPrivateObjectives);

        return result;
    }

    //Creazione strumenti
    public List<ToolCard> createTools(int difficultyLevelSP) {
        List<Tool> extractedTools = new ArrayList<Tool>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = difficultyLevelSP; //da 1 a 5 a seconda della difficoltà a cui si vuole giocare
        } else {
            count = MP_TOOL_CARDS_COUNT;
        }

        while (countExtracted <= count) {
            List<Tool> toolsList = Collections.unmodifiableList(Arrays.asList(Tool.values()));
            Tool extracted = toolsList.get(random.nextInt(toolsList.size()));

            if (!extractedTools.contains(extracted)) {
                extractedTools.add(extracted);
                countExtracted++;
            }
        }
        List<ToolCard> result = ResourcesRetriver.retriveToolCards(extractedTools);

        return result;
    }

    //Creazione pattern finestre
    public List<Window> createWindowPatterns(int numberOfPlayers) {
        List<WindowPattern> extractedWindowPattern = new ArrayList<WindowPattern>();
        int countExtracted = 1;

        while (countExtracted <= numberOfPlayers * 4) {
            List<WindowPattern> windowPatternList = Collections.unmodifiableList(Arrays.asList(WindowPattern.values()));
            WindowPattern extracted = windowPatternList.get(random.nextInt(windowPatternList.size()));

            if (!extractedWindowPattern.contains(extracted)) {
                extractedWindowPattern.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(extractedWindowPattern);

        List<Window> result = ResourcesRetriver.retriveWindows(extractedWindowPattern);

        return result;
    }

    //Creazione sacco di dadi
    //NOTA: random number generator: "rand.nextInt((max - min) + 1) + min"
    public List<Die> createDiceBag() {
        List<Die> diceBag = new ArrayList<Die>();

        for (int i = 1; i <= SINGLE_COLOR_DICE_COUNT; i++) {
            diceBag.add(new Die(Color.YELLOW, random.nextInt(6) + 1));
            diceBag.add(new Die(Color.RED, random.nextInt(6) + 1));
            diceBag.add(new Die(Color.GREEN, random.nextInt(6) + 1));
            diceBag.add(new Die(Color.BLUE, random.nextInt(6) + 1));
            diceBag.add(new Die(new Color(148, 0, 211), random.nextInt(6) + 1));

        }
        Collections.shuffle(diceBag);

        return diceBag;
    }

    //Creazione riserva //TODO: implementazione
    //NOTA: numero dadi = 2*numberOfPlayers + 1; se singlePlayer allora 4 dadi
    public List<Die> createDraftPoll(int numberOfPlayers){
        return null
    }

    //-------------------------------- SPECIFICA PER IMPLEMENTAZIONE -----------------------------------//

    //Estrazione di 4 finestre per ogni player
    public List<Window> extractWindows(List<Window> createdWindows, int numberOfPlayers){
        List<Window> result = new ArrayList<Window>();

       for(int i = 0; i < WINDOWS_FOR_PLAYER; i++) {

        }

        return result;
    }



    //TODO: implementazione
    //Creazione nuovo match multiplayer nello stato iniziale
    public static Match createMultiPlayer(List<User> users) {
        //Comprende costruzione di un MatchDice e RoundTrack

        List<Player> players = new ArrayList<Player>();
        MatchBuilder matchBuilder = new MatchBuilder(false);
        List<Window> extractedWindows = matchBuilder.createWindowPatterns(users.size());
        List<PrivateObjectiveCard> extractedPrivateObjectiveCards = matchBuilder.createPrivateObjectives(users.size());
        List<PublicObjectiveCard> extractedPublicObjectiveCards = matchBuilder.createPublicObjectives();
        List<ToolCard> extractedToolCards = matchBuilder.createTools(0);

        //Crea i giocatori e assegna ad ognuno 4 finestre iniziali e una carte obiettivo privato
        for(int i = 0; i < users.size(); i++){
            Player player = new Player(users.get(i), null, null ,null, 0);

            player.setStartWindows(extractedWindows.subList(i * WINDOWS_FOR_PLAYER, i * WINDOWS_FOR_PLAYER + 4));
            player.setPrivateObjectiveCard(extractedPrivateObjectiveCards.get(i));
            users.get(i).addPlayer(player);
            players.add(player);

        }


        MatchDice matchDice = new MatchDice(users.size(), matchBuilder.createDiceBag(), )

        Match match = new Match(players, extractedPublicObjectiveCards, extractedToolCards,  );



        return null;
    }

    //TODO: implementazione
    //Creazione nuovo match singleplayer nello stato iniziale
    public static Match createSinglePlayer() {
        MatchBuilder matchBuilder = new MatchBuilder(true);

        return null;
    }


}