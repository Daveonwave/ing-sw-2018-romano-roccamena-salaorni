package gui;

import connection.Client;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mvc.exceptions.AppControllerException;
import mvc.stubs.AppControllerStub;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * controller for the gui menu scene
 */
public class GuiApp extends Application implements Serializable {
    //Applicazione gui principale

    private GuiView guiView;

    private boolean waitingMultiplayer = false;
    private boolean connected = false;
    private boolean rmiConnection = true;
    private List<RadioButton> matches;

    private transient Client client = new Client();

    //Componenti
    @FXML
    Label userNameLabel;
    @FXML
    Label connectionLabel;
    @FXML
    TextField userNameText;
    @FXML
    AnchorPane leftMatchesAnchorPane;
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

    /**
     * connect the client to the server using rmi
     * @return controller associated to this view
     */
    private AppControllerStub connectRmiController() throws IOException, NotBoundException {
        client.launchClient(true);
        return client.getRmiController();
    }

    /**
     * connect to the server using sockets
     * @return controller associated to this view
     */
    private AppControllerStub connectSocketController() throws IOException, NotBoundException {
        client.launchClient(false);
        return client.getSocketController();
    }

    /**
     * disconnect the client from the server using rmi
     */
    private void disconnectRmiController() throws RemoteException {

        //TODO: implementazione

        guiView = null;
    }

    /**
     * disconnect the client from the server using rmi
     */
    private void disconnectSocketController() throws RemoteException {

        //TODO: implementazione

        guiView = null;
    }


    /**
     * when the "connect" button is clicked, calls the corresponding connection method
     * @param event on click event
     */
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

    /**
     * when th "disconnect" button is clicked, calls the corresponding disconnect method
     * @param event on click event
     */
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

    /**
     * when the "login" button is clicked calls the login method of the view
     * @param event on click event
     */
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

    /**
     * when the "logout" button is clicked calls the logout method of the view
     * @param event on click event
     */
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

    /**
     * when the "multiplayer" button is clicked calls the join lobby method
     * @param event on click event
     */
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

    /**
     * when the "cancel" button is clicked cancel the last operation done
     * @param event on click event
     */
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

    /**
     * when the "exit" button is clicked close the menu gui
     * @param event on click event
     */
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

    /**
     * load an fxml file and change the scene of the current stage
     * @param fxml
     * @param stage
     * @throws IOException
     */
    public void changeScene(String fxml, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        GuiApp guiApp = loader.getController();
        guiApp.matches = new ArrayList<>();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * shows all the radio button representing every game you left
     */
    private void updateMatchesLeft(){
        int yPosition = 43;
        for (RadioButton match : matches){
            if(yPosition > 473){
                serverLogText.setText("[ERRORE] hai raggiunto il limite massimo di partite da visualizzare");
                return;
            }
            match.setPrefSize(201,17.6);
            leftMatchesAnchorPane.getChildren().remove(match);
            leftMatchesAnchorPane.getChildren().add(match);
            match.setLayoutX(6);
            match.setLayoutY(yPosition);
            yPosition += 43;
        }
    }

    /**
     * when you leave a match saves a new radio button in "Matches Left"
     * @param tokenMatch token of the match
     */
    public void addMatchLeft(String tokenMatch){

        for(RadioButton match : matches){
            if(match.getText().equals(tokenMatch)) {
                return;
            }
        }
        RadioButton matchLeft = new RadioButton(tokenMatch);
        matches.add(matchLeft);
        updateMatchesLeft();
    }

    /**
     * when you press the "rejoin button" rejoins the first selected match
     * @param mouseEvent on click event
     */
    public void rejoin(MouseEvent mouseEvent){
        RadioButton eliminatedMatch = null;
        for(RadioButton match: this.matches){
            if(match.isSelected()){
                try {
                    guiView.getController().rejoinMatch(guiView.getUserToken(),match.getText());
                } catch (RemoteException e) {
                    serverLogText.setText("[ERRORE] " + e.getMessage());
                }
                match.setVisible(false);
                eliminatedMatch = match;
                break;
            }
        }
        matches.remove(eliminatedMatch);
        updateMatchesLeft();
    }

    /**
     * method the starts the gui
     * @param primaryStage stage to open
     */
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(FXGuiConstant.TITLE);
        changeScene(FXGuiConstant.FXML_PATH, primaryStage);
    }

    /**
     * static method that runs the gui
     * @param args arg
     */
    public static void run(String[] args) {
        launch(args);
    }
}
