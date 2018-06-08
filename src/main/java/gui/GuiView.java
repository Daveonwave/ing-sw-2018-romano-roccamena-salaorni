package gui;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class GuiView extends AppView {
    //View gui dell'applicazione

    private GuiApp guiApp;
    private Map<String, GuiMultiplayer> multiplayerMap;

    //Costruttori
    public GuiView(AppControllerStub appController) {
        super(appController);
        this.guiApp = null;
        this.multiplayerMap = new HashMap<String, GuiMultiplayer>();
    }

    //Setter
    public void setGuiApp(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    //Operazioni utente
    public synchronized String login(String name) throws RemoteException {
        return getAppController().login(name, this);
    }
    public synchronized void logout() throws RemoteException {
        getAppController().logout(getUserToken());
    }

    //Risposte controllore
    public synchronized void respondError(String message) throws RemoteException {
        guiApp.respondError(message);
    }
    public synchronized void respondAck(String message) throws RemoteException {
        guiApp.respondAck(message);
    }

    //Osservazione multiplayer
    public synchronized void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public synchronized void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

    public synchronized void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Crea nuova gui ultiplayer
        GuiMultiplayer guiMultiplayer = new GuiMultiplayer();
        multiplayerMap.put(tokenMatch, guiMultiplayer);

        //Avvia altra finestra
        try {
            guiMultiplayer.show(match, tokenMatch);
        } catch (IOException e) {
            //guiApp.showError("Errore inaspettato, l'applicazione si chiuderà");
            System.exit(0);
        }
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
