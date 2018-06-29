package gui;

import connection.Client;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.exceptions.AppControllerException;
import mvc.stubs.AppControllerStub;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GuiApp extends Application implements Serializable {
    //Applicazione gui principale

    private GuiView guiView;

    private boolean waitingMultiplayer = false;
    private boolean connected = false;
    private boolean rmiConnection = true;

    private transient Client client = new Client();

    //Componenti
    @FXML
    Label userNameLabel;
    @FXML
    Label connectionLabel;
    @FXML
    TextField userNameText;
    @FXML
    TextArea serverLogText;
    @FXML
    RadioButton rmiRadio;
    @FXML
    RadioButton socketRadio;
    @FXML
    Button connectButton;
    @FXML
    Button disconnectButton;
    @FXML
    Button loginButton;
    @FXML
    Button logoutButton;
    @FXML
    Button multiplayerButton;
    @FXML
    Button cancelButton;
    @FXML
    Button exitButton;



    //Getter
    public void setWaitingMultiplayer(boolean waitingMultiplayer) {
        this.waitingMultiplayer = waitingMultiplayer;
    }

    public boolean getWaitingMultiplayer() {
        return waitingMultiplayer;
    }


    private AppControllerStub connectRmiController() throws IOException, NotBoundException {
        client.launchClient(true);
        return client.getRmiController();
    }
    private AppControllerStub connectSocketController() throws IOException, NotBoundException {
        client.launchClient(false);
        return client.getSocketController();
    }

    //Chiude la connessione al server
    private void disconnectRmiController() throws RemoteException {

        //TODO: implementazione

        guiView = null;
    }
    private void disconnectSocketController() throws RemoteException {

        //TODO: implementazione

        guiView = null;
    }



    //Eventi componenti gui
    public void onConnectClicked(MouseEvent event) {
        AppControllerStub controller = null;

        connectionLabel.setText("CONNETTENDO...");

        try {
            //Identifica quale connessione utilizzare
            if (rmiRadio.isSelected()) {
                controller = connectRmiController();
                rmiConnection = true;
                connected = true;
            }
            else {
                controller = connectSocketController();
                rmiConnection = false;
                connected = true;
            }
        } catch (Exception e) {
            connectionLabel.setText(FXGuiConstant.DISCONNECTED);

            //Visualizza errore
            GuiMessage.showError("Impossibile connettersi al server");
            return;
        }

        //Crea view client
        try {
            guiView = new GuiView(controller, rmiConnection);
            if(!rmiConnection){
                client.getSocketClient().getClientActionHandler().setView(guiView);
            }
        } catch (RemoteException e) {
            connectionLabel.setText(FXGuiConstant.DISCONNECTED);

            //Visualizza errore
            e.printStackTrace();
            GuiMessage.showError("Errore imprevisto");
            return;
        }

        guiView.setGuiApp(this);

        //Imposta stato componenti
        connectionLabel.setText(FXGuiConstant.CONNECTED);
        userNameText.setDisable(false);

        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        loginButton.setDisable(false);
        logoutButton.setDisable(false);

        //Visualizza messaggio
        GuiMessage.showInfo("Connesso al server");
    }
    public void onDisconnectClicked(MouseEvent event) {
        connectionLabel.setText("DISCONNETTENDO...");

        try {
            //Identifica quale disconnessione utilizzare
            if (rmiRadio.isSelected()) {
                disconnectRmiController();
                connected = false;
            }
            else {
                disconnectSocketController();
                connected = false;
            }
        } catch (Exception e) {
            connectionLabel.setText("CONNESSO");

            //Visualizza errore
            e.printStackTrace();
            GuiMessage.showError("Impossibile disconnettersi dal server");
            return;
        }

        //Elimina view client
        guiView = null;

        //Imposta stato componenti
        connectionLabel.setText("DISCONNESSO");
        userNameText.setText("");

        userNameText.setDisable(true);

        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        loginButton.setDisable(true);
        logoutButton.setDisable(true);
        multiplayerButton.setDisable(true);

        //Visualizza messaggio
        GuiMessage.showInfo("Disconnesso dal server");
    }
    public void onLoginClicked(MouseEvent event) {
        String name = userNameText.getText();

        try {
            //Esegue il login
            guiView.login(name);

            //Segnala errori
        } catch (AppControllerException e) {
            GuiMessage.showError(e.getMessage());
            return;
        } catch (RemoteException f) {
            GuiMessage.showError("Impossibile eseguire il login");
            return;
        }

        //Imposta stato componenti
        connectionLabel.setText(guiView.getUserToken());

        disconnectButton.setDisable(true);
        userNameText.setDisable(true);
        loginButton.setDisable(true);
        logoutButton.setDisable(false);
        multiplayerButton.setDisable(false);
    }
    public void onLogoutClicked(MouseEvent event) {
        try {
            guiView.logout();

            //Segnala errori
        } catch (AppControllerException e) {
            GuiMessage.showError(e.getMessage());
            return;
        } catch (RemoteException f) {
            GuiMessage.showError("Impossibile eseguire il logout");
            return;
        }

        //Imposta stato componenti
        connectionLabel.setText(FXGuiConstant.CONNECTED);

        disconnectButton.setDisable(false);
        userNameText.setDisable(false);
        loginButton.setDisable(false);
        logoutButton.setDisable(true);
        multiplayerButton.setDisable(true);
    }
    public void onMultiplayerClicked(MouseEvent event) throws IOException {
        //Esegue la partecipazione ad una nuova partita
        try {
            guiView.joinMatch();

            //Segnala errori
        } catch (AppControllerException e) {
            GuiMessage.showError(e.getMessage());
            return;
        } catch (RemoteException f) {
            GuiMessage.showError("Impossibile partecipare ad una partita");
            return;
        }

        waitingMultiplayer = true;

        //Imposta stato componenti
        multiplayerButton.setDisable(true);
        cancelButton.setDisable(false);
    }
    public void onCancelClicked(MouseEvent event) {
        //Esegue la cancellazione alla partecipazione di un multiplayer
        try {
            guiView.cancelJoinMatch();

            //Segnala errori
        } catch (AppControllerException e) {
            GuiMessage.showError(e.getMessage());
            return;
        } catch (RemoteException f) {
            GuiMessage.showError("Impossibile annullare la partecipazione");
            return;
        }

        waitingMultiplayer = false;

        //Imposta stato componenti
        multiplayerButton.setDisable(false);
        cancelButton.setDisable(true);
    }
    public void onExitClicked(MouseEvent event) {
        //Chiude connessione
        try {
            if (connected) {
                if (rmiConnection)
                    disconnectRmiController();
                else
                    disconnectSocketController();
            }
        } catch (Exception e) {
            //Visualizza errore
            GuiMessage.showError("Impossibile disconnettersi dal server");
        }

        //Termina applicazione
        System.exit(0);
    }


    //Cambia scena nella gui caricandola da un nuovo file FXML
    public void changeScene(String fxml, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    //Avvio applicazione
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(FXGuiConstant.TITLE);
        changeScene(FXGuiConstant.FXML_PATH, primaryStage);
    }

    //Lancia applicazione
    public static void run(String[] args) {
        launch(args);
    }
}
