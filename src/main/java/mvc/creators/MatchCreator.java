package mvc.creators;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;
import resources.ResourceRetriever;
import util.RandomHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creator of model's match instances
 */
public class MatchCreator {
    //Creatore di componenti del gioco

    private static ResourceRetriever resourceRetriever = new ResourceRetriever();
    private boolean singlePlayer;

    private static final Logger LOGGER = Logger.getLogger(MatchCreator.class.getName());

    //Costruttori
    /**
     * Create new match creator
     * @param singlePlayer If match to create is single player
     */
    public MatchCreator(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    //Creazione carte obiettivo pubblico
    /**
     * Create public objective cards
     * @param shuffle decide to shuffle or not
     * @return the list of extracted cards
     */
    private List<PublicObjectiveCard> createPublicObjectiveCards(boolean shuffle) {
        List<PublicObjectiveCard> result = new ArrayList<>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = GameConstants.SP_PUBLIC_OBJECTIVES_COUNT;
        } else {
            count = GameConstants.MP_PUBLIC_OBJECTIVES_COUNT;
        }

        List<PublicObjectiveCard> cards = null;
        try {
            cards = resourceRetriever.retrievePublicObjectiveCards();
        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING,ioe.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        }

        while (countExtracted <= count) {
            if(cards != null) {
                int index = RandomHandler.retrieveRandom().nextInt(cards.size());
                PublicObjectiveCard extracted = cards.get(index);
                if (!result.contains(extracted)) {
                    result.add(extracted);
                    countExtracted++;
                }
            }
        }
        if (shuffle)
            Collections.shuffle(result);

        return result;
    }
    /**
     * Support method to create public objective cards
     * @return list of extracted cards
     */
    public List<PublicObjectiveCard> createPublicObjectiveCards() {
        return createPublicObjectiveCards(true);
    }

    //Creazione carte obiettivo privato
    /**
     * Create private objective cards
     * @param numberOfPlayers players count
     * @param shuffle shuffle or not
     * @return list of extracted cards
     */
    private List<PrivateObjectiveCard> createPrivateObjectiveCards(int numberOfPlayers, boolean shuffle){
        List<PrivateObjectiveCard> result = new ArrayList<>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = GameConstants.SP_PRIVATE_OBJECTIVES_COUNT;
        } else {
            count = numberOfPlayers;
        }

        List<PrivateObjectiveCard> cards = null;
        try {
            cards = resourceRetriever.retrievePrivateObjectiveCards();
        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING,ioe.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        }

        while (countExtracted <= count) {
            if(cards != null) {
                int index = RandomHandler.retrieveRandom().nextInt(cards.size());
                PrivateObjectiveCard extracted = cards.get(index);

                if (!result.contains(extracted)) {
                    result.add(extracted);
                    countExtracted++;
                }
            }
        }
        if(shuffle)
            Collections.shuffle(result);

