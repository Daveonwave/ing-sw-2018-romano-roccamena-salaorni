package mvc.view.console.menu;

import mvc.view.console.ConsoleView;

public class ExitHandler extends MenuInputHandler {
    //Gestore uscita applicazione

    //Costruttori
    public ExitHandler(ConsoleView parentView, ConsoleMenu parentMenu) {
        super(parentView, parentMenu);
    }

    //Gestione input
    public void onInputHandle() throws Exception {
        System.exit(0);
    }
}
