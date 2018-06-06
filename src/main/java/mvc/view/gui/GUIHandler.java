package mvc.view.gui;

import connection.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.model.objects.Cell;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCardInput;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class
GUIHandler extends Application implements Serializable{
    //Gestore della gui dell'applicazione

    private static GUIView guiView;
    private MultiPlayerMatch match;
    private String tokenMatch;
    private boolean connected = false;
    private boolean queue = false;
    private boolean ready = false;
    private boolean hide = true;
    private boolean toPlace = false;

    //Componenti gui
    @FXML
    TextField input;
    @FXML
    TextArea console;
    @FXML
    Button rmi, socket, multi,single, annulla, observe;
    @FXML
    ImageView publicObjective1, publicObjective2, publicObjective3, toolCard1,toolCard2,toolCard3, privateObjective, zoom;
    @FXML
    ImageView d1,d2,d3,d4,d5,d6,d7,d8,d9;
    @FXML
    Text text;
    @FXML
    Pane pane, pane2, roundDice;
    @FXML
    ImageView round1,round2,round3,round4,round5,round6,round7,round8,round9,round10;
    @FXML
    ImageView roundDie1,roundDie2,roundDie3,roundDie4,roundDie5,roundDie6,roundDie7,roundDie8,roundDie9;
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
    public static void setViewConnection(GUIView guiView) {
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
    public void setToPlace(boolean toPlace) {
        this.toPlace = toPlace;
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
    public boolean isToPlace() {
        return toPlace;
    }

    //Inizializza la gui con la schermata di login
    public void start(Stage primaryStage) throws IOException, NotBoundException{
        Client client = new Client();
        client.launchClient(true);
        guiView = new GUIView(client.getController());
        changeScene("menu.fxml", primaryStage);
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
    public ImageView associateRound(int index){
        switch (index){
            case 1: return round1;
            case 2: return round2;
            case 3: return round3;
            case 4: return round4;
            case 5: return round5;
            case 6: return round6;
            case 7: return round7;
            case 8: return round8;
            case 9: return round9;
            case 10: return round10;
        }
        return null;
    }
    public ImageView associateRoundDie(int index){
        switch (index){
            case 0: return roundDie1;
            case 1: return roundDie2;
            case 2: return roundDie3;
            case 3: return roundDie4;
            case 4: return roundDie5;
            case 5: return roundDie6;
            case 6: return roundDie7;
            case 7: return roundDie8;
            case 8: return roundDie9;
        }
        return null;
    }

    //metodi che restituiscono l'oggetto della view in base al bottone selezionato
    public CellView retrieveCell(Object source){
        WindowView windowView = guiView.retrieveThisPlayer().getWindow();
        if(source.equals(p1_11)) return windowView.getCells()[0][0];
        if(source.equals(p1_12)) return windowView.getCells()[0][1];
        if(source.equals(p1_13)) return windowView.getCells()[0][2];
        if(source.equals(p1_14)) return windowView.getCells()[0][3];
        if(source.equals(p1_15)) return windowView.getCells()[0][4];
        if(source.equals(p1_21)) return windowView.getCells()[1][0];
        if(source.equals(p1_22)) return windowView.getCells()[1][1];
        if(source.equals(p1_23)) return windowView.getCells()[1][2];
        if(source.equals(p1_24)) return windowView.getCells()[1][3];
        if(source.equals(p1_25)) return windowView.getCells()[1][4];
        if(source.equals(p1_31)) return windowView.getCells()[2][0];
        if(source.equals(p1_32)) return windowView.getCells()[2][1];
        if(source.equals(p1_33)) return windowView.getCells()[2][2];
        if(source.equals(p1_34)) return windowView.getCells()[2][3];
        if(source.equals(p1_35)) return windowView.getCells()[2][4];
        if(source.equals(p1_41)) return windowView.getCells()[3][0];
        if(source.equals(p1_42)) return windowView.getCells()[3][1];
        if(source.equals(p1_43)) return windowView.getCells()[3][2];
        if(source.equals(p1_44)) return windowView.getCells()[3][3];
        if(source.equals(p1_45)) return windowView.getCells()[3][4];
        return null;
    }
    public DieView retrieveDie(Object source){
        if(source.equals(d1)) return guiView.getDice().get(0);
        if(source.equals(d2)) return guiView.getDice().get(1);
        if(source.equals(d3)) return guiView.getDice().get(2);
        if(source.equals(d4)) return guiView.getDice().get(3);
        if(source.equals(d5)) return guiView.getDice().get(4);
        if(source.equals(d6)) return guiView.getDice().get(5);
        if(source.equals(d7)) return guiView.getDice().get(6);
        if(source.equals(d8)) return guiView.getDice().get(7);
        if(source.equals(d9)) return guiView.getDice().get(8);
        return null;
    }
    public ToolCardView retrieveToolCard(Object source){
        if(source.equals(toolCard1)) return guiView.getToolCards().get(0);
        if(source.equals(toolCard2)) return guiView.getToolCards().get(0);
        if(source.equals(toolCard3)) return guiView.getToolCards().get(0);
        return null;
    }
    public DieView retrieveRoundDie(Object source){
        List<DieView> dice = guiView.getRounds().get(match.getTurnHandler().getRound()-1).getDieViews();
       if(source.equals(roundDie1)) return dice.get(0);
       if(source.equals(roundDie2)) return dice.get(1);
       if(source.equals(roundDie3)) return dice.get(2);
       if(source.equals(roundDie4)) return dice.get(3);
       if(source.equals(roundDie5)) return dice.get(4);
       if(source.equals(roundDie6)) return dice.get(5);
       if(source.equals(roundDie7)) return dice.get(6);
       if(source.equals(roundDie8)) return dice.get(7);
       if(source.equals(roundDie9)) return dice.get(8);
       return null;
    }

    //metodi che creano liste con le tool card desiderate
    public List<String> WindowToolCards(){
        List<String> toolCards = new ArrayList<>();
        toolCards.add("pennello per eglomise");
        toolCards.add("alesatore per lamina di rame");
        toolCards.add("lathekin");
        toolCards.add("taglierina manuale");
        return toolCards;
    }
    public List<String> draftPoolToolCards(){
        List<String> toolCards = new ArrayList<>();
        toolCards.add("pinza sgrossatrice");
        toolCards.add("taglierina circolare");
        toolCards.add("pennello per pasta salda");
        toolCards.add("riga di sughero");
        toolCards.add("tampone diamantato");
        toolCards.add("diluente per pasta salda");
        return toolCards;
    }
    public List<String> choiceToolCards(){
        List<String> toolCards = new ArrayList<>();
        toolCards.add("pinza sgrossatrice");
        toolCards.add("diluente per pasta salda");
        toolCards.add("taglierina circolare");
        return toolCards;
    }
    public List<String> noSelectionToolCards(){
        List<String> toolCards = new ArrayList<>();
        toolCards.add("martelletto");
        toolCards.add("tenaglia a rotelle");
        return toolCards;
    }
    public List<String> toPlaceToolCard(){
        List<String> toolCards = new ArrayList<>();
        toolCards.add("pinza sgrossatrice");
        toolCards.add("pennello per pasta salda");
        toolCards.add("riga di sughero");
        toolCards.add("tampone diamantato");
        toolCards.add("diluente per pasta salda");
        return toolCards;
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
    public void startGame(MultiPlayerMatch match, String tokenMatch) throws IOException{
        this.match = match;
        this.tokenMatch = tokenMatch;
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("game.fxml"));
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setPrefSize(1270,806);
        List<WindowView> windows = new ArrayList<>(4);
        for(Player player:match.getPlayers()){
            if (player.getUser().getAppView().equals(this.guiView)){
                for( int i = 0; i<4; i++){
                    windows.add(new WindowView(new ImageView(),player.getStartWindows().get(i),null));
                }
                break;
            }
        }
        setImageView(windows.get(0).getImageView(),338,174,103,158);
        setImageView(windows.get(1).getImageView(),338,174,565,158);
        setImageView(windows.get(2).getImageView(),338,174,99,455);
        setImageView(windows.get(3).getImageView(),338,174,588,455);
        for(WindowView windowView: windows){
            windowView.getImageView().setOnMouseClicked(event -> {
                try {
                    this.guiView.getAppController().chooseWindow(this.guiView.getUserToken(),tokenMatch,windowView.getWindow());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            anchorPane1.getChildren().add(windowView.getImageView());
        }
        root.getChildren().add(anchorPane1);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public void initializeGameGui(){
        d1.setVisible(false);
        d2.setVisible(false);
        d3.setVisible(false);
        d4.setVisible(false);
        d5.setVisible(false);
        d6.setVisible(false);
        d7.setVisible(false);
        d8.setVisible(false);
        d9.setVisible(false);
        roundDice.setVisible(false);
        hide = false;
        observe.setVisible(false);
        round1.setVisible(false);
        round2.setVisible(false);
        round3.setVisible(false);
        round4.setVisible(false);
        round5.setVisible(false);
        round6.setVisible(false);
        round7.setVisible(false);
        round8.setVisible(false);
        round9.setVisible(false);
        round10.setVisible(false);
    }

    //gioco
    public void dieMove(ActionEvent actionEvent) throws RemoteException{
        if(this.match.getTurnPlayer().getUser().getAppView().equals(guiView)){
            return;
        }
        if(this.toPlace){
            console.setText("devi prima posizionare il dado già selezionato");
        }
        DieView selectedDie = retrieveDie(actionEvent.getSource());

        if(selectedDie != guiView.getSelectedDie()){
            if(guiView.getSelectedToolCard() != null){
                String name = guiView.getSelectedToolCard().getToolCard().getName();
                if(draftPoolToolCards().contains(name)){
                    if(choiceToolCards().contains(name)){
                     //TODO: implementare carte con scelta
                        if(name.equals("taglierina circolare")){
                            guiView.getInput().setChoosenDie(selectedDie.getDie());
                            this.console.setText("scegli un dado del tracciato dei round");
                            return;
                        }
                    }else{
                        guiView.getInput().setChoosenDie(selectedDie.getDie());
                        guiView.getAppController().useToolCard(guiView.getUserToken(),this.tokenMatch,guiView.getInput(),guiView.getSelectedToolCard().getToolCard());
                    }
                }else{
                    this.console.setText("dado non valido per questa tool card");
                    return;
                }
            }else{
                guiView.setSelectedDie(selectedDie);
                this.console.setText("hai selezionato il dado"+ guiView.getSelectedDie().getDie().getShade() + "" + guiView.getSelectedDie().getDie().getColor().toString());
            }
        }else{
            if(this.isToPlace()){
                return;
            }else {
                this.console.setText("hai deselezionato il dado"+ guiView.getSelectedDie().getDie().getShade() + "" + guiView.getSelectedDie().getDie().getColor().toString());
                guiView.setSelectedDie(null);

                return;
            }
        }
    }
    public void cellMove(ActionEvent actionEvent) throws RemoteException{
        if(this.match.getTurnPlayer().getUser().getAppView().equals(guiView)){
            return;
        }
        CellView selectedCell = retrieveCell(actionEvent);
        if(selectedCell.getCell().getDie()==null){
            if(guiView.getSelectedDie() != null){
                this.setToPlace(false);
                guiView.getAppController().placeDie(guiView.getUserToken(),this.tokenMatch,selectedCell.getCell(),guiView.getSelectedDie().getDie());
                return;
            }else{
                if(guiView.getInput().getChoosenDie()!=null){
                    String name = guiView.getSelectedToolCard().getToolCard().getName();
                    if(WindowToolCards().contains(name)){
                        guiView.getInput().setDestinationCell1(selectedCell.getCell());
                        guiView.getAppController().useToolCard(guiView.getUserToken(), this.tokenMatch, guiView.getInput(), guiView.getSelectedToolCard().getToolCard());
                        return;
                    }else{
                        return;
                    }
                }else {
                    return;
                }
            }
        }else {
            if (guiView.getSelectedToolCard() != null){
                String name = guiView.getSelectedToolCard().getToolCard().getName();
                if(WindowToolCards().contains(name)){
                    guiView.getInput().setChoosenDie(selectedCell.getCell().getDie());
                    guiView.getInput().setOriginCell1(selectedCell.getCell());
                    return;
                }
            }else{
                return;
            }
        }
    }
    public void toolCardMove(ActionEvent actionEvent) throws RemoteException{
        if(this.match.getTurnPlayer().getUser().getAppView().equals(guiView)){
            return;
        }
        if(this.isToPlace()){
            console.setText("devi prima posizionare il dado già selezionato");
            return;
        }
        ToolCardView selectedToolCard = retrieveToolCard(actionEvent.getSource());
        if(selectedToolCard != guiView.getSelectedToolCard()){
            guiView.setSelectedToolCard(selectedToolCard);
            guiView.setSelectedDie(null);
            guiView.setInput(new ToolCardInput(null, null, null,null,match.getTurnHandler().getRound(),null,null,null,0,false));
            String name = guiView.getSelectedToolCard().getToolCard().getName();
            if(noSelectionToolCards().contains(name)){
                guiView.getAppController().useToolCard(guiView.getUserToken(),this.tokenMatch, guiView.getInput(),guiView.getSelectedToolCard().getToolCard());
            }else{
                console.setText("hai selezionato la carta tool" + guiView.getSelectedToolCard().getToolCard().getName());
                return;
            }
        }else{
            console.setText("hai deselezionato la carta tool" + guiView.getSelectedToolCard().getToolCard().getName());
            guiView.setSelectedToolCard(null);
            guiView.setInput(null);
        }

    }
    public void roundDieMove(ActionEvent actionEvent) throws RemoteException{
        if(this.match.getTurnPlayer().getUser().getAppView().equals(guiView)){
            return;
        }
        DieView selectedRoundDie = retrieveRoundDie(actionEvent.getSource());
        if(guiView.getSelectedToolCard() != null){
            String name = guiView.getSelectedToolCard().getToolCard().getName();
            if(name.equals("taglierina circolare")) {
                guiView.getInput().setRoundTrackDie(selectedRoundDie.getDie());
                return;
            }else{
                return;
            }
        }else{
            return;
        }
    }
    public void endTurn(ActionEvent actionEvent) throws RemoteException{
        guiView.getAppController().endTurn(guiView.getUserToken(),this.tokenMatch);
    }


    //Osservazione
    public void observe(ActionEvent actionEvent){
        observe.setVisible(!hide);
    }
    public void observeRoundDice(int round){
        for(int i=0;i<9; i++){
            if(guiView.getRounds().get(round).getDieViews().get(i)!= null){
                associateRoundDie(i).setVisible(true);
                associateRoundDie(i).setImage(guiView.getRounds().get(round).getDieViews().get(i).imagePath());
            }else{
                associateRoundDie(i).setVisible(false);
            }
        }

    }
    public void zoomIn(ActionEvent actionEvent){
        if(actionEvent.getSource().equals(toolCard1)) zoom.setImage(toolCard1.getImage());
        if(actionEvent.getSource().equals(toolCard2)) zoom.setImage(toolCard2.getImage());
        if(actionEvent.getSource().equals(toolCard3)) zoom.setImage(toolCard3.getImage());
        if(actionEvent.getSource().equals(publicObjective1)) zoom.setImage(publicObjective1.getImage());
        if(actionEvent.getSource().equals(publicObjective2)) zoom.setImage(publicObjective2.getImage());
        if(actionEvent.getSource().equals(publicObjective3)) zoom.setImage(publicObjective3.getImage());
        if(actionEvent.getSource().equals(privateObjective)) zoom.setImage(privateObjective.getImage());
    }
    public void zoomOut(ActionEvent actionEvent){
        zoom.setImage(null);
    }
    public void roundTrackDice(ActionEvent actionEvent){
        Object source = actionEvent.getSource();
        roundDice.setVisible(true);
        if(source.equals(round1)) observeRoundDice(1);
        if(source.equals(round2)) observeRoundDice(2);
        if(source.equals(round3)) observeRoundDice(3);
        if(source.equals(round4)) observeRoundDice(4);
        if(source.equals(round5)) observeRoundDice(5);
        if(source.equals(round6)) observeRoundDice(6);
        if(source.equals(round7)) observeRoundDice(7);
        if(source.equals(round8)) observeRoundDice(8);
        if(source.equals(round9)) observeRoundDice(9);
        if(source.equals(round10)) observeRoundDice(10);
    }
    public void closeRoundDicePanel(ActionEvent actionEvent){
        roundDice.setVisible(false);
    }

    //Cambia scena nella gui caricandola da un nuovo file FXML
    public void changeScene(String fxml, Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
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

   public static void main(String[] args){
        launch(args);
    }
}
