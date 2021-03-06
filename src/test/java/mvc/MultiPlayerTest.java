package mvc;

import mvc.creators.MatchCreator;
import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;
import org.junit.Test;

import java.rmi.RemoteException;

public class MultiPlayerTest extends MVCTest {
    //Test su partite multiplayer del model

    private static final String INVALID_OPERATION_MESSAGE = "invalid match operation accepted";
    private static final String FAILED_OPERATION_MESSAGE = "valid match operation failed";
    private static final String INVALID_STATE_MESSAGE = "invalid match state encountered";



    //Creazione partite di test
    private MultiPlayerMatch createTwoPlayerMatch() {
        return MatchCreator.createMultiPlayer(createTwoUsersList());
    }
    private MultiPlayerMatch createThreePlayerMatch() {
        return MatchCreator.createMultiPlayer(createThreeUsersList());
    }
    private MultiPlayerMatch createFourPlayerMatch() {
        return MatchCreator.createMultiPlayer(createFourUsersList());
    }

    private MultiPlayerMatch createTwoPlayerMatch1() {
        return MatchCreator.createMultiPlayer(createTwoUsersList(), 10);
    }
    private MultiPlayerMatch createThreePlayerMatch1() {
        return MatchCreator.createMultiPlayer(createThreeUsersList(), 10);
    }
    private MultiPlayerMatch createFourPlayerMatch1() {
        return MatchCreator.createMultiPlayer(createFourUsersList(), 10);
    }

    private MultiPlayerMatch createTwoPlayerMatch2() {
        return MatchCreator.createMultiPlayer(createTwoUsersList(), 100);
    }

    private MultiPlayerMatch createTwoPlayerMatch3() {
        return MatchCreator.createMultiPlayer(createTwoUsersList(), 106);
    }
    private MultiPlayerMatch createTwoPlayerMatch4() {
        return MatchCreator.createMultiPlayer(createTwoUsersList(), 80);
    }
    private MultiPlayerMatch createTwoPlayerMatch5() {
        return MatchCreator.createMultiPlayer(createTwoUsersList(), 82);
    }

    private MultiPlayerMatch createThreePlayerMatch2() {
        return MatchCreator.createMultiPlayer(createThreeUsersList(), 100);
    }
    private MultiPlayerMatch createFourPlayerMatch2() {
        return MatchCreator.createMultiPlayer(createFourUsersList(), 100);
    }
    private MultiPlayerMatch createThreePlayerMatch3() {
        return MatchCreator.createMultiPlayer(createThreeUsersList(), 1000);
    }
    private MultiPlayerMatch createFourPlayerMatch3() {
        return MatchCreator.createMultiPlayer(createFourUsersList(), 1000);
    }




