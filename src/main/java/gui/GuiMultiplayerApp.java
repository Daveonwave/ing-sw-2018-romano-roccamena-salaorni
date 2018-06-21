package gui;

import gui.objects.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mvc.model.objects.*;
import mvc.stubs.MultiplayerObserver;
import mvc.stubs.ViewResponder;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GuiMultiplayerApp implements ViewResponder, MultiplayerObserver, Serializable {

    //Finestra gui di una partita multiplayer
    public final static String FXML_PATH = "fxml/Match.fxml";
    public final static String TITLE = "Sagrada Multiplayer";

    private static GuiView guiView;
    private  static MultiPlayerMatch multiPlayerMatch;
    private static String multiTokenMatch;
    private PointsWindowController pointsWindow;

    private boolean toPlace = false;
    private boolean twoMoves = false;
    private int roundView = 0;
    private boolean choice = false;

    private MatchView matchView;

    @FXML
    TextArea console;
    @FXML
    ImageView background;
    @FXML
    Label player2Name, player3Name, player4Name, favorTokens;
    @FXML
    AnchorPane matchAnchorPane;
    @FXML
    Pane pane2, roundDice;
    @FXML
    ImageView w1, w2, w3, w4;
    @FXML
    ImageView d1, d2, d3, d4, d5, d6, d7, d8, d9;
    @FXML
    ImageView p1_11, p1_12, p1_13, p1_14, p1_15, p1_21, p1_22, p1_23, p1_24, p1_25, p1_31, p1_32, p1_33, p1_34, p1_35, p1_41, p1_42, p1_43, p1_44, p1_45;
    @FXML
    ImageView p2_11, p2_12, p2_13, p2_14, p2_15, p2_21, p2_22, p2_23, p2_24, p2_25, p2_31, p2_32, p2_33, p2_34, p2_35, p2_41, p2_42, p2_43, p2_44, p2_45;
    @FXML
    ImageView p3_11, p3_12, p3_13, p3_14, p3_15, p3_21, p3_22, p3_23, p3_24, p3_25, p3_31, p3_32, p3_33, p3_34, p3_35, p3_41, p3_42, p3_43, p3_44, p3_45;
    @FXML
    ImageView p4_11, p4_12, p4_13, p4_14, p4_15, p4_21, p4_22, p4_23, p4_24, p4_25, p4_31, p4_32, p4_33, p4_34, p4_35, p4_41, p4_42, p4_43, p4_44, p4_45;
    @FXML
    ImageView publicObjective1, publicObjective2, publicObjective3, toolCard1, toolCard2, toolCard3, privateObjective1, zoom;
    @FXML
    ImageView roundTrack, round1, round2, round3, round4, round5, round6, round7, round8, round9, round10;
    @FXML
    ImageView roundDie1, roundDie2, roundDie3, roundDie4, roundDie5, roundDie6, roundDie7, roundDie8, roundDie9;



    //Setter/Getter
    public MatchView getMatchView() {
        return matchView;
    }
    public static GuiView getGuiView() {
        return guiView;
    }
    public static MultiPlayerMatch getMultiPlayerMatch() {
        return multiPlayerMatch;
    }
    public static String getMultiTokenMatch() {
        return multiTokenMatch;
    }
    public PointsWindowController getPointsWindow() {
        return pointsWindow;
    }

    public void setMatchView(MatchView matchView) {
        this.matchView = matchView;
    }
    public static void setMultiPlayerMatch(MultiPlayerMatch multiPlayerMatch) {
        GuiMultiplayerApp.multiPlayerMatch = multiPlayerMatch;
    }

    public static void setGuiView(GuiView guiView) {
        GuiMultiplayerApp.guiView = guiView;
    }

    public static void setMultiTokenMatch(String multiTokenMatch) {
        GuiMultiplayerApp.multiTokenMatch = multiTokenMatch;
    }

    public void setPointsWindow(PointsWindowController pointsWindow) {
        this.pointsWindow = pointsWindow;
    }

    //Crea e visualizza la finestra
    public void show(MultiPlayerMatch match, String tokenMatch) throws IOException {
        multiPlayerMatch = match;
        multiTokenMatch = tokenMatch;
        createWindowChoiceGui();
    }


    //Associazioni tra bottoni e classi view corrispondenti
    public ImageView associateWindow(int index) {
        switch (index) {
            case 1:
                return w1;
            case 2:
                return w2;
            case 3:
                return w3;
            case 4:
                return w4;
        }
        return null;
    }
    public ImageView associateToolCard(int index) {
        switch (index) {
            case 1:
                return toolCard1;
            case 2:
                return toolCard2;
            case 3:
                return toolCard3;
        }
        return null;
    }
    public CellView[][] associateCells(Cell[][] cell, int index) {
        CellView[][] cells = new CellView[4][5];
        switch (index) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
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
            case 2:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
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

            case 3:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
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

            case 4:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
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
    public ImageView associateDice(int index) {
        switch (index) {
            case 1:
                return d1;
            case 2:
                return d2;
            case 3:
                return d3;
            case 4:
                return d4;
            case 5:
                return d5;
            case 6:
                return d6;
            case 7:
                return d7;
            case 8:
                return d8;
            case 9:
                return d9;
        }
        return null;
    }
    public ImageView associatePublicObjective(int index) {
        switch (index) {
            case 1:
                return publicObjective1;
            case 2:
                return publicObjective2;
            case 3:
                return publicObjective3;
        }
        return null;
    }
    public ImageView associateRound(int index) {
        switch (index) {
            case 1:
                return round1;
            case 2:
                return round2;
            case 3:
                return round3;
            case 4:
                return round4;
            case 5:
                return round5;
            case 6:
                return round6;
            case 7:
                return round7;
            case 8:
                return round8;
            case 9:
                return round9;
            case 10:
                return round10;
        }
        return null;
    }
    public ImageView associateRoundDie(int index) {
        switch (index) {
            case 0:
                return roundDie1;
            case 1:
                return roundDie2;
            case 2:
                return roundDie3;
            case 3:
                return roundDie4;
            case 4:
                return roundDie5;
            case 5:
                return roundDie6;
            case 6:
                return roundDie7;
            case 7:
                return roundDie8;
            case 8:
                return roundDie9;
        }
        return null;
    }
    public Label associateNameLabel(int index) {
        switch (index) {
            case 2:
                return player2Name;
            case 3:
                return player3Name;
            case 4:
                return player4Name;
        }
        return null;
    }

    //Restituiscono l'oggetto della view in base al bottone selezionato
    public CellView retrieveCell(Object source) {
        WindowView windowView = matchView.retrieveThisPlayer(guiView.getUserName()).getWindow();

        if (source.equals(p1_11)) return windowView.getCells()[0][0];
        if (source.equals(p1_12)) return windowView.getCells()[0][1];
        if (source.equals(p1_13)) return windowView.getCells()[0][2];
        if (source.equals(p1_14)) return windowView.getCells()[0][3];
        if (source.equals(p1_15)) return windowView.getCells()[0][4];
        if (source.equals(p1_21)) return windowView.getCells()[1][0];
        if (source.equals(p1_22)) return windowView.getCells()[1][1];
        if (source.equals(p1_23)) return windowView.getCells()[1][2];
        if (source.equals(p1_24)) return windowView.getCells()[1][3];
        if (source.equals(p1_25)) return windowView.getCells()[1][4];
        if (source.equals(p1_31)) return windowView.getCells()[2][0];
        if (source.equals(p1_32)) return windowView.getCells()[2][1];
        if (source.equals(p1_33)) return windowView.getCells()[2][2];
        if (source.equals(p1_34)) return windowView.getCells()[2][3];
        if (source.equals(p1_35)) return windowView.getCells()[2][4];
        if (source.equals(p1_41)) return windowView.getCells()[3][0];
        if (source.equals(p1_42)) return windowView.getCells()[3][1];
        if (source.equals(p1_43)) return windowView.getCells()[3][2];
        if (source.equals(p1_44)) return windowView.getCells()[3][3];
        if (source.equals(p1_45)) return windowView.getCells()[3][4];

        return null;
    }
    public DieView retrieveDie(Object source) {
        if (source.equals(d1)) return matchView.getDice().get(0);
        if (source.equals(d2)) return matchView.getDice().get(1);
        if (source.equals(d3)) return matchView.getDice().get(2);
        if (source.equals(d4)) return matchView.getDice().get(3);
        if (source.equals(d5)) return matchView.getDice().get(4);
        if (source.equals(d6)) return matchView.getDice().get(5);
        if (source.equals(d7)) return matchView.getDice().get(6);
        if (source.equals(d8)) return matchView.getDice().get(7);
        if (source.equals(d9)) return matchView.getDice().get(8);

        return null;
    }
    public ToolCardView retrieveToolCard(Object source) {
        if (source.equals(toolCard1)) return matchView.getToolCards().get(0);
        if (source.equals(toolCard2)) return matchView.getToolCards().get(1);
        if (source.equals(toolCard3)) return matchView.getToolCards().get(2);
        return null;
    }
    public DieView retrieveRoundDie(Object source) {
        List<DieView> dice = matchView.getRounds().get(roundView-1).getDieViews();
        if (source.equals(roundDie1)) return dice.get(0);
        if (source.equals(roundDie2)) return dice.get(1);
        if (source.equals(roundDie3)) return dice.get(2);
        if (source.equals(roundDie4)) return dice.get(3);
        if (source.equals(roundDie5)) return dice.get(4);
        if (source.equals(roundDie6)) return dice.get(5);
        if (source.equals(roundDie7)) return dice.get(6);
        if (source.equals(roundDie8)) return dice.get(7);
        if (source.equals(roundDie9)) return dice.get(8);
        return null;
    }

    //Ottengono liste di oggetti gioco separati secondo relazione di utilizzo
    public List<String> windowToolCards() {

        List<String> toolCards = new ArrayList<>();
        toolCards.add("pennello per eglomise");
        toolCards.add("alesatore per lamina di rame");
        toolCards.add("lathekin");
        toolCards.add("taglierina manuale");

        return toolCards;
    }
    public List<String> draftPoolToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("pinza sgrossatrice");
        toolCards.add("taglierina circolare");
        toolCards.add("pennello per pasta salda");
        toolCards.add("riga di sughero");
        toolCards.add("tampone diamantato");
        toolCards.add("diluente per pasta salda");

        return toolCards;
    }
    public List<String> noSelectionToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("martelletto");
        toolCards.add("tenaglia a rotelle");

        return toolCards;
    }
    public List<String> toPlaceToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("pinza sgrossatrice");
        toolCards.add("riga di sughero");
        toolCards.add("tampone diamantato");
        toolCards.add("diluente per pasta salda");

        return toolCards;
    }


    //Crea oggetti gui gioco a inizio partita
    public void createWindowChoiceGui() throws IOException {
        //Carica fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/WindowChoiceMenu.fxml"));
        Parent root = loader.load();
        FXWindowChoiceMenu choiceMenu = loader.getController();
        choiceMenu.initializeMenu(multiPlayerMatch, this);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Inizializza oggetti gui gioco a round iniziati
    public void initializeMatchRoundsGui(MultiPlayerMatch match) {
        initializeDice();
        roundDice.setVisible(false);
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

        roundTrack.setImage(new Image(getClass().getResourceAsStream("objects/images/roundtrack.PNG")));
        console.setText("partita iniziata. Turno di "+ match.getTurnPlayer().getUser().getName());
        int window = 2;
        //Setta componenti view finestre e carte private
        matchView = new MatchView(new ArrayList<>(),new ArrayList<>(),null,new ArrayList<>(),new ArrayList<>(),null,new ArrayList<>(),null,null);
        for (Player player : match.getPlayers()) {
            if (player.getUser().getName().equals(guiView.getUserName())) {
                matchView.getPlayers().add(new PlayerView(new WindowView(associateWindow(1), player.getWindow(), associateCells(player.getWindow().getCells(), 1)), player));
                matchView.setPrivateObjective(new ObjectiveCardView(privateObjective1, player.getPrivateObjectiveCards().get(0)));
                favorTokens.setText("" + player.getFavorTokens());
            } else {
                matchView.getPlayers().add(new PlayerView(new WindowView(associateWindow(window), player.getWindow(), associateCells(player.getWindow().getCells(), window)), player));
                associateNameLabel(window).setText(player.getUser().getName());
                window++;
            }
        }

        //Setta componenti view carte strumento e tracciato dadi
        for (ToolCard toolCard : match.getToolCards()) {
            matchView.getToolCards().add(new ToolCardView(associateToolCard(match.getToolCards().indexOf(toolCard) + 1), toolCard));
        }
        for (Die die : match.getMatchDice().getDraftPool()) {
            matchView.getDice().add(new DieView(associateDice(match.getMatchDice().getDraftPool().indexOf(die) + 1), die));
        }
        for (int i = 1; i < 11; i++) {
            matchView.getRounds().add(new RoundView(associateRound(i), new ArrayList<>(), i));
        }

        //Setta componenti view riserva dadi e carte obiettivo pubbliche
        for (DieView die : matchView.getDice()) {
            die.getImageView().setVisible(true);
        }
        for (PublicObjectiveCard card : match.getPublicObjectiveCards()) {
            matchView.getPublicObjective().add(new ObjectiveCardView(associatePublicObjective(match.getPublicObjectiveCards().indexOf(card) + 1), card));
        }

    }
    public void initializeDice(){
        d1.setVisible(false);
        d2.setVisible(false);
        d3.setVisible(false);
        d4.setVisible(false);
        d5.setVisible(false);
        d6.setVisible(false);
        d7.setVisible(false);
        d8.setVisible(false);
        d9.setVisible(false);
    }

    //Crea oggetti gui gioco a round iniziati
    public void createMatchRoundsGui(MultiPlayerMatch match)throws IOException{
        //Inizializza componenti
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Match.fxml"));

        Parent root = loader.load();

        GuiMultiplayerApp guiController = loader.getController();
        guiController.setMultiPlayerMatch(match);
        guiView.getMultiplayerApps().replace(multiTokenMatch, guiController);
        guiController.initializeMatchRoundsGui(match);

        Stage matchStage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("fxml/style.css").toExternalForm());
        matchStage.setScene(scene);
        matchStage.setResizable(false);
        matchStage.show();
    }

    //Eventi componenti gui di mosse della partita
    public void onDieClick(MouseEvent actionEvent) {
        if (!this.multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())) {
            return;
        }
        if (this.toPlace) {
            console.setText("devi prima posizionare il dado già selezionato");
            return;
        }
        if(choice){
            console.setText("devi prima compiere una scelta");
            return;
        }

        DieView selectedDie = retrieveDie(actionEvent.getSource());

        if (selectedDie != matchView.getSelectedDie()) {
            if (matchView.getSelectedToolCard() != null) {
                String name = matchView.getSelectedToolCard().getToolCard().getName();
                if (draftPoolToolCards().contains(name)) {
                    if (name.equals("taglierina circolare")) {
                        matchView.getInput().setChoosenDie(selectedDie.getDie());
                        this.console.setText("scegli un dado del tracciato dei round");
                        return;
                    } else {
                        matchView.getInput().setChoosenDie(selectedDie.getDie());
                        try {
                            guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    console.setText("il dado selezionato non va bene per questa tool card");
                    return;
                }

            } else {
                if(matchView.getSelectedDie() != null){
                    matchView.getSelectedDie().getImageView().getStyleClass().remove("selected");
                }
                matchView.setSelectedDie(selectedDie);
                matchView.getSelectedDie().getImageView().getStyleClass().add("selected");
                this.console.setText("hai selezionato il dado " + matchView.getSelectedDie().getDie().getShade() + " " + matchView.getSelectedDie().getDie().getColor().toString());

            }

        } else {
            this.console.setText("hai deselezionato il dado " + matchView.getSelectedDie().getDie().getShade() + " " + matchView.getSelectedDie().getDie().getColor());
            matchView.getSelectedDie().getImageView().getStyleClass().remove("selected");
            matchView.setSelectedDie(null);
            return;
        }
    }
    public void onCellClick(MouseEvent actionEvent){
        if(!multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            return;
        }
        if(choice){
            console.setText("devi prima compiere una scelta");
            return;
        }

        CellView selectedCell = retrieveCell(actionEvent.getSource());

        if(selectedCell.getCell().getDie()==null){
            if(matchView.getSelectedDie() != null){
                toPlace = false;
                for(DieView dieView : matchView.getDice()){
                    if(dieView.getDie().getColor().equals(matchView.getSelectedDie().getDie().getColor()) && dieView.getDie().getShade() == matchView.getSelectedDie().getDie().getShade()){
                        matchView.setSelectedDie(dieView);
                        break;
                    }
                }
                try {
                    guiView.getController().placeDie(guiView.getUserToken(), multiTokenMatch,selectedCell.getCell(), matchView.getSelectedDie().getDie());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return;
            } else {
                if(matchView.getSelectedToolCard() != null) {
                    if (matchView.getInput().getOriginCell1() != null) {
                        String name = matchView.getSelectedToolCard().getToolCard().getName();
                        if (windowToolCards().contains(name)) {
                            if(name.equals("lathekin") || twoMoves) {
                                if(matchView.getInput().getDestinationCell1() == null) {
                                    matchView.getInput().setDestinationCell1(selectedCell.getCell());
                                    matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView().getStyleClass().remove("salected");
                                    console.setText("prima cella di destinazione selezionata");
                                    return;
                                }else{
                                    matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell2().getRow()][matchView.getInput().getOriginCell2().getColumn()].getImageView().getStyleClass().remove("salected");
                                    matchView.getInput().setDestinationCell2(selectedCell.getCell());
                                }
                            }else{
                                matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView().getStyleClass().remove("salected");
                                matchView.getInput().setDestinationCell1(selectedCell.getCell());
                            }
                            try {
                                guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch,matchView.getInput(),matchView.getSelectedToolCard().getToolCard());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        } else {
            if (matchView.getSelectedToolCard() != null){
                String name = matchView.getSelectedToolCard().getToolCard().getName();
                if(windowToolCards().contains(name)){
                    if(name.equals("lathekin") || twoMoves) {
                        if(matchView.getInput().getOriginCell1() != null) {
                            matchView.getInput().setOriginCell2(selectedCell.getCell());
                            selectedCell.getImageView().getStyleClass().add("selected");
                            console.setText("seconda cella di partenza selezionata");
                            return;
                        }
                    }
                    matchView.getInput().setOriginCell1(selectedCell.getCell());
                    selectedCell.getImageView().getStyleClass().add("selected");
                    console.setText("prima cella di partenza selezionata");
                }
            } else {
                if(matchView.getSelectedDie() != null){
                    console.setText("la cella è già occupata da un altro dado");
                }
            }
        }
    }
    public void onToolCardClick(MouseEvent actionEvent){
        if(!multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            return;
        }
        if(toPlace){
            console.setText("devi prima posizionare il dado già selezionato");
            return;
        }
        if(choice){
            console.setText("devi prima compiere una scelta");
            return;
        }

        ToolCardView selectedToolCard = retrieveToolCard(actionEvent.getSource());

        if(selectedToolCard != matchView.getSelectedToolCard()){
            if(matchView.getSelectedToolCard() != null){
                matchView.getSelectedToolCard().getImageView().getStyleClass().remove("selected");
            }
            matchView.setSelectedToolCard(selectedToolCard);
            matchView.getSelectedToolCard().getImageView().getStyleClass().add("selected");
            if(matchView.getSelectedDie() != null) {
                matchView.getSelectedDie().getImageView().getStyleClass().remove("selected");
                matchView.setSelectedDie(null);
            }
            matchView.setInput(new ToolCardInput(null, null, null,null, multiPlayerMatch.getTurnHandler().getRound(),null,null,null,0,false));
            String name = matchView.getSelectedToolCard().getToolCard().getName();
            if(noSelectionToolCards().contains(name)){
                try {
                    guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }else{
                if(selectedToolCard.getToolCard().getName().equals("pinza sgrossatrice")){
                    choice = true;
                    Pane pane = new Pane();
                    pane.setPrefWidth(208);
                    pane.setPrefHeight(91);
                    matchAnchorPane.getChildren().add(pane);
                    pane.setLayoutX(451);
                    pane.setLayoutY(207);
                    Button increase = new Button("+");
                    increase.setPrefSize(52,45);
                    pane.getChildren().add(increase);
                    increase.setLayoutX(24);
                    increase.setLayoutY(23);
                    console.setText("decidi se aumentare o diminuire il valore del dado");
                    increase.setOnMouseClicked(event -> {
                        console.setText("seleziona il dado di cui aumentare il valore");
                        pane.setVisible(false);
                        choice = false;
                        matchView.getInput().setIncreaseShade(true);
                    });
                    Button decrease = new Button("-");
                    decrease.setPrefSize(52,45);
                    pane.getChildren().add(decrease);
                    decrease.setLayoutX(132);
                    decrease.setLayoutY(23);
                    decrease.setOnMouseClicked(event -> {
                        console.setText("seleziona il dado di cui diminuire il valore");
                        matchAnchorPane.getChildren().remove(pane);
                        choice = false;
                        matchView.getInput().setIncreaseShade(false);
                    });
                }
                if(selectedToolCard.getToolCard().getName().equals("diluente per pasta salda")){
                    choice = true;
                    Pane pane = new Pane();
                    pane.setPrefWidth(208);
                    pane.setPrefHeight(91);
                    matchAnchorPane.getChildren().add(pane);
                    pane.setLayoutX(451);
                    pane.setLayoutY(207);
                    TextField textField = new TextField();
                    textField.setPrefSize(108,38);
                    pane.getChildren().add(textField);
                    textField.setLayoutX(13);
                    textField.setLayoutY(27);
                    Button button = new Button("scegli");
                    button.setPrefSize(51.625,25);
                    pane.getChildren().add(button);
                    button.setLayoutX(135);
                    button.setLayoutY(34);
                    console.setText("scegli che valore dare al dado ripescato");
                    button.setOnMouseClicked(event -> {
                        if(Integer.parseInt(textField.getText())>0 && Integer.parseInt(textField.getText())<7){
                            matchView.getInput().setChoosenShade(Integer.parseInt(textField.getText()));
                            console.setText("selezionare il dado da ripescare");
                            choice = false;
                            matchAnchorPane.getChildren().remove(pane);
                        }else{
                            console.setText("input non valido");
                        }
                    });
                }
                if(selectedToolCard.getToolCard().getName().equals("taglierina manuale")) {
                    choice = true;
                    Pane pane = new Pane();
                    pane.setPrefWidth(208);
                    pane.setPrefHeight(91);
                    matchAnchorPane.getChildren().add(pane);
                    pane.setLayoutX(451);
                    pane.setLayoutY(207);
                    TextField textField = new TextField();
                    textField.setPrefSize(108, 38);
                    pane.getChildren().add(textField);
                    textField.setLayoutX(13);
                    textField.setLayoutY(27);
                    Button button = new Button("scegli");
                    button.setPrefSize(51.625, 25);
                    pane.getChildren().add(button);
                    button.setLayoutX(135);
                    button.setLayoutY(34);
                    console.setText("scegli quanti spostamenti vuoi fare( 1 o 2)");
                    button.setOnMouseClicked(event -> {
                        if (Integer.parseInt(textField.getText()) > 0 && Integer.parseInt(textField.getText()) < 3) {
                            if(Integer.parseInt(textField.getText()) == 2) {
                                twoMoves = true;
                            }
                            console.setText("selezionare i dadi da spostare");
                            choice = false;
                            matchAnchorPane.getChildren().remove(pane);
                        } else {
                            console.setText("input non valido");
                        }
                    });
                }
                console.setText("hai selezionato la carta tool " + matchView.getSelectedToolCard().getToolCard().getName());
                return;
            }
        }else{
            console.setText("hai deselezionato la carta tool " + matchView.getSelectedToolCard().getToolCard().getName());
            matchView.getSelectedToolCard().getImageView().getStyleClass().remove("selected");
            matchView.setSelectedToolCard(null);
            matchView.setInput(null);
        }

    }
    public void onRoundDieClick(MouseEvent actionEvent){
        if(!this.multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            return;
        }

        DieView selectedRoundDie = retrieveRoundDie(actionEvent.getSource());
        if(matchView.getSelectedToolCard() != null){
            String name = matchView.getSelectedToolCard().getToolCard().getName();
            if(name.equals("taglierina circolare")) {
                matchView.getInput().setRoundTrackDie(selectedRoundDie.getDie());
                try {
                    guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void endTurn(ActionEvent actionEvent) throws RemoteException{
        toPlace = false;
        twoMoves = false;
        choice = false;
        matchView.setSelectedDie(null);
        matchView.setSelectedToolCard(null);
        guiView.getController().endTurn(guiView.getUserToken(),this.multiTokenMatch);
    }

    //Eventi di altre componenti gui
    public void observeRoundDice(int round){
        roundView = round;
        for(DieView dieView : matchView.getRounds().get(round-1).getDieViews()){
           int index = matchView.getRounds().get(round-1).getDieViews().indexOf(dieView);
           associateRoundDie(index).setImage(dieView.imagePath());
           associateRoundDie(index).setVisible(true);
        }
        int i = matchView.getRounds().get(round-1).getDieViews().size();
        while(i<9){
            associateRoundDie(i).setVisible(false);
            i++;
        }

    }
    public void zoomIn(MouseEvent actionEvent){
        Object source = actionEvent.getSource();
        if(source.equals(toolCard1)) zoom.setImage(toolCard1.getImage());
        if(source.equals(toolCard2)) zoom.setImage(toolCard2.getImage());
        if(source.equals(toolCard3)) zoom.setImage(toolCard3.getImage());
        if(source.equals(publicObjective1)) zoom.setImage(publicObjective1.getImage());
        if(source.equals(publicObjective2)) zoom.setImage(publicObjective2.getImage());
        if(source.equals(publicObjective3)) zoom.setImage(publicObjective3.getImage());
        if(source.equals(privateObjective1)) zoom.setImage(privateObjective1.getImage());
    }
    public void zoomOut(MouseEvent actionEvent){
        zoom.setImage(null);
    }
    public void roundTrackDice(MouseEvent actionEvent){
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
    public void closeRoundDicePanel(MouseEvent actionEvent){
        roundDice.setVisible(false);
    }


    //Risposte controllore
    public void respondError(String message, String tokenMatch) throws RemoteException {
        if(console != null){
            console.setText(message);
        }
    }
    public void respondAck(String message, String tokenMatch) throws RemoteException {
        if(console != null){
            console.setText(message);
        }
    }

   //Osservazione multiplayer
    public void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        try {
            show(match, tokenMatch);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        multiPlayerMatch = match;
        if(match.getTurnHandler().isFirstTurn()){
            try {
                createMatchRoundsGui(match);
            }catch (IOException e){
                e.printStackTrace();
            }
            return;
        }
        if (match.getTurnHandler().isRoundFirstTurn() && !match.getTurnHandler().isFirstTurn()){
            initializeDice();
            matchView.getDice().clear();
            for (Die die1 : match.getMatchDice().getDraftPool()) {
                matchView.getDice().add(new DieView(associateDice(match.getMatchDice().getDraftPool().indexOf(die1) + 1), die1));
            }
            for (DieView die1 : matchView.getDice()) {
                die1.getImageView().setVisible(true);
            }
            matchView.getRounds().get(match.getTurnHandler().getRound()-2).getImageView().setVisible(true);
            List<Die> roundtrackDice = match.getRoundTrack().retrieveDice(match.getTurnHandler().getRound()-1);
            for(Die die: roundtrackDice) {
                matchView.getRounds().get(match.getTurnHandler().getRound()-2).getDieViews().add(new DieView(null,die));
            }
        }
        if(match.getTurnHandler().isLastTurn()){
            pointsWindow = new PointsWindowController();
        }

    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        DieView dieView = matchView.retrieveDieView(die);
        matchView.retrievePlayer(match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()].getCell().setDie(dieView.getDie());
        matchView.retrievePlayer(match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()].getImageView().setImage(dieView.getImageView().getImage());
        if(match.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
             matchView.getSelectedDie().getImageView().getStyleClass().remove("selected");
             matchView.setSelectedDie(null);
        }
        initializeDice();
        matchView.getDice().clear();
        for (Die die1 : match.getMatchDice().getDraftPool()) {
            matchView.getDice().add(new DieView(associateDice(match.getMatchDice().getDraftPool().indexOf(die1) + 1), die1));
        }
        for (DieView die1 : matchView.getDice()) {
            die1.getImageView().setVisible(true);
        }
    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException  {

        if(match.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            if(toPlaceToolCards().contains(toolCard.getName())){
                for(DieView dieView : matchView.getDice()){
                    if(dieView.getDie() == matchView.getInput().getChoosenDie()){
                        matchView.setSelectedDie(dieView);
                        matchView.getSelectedDie().getImageView().getStyleClass().add("selected");
                        toPlace = true;
                    }

                }
                favorTokens.setText("" + match.getTurnPlayer().getFavorTokens());
            }
            matchView.getSelectedToolCard().getImageView().getStyleClass().remove("selected");
            matchView.setSelectedToolCard(null);
            matchView.setInput(null);
            twoMoves = false;
        }

        for (Die die : match.getMatchDice().getDraftPool()){
            DieView dieView = matchView.getDice().get(match.getMatchDice().getDraftPool().indexOf(die));
            dieView.setDie(die);
            dieView.getImageView().setImage(dieView.imagePath());
            dieView.getImageView().setVisible(true);
        }
        for (Cell[] cells : match.getTurnPlayer().getWindow().getCells()){
            for (Cell cell: cells){
                if(cell.getDie()!= null){
                    CellView cellView = matchView.retrievePlayer(match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()];
                    cellView.getCell().setDie(cell.getDie());
                    cellView.getImageView().setImage(cellView.imagePath());
                }else{
                    CellView cellView = matchView.retrievePlayer(match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()];
                    cellView.getCell().setDie(null);
                    cellView.getImageView().setImage(null);
                }
            }
        }

        if(toolCard.getName().equals("taglierina circolare")){
            for (RoundView roundView : matchView.getRounds()){
                if(roundView.getRound() < match.getTurnHandler().getRound()){
                    for (DieView dieView : roundView.getDieViews()){
                        dieView.setDie(match.getRoundTrack().getDiceStack().get(matchView.getRounds().indexOf(roundView)).get(roundView.getDieViews().indexOf(dieView)));
                        dieView.getImageView().setImage(dieView.imagePath());
                    }
                }

            }
            roundDice.setVisible(false);
        }



    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException{
        pointsWindow.associateMatch(match);
        pointsWindow.associatePoints(player.getUser().getName(),points);
    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        FXMLLoader loader = new FXMLLoader();
        try {
            AnchorPane root = loader.load(getClass().getResource("fxml/points.fxml"));
            matchAnchorPane.getChildren().add(root);
            root.setLayoutX(333);
            root.setLayoutY(194);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
