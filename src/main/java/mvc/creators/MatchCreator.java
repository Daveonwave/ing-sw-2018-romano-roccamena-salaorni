package mvc.creators;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;
import resources.ResourceRetriever;
import util.RandomHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creator of model's match instances
 */
public class MatchCreator {
    //Creatore di componenti del gioco

    private boolean singlePlayer;

    //Costruttori
    /**
     * Create new match creator
     * @param singlePlayer If match to create is single player
     */
    public MatchCreator(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    //Creazione carte
    public List<PublicObjectiveCard> createPublicObjectiveCards(boolean shuffle) {
        ResourceRetriever resourceRetriever = new ResourceRetriever();
        List<PublicObjectiveCard> result = new ArrayList<PublicObjectiveCard>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = GameConstants.SP_PUBLIC_OBJECTIVES_COUNT;
        } else {
            count = GameConstants.MP_PUBLIC_OBJECTIVES_COUNT;
        }

        //TODO: update serializzazione
        //List<PublicObjectiveCard> cards = resourceRetriever.retrievePublicObjectiveCards();
        List<PublicObjectiveCard> cards = ObjectivesCreator.createPublicObjectiveCards();

        while (countExtracted <= count) {
            int index = RandomHandler.retrieveRandom().nextInt(cards.size());
            PublicObjectiveCard extracted = cards.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        if (shuffle)
            Collections.shuffle(result);

        return result;
    }
    public List<PublicObjectiveCard> createPublicObjectiveCards() {
        return createPublicObjectiveCards(true);
    }
    public List<PrivateObjectiveCard> createPrivateObjectiveCards(int numberOfPlayers, boolean shuffle) {
        ResourceRetriever resourceRetriever = new ResourceRetriever();
        List<PrivateObjectiveCard> result = new ArrayList<PrivateObjectiveCard>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = GameConstants.SP_PRIVATE_OBJECTIVES_COUNT;
        } else {
            count = numberOfPlayers;
        }

        //TODO: update serializzazione
        //List<PrivateObjectiveCard> cards = resourceRetriever.retrievePrivateObjectiveCards();
        List<PrivateObjectiveCard> cards = ObjectivesCreator.createPrivateObjectiveCards();

        while (countExtracted <= count) {
            int index = RandomHandler.retrieveRandom().nextInt(cards.size());
            PrivateObjectiveCard extracted = cards.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        if(shuffle)
            Collections.shuffle(result);

        return result;
    }
    public List<PrivateObjectiveCard> createPrivateObjectiveCards(int numberOfPlayers) {
        return createPrivateObjectiveCards(numberOfPlayers, true);
    }
    public List<ToolCard> createTools(int difficultyLevelSP, boolean shuffle) {
        ResourceRetriever resourceRetriever = new ResourceRetriever();
        List<ToolCard> result = new ArrayList<ToolCard>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = 6 - difficultyLevelSP; //da 1 a 5 a seconda della difficoltà a cui si vuole giocare
        } else {
            count = GameConstants.MP_TOOL_CARDS_COUNT;
        }

        //TODO: update serializzazione
        //List<ToolCard> cards = resourceRetriever.retrieveToolCards();
        List<ToolCard> cards = ToolsCreator.createToolCards();

        while (countExtracted <= count) {
            int index = RandomHandler.retrieveRandom().nextInt(cards.size());
            ToolCard extracted = cards.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        if (shuffle)
            Collections.shuffle(result);

        return result;
    }
    public List<ToolCard> createTools(int difficultyLevelSP) {
        return createTools(difficultyLevelSP, true);
    }

    //Creazione finestre
    public List<Window> createWindows(int numberOfPlayers, boolean shuffle) {
        ResourceRetriever resourceRetriever = new ResourceRetriever();
        List<Window> result = new ArrayList<Window>();
        int countExtracted = 1;

        //TODO: update serializzazione
        //List<Window> windows = resourceRetriever.retrieveWindows();
        List<Window> windows = WindowsCreator.createWindows();

        while (countExtracted <= numberOfPlayers*4) {
            int index = RandomHandler.retrieveRandom().nextInt(windows.size());
            Window extracted = windows.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        if (shuffle)
            Collections.shuffle(result);

        return result;
    }
    public List<Window> createWindows(int numberOfPlayers) {
        return createWindows(numberOfPlayers, true);
    }

    //Creazione sacco di dadi
    public List<Die> createDiceBag(boolean shuffle) {
        List<Die> diceBag = new ArrayList<Die>();

        for (int i = 1; i <= GameConstants.SINGLE_COLOR_DICE_COUNT; i++) {
            diceBag.add(new Die(DieColor.YELLOW));
            diceBag.add(new Die(DieColor.RED));
            diceBag.add(new Die(DieColor.GREEN));
            diceBag.add(new Die(DieColor.BLUE));
            diceBag.add(new Die(DieColor.PURPLE));

        }
        if (shuffle)
            Collections.shuffle(diceBag);

        return diceBag;
    }
    public List<Die> createDiceBag() {
        return createDiceBag(true);
    }

    //Creazione riserva dadi
    public List<Die> createDraftPool(List<Die> dieBag, int numberOfPlayers){
        List<Die> result = new ArrayList<Die>();

        //Aggiunge dadi a riserva e li toglie dal sacco
        if(numberOfPlayers == 1){
            for(int i = 0; i < GameConstants.SP_DRAFTPOOL_SIZE; i++){
                result.add(dieBag.get(i));
                dieBag.remove(i);
            }
        }
        else {
            for(int i = 0; i < 2 * numberOfPlayers + 1; i++){
                result.add(dieBag.get(0));
                dieBag.remove(0);
            }
        }

        return result;
    }

    //Creazione tracciato round
    public RoundTrack createRoundTrack(){
       List<List<Die>> diceStack = new ArrayList<List<Die>>();

       for (int i = 0; i<GameConstants.ROUNDS_COUNT; i++)
           diceStack.add(new ArrayList<Die>());

       RoundTrack result = new RoundTrack(diceStack);
       return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Factory partite multiplayer e singleplayer
    /**
     * Let subList method of List to be converted into serializable obect
     * @param windows Windows instances
     * @param fromIndex Start index of sub
     * @param toIndex End index of sub
     * @return
     */
    private static List<Window> selectWindows(List<Window> windows, int fromIndex, int toIndex) {
        List<Window> sub = windows.subList(fromIndex, toIndex);
        return new ArrayList<Window>(sub);
    }
    /**
     * Create new multiplayer match instance
     * @param users Partecipant users
     * @param seed Seed of the match
     * @return
     */
    public static MultiPlayerMatch createMultiPlayer(List<User> users, long seed) {
        //Verifica condizione sulla casualità
        boolean seeded = seed > 0;

        //Crea giocatori, finestre iniziali e le carte
        MatchCreator matchCreator = new MatchCreator(false);

        if (seeded)
            RandomHandler.createRandom(seed);
        else
            RandomHandler.createRandom();

        List<Player> players = new ArrayList<Player>();
        List<Window> extractedWindows = matchCreator.createWindows(users.size(), !seeded);
        List<PrivateObjectiveCard> extractedPrivateObjectiveCards = matchCreator.createPrivateObjectiveCards(users.size(), !seeded);
        List<PublicObjectiveCard> extractedPublicObjectiveCards = matchCreator.createPublicObjectiveCards(!seeded);
        List<ToolCard> extractedToolCards = matchCreator.createTools(0, !seeded);

        //Crea i giocatori e li assegna agli utenti, assegna finestra e carte obiettivo private
        for(int i = 0; i < users.size(); i++){
            List<Window> startWindows = selectWindows(extractedWindows, i * GameConstants.WINDOWS_FOR_PLAYER, i * GameConstants.WINDOWS_FOR_PLAYER + 4);
            PrivateObjectiveCard privateObjectiveCard = extractedPrivateObjectiveCards.get(i);

            Player player = new Player(users.get(i), null, startWindows ,privateObjectiveCard, 0);
            users.get(i).addPlayer(player);
            players.add(player);

        }

        //Crea tracciato, sacco e riserva dadi
        RoundTrack roundTrack = matchCreator.createRoundTrack();
        List<Die> diceBag = matchCreator.createDiceBag(!seeded);
        List<Die> draftPool = matchCreator.createDraftPool(diceBag, users.size());
        MatchDice matchDice = new MatchDice(users.size(),diceBag, draftPool);

        //Crea partita
        MultiPlayerMatch match = new MultiPlayerMatch(players, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);

        return match;
    }
    /**
     * Create new multiplayer match instance with default seed computation
     * @param users Partecipant users
     * @return
     */
    public static MultiPlayerMatch createMultiPlayer(List<User> users) {
        return createMultiPlayer(users, -1);
    }

    public static SinglePlayerMatch createSinglePlayer(User user, int difficultyLevelSP, long seed) {
        //Verifica condizione sulla casualità
        boolean seeded = seed > 0;

        //Crea giocatori, finestre iniziali e le carte
        MatchCreator matchCreator = new MatchCreator(true);

        if (seeded)
            RandomHandler.createRandom(seed);
        else
            RandomHandler.createRandom();

        //Crea giocatori, finestre iniziali e le carte
        List<Window> extractedWindows = matchCreator.createWindows(1);
        List<PrivateObjectiveCard> extractedPrivateObjectiveCards = matchCreator.createPrivateObjectiveCards(1);
        List<PublicObjectiveCard> extractedPublicObjectiveCards = matchCreator.createPublicObjectiveCards();
        List<ToolCard> extractedToolCards = matchCreator.createTools(difficultyLevelSP);

        //Crea i giocatore e lo assegna all'utente
        Player player = new Player(user, null, extractedWindows, extractedPrivateObjectiveCards, 0);
        user.addPlayer(player);

        //Crea tracciato, sacco e riserva dadi
        RoundTrack roundTrack = matchCreator.createRoundTrack();
        List<Die> diceBag = matchCreator.createDiceBag();
        List<Die> draftPool = matchCreator.createDraftPool(diceBag, 1);
        MatchDice matchDice = new MatchDice(1, diceBag, draftPool);

        //Crea partita
        SinglePlayerMatch match = new SinglePlayerMatch(player, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);
        return match;
    }
    public static SinglePlayerMatch createSinglePlayer(User user, int difficultyLevelSP) {
        return createSinglePlayer(user, difficultyLevelSP, -1);
    }
}