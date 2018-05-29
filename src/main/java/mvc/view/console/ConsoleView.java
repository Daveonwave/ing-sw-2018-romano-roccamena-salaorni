package mvc.view.console;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;
import mvc.view.console.menu.ConsoleMenu;
import mvc.view.console.menu.MainMenu;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleView extends AppView {
    //View console dell'applicazione

    private MainMenu mainMenu;

    private String matchToken;

    private List<String> ackMessages;
    private List<String> errorMessages;

    private boolean waitingMultiplayer;

    public final int WIDTH = 50;

    //Costruttori
    public ConsoleView(AppControllerStub appController) throws RemoteException {
        super(appController);
        this.mainMenu = new MainMenu(WIDTH, this);
        this.matchToken = "";
        this.ackMessages = new ArrayList<String>();
        this.errorMessages = new ArrayList<String>();
        this.waitingMultiplayer = false;
    }

    //Setter/Getter
    public boolean getWaitingMultiplayer() {
        return waitingMultiplayer;
    }

    public void setWaitingMultiplayer(boolean waitingMultiplayer) {
        this.waitingMultiplayer = waitingMultiplayer;
    }

    //Visualizzazioni varie
    private String showMenu(ConsoleMenu menu) throws IOException {
        String input = "";
        boolean start = true;
        boolean valid = !errorMessages.isEmpty();

        do {
            //Print menu
            menu.printMenu();

            Console.newLine();

            //Segnalazione stato online/offline
            if (isLogged()) {
                signalAck("CONNESSO");
                signalAck("[" + getUserName() + "]");
            }
            else
                signalAck("DISCONNESSO");

            Console.newLine();

            //Controllo input corretto
            if (!start && !valid)
                signalError("scelta non valida");

            //Print ack/error
            printAck();
            printError();

            //Pulisce ack/error
            ackMessages.clear();
            errorMessages.clear();

            Console.newLine();

            //Lettura input scelta
            input = Console.readLine();
            valid = menu.isValidInput(input);

            Console.clearScreen();

            start = false;
        } while(!valid);

        return input;
    }
    public String showMainMenu() throws IOException {
        return showMenu(mainMenu);
    }

    public void show() throws Exception {
        String input = showMainMenu();

        mainMenu.handleInput(input);
    }

    //Metodi input output gico
    public void printInfo(String text) {
        Console.setColor(ConsoleColors.GREEN);
        Console.printlnCentered(text, WIDTH, " ");
    }
    public void printAck() {
        Console.setColor(ConsoleColors.GREEN);

        for (String message : ackMessages)
            Console.printlnCentered(message, WIDTH, " ");
    }
    public void printError() {
        Console.setColor(ConsoleColors.PURPLE_BOLD);

        for (String message : errorMessages)
            Console.printlnCentered(message, WIDTH, " ");
    }
    public String askInput(String text) throws IOException {
        Console.setColor(ConsoleColors.GREEN);
        Console.printlnCentered(text, WIDTH, " ");

        return Console.readLine();
    }

    //Segnalazione ack ed error
    public void signalAck(String message) {
        ackMessages.add(message);
    }
    public void signalError(String message) {
        errorMessages.add(message);
    }

    //Risposte al constrollore
    public void respondAck(String message) {
        signalAck(message);
    }
    public void respondError(String message) {
        signalError(message);

        //Gestione attesa multiplayer
        if (waitingMultiplayer) {
            waitingMultiplayer = false;

            Console.clearScreen();
            try {
                showMainMenu();
            } catch (IOException e) {}
        }
    }

    //Operazioni su utente
    public String login(String name) throws RemoteException {
        return getAppController().login(getUserName(), this);
    }
    public void logout() throws RemoteException {
        getAppController().logout(getUserToken());
    }

    //Osservazione partita
    public synchronized void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public synchronized void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public synchronized void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public synchronized void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public synchronized void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {

    }
    public synchronized void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {

    }
    public synchronized void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {

    }
    public synchronized void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
}

