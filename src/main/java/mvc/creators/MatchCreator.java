package mvc.creators;

import mvc.model.objects.*;
import mvc.model.objects.Window;
import mvc.model.objects.enums.Tool;
import resources.ResourceRetriever;

import java.util.*;

public class MatchCreator {
    //Creatore di componenti del gioco

    private boolean singlePlayer;
    private Random random;

    //Costruttori
    public MatchCreator(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        this.random = new Random();
    }

    //Creazione carte
    public List<PublicObjectiveCard> createPublicObjectiveCards() {
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
            int index = random.nextInt(cards.size());
            PublicObjectiveCard extracted = cards.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(result);

        return result;
    }
    public List<PrivateObjectiveCard> createPrivateObjectiveCards(int numberOfPlayers) {
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
            int index = random.nextInt(cards.size());
            PrivateObjectiveCard extracted = cards.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(result);

        return result;
    }
    public List<ToolCard> createTools(int difficultyLevelSP) {
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
            int index = random.nextInt(cards.size());
            ToolCard extracted = cards.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(result);

        return result;
    }

    //Creazione finestre
    public List<Window> createWindows(int numberOfPlayers) {
        ResourceRetriever resourceRetriever = new ResourceRetriever();
        List<Window> result = new ArrayList<Window>();
        int countExtracted = 1;

        //TODO: update serializzazione
        //List<Window> windows = resourceRetriever.retrieveWindows();
        List<Window> windows = WindowsCreator.createWindows();

        while (countExtracted <= numberOfPlayers*4) {
            int index = random.nextInt(windows.size());
            Window extracted = windows.get(index);

            if (!result.contains(extracted)) {
                result.add(extracted);
                countExtracted++;
            }
        }
        Collections.shuffle(result);

        return result;
    }

    //Creazione sacco di dadi
    public List<Die> createDiceBag() {
        List<Die> diceBag = new ArrayList<Die>();

        for (int i = 1; i <= GameConstants.SINGLE_COLOR_DICE_COUNT; i++) {
            diceBag.add(new Die(GameConstants.YELLOW));
            diceBag.add(new Die(GameConstants.RED));
            diceBag.add(new Die(GameConstants.GREEN));
            diceBag.add(new Die(GameConstants.BLUE));
            diceBag.add(new Die(GameConstants.PURPLE));

        }
        Collections.shuffle(diceBag);
        return diceBag;
    }

    //Creazione riserva dadi
    public List<Die> createDraftPool(List<Die> dieBag, int numberOfPlayers){
        List<Die> result = new ArrayList<Die>();

        if(numberOfPlayers == 1){
            for(int i = 0; i < GameConstants.SP_DRAFTPOOL_SIZE; i++){
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

    //Creazione tracciato round
    public RoundTrack createRoundTrack(){
       List<List<Die>> diceStack = new ArrayList<List<Die>>();

       for (int i=0; i<GameConstants.ROUNDS_COUNT; i++)
           diceStack.add(new ArrayList<Die>());

       RoundTrack result = new RoundTrack(diceStack);
       return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Factory partite multiplayer e singleplayer
    public static Match createMultiPlayer(List<User> users) {
        //Crea giocatori, finestre iniziali e le carte
        MatchCreator matchCreator = new MatchCreator(false);
        List<Player> players = new ArrayList<Player>();
        List<Window> extractedWindows = matchCreator.createWindows(users.size());
        List<PrivateObjectiveCard> extractedPrivateObjectiveCards = matchCreator.createPrivateObjectiveCards(users.size());
        List<PublicObjectiveCard> extractedPublicObjectiveCards = matchCreator.createPublicObjectiveCards();
        List<ToolCard> extractedToolCards = matchCreator.createTools(0);

        //Crea i giocatori e li assegna agli utenti, assegna finestra e carte obiettivo private
        for(int i = 0; i < users.size(); i++){
            List<Window> startWindows = extractedWindows.subList(i * GameConstants.WINDOWS_FOR_PLAYER, i * GameConstants.WINDOWS_FOR_PLAYER + 4);
            PrivateObjectiveCard privateObjectiveCard = extractedPrivateObjectiveCards.get(i);

            Player player = new Player(users.get(i), null, startWindows ,privateObjectiveCard, 0);
            users.get(i).addPlayer(player);
            players.add(player);

        }

        //Crea tracciato, sacco e riserva dadi
        RoundTrack roundTrack = matchCreator.createRoundTrack();
        List<Die> diceBag = matchCreator.createDiceBag();
        List<Die> draftPool = matchCreator.createDraftPool(diceBag, users.size());
        MatchDice matchDice = new MatchDice(users.size(),diceBag, draftPool);

        //Crea partita
        Match match = new Match(players, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);

        return match;
    }
    public static Match createSinglePlayer(User user, int difficultyLevelSP) {
        //Crea giocatori, finestre iniziali e le carte
        MatchCreator matchCreator = new MatchCreator(true);
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
        Match match = new Match(player, extractedPublicObjectiveCards, extractedToolCards, matchDice, roundTrack);
        return match;
    }

}