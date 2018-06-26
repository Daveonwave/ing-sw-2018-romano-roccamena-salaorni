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
    public void respondError(String message, String tokenMatch) throws RemoteException {
        if (tokenMatch == null) {
            if (guiApp.getWaitingMultiplayer()) {
                //Imposta stato componenti
                guiApp.setWaitingMultiplayer(false);

                guiApp.multiplayerButton.setDisable(false);
                guiApp.cancelButton.setDisable(true);
            }

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
    public void respondAck(String message, String tokenMatch) throws RemoteException {
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
        //Riabilita la finestra principale ad un nuovo multiplayer
        guiApp.multiplayerButton.setDisable(false);
        guiApp.cancelButton.setDisable(true);

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
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    getMultiplayerApps().get(tokenMatch).onChooseWindows(tokenMatch,match);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });

    }
    public synchronized void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    getMultiplayerApps().get(tokenMatch).onTurnStart(tokenMatch,match);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });


    }
    public synchronized void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    getMultiplayerApps().get(tokenMatch).onTurnEnd(tokenMatch,match);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });



    }
    public synchronized void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    getMultiplayerApps().get(tokenMatch).onPlaceDie(tokenMatch,match,cell,die);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });


    }
    public synchronized void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    getMultiplayerApps().get(tokenMatch).onUseTool(tokenMatch,match,toolCard);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });

    }
    public synchronized void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    getMultiplayerApps().get(tokenMatch).onGetPoints(tokenMatch,match,player,points);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });

    }
    public synchronized void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        Platform.runLater(new Runnable() {

            //Esecuzione nel thread javafx
            public void run() {
                try {
                    getMultiplayerApps().get(tokenMatch).onMatchEnd(tokenMatch,match);
                } catch (IOException e) {
                    //Segnala errore
                    e.printStackTrace();
                    GuiMessage.showError("impossibile mostrare una nuova partita");
                }
            }
        });

    }
}