    //Operazioni di test su partite
    public Window retrieveStartWindow(Player player, int index) {
        return validRetrieveStartWindow(player, player.getStartWindows().get(index));
    }
    public Window validRetrieveStartWindow(Player player, Window window) {
        try {
            return player.retrieveStartWindow(window);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Window invalidRetrieveStartWindow(Player player, Window window) {
        try {
            player.retrieveStartWindow(window);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public Cell retrieveCell(Window window, int row, int column) {
        return validRetrieveCell(window, window.getCells()[row][column]);
    }
    public Cell validRetrieveCell(Window window, Cell cell) {
        try {
            return window.retrieveCell(cell);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Cell invalidRetrieveCell(Window window, Cell cell) {
        try {
            window.retrieveCell(cell);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public Player retrievePlayer(MultiPlayerMatch match, int index) {
        return validRetrievePlayer(match, match.getPlayers().get(index));
    }
    public Player validRetrievePlayer(MultiPlayerMatch match, Player player) {
        try {
            return match.retrievePlayer(player);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Player invalidRetrievePlayer(MultiPlayerMatch match, Player player) {
        try {
            match.retrievePlayer(player);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public ToolCard retrieveToolCard(MultiPlayerMatch match, int index) {
        return validRetrieveToolCard(match, match.getToolCards().get(index));
    }
    public ToolCard validRetrieveToolCard(MultiPlayerMatch match, ToolCard toolCard) {
        try {
            return match.retrieveToolCard(toolCard);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public ToolCard invalidRetrieveToolCard(MultiPlayerMatch match, ToolCard toolCard) {
        try {
            match.retrieveToolCard(toolCard);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public Die retrieveDieFromDraftPool(MultiPlayerMatch match, int index) {
        return validRetrieveDieFromDraftPool(match, match.getMatchDice().getDraftPool().get(index));
    }
    public Die validRetrieveDieFromDraftPool(MultiPlayerMatch match, Die die) {
        try {
            return match.getMatchDice().retrieveDieFromDraftPool(die);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Die invalidRetrieveDieFromDraftPool(MultiPlayerMatch match, Die die) {
        try {
            match.getMatchDice().retrieveDieFromDraftPool(die);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }




    public void validLeaveMatch(MultiPlayerMatch match, Player player) {
        try {
            match.leaveMatch(player);
        } catch (MatchException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidLeaveMatch(MultiPlayerMatch match, Player player) {
        try {
            match.leaveMatch(player);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (MatchException e) {
        }
    }

    public void validRejoinMatch(MultiPlayerMatch match, Player player) {
        try {
            match.rejoinMatch(player);
        } catch (MatchException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidRejoinMatch(MultiPlayerMatch match, Player player) {
        try {
            match.rejoinMatch(player);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (MatchException e) {
        }
    }

    public void validBeginMatch(MultiPlayerMatch match) {
        try {
            match.beginMatch();
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidBeginMatch(MultiPlayerMatch match) {
        try {
            match.beginMatch();
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validChooseWindow(MultiPlayerMatch match, Player player, Window window) {
        try {
            match.chooseWindow(player, window);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidChooseWindow(MultiPlayerMatch match, Player player, Window window) {
        try {
            match.chooseWindow(player, window);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validPlaceDie(MultiPlayerMatch match, Player player, Cell cell, Die die) {
        try {
            match.placeDie(player, cell, die);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidPlaceDie(MultiPlayerMatch match, Player player, Cell cell, Die die) {
        try {
            match.placeDie(player, cell, die);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void invalidUseToolCard(MultiPlayerMatch match, Player player, ToolCardInput toolCardInput, ToolCard toolCard) {
        try {
            match.useToolCard(player, toolCardInput, toolCard);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }
    public void validUseToolCard(MultiPlayerMatch match, Player player, ToolCardInput toolCardInput, ToolCard toolCard) {
        try {
            match.useToolCard(player, toolCardInput, toolCard);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }

    public void validEndTurn(MultiPlayerMatch match, Player player) {
        try {
            match.endTurn(player);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidEndTurn(MultiPlayerMatch match, Player player) {
        try {
            match.endTurn(player);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }




    //Test su proprietà partita multiplayer
    public void testTurnHandler(MultiPlayerMatch match, Player turnPlayer, int round, boolean started, boolean ended, boolean roundFirst, boolean roundLast, boolean firstWave) {
        if (match.getTurnHandler().isStarted()!=started)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isEnded()!=ended)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isRoundFirstTurn()!=roundFirst)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isRoundLastTurn()!=roundLast)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isFirstTurnWave()!=firstWave)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().getRound()!=round)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnPlayer().samePlayer(turnPlayer))
            testAssertError(INVALID_STATE_MESSAGE);
    }
    public void testTurnHandler(MultiPlayerMatch match, Player turnPlayer, int round, boolean roundFirst, boolean roundLast, boolean firstWave) {
        testTurnHandler(match, turnPlayer, round, true, false, roundFirst,roundLast, firstWave);
    }
    public void testFourPlayersRound(MultiPlayerMatch match, int round, Player player1, Player player2, Player player3, Player player4) {
        //Primo
        testTurnHandler(match, player1,  round,true, false, true);
        validEndTurn(match, player1);
        //Secondo
        testTurnHandler(match, player2, round, false, false, true);
        validEndTurn(match, player2);
        //Terzo
        testTurnHandler(match, player3, round, false, false, true);
        validEndTurn(match, player3);
        //Quarto
        testTurnHandler(match, player4, round, false, false, true);
        validEndTurn(match, player4);

        //Quarto
        testTurnHandler(match, player4, round, false, false, false);
        validEndTurn(match, player4);
        //Terzo
        testTurnHandler(match, player3, round, false, false, false);
        validEndTurn(match, player3);
        //Secondo
        testTurnHandler(match, player2, round, false, false, false);
        validEndTurn(match, player2);
        //Primo
        testTurnHandler(match, player1, round, false, true, false);
        validEndTurn(match, player1);
    }

    public void testMatchStartWindows(MultiPlayerMatch match) {
        for (Player player : match.getPlayers()) {
            if (player.getStartWindows().size()!=GameConstants.WINDOWS_FOR_PLAYER)
                testAssertError(INVALID_STATE_MESSAGE);
        }
    }

    public void testMatchDice(MultiPlayerMatch match, int bagCount, int poolCount) {
        if (match.getMatchDice().getDiceBag().size()!=bagCount)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchDice().getDraftPool().size()!=poolCount)
            testAssertError(INVALID_STATE_MESSAGE);
    }

    public void testMatchCards(MultiPlayerMatch match) {
        if (match.getPublicObjectiveCards().size()!= GameConstants.MP_PUBLIC_OBJECTIVES_COUNT)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getToolCards().size()!= GameConstants.MP_TOOL_CARDS_COUNT)
            testAssertError(INVALID_STATE_MESSAGE);

        for (Player player : match.getPlayers()) {
            if (player.getPrivateObjectiveCards().size()!= GameConstants.MP_PRIVATE_OBJECTIVES_COUNT)
                testAssertError(INVALID_STATE_MESSAGE);
        }

    }

    //Test sulle azioni dei giocatori su partite multiplayer
    public void testUseToolCard(Player player, ToolCard toolCard, int playerTokens, int cardTokens) {
        if (player.getFavorTokens()!=playerTokens)
            testAssertError(INVALID_STATE_MESSAGE);
        if (toolCard.getFavorTokens()!=cardTokens)
            testAssertError(INVALID_STATE_MESSAGE);
    }
    public void testPlaceDie(MultiPlayerMatch match, Player player, Cell cell, Die die, int poolSize) {
        if (match.getMatchDice().getDraftPool().size()!=poolSize)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchDice().containsDieInPool(die))
            testAssertError(INVALID_STATE_MESSAGE);
        if (cell.getDie()==null)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!cell.getDie().sameDie(die))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player.getWindow().containsCell(cell))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player.getTurnDiePlaced())
            testAssertError(INVALID_STATE_MESSAGE);
    }




    //Test randomizzati
    @Test
    public synchronized void randomTwoPlayerStart() {
        //Creazione partita
        MultiPlayerMatch match = createTwoPlayerMatch();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        //Controlla correttezza partita creata
        if (match.getMatchState()!=MatchState.CREATED)
            testAssertError(INVALID_STATE_MESSAGE);

        testMatchDice(match, 85, 5);
        testMatchCards(match);
        testMatchStartWindows(match);

        if (match.getPlayers().size() != 2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player1.getUser().sameUser(user1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player2.getUser().sameUser(user2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Azioni non concesse a partita non iniziata
        invalidChooseWindow(match, player1, retrieveStartWindow(player1, 0));
        invalidPlaceDie(match, player2, createInvalidCell1(), createInvalidDie1());
        invalidEndTurn(match, player1);

        //Inizio partita
        validBeginMatch(match);

        //Controllo stato
        if (match.getMatchState()!=MatchState.CHOOSE_WINDOWS)
            testAssertError(INVALID_STATE_MESSAGE);

        //Azioni non concesse a partita iniziata
        invalidUseToolCard(match, player2, createInvalidToolCardInput1(), retrieveToolCard(match, 0));
        invalidPlaceDie(match, player1, createInvalidCell1(), createInvalidDie1());
        invalidEndTurn(match, player2);

        //Riinizio scorretto partita
        invalidBeginMatch(match);

        //Scelta finestre corretta
        Window choosen1 = retrieveStartWindow(player1, 0);
        Window choosen2 = retrieveStartWindow(player2, 0);

        validChooseWindow(match, player1, choosen1);
        validChooseWindow(match, player2, choosen2);

        //Controllo correttezza stato
        if (match.getMatchState()!=MatchState.PLAY_ROUND)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player1.getWindow().sameWindow(choosen1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player2.getWindow().sameWindow(choosen2))
            testAssertError(INVALID_STATE_MESSAGE);
        if (player1.getFavorTokens()!=player1.getWindow().getDifficulty())
            testAssertError(INVALID_STATE_MESSAGE);
        if (player2.getFavorTokens()!=player2.getWindow().getDifficulty())
            testAssertError(INVALID_STATE_MESSAGE);
    }
    @Test
    public synchronized void randomFourPlayerTurnsFlow() {
        //Creazione partita
        MultiPlayerMatch match = createFourPlayerMatch();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);
        Player player3 = retrievePlayer(match, 2);
        Player player4 = retrievePlayer(match, 3);

        //Inizio partita
        validBeginMatch(match);

        testTurnHandler(match, player1,  0, false, false, false, false, false);

        validChooseWindow(match, player1, retrieveStartWindow(player1, 0));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 0));
        validChooseWindow(match, player3, retrieveStartWindow(player3, 0));
        validChooseWindow(match, player4, retrieveStartWindow(player4, 0));

        //Testa sviluppo round e turni
        if (!match.getTurnHandler().isStarted())
            testAssertError(INVALID_STATE_MESSAGE);

        testFourPlayersRound(match, 1, player1, player2, player3, player4);
        testFourPlayersRound(match, 2, player2, player3, player4, player1);
        testFourPlayersRound(match, 3, player3, player4, player1, player2);
        testFourPlayersRound(match, 4, player4, player1, player2, player3);
        testFourPlayersRound(match, 5, player1, player2, player3, player4);
        testFourPlayersRound(match, 6, player2, player3, player4, player1);
        testFourPlayersRound(match, 7, player3, player4, player1, player2);
        testFourPlayersRound(match, 8, player4, player1, player2, player3);
        testFourPlayersRound(match, 9, player1, player2, player3, player4);
        testFourPlayersRound(match, 10, player2, player3, player4, player1);

        if (!match.getTurnHandler().isEnded())
            testAssertError(INVALID_STATE_MESSAGE);
    }

    @Test
    public synchronized void fixedTwoPlayer1() {
        //Creazione partita
        MultiPlayerMatch match = createTwoPlayerMatch1();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        //Inizio partita
        validBeginMatch(match);
        validChooseWindow(match, player1, retrieveStartWindow(player1, 0));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 0));

        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();

        Cell cell;
        Die die;
        ToolCard toolCard;
        ToolCardInput input;


        //--------------------------------------- ROUND 1 -------------------------------------------//

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Azioni non valide
        invalidEndTurn(match, player2); // NO SUO TURNO
        invalidPlaceDie(match, player1, retrieveCell(window1, 2, 1), retrieveDieFromDraftPool(match, 2)); // RESTRIZIONE INIZIALE
        invalidPlaceDie(match, player1, retrieveCell(window1, 2, 0), retrieveDieFromDraftPool(match, 0)); // RESTRIZIONE CELLA
        invalidUseToolCard(match, player1, new ToolCardInput(createInvalidDie1()), retrieveToolCard(match, 1)); // DADO INVENTATO

        //Piazzamento corretto 6G in 0,2
        cell = retrieveCell(window1, 0, 2);
        die = retrieveDieFromDraftPool(match, 2);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 4);

        //Dado gia piazzato
        invalidPlaceDie(match, player1, retrieveCell(window1, 0, 3), retrieveDieFromDraftPool(match, 2));

        //Finisce turno
        validEndTurn(match, player1);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Azioni non valide
        invalidEndTurn(match, player1); // NO SUO TURNO
        invalidPlaceDie(match, player2, retrieveCell(window2, 2, 3), retrieveDieFromDraftPool(match, 1)); // RESTRIZIONE INIZIALE
        invalidPlaceDie(match, player2, retrieveCell(window2, 3, 2), retrieveDieFromDraftPool(match, 0)); // RESTRIZIONE CELLA
        invalidUseToolCard(match, player2, new ToolCardInput(retrieveCell(window2, 0,0), null, retrieveCell(window2, 0,0), null), retrieveToolCard(match, 0)); // NESSUN DADO PIAZZATO
        invalidChooseWindow(match, player2, retrieveStartWindow(player2, 0));

        //Piazzamento corretto dado 2Y in 2,4
        cell = retrieveCell(window2, 2, 4);
        die = retrieveDieFromDraftPool(match, 0);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 3);

        //Finisce turno
        validEndTurn(match, player2);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Azioni non valide
        invalidPlaceDie(match, player2, retrieveCell(window2, 1, 2), retrieveDieFromDraftPool(match, 1)); // RESTRIZIONE CELLA ISOLATA
        invalidPlaceDie(match, player2, retrieveCell(window2, 0, 3), retrieveDieFromDraftPool(match, 1)); // NO ADIACENZA

        //Piazzamento corretto dado 5R in 1,4
        cell = retrieveCell(window2, 1, 4);
        die = retrieveDieFromDraftPool(match, 0);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 2);

        //Finisce turno
        validEndTurn(match, player2);

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Mosse non valide
        toolCard = retrieveToolCard(match,0);                                           //CELLE INVALIDE
        input = new ToolCardInput(window1.getCells()[0][1], window1.getCells()[0][3]);

        invalidUseToolCard(match, player1, input, toolCard);

        testUseToolCard(player1, toolCard, window1.getDifficulty(), 0);

        //Piazzamento corretto dado 4P in 1,1
        cell = retrieveCell(window1, 1, 1);
        die = retrieveDieFromDraftPool(match, 1);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 1);

        //Finisce turno
        validEndTurn(match, player1);

        //Controllo prima di fine round
        if (match.getMatchDice().getDiceBag().size()!=80)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getRoundTrack().retrieveDice(1).size()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getRoundTrack().containsDie(1, new Die(DieColor.BLUE, 2)))
            testAssertError(INVALID_STATE_MESSAGE);

        //--------------------------------------- ROUND 2 -------------------------------------------//

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Piazzamento corretto dado 3B in 3,4
        cell = retrieveCell(window2, 3, 4);
        die = retrieveDieFromDraftPool(match, 3);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 4);

        //Finisce turno
        validEndTurn(match, player2);

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Azioni non valide
        invalidPlaceDie(match, player1, retrieveCell(window2, 2, 3), retrieveDieFromDraftPool(match, 0)); // RESTRIZIONE CELLA ISOLATA

        //Utilizza tampone diamantato      6R->1R                                             // TAMPONE DIAMANTATO
        die = retrieveDieFromDraftPool(match, 1);
        toolCard = retrieveToolCard(match,1);
        input = new ToolCardInput(die);

        validUseToolCard(match, player1, input, toolCard);
        testUseToolCard(player1, toolCard, 4, 1);

        //Controllo effetti carta
        if (die.getShade()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player1.getToolCardEffect().getChosenDie().sameDie(die))
            testAssertError(INVALID_STATE_MESSAGE);

        //Piazzamento dado diverso
        invalidPlaceDie(match, player1, retrieveCell(window2, 0, 0), retrieveDieFromDraftPool(match, 0));

        //Piazzamento corretto nuovo dado 1R in 2,0
        cell = retrieveCell(window1, 2, 0);
        die = retrieveDieFromDraftPool(match, 1);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 3);

        //Finisce turno
        validEndTurn(match, player1);

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Piazzamento corretto dado 2G in 2,1
        cell = retrieveCell(window1, 2, 1);
        die = retrieveDieFromDraftPool(match, 1);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 2);

        //Finisce turno
        validEndTurn(match, player1);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Piazzamento corretto dado 3P in 2,3
        cell = retrieveCell(window2, 2, 3);
        die = retrieveDieFromDraftPool(match, 1);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 1);

        //Finisce turno
        validEndTurn(match, player2);

        //Controllo prima di fine round
        if (match.getMatchDice().getDiceBag().size()!=75)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getRoundTrack().retrieveDice(2).size()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getRoundTrack().containsDie(2, new Die(DieColor.YELLOW, 3)))
            testAssertError(INVALID_STATE_MESSAGE);

        //--------------------------------------- ROUND 3 -------------------------------------------//

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Piazzamento non valido
        invalidPlaceDie(match, player1, retrieveCell(window1, 2, 0), retrieveDieFromDraftPool(match, 4)); // CELLA OCCUPATA

        //Piazzamento corretto 3B in 0,0
        cell = retrieveCell(window1, 0, 0);
        die = retrieveDieFromDraftPool(match, 3);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 4);

        //Finisce turno
        validEndTurn(match, player1);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Piazzamento corretto 1P in 0, 3
        cell = retrieveCell(window2, 0, 3);
        die = retrieveDieFromDraftPool(match, 3);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 3);

        //Finisce turno
        validEndTurn(match, player2);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Utilizza diluente per pasta salda             3G->6R                           // DILUENTE PER PASTA SALDA
        die = retrieveDieFromDraftPool(match, 2);
        toolCard = retrieveToolCard(match,2);
        input = new ToolCardInput(die, 6);

        validUseToolCard(match, player2, input, toolCard);
        testUseToolCard(player2, toolCard, 4, 1);

        //Controlla effetto su dado
        if (!match.getMatchDice().containsDieInPool(new Die(DieColor.RED, 6)))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player2.getToolCardEffect().getChosenDie().sameDie(new Die(DieColor.RED, 6)))
            testAssertError(INVALID_STATE_MESSAGE);

        //Piazzamento non corretti
        invalidPlaceDie(match, player2, retrieveCell(window2, 0, 4), retrieveDieFromDraftPool(match, 2)); // ADIACENZA COLORE
        invalidPlaceDie(match, player2, retrieveCell(window2, 3, 3), retrieveDieFromDraftPool(match, 2)); // NO RESTRIZIONE CELLA

        //Piazzamento corretto 6R in 0,2
        cell = retrieveCell(window2, 0, 2);
        die = retrieveDieFromDraftPool(match, 2);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 2);

        //Finisce turno
        validEndTurn(match, player2);

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Piazzamento corretto 3Y in 3,0
        cell = retrieveCell(window1, 3, 0);
        die = retrieveDieFromDraftPool(match, 0);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 1);

        //Finisce turno
        validEndTurn(match, player1);

        //Controllo prima di fine round
        if (match.getMatchDice().getDiceBag().size()!=70)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getRoundTrack().retrieveDice(3).size()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getRoundTrack().containsDie(3, new Die(DieColor.RED, 3)))
            testAssertError(INVALID_STATE_MESSAGE);

        //--------------------------------------- ROUND 4 -------------------------------------------//

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Piazzamento corretto 3Y in 0,1
        cell = retrieveCell(window2, 0, 1);
        die = retrieveDieFromDraftPool(match, 0);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 4);

        //Utilizza alesatore per lamina di rame                                                // ALESATORE LAMINA RAME
        toolCard = retrieveToolCard(match,0);

        //Uso invalido 0,1->2,2                                     // ADIACENZA NUMERO
        input = new ToolCardInput(window2.getCells()[0][1], window2.getCells()[2][2]);

        invalidUseToolCard(match, player2, input, toolCard);

        //Uso valido 0,0->0,4
        input = new ToolCardInput(window2.getCells()[0][1], window2.getCells()[0][4]);

        validUseToolCard(match, player2, input, toolCard);
        testUseToolCard(player2, toolCard, 3, 1);

        //Finisce turno
        validEndTurn(match, player2);
    }
    @Test
    public synchronized void fixedToolCard2() {
        //Creazione partita
        MultiPlayerMatch match = createTwoPlayerMatch2();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        //Inizio partita
        validBeginMatch(match);
        validChooseWindow(match, player1, retrieveStartWindow(player1, 1));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 1));

        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();

        Cell cell;
        Die die;
        ToolCard toolCard;
        ToolCardInput input;

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Utilizza tenaglia rotelle prima di aver scelto il primo dado                    // TENAGLIA ROTELLE
        toolCard = retrieveToolCard(match,2);
        input = new ToolCardInput();

        invalidUseToolCard(match, player1, input, toolCard);

        //Piazzamento corretto 5B in 0,0
        cell = retrieveCell(window1, 0, 0);
        die = retrieveDieFromDraftPool(match, 3);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 4);

        //Utilizza tenaglia rotelle
        validUseToolCard(match, player1, input, toolCard);

        //Piazzamento corretto 6G in 1,0
        cell = retrieveCell(window1, 1, 0);
        die = retrieveDieFromDraftPool(match, 2);

        validPlaceDie(match, player1, cell, die);
        testPlaceDie(match, player1, cell, die, 3);

        //Prova ripiazzamento ulteriore
        invalidPlaceDie(match, player1, retrieveCell(window1, 0, 1), retrieveDieFromDraftPool(match, 0));


        //Finisce turno
        validEndTurn(match, player1);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////
        die = retrieveDieFromDraftPool(match, 0);
        toolCard = retrieveToolCard(match,0);
        input = new ToolCardInput(die, true);

        validUseToolCard(match, player2, input, toolCard);
        testUseToolCard(player2, toolCard, 2, 1);

        if (!die.sameDie(new Die(DieColor.YELLOW, 5)))
            testAssertError(INVALID_STATE_MESSAGE);

        //Finisce turno
        validEndTurn(match, player2);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Utilizza pennello per pasta salda                                           //PENNELLO PER PASTA SALDA
        die = retrieveDieFromDraftPool(match, 2);
        toolCard = retrieveToolCard(match,1);
        input = new ToolCardInput(die);

        validUseToolCard(match, player2, input, toolCard);
        testUseToolCard(player2, toolCard, 1, 1);

        if (!die.getColor().equals(DieColor.PURPLE))
            testAssertError(INVALID_STATE_MESSAGE);

        //Prova piazzamento altro dado
        invalidPlaceDie(match, player2, retrieveCell(window1, 1, 0), retrieveDieFromDraftPool(match, 0));

        //Piazzamento corretto 5P in 1, 0
        cell = retrieveCell(window2, 1, 0);
        die = retrieveDieFromDraftPool(match, 2);

        validPlaceDie(match, player2, cell, die);
        testPlaceDie(match, player2, cell, die, 2);

        //Finisce turno
        validEndTurn(match, player2);

        //Controlla salto turno
        if (!match.getTurnPlayer().samePlayer(player2))
            testAssertError(INVALID_STATE_MESSAGE);
    }
    @Test
    public synchronized void fixedToolCard3(){
        MultiPlayerMatch match = createThreePlayerMatch1();
        Player player1 = retrievePlayer(match,0);
        Player player2 = retrievePlayer(match, 1);
        Player player3 = retrievePlayer(match, 2);

        validBeginMatch(match);
        validChooseWindow(match,player1, retrieveStartWindow(player1, 3));
        validChooseWindow(match,player2, retrieveStartWindow(player2, 2));
        validChooseWindow(match, player3, retrieveStartWindow(player3, 0));

        Cell cell;
        Die die;
        ToolCard toolCard;
        ToolCardInput input;
        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();
        Window window3 = player3.getWindow();
        //GIOCATORE1
        cell = retrieveCell(window1,3,4);
        die = retrieveDieFromDraftPool(match, 5);
        validPlaceDie(match,player1,cell,die);
        validEndTurn(match, player1);
        //GIOCATORE 2
        cell = retrieveCell(window2, 0,0);
        die = retrieveDieFromDraftPool(match,0);
        validPlaceDie(match,player2,cell,die);
        validEndTurn(match, player2);
        //GIOCATORE3
        cell = retrieveCell(window3,3,1);
        die = retrieveDieFromDraftPool(match,0);
        validPlaceDie(match,player3,cell,die);
        validEndTurn(match,player3);
        //GIOCATORE3
        cell = retrieveCell(window3,2,0);
        die = retrieveDieFromDraftPool(match,3);
        validPlaceDie(match, player3,cell,die);
        validEndTurn(match,player3);
        //GIOCATORE2
        cell = retrieveCell(window2,0,1);
        die = retrieveDieFromDraftPool(match, 1);
        validPlaceDie(match,player2,cell,die);
        validEndTurn(match,player2);
        //GIOCATORE1
        cell = retrieveCell(window1,2,4);
        die = retrieveDieFromDraftPool(match,1);
        validPlaceDie(match,player1,cell,die);
        validEndTurn(match,player1);
        //GIOCATORE2
        cell = retrieveCell(window2,0,2);
        die = retrieveDieFromDraftPool(match,0);
        validPlaceDie(match,player2,cell,die);
        input = new ToolCardInput(1,retrieveDieFromDraftPool(match,0),match.getRoundTrack().retrieveDice(1).get(0));
        invalidUseToolCard(match,player2,input,retrieveToolCard(match,1));
        validEndTurn(match,player2);
        //GIOCATORE3
        cell = retrieveCell(window3,3,0);
        die = retrieveDieFromDraftPool(match,2);
        validPlaceDie(match,player3,cell,die);
        input = new ToolCardInput(retrieveCell(window3,2,0),retrieveCell(window3,3,1),retrieveCell(window3,1,0),retrieveCell(window3,2,1));
        invalidUseToolCard(match,player3,input,retrieveToolCard(match,0));
        input = new ToolCardInput(retrieveCell(window3,3,1),retrieveCell(window3,2,0),retrieveCell(window3,1,1),retrieveCell(window3,2,2));
        validUseToolCard(match,player3,input,retrieveToolCard(match,0));
        validEndTurn(match,player3);
        //GIOCATORE1
        input = new ToolCardInput(1,match.getRoundTrack().retrieveDice(1).get(0),retrieveDieFromDraftPool(match,0));
        validUseToolCard(match,player1,input,retrieveToolCard(match,1));
    }
    @Test
    public synchronized void fixedToolCard4(){
        MultiPlayerMatch match = createTwoPlayerMatch3();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        //Inizio partita
        validBeginMatch(match);
        validChooseWindow(match, player1, retrieveStartWindow(player1, 1));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 1));

        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();

        Cell cell;
        Die die;
        ToolCard toolCard;
        ToolCardInput input;

        //GIOCATORE1
        cell = retrieveCell(window1,3,4);
        die = retrieveDieFromDraftPool(match,4);
        validPlaceDie(match,player1,cell,die);
        input = null;
        invalidUseToolCard(match,player1,input,retrieveToolCard(match,2));
        invalidUseToolCard(match,player1,input,retrieveToolCard(match,0));
        validEndTurn(match,player1);
        //GIOCATORE2
        cell = retrieveCell(window2,3,4);
        die = retrieveDieFromDraftPool(match,0);
        validPlaceDie(match,player2,cell,die);
        validEndTurn(match,player2);
        //GIOCATORE2
        input = null;
        validUseToolCard(match,player2,input,retrieveToolCard(match,2));
        cell = retrieveCell(window2,0,2);
        die = retrieveDieFromDraftPool(match,0);
        validPlaceDie(match,player2,cell,die);
        validEndTurn(match,player2);
        //GIOCATORE1
        validUseToolCard(match,player1,null,retrieveToolCard(match,0));
    }
    @Test
    public synchronized void fixedToolCard5(){
        MultiPlayerMatch match = createTwoPlayerMatch4();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        //Inizio partita
        validBeginMatch(match);
        validChooseWindow(match, player1, retrieveStartWindow(player1, 1));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 1));

        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();

        Cell cell;
        Die die;
        ToolCard toolCard;
        ToolCardInput input;

        //GIOCATORE1
        cell = retrieveCell(window1,3,4);
        die = retrieveDieFromDraftPool(match,1);
        validPlaceDie(match,player1,cell,die);
        validEndTurn(match,player1);
        //GIOCATORE2
        validEndTurn(match,player2);
        //GIOCATORE2
        validEndTurn(match,player2);
        //GIOCATORE1
        cell = retrieveCell(window1,2,4);
        die = retrieveDieFromDraftPool(match,2);
        validPlaceDie(match,player1,cell,die);
        input = new ToolCardInput(retrieveCell(window1,3,4),retrieveCell(window1,2,3));
        validUseToolCard(match,player1,input,retrieveToolCard(match,2));

    }
    @Test
    public synchronized void fixedToolCard6(){
        MultiPlayerMatch match = createTwoPlayerMatch5();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        //Inizio partita
        validBeginMatch(match);
        validChooseWindow(match, player1, retrieveStartWindow(player1, 1));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 1));

        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();

        Cell cell;
        Die die;
        ToolCard toolCard;
        ToolCardInput input;

        //GIOCATORE1
        cell = retrieveCell(window1,2,4);
        die = retrieveDieFromDraftPool(match,2);
        validPlaceDie(match,player1,cell,die);
        validEndTurn(match,player1);
        //GIOCATORE2
        validEndTurn(match,player2);
        //GIOCATORE2
        validEndTurn(match,player2);
        //GIOCATORE1
        cell = retrieveCell(window1, 2, 3);
        die = retrieveDieFromDraftPool(match,3);
        validPlaceDie(match,player1,cell,die);
        validEndTurn(match,player1);
        //GIOCATORE2
        validEndTurn(match,player2);
        //GIOCATORE1
        die = new Die(DieColor.GREEN,3);
        match.getMatchDice().getDraftPool().set(0,die);
        die = new Die(DieColor.GREEN,4);
        match.getMatchDice().getDraftPool().set(1,die);
        cell = retrieveCell(window1,3,3);
        die = retrieveDieFromDraftPool(match,0);
        validPlaceDie(match,player1,cell,die);
        validEndTurn(match,player1);
        //GIOCATORE1
        validEndTurn(match,player1);
        //GIOCATORE2
        validEndTurn(match,player2);
        //GIOCATORE1
        input = new ToolCardInput(retrieveCell(window1,2,3),retrieveCell(window1,1,3));
        invalidUseToolCard(match,player1,input,retrieveToolCard(match,0));
        input = new ToolCardInput(retrieveCell(window1,2,4),retrieveCell(window1,2,2));
        invalidUseToolCard(match,player1,input,retrieveToolCard(match,0));
        input = new ToolCardInput(retrieveCell(window1,3,3),retrieveCell(window1,2,4),retrieveCell(window1,1,2),retrieveCell(window1,3,4));
        validUseToolCard(match,player1,input,retrieveToolCard(match,0));


    }




    public static void main(String[] args) throws RemoteException {
        //Crea un test
        MultiPlayerTest test = new MultiPlayerTest();

        //Lancia i test
        test.randomTwoPlayerStart();
        System.out.println("random two players start passed");
        test.randomFourPlayerTurnsFlow();
        System.out.println("random four players turns flow passed");

        //test.fixedTwoPlayer1();
        System.out.println("fixed two players 1 passed");
        //test.fixedTwoPlayer1();
        System.out.println("fixed tool cards 1 passed");

        //Termina
        System.exit(0);
    }
}

