package gui;

import javafx.application.Platform;
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

    private transient GuiApp guiApp;
    private transient Map<String, GuiMultiplayerApp> multiplayerApps;

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
    public synchronized void respondError(String message, String tokenMatch) throws RemoteException {
        if (tokenMatch == null) {
            guiApp.serverLogText.setText(guiApp.serverLogText.getText() + "\n[ERROR] " + message);
        } else {
            GuiMultiplayerApp guiMultiplayerApp = multiplayerApps.get(tokenMatch);

            if (guiMultiplayerApp == null) {
                //Segnala errore
                GuiMessage.showError("richiesta comunicazione con partit multiplayer inesistente");
                return;
            }

            //Inoltra la risposta alla gui corrispondente
            Platform.runLater(new Runnable() {

                //Esecuzione nel thread javafx
                public void run() {
                    try {
                        guiMultiplayerApp.respondAck(message, tokenMatch);
                    } catch (IOException e) {
                        //Segnala errore
                        e.printStackTrace();
                        GuiMessage.showError("errore inaspettato durante la comunicazione con il server per una partita multiplayer");
                    }
                }
            });
        }
    }
    public synchronized void respondAck(String message, String tokenMatch) throws RemoteException {
        if (tokenMatch == null) {
            guiApp.serverLogText.setText(guiApp.serverLogText.getText() + "\n[INFO] " + message);
        } else {
            GuiMultiplayerApp guiMultiplayerApp = multiplayerApps.get(tokenMatch);

            if (guiMultiplayerApp == null) {
                //Segnala errore
                GuiMessage.showError("richiesta comunicazione con partit multiplayer inesistente");
                return;
            }

            //Inoltra la risposta alla gui corrispondente
            Platform.runLater(new Runnable() {

                //Esecuzione nel thread javafx
                public void run() {
                    try {
                        guiMultiplayerApp.respondError(message, tokenMatch);
                    } catch (IOException e) {
                        //Segnala errore
                        e.printStackTrace();
                        GuiMessage.showError("errore inaspettato durante la comunicazione con il server per una partita multiplayer");
                    }
                }
            });
        }
    }

    //Osservazione multiplayer
    public synchronized void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public synchronized void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

    public synchronized void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Crea nuova gui multiplayer e view associata
        GuiMultiplayerApp guiMultiplayerApp = new GuiMultiplayerApp();
        guiMultiplayerApp.setGuiView(this);

        multiplayerApps.put(tokenMatch, guiMultiplayerApp);

        //Mostra nuova finestra
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    guiMultiplayerApp.onMatchStart(tokenMatch, match);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });

    }
    public synchronized void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        getMultiplayerApps().get(tokenMatch).onChooseWindows(tokenMatch,match);
    }
    public synchronized void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        getMultiplayerApps().get(tokenMatch).onTurnStart(tokenMatch,match);

    }
    public synchronized void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        this.getMultiplayerApps().get(tokenMatch).onTurnEnd(tokenMatch,match);


    }
    public synchronized void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        this.getMultiplayerApps().get(tokenMatch).onPlaceDie(tokenMatch,match,cell,die);

    }
    public synchronized void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        getMultiplayerApps().get(tokenMatch).onUseTool(tokenMatch,match,toolCard);
    }
    public synchronized void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {

    }
    public synchronized void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
}
