package mvc.view.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.controller.AppController;

import java.io.*;
import java.rmi.RemoteException;

public class
GUIHandler extends Application {
    //Gestore della gui dell'applicazione

    private static GUIView guiView;
    private boolean connected = false;
    private boolean queue = false;
    private boolean ready = false;
    private boolean hide = true;
    private Stage stage;

    //Componenti gui
    @FXML
    TextField input;
    @FXML
    Button rmi, socket, multi,single, annulla, privateObjective, publicObjective1, publicObjective2, publicObjective3, toolCard1,toolCard2,toolCard3, observe;
    @FXML
    Button d1,d2,d3,d4,d5,d6,d7,d8,d9;
    @FXML
    Text text;
    @FXML
    Pane pane, pane2;
    @FXML
    ImageView w1,w2,w3,w4;
    @FXML
    Button p1_11,p1_12,p1_13,p1_14,p1_15,p1_21,p1_22,p1_23,p1_24,p1_25,p1_31,p1_32,p1_33,p1_34,p1_35,p1_41,p1_42,p1_43,p1_44,p1_45;
    @FXML
    ImageView p2_11,p2_12,p2_13,p2_14,p2_15,p2_21,p2_22,p2_23,p2_24,p2_25,p2_31,p2_32,p2_33,p2_34,p2_35,p2_41,p2_42,p2_43,p2_44,p2_45;
    @FXML
    ImageView p3_11,p3_12,p3_13,p3_14,p3_15,p3_21,p3_22,p3_23,p3_24,p3_25,p3_31,p3_32,p3_33,p3_34,p3_35,p3_41,p3_42,p3_43,p3_44,p3_45;
    @FXML
    ImageView p4_11,p4_12,p4_13,p4_14,p4_15,p4_21,p4_22,p4_23,p4_24,p4_25,p4_31,p4_32,p4_33,p4_34,p4_35,p4_41,p4_42,p4_43,p4_44,p4_45;

    //Getter/Setter
    public static void setGuiView(GUIView guiView) {
        GUIHandler.guiView = guiView;
    }
    public void setConnected(boolean connected) {
        this.connected = connected;
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

    public static GUIView getGuiView() {
        return guiView;
    }
    public boolean isConnected() {
        return connected;
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

    //Inizializza la gui con la schermata di login
    public void start(Stage primaryStage) throws IOException{
        guiView = new GUIView(new AppController());
        changeScene("menu.fxml",primaryStage);
        guiView.setGuiHandler(this);
        this.stage = primaryStage;
    }

    //Gestori bottoni
    public void  login(ActionEvent actionEvent) throws IOException{
        pane.setVisible(false);
        text.setText(guiView.login(input.getText()) + ".Scegliere il tipo di connessione");
    }
    public void cancel(ActionEvent actionEvent)throws RemoteException{
        if (!connected) {
            pane.setVisible(true);
            text.setText("Inserire il nome utente");
            guiView.logout();
        }
        else{
            if(!queue) {
                rmi.setVisible(true);
                socket.setVisible(true);
                text.setText("Scegliere il tipo di connessione");
                connected = false;
            }
            else{
                multi.setVisible(true);
                single.setVisible(true);
                guiView.getAppController().cancelJoinMatch(guiView.getUserToken());
                queue = false;
            }
        }
    }
    public void connection(ActionEvent actionEvent){
        rmi.setVisible(false);
        socket.setVisible(false);
        text.setText("Scegliere la modalita' di gioco");
        this.connected = true;
    }

    //Attesa
    public void waitGame(ActionEvent actionEvent) throws RemoteException{
        multi.setVisible(false);
        single.setVisible(false);
        this.guiView.getAppController().joinMatch(guiView.getUserToken());
        guiView.getAppController().joinMatch(guiView.getUserToken());
        queue = true;
        text.setText("In attesa di altri giocatori...");
    }
    //Inizia gico
    public void startGame() throws IOException{
        changeScene("game.fxml",this.stage);
    }
    //Osservazione
    public void observe(ActionEvent actionEvent){
        observe.setVisible(!hide);
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
