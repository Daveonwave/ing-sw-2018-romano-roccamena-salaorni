package mvc.view.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.controller.AppController;

import java.io.*;
import java.rmi.RemoteException;

public class GUIHandler extends Application {
    //Gestore della gui dell'applicazione

    private static GUIView guiView;
    private int players = 0;
    private boolean connected = false;
    private boolean mode = false;
    private boolean queue = false;
    private boolean ready = false;
    private Stage stage;

    @FXML
    TextField input;
    @FXML
    Button rmi, socket, fourplayers, multi,single, twoplayers,threeplayers, annulla;
    @FXML
    Text text;
    @FXML
    Pane pane;
    @FXML
    ImageView container;

    public static GUIView getGuiView() {
        return guiView;
    }

    public int getPlayers() {
        return players;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isMode() {
        return mode;
    }

    public boolean isQueue() {
        return queue;
    }

    public boolean isReady() {
        return ready;
    }

    public Stage getStage() {
        return stage;
    }

    public static void setGuiView(GUIView guiView) {
        GUIHandler.guiView = guiView;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public void setQueue(boolean queue) {
        this.queue = queue;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //Inizializza la gui con la schermata di login
    public void start(Stage primaryStage) throws IOException{
        guiView = new GUIView(new AppController());
        changeScene("menu.fxml",primaryStage);
        guiView.setGuiHandler(this);
        this.stage = primaryStage;
    }

    //Bottone di login
    public void  login(ActionEvent actionEvent) throws IOException{
        pane.setVisible(false);
        fourplayers.setVisible(false);
        text.setText(guiView.login(input.getText()) + ".Scegliere il tipo di connessione");
    }

    public void cancel(ActionEvent actionEvent)throws RemoteException{
        if (!connected) {
            pane.setVisible(true);
            text.setText("Inserire il nome utente");
            guiView.logout();
        }
        else{
            if(!mode) {
                rmi.setVisible(true);
                socket.setVisible(true);
                text.setText("Scegliere il tipo di connessione");
                connected = false;
            }
            else{
                if(!queue) {
                    multi.setVisible(true);
                    single.setVisible(true);
                    fourplayers.setVisible(false);
                    mode = false;
                }
                else {
                    text.setText("Scegliere numero di giocatori");
                    fourplayers.setVisible(true);
                    twoplayers.setVisible(true);
                    threeplayers.setVisible(true);
                    queue = false;
                    this.guiView.getAppController().cancelJoinMatch(guiView.getUserToken(), this.players);
                }

            }
        }
    }

    public void connection(ActionEvent actionEvent){
        rmi.setVisible(false);
        socket.setVisible(false);
        text.setText("Scegliere la modalita' di gioco");
        this.connected = true;
    }

    public void multiplayer(ActionEvent actionEvent){
        multi.setVisible(false);
        single.setVisible(false);
        fourplayers.setVisible(true);
        mode = true;
        text.setText("Scegliere numero di giocatori");
    }

    public void waitGame(ActionEvent actionEvent) throws RemoteException{
        if (actionEvent.getSource().equals(twoplayers)) this.players = 2;
        if (actionEvent.getSource().equals(threeplayers)) this.players = 3;
        if (actionEvent.getSource().equals(fourplayers)) this.players = 4;
        this.guiView.getAppController().joinMatch(guiView.getUserToken(), players);
        fourplayers.setVisible(false);
        twoplayers.setVisible(false);
        threeplayers.setVisible(false);
        queue = true;
        text.setText("In attesa di altri giocatori...");
    }

    public void startGame() throws IOException{
        changeScene("game.fxml",this.stage);
    }

    //Cambia scena nella gui caricandola da un nuovo file FXML
    public void changeScene(String fxml, Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws RemoteException{
        launch(args);
    }
}
