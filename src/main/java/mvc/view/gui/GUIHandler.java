package mvc.view.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.controller.AppController;
import mvc.model.objects.Cell;
import mvc.model.objects.Match;
import mvc.model.objects.MultiPlayerMatch;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class
GUIHandler extends Application {
    //Gestore della gui dell'applicazione

    private static GUIView guiView;
    private MultiPlayerMatch match;
    private boolean connected = false;
    private boolean queue = false;
    private boolean ready = false;
    private boolean hide = true;
    private Stage stage;

    //Componenti gui
    @FXML
    TextField input;
    @FXML
    Button rmi, socket, multi,single, annulla, observe;
    @FXML
    ImageView publicObjective1, publicObjective2, publicObjective3, toolCard1,toolCard2,toolCard3, privateObjective;
    @FXML
    ImageView d1,d2,d3,d4,d5,d6,d7,d8,d9;
    @FXML
    Text text;
    @FXML
    Pane pane, pane2;
    @FXML
    ImageView w1,w2,w3,w4;
    @FXML
    ImageView p1_11,p1_12,p1_13,p1_14,p1_15,p1_21,p1_22,p1_23,p1_24,p1_25,p1_31,p1_32,p1_33,p1_34,p1_35,p1_41,p1_42,p1_43,p1_44,p1_45;
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
        this.stage = primaryStage;
        changeScene("menu.fxml");
        guiView.setGuiHandler(this);
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

    //associazioni tra bottoni e classi view corrispondenti
    public ImageView associateWindow(int index){
        switch (index){
            case 1: return w1;
            case 2: return w2;
            case 3: return w3;
            case 4: return w4;
        }
        return null;
    }
    public ImageView associateToolCard(int index){
        switch (index){
            case 1: return toolCard1;
            case 2: return  toolCard2;
            case 3: return toolCard3;
        }
        return null;
    }
    public CellView[][] associateCells(Cell[][] cell, int index){
        CellView[][] cells = new CellView[4][5];
        switch (index){
            case 1: for (int i = 0; i<4; i++) {
                for (int j = 0; j < 5; j++) {
                    cells[i][j].setCell(cell[i][j]);
                }
            }
                cells[0][0].setImageView(p1_11);
                cells[0][1].setImageView(p1_12);
                cells[0][2].setImageView(p1_13);
                cells[0][3].setImageView(p1_14);
                cells[0][4].setImageView(p1_15);

                cells[1][0].setImageView(p1_21);
                cells[1][1].setImageView(p1_22);
                cells[1][2].setImageView(p1_23);
                cells[1][3].setImageView(p1_24);
                cells[1][4].setImageView(p1_25);

                cells[2][0].setImageView(p1_31);
                cells[2][1].setImageView(p1_32);
                cells[2][2].setImageView(p1_33);
                cells[2][3].setImageView(p1_34);
                cells[2][4].setImageView(p1_35);

                cells[3][0].setImageView(p1_41);
                cells[3][1].setImageView(p1_42);
                cells[3][2].setImageView(p1_43);
                cells[3][3].setImageView(p1_44);
                cells[3][4].setImageView(p1_45);
                break;
            case 2: for (int i = 0; i<4; i++) {
                for (int j = 0; j < 5; j++) {
                    cells[i][j].setCell(cell[i][j]);
                }
            }
                cells[0][0].setImageView(p2_11);
                cells[0][1].setImageView(p2_12);
                cells[0][2].setImageView(p2_13);
                cells[0][3].setImageView(p2_14);
                cells[0][4].setImageView(p2_15);

                cells[1][0].setImageView(p2_21);
                cells[1][1].setImageView(p2_22);
                cells[1][2].setImageView(p2_23);
                cells[1][3].setImageView(p2_24);
                cells[1][4].setImageView(p2_25);

                cells[2][0].setImageView(p2_31);
                cells[2][1].setImageView(p2_32);
                cells[2][2].setImageView(p2_33);
                cells[2][3].setImageView(p2_34);
                cells[2][4].setImageView(p2_35);

                cells[3][0].setImageView(p2_41);
                cells[3][1].setImageView(p2_42);
                cells[3][2].setImageView(p2_43);
                cells[3][3].setImageView(p2_44);
                cells[3][4].setImageView(p2_45);
                break;

            case 3: for (int i = 0; i<4; i++) {
                for (int j = 0; j < 5; j++) {
                    cells[i][j].setCell(cell[i][j]);
                }
            }
                cells[0][0].setImageView(p3_11);
                cells[0][1].setImageView(p3_12);
                cells[0][2].setImageView(p3_13);
                cells[0][3].setImageView(p3_14);
                cells[0][4].setImageView(p3_15);

                cells[1][0].setImageView(p3_21);
                cells[1][1].setImageView(p3_22);
                cells[1][2].setImageView(p3_23);
                cells[1][3].setImageView(p3_24);
                cells[1][4].setImageView(p3_25);

                cells[2][0].setImageView(p3_31);
                cells[2][1].setImageView(p3_32);
                cells[2][2].setImageView(p3_33);
                cells[2][3].setImageView(p3_34);
                cells[2][4].setImageView(p3_35);

                cells[3][0].setImageView(p3_41);
                cells[3][1].setImageView(p3_42);
                cells[3][2].setImageView(p3_43);
                cells[3][3].setImageView(p3_44);
                cells[3][4].setImageView(p3_45);
                break;

            case 4: for (int i = 0; i<4; i++) {
                for (int j = 0; j < 5; j++) {
                    cells[i][j].setCell(cell[i][j]);
                }
            }
                cells[0][0].setImageView(p4_11);
                cells[0][1].setImageView(p4_12);
                cells[0][2].setImageView(p4_13);
                cells[0][3].setImageView(p4_14);
                cells[0][4].setImageView(p4_15);

                cells[1][0].setImageView(p4_21);
                cells[1][1].setImageView(p4_22);
                cells[1][2].setImageView(p4_23);
                cells[1][3].setImageView(p4_24);
                cells[1][4].setImageView(p4_25);

                cells[2][0].setImageView(p4_31);
                cells[2][1].setImageView(p4_32);
                cells[2][2].setImageView(p4_33);
                cells[2][3].setImageView(p4_34);
                cells[2][4].setImageView(p4_35);

                cells[3][0].setImageView(p4_41);
                cells[3][1].setImageView(p4_42);
                cells[3][2].setImageView(p4_43);
                cells[3][3].setImageView(p4_44);
                cells[3][4].setImageView(p4_45);
                break;
            }
        return cells;
    }
    public ImageView associateDice(int index){
        switch (index){
            case 1: return d1;
            case 2: return d2;
            case 3: return d3;
            case 4: return d4;
            case 5: return d5;
            case 6: return d6;
            case 7: return d7;
            case 8: return d8;
            case 9: return d9;
        }
        return null;
    }
    public ImageView associatePublicObjective(int index){
        switch (index){
            case 1: return publicObjective1;
            case 2: return publicObjective2;
            case 3: return publicObjective3;
        }
        return null;
    }

    //Attesa
    public void waitGame(ActionEvent actionEvent) throws RemoteException{
        multi.setVisible(false);
        single.setVisible(false);
        guiView.getAppController().joinMatch(guiView.getUserToken());
        queue = true;
        text.setText("In attesa di altri giocatori...");
    }
    //Inizia gioco
    public void startGame(MultiPlayerMatch match) throws IOException{
        this.match = match;
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("game.fxml"));
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setPrefSize(1000,799);
        List<WindowView> windows = new ArrayList<>(4);
        for(int i=0; i<4; i++){
            windows.add(new WindowView(new ImageView(), this.getGuiView().getAppController().getModel().retrieveUser(this.getGuiView().getUserToken()).getPlayers().get(0).getStartWindows().get(i),null));
        }
        setImageView(windows.get(0).getImageView(),338,174,103,158);
        setImageView(windows.get(1).getImageView(),338,174,565,158);
        setImageView(windows.get(2).getImageView(),338,174,99,455);
        setImageView(windows.get(3).getImageView(),338,174,588,455);
        for(WindowView windowView: windows){
            windowView.getImageView().setOnMouseClicked(event -> {
                loadGame();
            });
            anchorPane1.getChildren().add(windowView.getImageView());
        }
        root.getChildren().add(anchorPane1);
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public void loadGame(){
        d1.setVisible(false);
        d2.setVisible(false);
        d3.setVisible(false);
        d4.setVisible(false);
        d5.setVisible(false);
        d6.setVisible(false);
        d7.setVisible(false);
        d8.setVisible(false);
        d9.setVisible(false);
        guiView.createGame(this.match);
    }

    public void getWindow(ActionEvent actionEvent){

    }
    //Osservazione
    public void observe(ActionEvent actionEvent){
        observe.setVisible(!hide);
    }

    //Cambia scena nella gui caricandola da un nuovo file FXML
    public void changeScene(String fxml) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void setImageView(ImageView imageView, int w, int h, int x, int y){
        imageView.setFitWidth(w);
        imageView.setFitHeight(h);
        imageView.setX(x);
        imageView.setY(y);
    }

    public static void main(String[] args) throws RemoteException{
        launch(args);
    }
}
