package mvc.view.console.menu;

import mvc.exceptions.AppViewException;
import mvc.view.console.ConsoleView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class ConsoleMenu implements Serializable {
    //Menu della view console

    private int width;
    private ConsoleView consoleView;
    private Map<String, MenuInputHandler> menuInputHandlers;

    //Costruttori
    public ConsoleMenu(int width, ConsoleView parentGame) {
        this.width = width;
        this.consoleView = parentGame;
        this.menuInputHandlers = new HashMap<String, MenuInputHandler>();
    }

    //Setter/Getter
    public void setWidth(int width) {
        this.width = width;
    }
    public void setConsoleView(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }
    public void setMenuInputHandlers(Map<String, MenuInputHandler> menuInputHandlers) {
        this.menuInputHandlers = menuInputHandlers;
    }

    public int getWidth() {
        return width;
    }
    public ConsoleView getConsoleView() {
        return consoleView;
    }
    public Map<String, MenuInputHandler> getMenuInputHandlers() {
        return menuInputHandlers;
    }

    //Verifica input valido
    public boolean isValidInput(String input) {
        return menuInputHandlers.keySet().contains(input);
    }
    public void handleInput(String input) throws Exception {
        MenuInputHandler handler = menuInputHandlers.get(input);

        if (handler == null)
            throw new AppViewException("invalid input");

        menuInputHandlers.get(input).onInputHandle();
    }

    //Print menu
    public abstract void printMenu();
}
