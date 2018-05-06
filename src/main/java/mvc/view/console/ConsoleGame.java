package mvc.view.console;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

import java.rmi.RemoteException;

public class ConsoleGame extends AppView {
    //View console dell'applicazione

    private boolean logged = false;
    private String userName = "";
    private String userToken = "";
    private String matchToken = "";

    public final int WIDTH = 50;

    //Costruttori
    protected ConsoleGame() throws RemoteException {
        super();
    }
    public ConsoleGame(AppControllerStub appController) throws RemoteException {
        super(appController);
    }

    //Print vari
    private void printGameLogo() {
        Console.printlnCentered(" ____     __       ___   ___      __     ____       __", WIDTH, " ");
        Console.printlnCentered("|        |  |     /     |   |    |  |   |    \\    |  |   ", WIDTH, " ");
        Console.printlnCentered("|        |  |    /      |   |    |  |   |     \\   |  |   ", WIDTH, " ");
        Console.printlnCentered("|____   |    |  /       |___|   |    |  |      |  |    |   ", WIDTH, " ");
        Console.printlnCentered("     |  |____|  |   __  | \\    |____|  |      |  |____|  ", WIDTH, " ");
        Console.printlnCentered("     | |      | |     | |  \\  |      | |     /  |      | ", WIDTH, " ");
        Console.printlnCentered("_____| |      | |_____| |   \\ |      | |____/   |      | ", WIDTH, " ");
    }
    private void printLogin() {
        Console.printlnCentered("(1) LOGIN (1)", WIDTH, " ");
    }
    private void printLogout() {
        Console.printlnCentered("(2) LOGOUT (2)", WIDTH, " ");
    }
    private void printSinglePlayer() {
        Console.printlnCentered("(3) SINGLE PLAYER (3)", WIDTH, " ");
    }
    private void printMultiPlayer() {
        Console.printlnCentered("(4) MULTI PLAYER (4)", WIDTH, " ");
    }
    private void printExit() {
        Console.printlnCentered("(5) EXIT (5)", WIDTH, " ");
    }

    //Validità input
    private boolean validStartChoice(int choice) {
        return choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5;
    }

    //Print interfacce
    public int printStartView() {
        int choice = 0;
        boolean start = true;
        boolean valid = false;

        do {
            if(!start)
                Console.clearScreen();

            Console.setColor(ConsoleColors.CYAN_BOLD);
            printGameLogo();

            Console.newLine();

            Console.setColor(ConsoleColors.YELLOW_BOLD);
            printLogin();
            printLogout();
            printSinglePlayer();
            printMultiPlayer();
            printExit();

            Console.newLine();

            if (!start && !valid) {
                Console.newLine();
                respondError("invalid choice");
            }

            Console.newLine();

            String input = Console.read();
            try {
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                choice = 0;
            }
            valid = validStartChoice(choice);

            start = false;
        } while(!valid);

        return choice;
    }

    //Inizia gioco
    public void start() throws RemoteException {
        int choice = printStartView();

        Console.clearScreen();

        switch (choice) {
            case 1:
                respondAck("NAME?");
                userName = Console.readLine();

                try {
                    userToken = getAppController().login(userName, this);
                } catch (RemoteException e) {
                    respondError(e.getMessage());
                }

                logged = true;

                Console.clearScreen();

                start();
                break;
            case 2:
                try {
                    getAppController().logout(userToken);
                } catch (RemoteException e) {
                    respondError(e.getMessage());
                }

                logged = false;

                Console.clearScreen();

                start();
                break;
            case 3:
                //TODO
                break;
            case 4:
                respondAck("HOW MANY PLAYERS?");
                String input = Console.readLine();
                int playersCount = Integer.parseInt("2");

                try {
                    getAppController().joinMatch(userToken, playersCount);
                } catch (RemoteException e) {
                    respondError(e.getMessage());
                }

                Console.clearScreen();

                //Inizia partita
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    //Risposta scritta
    public void respondError(String message) {
        Console.setColor(ConsoleColors.PURPLE_BOLD);
        Console.printlnCentered(message, WIDTH, " ");
    }
    public void respondAck(String message) {
        Console.setColor(ConsoleColors.GREEN);
        Console.printlnCentered(message, WIDTH, " ");
    }

    //Osservazione partita
    public void onMatchStart(String tokenMatch, Match match) throws RemoteException {

    }
    public void onChooseWindows(String tokenMatch, Match match) throws RemoteException {

    }
    public void onTurnStart(String tokenMatch, Match match) throws RemoteException {

    }
    public void onTurnEnd(String tokenMatch, Match match) throws RemoteException {

    }
    public void onPlaceDie(String tokenMatch, Match match, Cell cell, Die die) throws RemoteException {

    }
    public void onUseTool(String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {

    }
    public void onGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException {

    }
    public void onMatchEnd(String tokenMatch, Match match) throws RemoteException {

    }
}

