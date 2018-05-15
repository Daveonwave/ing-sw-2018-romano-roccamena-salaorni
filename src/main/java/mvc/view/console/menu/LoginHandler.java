package mvc.view.console.menu;

import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;

public class LoginHandler extends MenuInputHandler {
    //Gestore logout

    //Costruttori
    public LoginHandler(ConsoleView parentGame, ConsoleMenu parentMenu) {
        super(parentGame, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        ConsoleView game = getParentGame();
        ConsoleMenu menu = getParentMenu();

        //Controlla che l'utente sia gia loggato
        if (game.isLogged()){
            game.signalError("sei gia connesso al server");

            Console.clearScreen();

            game.show();
            return;
        }

        //Chiede e impost nome utente
        game.setUserName(game.askInput("qual'e il tuo nome?"));

        //Richiesta controllore
        try {
            game.setUserToken(game.login(game.getUserName()));
        } catch (AppModelException e) {
            game.signalError(e.getMessage());
        } catch (AppControllerException e) {}

        //Client loggato
        game.setLogged(true);

        Console.clearScreen();

        game.show();
    }
}
