package mvc.creators;

import mvc.model.objects.*;
import mvc.model.objects.Window;
import resources.ResourcesRetriever;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MatchCreator {
    //Creatore di componenti del gioco

    private static final int SP_PRIVATE_OBJECTIVES_COUNT = 2;
    private static final int SP_PUBLIC_OBJECTIVES_COUNT = 2;
    private static final int SP_TOOL_CARDS_COUNT = 12;
    private static final int MP_PUBLIC_OBJECTIVES_COUNT = 3;
    private static final int MP_TOOL_CARDS_COUNT = 3;
    private static final int SINGLE_COLOR_DICE_COUNT = 18;
    private static final int WINDOWS_FOR_PLAYER = 4;
    private static final int SP_DICE_DRAFTPOOL = 4;

    private boolean singlePlayer;
    private Random random;

    //Costruttori
    public MatchCreator(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        this.random = new Random();
    }

    //Creazione obiettivi pubblici
    public List<PublicObjectiveCard> createPublicObjectives() {
        ResourcesRetriever resourcesRetriever = new ResourcesRetriever();
        List<PublicObjectiveCard> result = new ArrayList<PublicObjectiveCard>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = SP_PUBLIC_OBJECTIVES_COUNT;
        } else {
            count = MP_PUBLIC_OBJECTIVES_COUNT;
        }

        while (countExtracted <= count) {
            int index = random.nextInt(resourcesRetriever.retrievePublicObjectiveCards().size());
            PublicObjectiveCard extracted = resourcesRetriever.retrievePublicObjectiveCards().get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(result);
        return result;
    }

    //Creazione obiettivi privati
    public List<PrivateObjectiveCard> createPrivateObjectives(int numberOfPlayers) {
        ResourcesRetriever resourcesRetriever = new ResourcesRetriever();
        List<PrivateObjectiveCard> result = new ArrayList<PrivateObjectiveCard>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = SP_PRIVATE_OBJECTIVES_COUNT;
        } else {
            count = numberOfPlayers;
        }

        while (countExtracted <= count) {
            int index = random.nextInt(resourcesRetriever.retrievePrivateObjectiveCards().size());
            PrivateObjectiveCard extracted = resourcesRetriever.retrievePrivateObjectiveCards().get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(result);
        return result;
    }

    //Creazione strumenti
    public List<ToolCard> createTools(int difficultyLevelSP) {
        ResourcesRetriever resourcesRetriever = new ResourcesRetriever();
        List<ToolCard> result = new ArrayList<ToolCard>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = 6 - difficultyLevelSP; //da 1 a 5 a seconda della difficoltà a cui si vuole giocare
        } else {
            count = MP_TOOL_CARDS_COUNT;
        }

        while (countExtracted <= count) {
            int index = random.nextInt(resourcesRetriever.retrieveToolCards().size());
            ToolCard extracted = resourcesRetriever.retrieveToolCards().get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        return result;
    }

    //Creazione pattern finestre
    public List<Window> createWindowPatterns(int numberOfPlayers) {
        ResourcesRetriever resourcesRetriever = new ResourcesRetriever();
        List<Window> result = new ArrayList<Window>();
        int countExtracted = 1;

        while (countExtracted <= numberOfPlayers * 4) {
            int index = random.nextInt(resourcesRetriever.retrieveWindows().size());
            Window extracted = resourcesRetriever.retrieveWindows().get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(result);
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

    //Creazione riserva //
    //NOTA1: numero dadi = 2*numberOfPlayers + 1; se singlePlayer allora 4 dadi
    //NOTA2: estraggo i primi 4 perché li ho già mischiati prima
    public List<Die> createDraftPoll(List<Die> dieBag, int numberOfPlayers){
        List<Die> result = new ArrayList<Die>();

        if(numberOfPlayers == 1){
            for(int i = 0; i < SP_DICE_DRAFTPOOL; i++){
                result.add(dieBag.get(i));
                dieBag.remove(i);
            }
        }
        else {
            for(int i = 0; i < 2 * numberOfPlayers + 1; i++){
                result.add(dieBag.get(i));
                dieBag.remove(i);
            }
        }
        return result;
    }

    //Creazione roundTrack
    public RoundTrack createRoundTrack(){
       List<List<Die>> diceStack = new ArrayList<List<Die>>();
       RoundTrack result = new RoundTrack(diceStack);
       return result;
    }

    //-------------------------------- FACTORY -----------------------------------//

    //Creazione nuovo match multiplayer nello stato iniziale
    public static Match createMultiPlayer(List<User> users) {
        //Comprende costruzione di un MatchDice e RoundTrack

        MatchCreator matchCreator = new MatchCreator(false);
        List<Player> players = new ArrayList<Player>();
        List<Window> extractedWindows = matchCreator.createWindowPatterns(users.size());
        List<PrivateObjectiveCard> extractedPrivateObjectiveCards = matchCreator.createPrivateObjectives(users.size());
        List<PublicObjectiveCard> extractedPublicObjectiveCards = matchCreator.createPublicObjectives();
        List<ToolCard> extractedToolCards = matchCreator.createTools(0);

        //Crea i giocatori e assegna ad ognuno 4 finestre iniziali e una carte obiettivo privato
        for(int i = 0; i < users.size(); i++){
            List<Window> startWindows = extractedWindows.subList(i * WINDOWS_FOR_PLAYER, i * WINDOWS_FOR_PLAYER + 4);
            PrivateObjectiveCard privateObjectiveCard = extractedPrivateObjectiveCards.get(i);

            Player player = new Player(users.get(i), null, startWindows ,privateObjectiveCard, 0);
            users.get(i).addPlayer(player);
            players.add(player);

        }
        RoundTrack roundTrack = matchCreator.createRoundTrack();
        MatchDice matchDice = new MatchDice(users.size(), matchCreator.createDiceBag());

        Match match = new Match(players, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);
        return match;
    }

    //Creazione nuovo match singleplayer nello stato iniziale
    public static Match createSinglePlayer(User user, int difficultyLevelSP) {
        //Comprende costruzione MatchDice e RoundTrack

        MatchCreator matchCreator = new MatchCreator(true);
        List<Window> extractedWindows = matchCreator.createWindowPatterns(1);
        List<PrivateObjectiveCard> extractedPrivateObjectiveCards = matchCreator.createPrivateObjectives(1);
        List<PublicObjectiveCard> extractedPublicObjectiveCards = matchCreator.createPublicObjectives();
        List<ToolCard> extractedToolCards = matchCreator.createTools(difficultyLevelSP);
        RoundTrack roundTrack = matchCreator.createRoundTrack();
        MatchDice matchDice = new MatchDice(1, matchCreator.createDiceBag());

        Player player = new Player(user, null, extractedWindows, extractedPrivateObjectiveCards, 0);
        user.addPlayer(player);

        Match match = new Match(player, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);
        return match;
    }

}