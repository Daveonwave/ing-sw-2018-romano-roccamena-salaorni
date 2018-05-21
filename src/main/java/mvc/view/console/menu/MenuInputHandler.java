package mvc.view.console.menu;

import mvc.view.console.ConsoleView;

public abstract class MenuInputHandler {
    //Input di un menu

    private ConsoleView consoleView;
    private ConsoleMenu parentMenu;

    //Costruttori
    public MenuInputHandler(ConsoleView consoleView, ConsoleMenu parentMenu) {
        this.consoleView = consoleView;
        this.parentMenu = parentMenu;
    }

    //Setter/Getter
    public void setConsoleView(ConsoleView consoleGame) {
        this.consoleView = consoleGame;
    }
    public void setParentMenu(ConsoleMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public ConsoleView getConsoleView() {
        return consoleView;
    }
    public ConsoleMenu getParentMenu() {
        return parentMenu;
    }

    //Evento di gestione input
    public abstract void onInputHandle() throws Exception;
}
