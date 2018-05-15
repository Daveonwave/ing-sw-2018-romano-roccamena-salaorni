package mvc.view.console.menu;

import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;

public class MultiPlayerHandler extends MenuInputHandler {
    //Gestore partita multiplayer

    //Costruttori
    public MultiPlayerHandler(ConsoleView parentGame, ConsoleMenu parentMenu) {
        super(parentGame, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        ConsoleView game = getParentGame();
        ConsoleMenu menu = getParentMenu();

        //Controlla che l'utente sia loggato
        if (!game.isLogged()){
            game.signalError("non sei connesso al server");

            Console.clearScreen();

            game.show();
            return;
        }

        //Chiede numero giocatori
        String input = "";
        int playersCount = -1;
        boolean ok = true;

        do {
            boolean exception = false;

            input = game.askInput("quanti giocatori? (2/3/4)");
            try {
                playersCount = Integer.parseInt(input);
            } catch (Exception e) {
                exception = true;
            }

            if (exception)
                ok = false;
            else
                ok = true;
        } while (!ok);

        //Richiesta controllore
        try {
            game.getAppController().joinMatch(game.getUserToken(), playersCount);
        } catch (AppModelException e) {
            game.signalError(e.getMessage());
            Console.clearScreen();
            game.show();
            return;
        } catch (AppControllerException e) {
            Console.clearScreen();
            game.show();
            return;
        }

        Console.clearScreen();
    }
}
