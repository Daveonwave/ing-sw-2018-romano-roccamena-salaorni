package mvc.view.console.menu;

import mvc.view.console.ConsoleView;

public abstract class MenuInputHandler {
    //Input di un menu

    private ConsoleView parentGame;
    private ConsoleMenu parentMenu;

    //Costruttori
    public MenuInputHandler(ConsoleView parentGame, ConsoleMenu parentMenu) {
        this.parentGame = parentGame;
        this.parentMenu = parentMenu;
    }

    //Setter/Getter
    public void setParentGame(ConsoleView parentGame) {
        this.parentGame = parentGame;
    }
    public void setParentMenu(ConsoleMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public ConsoleView getParentGame() {
        return parentGame;
    }
    public ConsoleMenu getParentMenu() {
        return parentMenu;
    }

    //Evento di gestione input
    public abstract void onInputHandle() throws Exception;
}
