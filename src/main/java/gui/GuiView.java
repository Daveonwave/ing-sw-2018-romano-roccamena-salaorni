package gui;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class GuiView extends AppView {
    //View gui dell'applicazione

    private GuiApp guiApp;
    private Map<String, GuiMultiplayerApp> multiplayerApps;

    //Costruttori
    public GuiView(AppControllerStub appController, boolean rmiConnection) throws RemoteException {
        super(appController);
        this.guiApp = null;
        this.multiplayerApps = new HashMap<String, GuiMultiplayerApp>();

        if (rmiConnection)
            UnicastRemoteObject.exportObject(this, 0);
    }

    //Setter/Getter
    public void setGuiApp(GuiApp guiApp) {
        this.guiApp = guiApp;
    }
    public void setMultiplayerApps(Map<String, GuiMultiplayerApp> multiplayerApps) {
        this.multiplayerApps = multiplayerApps;
    }


    public GuiApp getGuiApp() {
        return this.guiApp;
    }
    public Map<String, GuiMultiplayerApp> getMultiplayerApps() {
        return this.multiplayerApps;

    }

    //Risposte controllore
    public synchronized void respondError(String message) throws RemoteException {
        guiApp.serverLogText.setText(guiApp.serverLogText.getText() + "\n[ERROR] " + message);
    }
    public synchronized void respondAck(String message) throws RemoteException {
        guiApp.serverLogText.setText(guiApp.serverLogText.getText() + "\n[INFO] " + message);
    }

    //Osservazione multiplayer
    public synchronized void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public synchronized void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

    public synchronized void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Crea nuova gui multiplayer e view associata
        GuiMultiplayerApp guiMultiplayerApp = new GuiMultiplayerApp();

        multiplayerApps.put(tokenMatch, guiMultiplayerApp);

        //Mostra nuova finestra
        try {
            guiMultiplayerApp.show(match, tokenMatch);
        } catch (IOException e) {
            //Segnala errore
            guiApp.showError("Impossibile mostrare una nuova partita");

            System.exit(0);
        }

        //Chiama evento della nuova finestra
        guiMultiplayerApp.onMatchStart(tokenMatch, match);
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