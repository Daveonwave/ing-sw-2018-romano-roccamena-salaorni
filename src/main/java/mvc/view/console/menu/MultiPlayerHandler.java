package mvc.view.console.menu;

import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;

public class MultiPlayerHandler extends MenuInputHandler {
    //Gestore partita multiplayer

    //Costruttori
    public MultiPlayerHandler(ConsoleView parentView, ConsoleMenu parentMenu) {
        super(parentView, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        ConsoleView view = getConsoleView();
        ConsoleMenu menu = getParentMenu();

        //Controlla che l'utente sia loggato
        if (!view.isLogged()){
            view.signalError("non sei connesso al server");

            Console.clearScreen();

            view.show();
            return;
        }

        //Notifica attesa
        view.printInfo("in attesa di partecipanti...");

        //Richiesta controllore
        try {
            getConsoleView().setWaitingMultiplayer(true);

            view.getAppController().joinMatch(view.getUserToken());
        } catch (AppModelException e) {
            view.signalError(e.getMessage());
            Console.clearScreen();
            view.show();
            return;
        } catch (AppControllerException e) {
            Console.clearScreen();
            view.show();
            return;
        }

        Console.clearScreen();
    }
}
