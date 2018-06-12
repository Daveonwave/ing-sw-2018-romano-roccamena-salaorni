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

import mvc.controller.AppController;
import mvc.stubs.AppControllerStub;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class GuiApp extends Application implements Serializable {
    //Applicazione gui principale

    public final String FXML_PATH = "fxml/MainMenu.fxml";
    public final String TITLE = "Sagrada";

    private GuiView guiView;

    private boolean connected = false;
    private boolean rmiConnection = true;

    private Client client = new Client();

    //Componenti
    @FXML
    Label userNameLabel, connectionLabel;
    @FXML
    TextField userNameText;
    @FXML
    TextArea serverLogText;
    @FXML
    RadioButton rmiRadio, socketRadio;
    @FXML
    Button connectButton, disconnectButton, loginButton, logoutButton, multiplayerButton, exitButton;




    //Ottiene connessione al server e restituisce controller corrispondente
    private AppControllerStub connectLocalController() {
        return new AppController();
    }

    private AppControllerStub connectRmiController() throws Exception {
        //showInfo("connecting rmi...");

        client.launchClient(true);

        return client.getController();
    }
    private AppControllerStub connectSocketController() throws Exception {
        //showInfo("connecting socket...");

        return connectLocalController();
    }

    //Chiude la connessione al server
    private void disconnectRmiController() throws RemoteException {
        //showInfo("disconnecting rmi...");

        //TODO: implementazione

        guiView = null;
    }
    private void disconnectSocketController() throws RemoteException {
        //showInfo("disconnecting socket...");

        //TODO: implementazione

        guiView = null;
    }




    //Funzioni gui di comunicazione con utente
    public void showInfo(String message) {
        GuiMessage.showInfo(TITLE, message);
    }
    public void showError(String message) {
        GuiMessage.showError(TITLE, message);
    }

    //Risposte controllore
    public void respondError(String message) throws RemoteException {
        serverLogText.setText(serverLogText.getText() + "\n[ERROR] " + message);
    }
    public void respondAck(String message) throws RemoteException {
        serverLogText.setText(serverLogText.getText() + "\n[INFO] " + message);
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
            connectionLabel.setText("DISCONNESSO");

            //Visualizza errore
            showError("Impossibile connettersi al server");
            return;
        }

        //Crea view client
        guiView = new GuiView(controller);
        guiView.setGuiApp(this);

        //Imposta stato componenti
        connectionLabel.setText("CONNESSO");
        userNameText.setDisable(false);

        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        loginButton.setDisable(false);
        logoutButton.setDisable(false);

        //Visualizza messaggio
        showInfo("Connesso al server");
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
            showError("Impossibile disconnettersi dal server");
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
        showInfo("Disconnesso dal server");
    }
    public void onLoginClicked(MouseEvent event) {
        String name = userNameText.getText();
        String token = "";

        try {
            token = guiView.login(name);
        } catch (RemoteException e) {
            e.printStackTrace();
            showError(e.getMessage());
            return;
        }

        guiView.setUserName(name);
        guiView.setUserToken(token);

        //Imposta stato componenti
        connectionLabel.setText(token);

        disconnectButton.setDisable(true);
        userNameText.setDisable(true);
        loginButton.setDisable(true);
        logoutButton.setDisable(false);
        multiplayerButton.setDisable(false);
    }
    public void onLogoutClicked(MouseEvent event) {
        try {
            guiView.logout();
        } catch (RemoteException e) {
            showError(e.getMessage());
            return;
        }

        //Imposta stato componenti
        connectionLabel.setText("CONNESSO");
        userNameText.setText("");

        disconnectButton.setDisable(false);
        userNameText.setDisable(false);
        loginButton.setDisable(false);
        logoutButton.setDisable(true);
        multiplayerButton.setDisable(true);
    }
    public void onMultiplayerClicked(MouseEvent event) {
        //TODO: implementazione

        //Visualizza messaggio
        showError("In attesa dello sviluppo");
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
            showError("Impossibile disconnettersi dal server");
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
        primaryStage.setTitle(TITLE);
        changeScene(FXML_PATH, primaryStage);
    }

    //Lancia applicazione
    public static void run(String[] args) {
        launch(args);
    }
}
