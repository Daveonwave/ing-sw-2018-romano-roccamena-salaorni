package views;

import mvc.controller.AppController;
import mvc.view.console.ConsoleGame;

import java.rmi.RemoteException;

public class ConsoleTest {

    public static void main(String[] args) throws RemoteException {
        ConsoleGame consoleGame = new ConsoleGame(new AppController());

        consoleGame.start();
    }

}
