package gui;

import javafx.application.Platform;
import javafx.scene.control.RadioButton;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * view class for the gui
 */
public class GuiView extends AppView {
    //View gui dell'applicazione

    private transient GuiApp guiApp;
    private transient Map<String, GuiMultiplayerApp> multiplayerApps;


    /**
     *
     * @param appController controller associated to the view
     * @param rmiConnection boolean stating the type of connection
     */
    public GuiView(AppControllerStub appController, boolean rmiConnection) throws RemoteException {
        super(appController);
        this.guiApp = null;
        this.multiplayerApps = new HashMap<>();

        if (rmiConnection)
            UnicastRemoteObject.exportObject(this, 0);
    }

    //Setter/Getter
    public void setGuiApp(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    public Map<String, GuiMultiplayerApp> getMultiplayerApps() {
        return this.multiplayerApps;

    }

    /**
     * method called by the model when an error occurred
     * @param message message to print
     * @param tokenMatch match in which this event occurred
     */
    public void respondError(String message, String tokenMatch)throws RemoteException{
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
            //Esecuzione nel thread javafx
            Platform.runLater(() -> {
                try {
                    guiMultiplayerApp.respondError(message, tokenMatch);
                } catch (IOException e) {
                    //Segnala errore
                    GuiMessage.showError("errore inaspettato durante la comunicazione con il server per una partita multiplayer");
                }
            });
        }
    }

    /**
     * method called by the model to notify an event occurred
     * @param message message too print
     * @param tokenMatch match in which this event occurred
     */
    public void respondAck(String message, String tokenMatch) throws RemoteException{
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
            //Esecuzione nel thread javafx
            Platform.runLater(() -> {
                try {
                    guiMultiplayerApp.respondAck(message, tokenMatch);
                } catch (IOException e) {
                    //Segnala errore
                    GuiMessage.showError("errore inaspettato durante la comunicazione con il server per una partita multiplayer");
                }
            });
        }
    }

    /**
     * method called by the model when a player leaves the match, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param player player who left the match
     * @throws RemoteException
     */
    public synchronized void onPlayerLeave(String tokenMatch, Player player) throws RemoteException {
        Platform.runLater(() -> {
            try {
                if(player.getUser().getName().equals(getUserName())){
                    guiApp.addMatchLeft(tokenMatch);
                }
                getMultiplayerApps().get(tokenMatch).onPlayerLeave(tokenMatch,player);
            } catch (IOException e) {
                //Segnala errore

                GuiMessage.showError("impossibile visualizzare le finestre");
            }
        });
    }

    /**
     * method called by the model when a player rejoin the match, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param player player who rejoined the match
     * @throws RemoteException
     */
    public synchronized void onPlayerRejoin(String tokenMatch, Player player) throws RemoteException {
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onPlayerRejoin(tokenMatch,player);
            } catch (IOException e) {
                //Segnala errore

                GuiMessage.showError("impossibile visualizzare le finestre");
            }
        });
    }

    /**
     * method called by the model when a match starts, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match of the model
     */
    public synchronized void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Riabilita la finestra principale ad un nuovo multiplayer
        guiApp.multiplayerButton.setDisable(false);
        guiApp.cancelButton.setDisable(true);

        //Crea nuova gui multiplayer e view associata
        GuiMultiplayerApp guiMultiplayerApp = new GuiMultiplayerApp();
        guiMultiplayerApp.setGuiView(this);

        multiplayerApps.put(tokenMatch, guiMultiplayerApp);

        //Mostra nuova finestra
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                guiMultiplayerApp.onMatchStart(tokenMatch, match);
            } catch (IOException e) {
                //Segnala errore
                GuiMessage.showError("impossibile mostrare una nuova partita");
            }
        });

    }

    /**
     * method called by the model when the window choice stage of the game starts, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     */
    public synchronized void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onChooseWindows(tokenMatch,match);
            } catch (IOException e) {
                //Segnala errore

                GuiMessage.showError("impossibile visualizzare le finestre");
            }
        });

    }

    /**
     * method called by the model when a turn starts, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     */
    public synchronized void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onTurnStart(tokenMatch,match);
            } catch (IOException e) {
                //Segnala errore
                GuiMessage.showError("impossibile iniziare il turno");
            }
        });


    }

    /**
     * method called by the gui when a turn ends, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     */
    public synchronized void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onTurnEnd(tokenMatch,match);
            } catch (IOException e) {
                //Segnala errore
                GuiMessage.showError("impossibile finire il turno");
            }
        });



    }

    /**
     * method called by the model when a die is placed, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @param cell cell where the die has been placed
     * @param die die placed
     * @throws RemoteException
     */
    public synchronized void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onPlaceDie(tokenMatch,match,cell,die);
            } catch (IOException e) {
                //Segnala errore
                GuiMessage.showError("impossibile piazzare il dado");
            }
        });


    }

    /**
     * mathod called by the model when a tool card is used, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @param toolCard tool card used
     */
    public synchronized void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onUseTool(tokenMatch,match,toolCard);
            } catch (IOException e) {
                //Segnala errore
                GuiMessage.showError("impossibile usare una tool card");
            }
        });

    }

    /**
     * method called by the model when a player's points are calculated, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @param player player whose points has benn calculated
     * @param points points calculated
     */
    public synchronized void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onGetPoints(tokenMatch,match,player,points);
            } catch (IOException e) {
                //Segnala errore
                GuiMessage.showError("impossibile prendere i punti");
            }
        });

    }

    /**
     * method called by the model when a match ends, calls the corresponding method of the gui controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @throws RemoteException
     */
    public synchronized void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Esecuzione nel thread javafx
        Platform.runLater(() -> {
            try {
                getMultiplayerApps().get(tokenMatch).onMatchEnd(tokenMatch,match);
            } catch (IOException e) {
                //Segnala errore
                GuiMessage.showError("impossibile finire la partita");
            }
        });

    }
}
