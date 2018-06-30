package mvc.view.console.menu;

import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;

import java.rmi.RemoteException;

public class LogoutHandler extends MenuInputHandler {
    //Gestore login

    //Costruttori
    public LogoutHandler(ConsoleView parentView, ConsoleMenu parentMenu) {
        super(parentView, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        ConsoleView view = getConsoleView();
        ConsoleMenu menu = getParentMenu();

        //Controlla che l'uente sia loggato
        if (!view.isLogged())
            view.signalError("non sei connesso al server");
        else {
            //Richiesta controllore
            try {
                view.logout();
            } catch (AppModelException e) {
                view.signalError(e.getMessage());
            } catch (AppControllerException e) {}
        }

        //Aggiorna schermo
        Console.clearScreen();

        view.show();
    }
}
