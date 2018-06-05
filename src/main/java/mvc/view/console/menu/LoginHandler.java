package mvc.view.console.menu;

import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;

public class LoginHandler extends MenuInputHandler {
    //Gestore logout

    //Costruttori
    public LoginHandler(ConsoleView parentView, ConsoleMenu parentMenu) {
        super(parentView, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        ConsoleView view = getConsoleView();
        ConsoleMenu menu = getParentMenu();

        //Controlla che l'utente sia gia loggato
        if (view.isLogged()){
            view.signalError("sei gia connesso al server");

            Console.clearScreen();

            view.show();
            return;
        }

        //Chiede e impost nome utente
        view.setUserName(view.askInput("qual'e il tuo nome?"));

        //Richiesta controllore
        try {
            view.setUserToken(view.login(view.getUserName()));
        } catch (AppModelException e) {
            view.signalError(e.getMessage());
        } catch (AppControllerException e) {}

        //Client loggato
        view.setLogged(true);

        Console.clearScreen();

        view.show();
    }
}
