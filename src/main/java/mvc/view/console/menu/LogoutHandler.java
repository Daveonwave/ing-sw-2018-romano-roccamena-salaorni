package mvc.view.console.menu;

import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;

public class LogoutHandler extends MenuInputHandler {
    //Gestore login

    //Costruttori
    public LogoutHandler(ConsoleView parentGame, ConsoleMenu parentMenu) {
        super(parentGame, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        ConsoleView game = getParentGame();
        ConsoleMenu menu = getParentMenu();

        //Controlla che l'uente sia loggato
        if (!game.isLogged())
            game.signalError("non sei connesso al server");
        else {
            //Richiesta controllore
            try {
                game.logout();
            } catch (AppModelException e) {
                game.signalError(e.getMessage());
            } catch (AppControllerException e) {}

            //Client non piu loggato
            game.setLogged(false);
        }

        Console.clearScreen();

        game.show();
    }
}
