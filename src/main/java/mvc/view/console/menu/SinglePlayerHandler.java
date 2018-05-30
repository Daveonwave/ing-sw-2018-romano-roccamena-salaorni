package mvc.view.console.menu;

import mvc.exceptions.AppControllerException;
import mvc.exceptions.AppModelException;
import mvc.exceptions.AppViewException;
import mvc.view.console.Console;
import mvc.view.console.ConsoleView;

public class SinglePlayerHandler extends MenuInputHandler {
    //Gestore partita singleplayer

    //Costruttori
    public SinglePlayerHandler(ConsoleView parentView, ConsoleMenu parentMenu) {
        super(parentView, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        throw new AppViewException("waiting implementation");
    }
}
