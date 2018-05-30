package mvc.view.console.menu;

import mvc.view.console.Console;
import mvc.view.console.ConsoleColors;
import mvc.view.console.ConsoleView;


public class MainMenu extends ConsoleMenu {
    //Menu principale della view console

    //Costruttori
    public MainMenu(int width, ConsoleView parentView) {
        super(width, parentView);

        getMenuInputHandlers().put("1", new LoginHandler(parentView, this));
        getMenuInputHandlers().put("2", new LogoutHandler(parentView, this));
        getMenuInputHandlers().put("3", new SinglePlayerHandler(parentView, this));
        getMenuInputHandlers().put("4", new MultiPlayerHandler(parentView, this));
        getMenuInputHandlers().put("5", new ExitHandler(parentView, this));
    }

    //Print vari
    private void printGameLogo() {
        Console.printlnCentered(" ____     __       ___   ___     __     ____       __", getWidth(), " ");
        Console.printlnCentered("|        |  |     /     |   |   |  |   |    \\     |  |   ", getWidth(), " ");
        Console.printlnCentered("|        |  |    /      |   |   |  |   |     \\    |  |   ", getWidth(), " ");
        Console.printlnCentered("|____   |    |  /       |___|  |    |  |      |  |    |   ", getWidth(), " ");
        Console.printlnCentered("     |  |____|  |   __  | \\    |____|  |      |  |____|  ", getWidth(), " ");
        Console.printlnCentered("     | |      | |     | |  \\  |      | |     /  |      | ", getWidth(), " ");
        Console.printlnCentered("_____| |      | |_____| |   \\ |      | |____/   |      | ", getWidth(), " ");
    }
    private void printLogin() {
        Console.printlnCentered("(1) LOGIN (1)", getWidth(), " ");
    }
    private void printLogout() {
        Console.printlnCentered("(2) LOGOUT (2)", getWidth(), " ");
    }
    private void printSinglePlayer() {
        Console.printlnCentered("(3) SINGLE PLAYER (3)", getWidth(), " ");
    }
    private void printMultiPlayer() {
        Console.printlnCentered("(4) MULTI PLAYER (4)", getWidth(), " ");
    }
    private void printExit() {
        Console.printlnCentered("(5) ESCI (5)", getWidth(), " ");
    }

    //Print menu
    public void printMenu() {
        Console.setColor(ConsoleColors.CYAN_BOLD);
        printGameLogo();

        Console.newLine();

        Console.setColor(ConsoleColors.YELLOW_BOLD);
        printLogin();
        printLogout();
        printSinglePlayer();
        printMultiPlayer();
        printExit();
    }
}
