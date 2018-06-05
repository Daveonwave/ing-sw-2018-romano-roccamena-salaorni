package mvc.view.console.menu;

import mvc.creators.MatchCreator;
import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.exceptions.AppViewException;
import mvc.model.objects.Player;
import mvc.model.objects.SinglePlayerMatch;
import mvc.model.objects.User;
import mvc.model.objects.Window;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;
import mvc.view.console.printer.ObjectPrinter;

public class SinglePlayerHandler extends MenuInputHandler {
    //Gestore partita singleplayer

    //Costruttori
    public SinglePlayerHandler(ConsoleView parentView, ConsoleMenu parentMenu) {
        super(parentView, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        ConsoleView view = getConsoleView();
        ConsoleMenu menu = getParentMenu();

        //Richiede difficolta partita
        String difficultyString = view.askInput("che livello di difficolta vuoi giocare?");
        while (!difficultyString.equals("1")&&!difficultyString.equals("2")&&!difficultyString.equals("3")&&!difficultyString.equals("4")&&!difficultyString.equals("5")) {
            view.signalError("input non valido");
            view.printError();

            difficultyString = view.askInput("che livello di difficolta vuoi giocare?");
        }

        //Crea partita
        SinglePlayerMatch match = MatchCreator.createSinglePlayer(new User("giocatore", view),Integer.parseInt(difficultyString));
        view.setSinglePlayerMatch(match);

        ObjectPrinter printer = getConsoleView().getObjectPrinter();

        //Pulisce
        Console.clearScreen();

        //Esegue print della griglia di restrizioni finestra
        getConsoleView().printInfo("scegli una finestra di gioco");
        Console.resetColor();
        Console.newLine();

        for (Window window : match.getPlayer().getStartWindows()) {
            printer.printWindowRestictions(window);
            view.printInfo("difficolta "+  window.getDifficulty());

            Console.resetColor();
            Console.newLine();
        }

        //Scelta finestra
        String choice=view.askInput("che finestra hai scelto?");
    }
}