        return result;
    }
    /**
     * Support method to create private objective cards
     * @param numberOfPlayers players count
     * @return list of private objective cards depending on the number of players
     */
    public List<PrivateObjectiveCard> createPrivateObjectiveCards(int numberOfPlayers){
        return createPrivateObjectiveCards(numberOfPlayers, true);
    }

    //Creazione carte strumento
    /**
     * Create tool cards
     * @param difficultyLevelSP level of difficulty decided in the single player mode
     * @param shuffle shuffle or not
     * @return list of extracted cards
     */
    private List<ToolCard> createTools(int difficultyLevelSP, boolean shuffle) {
        List<ToolCard> result = new ArrayList<>();
        int countExtracted = 1;

        int count;
        if (singlePlayer) {
            count = 6 - difficultyLevelSP; //da 1 a 5 a seconda della difficoltà a cui si vuole giocare
        } else {
            count = GameConstants.MP_TOOL_CARDS_COUNT;
        }

        List<ToolCard> cards = null;
        try {
            cards = resourceRetriever.retrieveToolCards();
        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING,ioe.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        }

        while (countExtracted <= count) {
            if(cards != null) {
                int index = RandomHandler.retrieveRandom().nextInt(cards.size());
                ToolCard extracted = cards.get(index);

                if (!result.contains(extracted)) {
                    result.add(extracted);
                    countExtracted++;
                }
            }
        }
        if (shuffle)
            Collections.shuffle(result);

        return result;
    }
    /**
     * Support method to create tool cards
     * @param difficultyLevelSP difficulty level of single player
     * @return list of extracted cards
     */
    public List<ToolCard> createTools(int difficultyLevelSP) {
        return createTools(difficultyLevelSP, true);
    }

    //Creazione finestre
    /**
     * Create the windows among which choose
     * @param numberOfPlayers players count
     * @param shuffle shuffle or not
     * @return list of extracted windows
     */
    private List<Window> createWindows(int numberOfPlayers, boolean shuffle) {
        List<Window> result = new ArrayList<>();
        int countExtracted = 1;

        List<Window> windows = null;
        try {
            windows = resourceRetriever.retrieveWindows();
        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING,ioe.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        }

        while (countExtracted <= numberOfPlayers*4) {
            if(windows != null) {
                int index = RandomHandler.retrieveRandom().nextInt(windows.size());
                Window extracted = windows.get(index);
                if (!result.contains(extracted)) {
                    result.add(extracted);
                    countExtracted++;
                }
            }
        }
        if (shuffle)
            Collections.shuffle(result);

        return result;
    }
    /**
     * Support method to create initial windows
     * @param numberOfPlayers players count
     * @return list of extracted windows
     */
    public List<Window> createWindows(int numberOfPlayers) {
        return createWindows(numberOfPlayers, true);
    }

    //Creazione sacco di dadi
    /**
     * Create the dice bag with 90 dice
     * @param shuffle shuffle or not
     * @return list of dice
     */
    private List<Die> createDiceBag(boolean shuffle) {
        List<Die> diceBag = new ArrayList<>();

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
    /**
     * Support method to create dice bag
     * @return list with all the dice of dicebag
     */
    public List<Die> createDiceBag() {
        return createDiceBag(true);
    }

    //Creazione riserva dadi
    /**
     * Create draft pool
     * @param dieBag dice among which extract draft pool
     * @param numberOfPlayers player count
     * @return list of dice
     */
    public List<Die> createDraftPool(List<Die> dieBag, int numberOfPlayers){
        List<Die> result = new ArrayList<>();

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
    /**
     * Create round track
     * @return object round track
     */
    private RoundTrack createRoundTrack(){
       List<List<Die>> diceStack = new ArrayList<>();

       for (int i = 0; i<GameConstants.ROUNDS_COUNT; i++)
           diceStack.add(new ArrayList<Die>());

       return new RoundTrack(diceStack);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Factory partite multiplayer e singleplayer
    /**
     * Let subList method of List to be converted into serializable obect
     * @param windows Windows instances
     * @param fromIndex Start index of sub
     * @param toIndex End index of sub
     * @return list of selected windows
     */
    private static List<Window> selectWindows(List<Window> windows, int fromIndex, int toIndex) {
        List<Window> sub = windows.subList(fromIndex, toIndex);
        return new ArrayList<>(sub);
    }
    /**
     * Create new multiplayer match instance
     * @param users Partecipant users
     * @param seed Seed of the match
     * @return the match
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

        List<Player> players = new ArrayList<>();
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
        return new MultiPlayerMatch(players, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);
    }
    /**
     * Create new multiplayer match instance with default seed computation
     * @param users Partecipant users
     * @return the match
     */
    public static MultiPlayerMatch createMultiPlayer(List<User> users) {
        return createMultiPlayer(users, -1);
    }

    /**
     * Create new single player match instance
     * @param user user who plays
     * @param difficultyLevelSP difficulty level of the game
     * @param seed seed of the match
     * @return the match
     */
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
        return new SinglePlayerMatch(player, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);
    }
    /**
     * Create new single player match instance with default seed computation
     * @param user user who plays
     * @param difficultyLevelSP difficulty level of the game
     * @return the match
     */
    public static SinglePlayerMatch createSinglePlayer(User user, int difficultyLevelSP){
        return createSinglePlayer(user, difficultyLevelSP, -1);
    }
}