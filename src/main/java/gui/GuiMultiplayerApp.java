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
import mvc.exceptions.AppViewException;
import mvc.model.objects.*;
import mvc.stubs.MultiplayerObserver;
import mvc.stubs.ViewResponder;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiMultiplayerApp implements ViewResponder, MultiplayerObserver, Serializable {

    //Finestra gui di una partita multiplayer
    public static final String TITLE = "Sagrada Multiplayer";

    private GuiView guiView;
    private MultiPlayerMatch multiPlayerMatch;
    private String multiTokenMatch;
    private transient PointsWindowController pointsWindow;
    private  transient MatchView matchView;
    private Stage stage;

    private boolean toPlace = false;
    private boolean twoMoves = false;
    private int roundView = 0;
    private boolean choice = false;


    @FXML
    TextArea console;
    @FXML
    Label player2Name;
    @FXML
    Label player3Name;
    @FXML
    Label player4Name;
    @FXML
    Label favorTokens;

    @FXML
    AnchorPane matchAnchorPane;
    @FXML
    Pane pane2;
    @FXML
    Pane roundDice;
    @FXML
    ImageView w1;
    @FXML
    ImageView w2;
    @FXML
    ImageView w3;
    @FXML
    ImageView w4;
    @FXML
    ImageView d1;
    @FXML
    ImageView d2;
    @FXML
    ImageView d3;
    @FXML
    ImageView d4;
    @FXML
    ImageView d5;
    @FXML
    ImageView d6;
    @FXML
    ImageView d7;
    @FXML
    ImageView d8;
    @FXML
    ImageView d9;
    @FXML
    ImageView player1Position11;
    @FXML
    ImageView player1Position12;
    @FXML
    ImageView player1Position13;
    @FXML
    ImageView player1Position14;
    @FXML
    ImageView player1Position15;
    @FXML
    ImageView player1Position21;
    @FXML
    ImageView player1Position22;
    @FXML
    ImageView player1Position23;
    @FXML
    ImageView player1Position24;
    @FXML
    ImageView player1Position25;
    @FXML
    ImageView player1Position31;
    @FXML
    ImageView player1Position32;
    @FXML
    ImageView player1Position33;
    @FXML
    ImageView player1Position34;
    @FXML
    ImageView player1Position35;
    @FXML
    ImageView player1Position41;
    @FXML
    ImageView player1Position42;
    @FXML
    ImageView player1Position43;
    @FXML
    ImageView player1Position44;
    @FXML
    ImageView player1Position45;
    @FXML
    ImageView player2Position11;
    @FXML
    ImageView player2Position12;
    @FXML
    ImageView player2Position13;
    @FXML
    ImageView player2Position14;
    @FXML
    ImageView player2Position15;
    @FXML
    ImageView player2Position21;
    @FXML
    ImageView player2Position22;
    @FXML
    ImageView player2Position23;
    @FXML
    ImageView player2Position24;
    @FXML
    ImageView player2Position25;
    @FXML
    ImageView player2Position31;
    @FXML
    ImageView player2Position32;
    @FXML
    ImageView player2Position33;
    @FXML
    ImageView player2Position34;
    @FXML
    ImageView player2Position35;
    @FXML
    ImageView player2Position41;
    @FXML
    ImageView player2Position42;
    @FXML
    ImageView player2Position43;
    @FXML
    ImageView player2Position44;
    @FXML
    ImageView player2Position45;
    @FXML
    ImageView player3Position11;
    @FXML
    ImageView player3Position12;
    @FXML
    ImageView player3Position13;
    @FXML
    ImageView player3Position14;
    @FXML
    ImageView player3Position15;
    @FXML
    ImageView player3Position21;
    @FXML
    ImageView player3Position22;
    @FXML
    ImageView player3Position23;
    @FXML
    ImageView player3Position24;
    @FXML
    ImageView player3Position25;
    @FXML
    ImageView player3Position31;
    @FXML
    ImageView player3Position32;
    @FXML
    ImageView player3Position33;
    @FXML
    ImageView player3Position34;
    @FXML
    ImageView player3Position35;
    @FXML
    ImageView player3Position41;
    @FXML
    ImageView player3Position42;
    @FXML
    ImageView player3Position43;
    @FXML
    ImageView player3Position44;
    @FXML
    ImageView player3Position45;
    @FXML
    ImageView player4Position11;
    @FXML
    ImageView player4Position12;
    @FXML
    ImageView player4Position13;
    @FXML
    ImageView player4Position14;
    @FXML
    ImageView player4Position15;
    @FXML
    ImageView player4Position21;
    @FXML
    ImageView player4Position22;
    @FXML
    ImageView player4Position23;
    @FXML
    ImageView player4Position24;
    @FXML
    ImageView player4Position25;
    @FXML
    ImageView player4Position31;
    @FXML
    ImageView player4Position32;
    @FXML
    ImageView player4Position33;
    @FXML
    ImageView player4Position34;
    @FXML
    ImageView player4Position35;
    @FXML
    ImageView player4Position41;
    @FXML
    ImageView player4Position42;
    @FXML
    ImageView player4Position43;
    @FXML
    ImageView player4Position44;
    @FXML
    ImageView player4Position45;
    @FXML
    ImageView publicObjective1;
    @FXML
    ImageView publicObjective2;
    @FXML
    ImageView publicObjective3;
    @FXML
    ImageView toolCard1;
    @FXML
    ImageView toolCard2;
    @FXML
    ImageView toolCard3;
    @FXML
    ImageView privateObjective1;
    @FXML
    ImageView zoom;
    @FXML
    ImageView roundTrack;
    @FXML
    ImageView round1;
    @FXML
    ImageView round2;
    @FXML
    ImageView round3;
    @FXML
    ImageView round4;
    @FXML
    ImageView round5;
    @FXML
    ImageView round6;
    @FXML
    ImageView round7;
    @FXML
    ImageView round8;
    @FXML
    ImageView round9;
    @FXML
    ImageView round10;
    @FXML
    ImageView roundDie1;
    @FXML
    ImageView roundDie2;
    @FXML
    ImageView roundDie3;
    @FXML
    ImageView roundDie4;
    @FXML
    ImageView roundDie5;
    @FXML
    ImageView roundDie6;
    @FXML
    ImageView roundDie7;
    @FXML
    ImageView roundDie8;
    @FXML
    ImageView roundDie9;



    //Setter/Getter
    public GuiView getGuiView() {
        return guiView;
    }
    public String getMultiTokenMatch() {
        return multiTokenMatch;
    }

    public void setGuiView(GuiView guiView) {
        this.guiView = guiView;
    }
    private void setMultiPlayerMatch(MultiPlayerMatch multiPlayerMatch) {
        this.multiPlayerMatch = multiPlayerMatch;
    }
    private void setMultiTokenMatch(String multiTokenMatch) {
        this.multiTokenMatch = multiTokenMatch;
    }

    //Associazioni tra bottoni e classi view corrispondenti

    private ImageView associateWindow(int index) {
        switch (index) {
            case 1:
                return w1;
            case 2:
                return w2;
            case 3:
                return w3;
            case 4:
                return w4;
            default:
                return null;
        }

    }
    private ImageView associateToolCard(int index) {
        switch (index) {
            case 1:
                return toolCard1;
            case 2:
                return toolCard2;
            case 3:
                return toolCard3;
            default:
                return null;
        }

    }
    private CellView[][] associateCells(Cell[][] cell, int index) {
        CellView[][] cells = new CellView[4][5];
        switch (index) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
                    }
                }
                cells[0][0].setImageView(player1Position11);
                cells[0][1].setImageView(player1Position12);
                cells[0][2].setImageView(player1Position13);
                cells[0][3].setImageView(player1Position14);
                cells[0][4].setImageView(player1Position15);

                cells[1][0].setImageView(player1Position21);
                cells[1][1].setImageView(player1Position22);
                cells[1][2].setImageView(player1Position23);
                cells[1][3].setImageView(player1Position24);
                cells[1][4].setImageView(player1Position25);

                cells[2][0].setImageView(player1Position31);
                cells[2][1].setImageView(player1Position32);
                cells[2][2].setImageView(player1Position33);
                cells[2][3].setImageView(player1Position34);
                cells[2][4].setImageView(player1Position35);

                cells[3][0].setImageView(player1Position41);
                cells[3][1].setImageView(player1Position42);
                cells[3][2].setImageView(player1Position43);
                cells[3][3].setImageView(player1Position44);
                cells[3][4].setImageView(player1Position45);
                break;
            case 2:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
                    }
                }
                cells[0][0].setImageView(player2Position11);
                cells[0][1].setImageView(player2Position12);
                cells[0][2].setImageView(player2Position13);
                cells[0][3].setImageView(player2Position14);
                cells[0][4].setImageView(player2Position15);

                cells[1][0].setImageView(player2Position21);
                cells[1][1].setImageView(player2Position22);
                cells[1][2].setImageView(player2Position23);
                cells[1][3].setImageView(player2Position24);
                cells[1][4].setImageView(player2Position25);

                cells[2][0].setImageView(player2Position31);
                cells[2][1].setImageView(player2Position32);
                cells[2][2].setImageView(player2Position33);
                cells[2][3].setImageView(player2Position34);
                cells[2][4].setImageView(player2Position35);

                cells[3][0].setImageView(player2Position41);
                cells[3][1].setImageView(player2Position42);
                cells[3][2].setImageView(player2Position43);
                cells[3][3].setImageView(player2Position44);
                cells[3][4].setImageView(player2Position45);
                break;

            case 3:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
                    }
                }
                cells[0][0].setImageView(player3Position11);
                cells[0][1].setImageView(player3Position12);
                cells[0][2].setImageView(player3Position13);
                cells[0][3].setImageView(player3Position14);
                cells[0][4].setImageView(player3Position15);

                cells[1][0].setImageView(player3Position21);
                cells[1][1].setImageView(player3Position22);
                cells[1][2].setImageView(player3Position23);
                cells[1][3].setImageView(player3Position24);
                cells[1][4].setImageView(player3Position25);

                cells[2][0].setImageView(player3Position31);
                cells[2][1].setImageView(player3Position32);
                cells[2][2].setImageView(player3Position33);
                cells[2][3].setImageView(player3Position34);
                cells[2][4].setImageView(player3Position35);

                cells[3][0].setImageView(player3Position41);
                cells[3][1].setImageView(player3Position42);
                cells[3][2].setImageView(player3Position43);
                cells[3][3].setImageView(player3Position44);
                cells[3][4].setImageView(player3Position45);
                break;

            case 4:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        cells[i][j] = new CellView(cell[i][j], null);
                    }
                }
                cells[0][0].setImageView(player4Position11);
                cells[0][1].setImageView(player4Position12);
                cells[0][2].setImageView(player4Position13);
                cells[0][3].setImageView(player4Position14);
                cells[0][4].setImageView(player4Position15);

                cells[1][0].setImageView(player4Position21);
                cells[1][1].setImageView(player4Position22);
                cells[1][2].setImageView(player4Position23);
                cells[1][3].setImageView(player4Position24);
                cells[1][4].setImageView(player4Position25);

                cells[2][0].setImageView(player4Position31);
                cells[2][1].setImageView(player4Position32);
                cells[2][2].setImageView(player4Position33);
                cells[2][3].setImageView(player4Position34);
                cells[2][4].setImageView(player4Position35);

                cells[3][0].setImageView(player4Position41);
                cells[3][1].setImageView(player4Position42);
                cells[3][2].setImageView(player4Position43);
                cells[3][3].setImageView(player4Position44);
                cells[3][4].setImageView(player4Position45);
                break;
            default:
                return cells;
        }
        return cells;
    }
    private ImageView associateDice(int index) {
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
            default:
                return null;
        }

    }
    private ImageView associatePublicObjective(int index) {
        switch (index) {
            case 1:
                return publicObjective1;
            case 2:
                return publicObjective2;
            case 3:
                return publicObjective3;
            default:
                return null;
        }

    }
    private ImageView associateRound(int index) {
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
            default:
                return null;
        }
    }
    private ImageView associateRoundDie(int index) {
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
            default:
                return null;
        }

    }
    private Label associateNameLabel(int index) {
        switch (index) {
            case 2:
                return player2Name;
            case 3:
                return player3Name;
            case 4:
                return player4Name;
            default:
                return null;
        }

    }

    //Restituiscono l'oggetto della view in base al bottone selezionato
    private CellView retrieveCell(Object source) {
        WindowView windowView = matchView.retrieveThisPlayer(guiView.getUserName()).getWindow();

        if (source.equals(player1Position11)) return windowView.getCells()[0][0];
        if (source.equals(player1Position12)) return windowView.getCells()[0][1];
        if (source.equals(player1Position13)) return windowView.getCells()[0][2];
        if (source.equals(player1Position14)) return windowView.getCells()[0][3];
        if (source.equals(player1Position15)) return windowView.getCells()[0][4];
        if (source.equals(player1Position21)) return windowView.getCells()[1][0];
        if (source.equals(player1Position22)) return windowView.getCells()[1][1];
        if (source.equals(player1Position23)) return windowView.getCells()[1][2];
        if (source.equals(player1Position24)) return windowView.getCells()[1][3];
        if (source.equals(player1Position25)) return windowView.getCells()[1][4];
        if (source.equals(player1Position31)) return windowView.getCells()[2][0];
        if (source.equals(player1Position32)) return windowView.getCells()[2][1];
        if (source.equals(player1Position33)) return windowView.getCells()[2][2];
        if (source.equals(player1Position34)) return windowView.getCells()[2][3];
        if (source.equals(player1Position35)) return windowView.getCells()[2][4];
        if (source.equals(player1Position41)) return windowView.getCells()[3][0];
        if (source.equals(player1Position42)) return windowView.getCells()[3][1];
        if (source.equals(player1Position43)) return windowView.getCells()[3][2];
        if (source.equals(player1Position44)) return windowView.getCells()[3][3];
        if (source.equals(player1Position45)) return windowView.getCells()[3][4];

        return null;
    }
    private DieView retrieveDie(Object source) {
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
    private ToolCardView retrieveToolCard(Object source) {
        if (source.equals(toolCard1)) return matchView.getToolCards().get(0);
        if (source.equals(toolCard2)) return matchView.getToolCards().get(1);
        if (source.equals(toolCard3)) return matchView.getToolCards().get(2);
        return null;
    }
    private DieView retrieveRoundDie(Object source) {
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
    private List<String> windowToolCards() {

        List<String> toolCards = new ArrayList<>();
        toolCards.add("pennello per eglomise");
        toolCards.add("alesatore per lamina di rame");
        toolCards.add("lathekin");
        toolCards.add("taglierina manuale");

        return toolCards;
    }
    private List<String> draftPoolToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("pinza sgrossatrice");
        toolCards.add("taglierina circolare");
        toolCards.add("pennello per pasta salda");
        toolCards.add("riga di sughero");
        toolCards.add("tampone diamantato");
        toolCards.add("diluente per pasta salda");

        return toolCards;
    }
    private List<String> noSelectionToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("martelletto");
        toolCards.add("tenaglia a rotelle");

        return toolCards;
    }
    private List<String> toPlaceToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("pinza sgrossatrice");
        toolCards.add("riga di sughero");
        toolCards.add("tampone diamantato");
        toolCards.add("diluente per pasta salda");
        toolCards.add("taglierina circolare");

        return toolCards;
    }

    //Crea e visualizza la finestra
    public void show(MultiPlayerMatch match, String tokenMatch) throws IOException {
        multiPlayerMatch = match;
        multiTokenMatch = tokenMatch;
        createWindowChoiceGui();
    }

    //Crea oggetti gui gioco a inizio partita
    private void createWindowChoiceGui() throws IOException {
        //Carica fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/WindowChoiceMenu.fxml"));
        Parent root = loader.load();
        FXWindowChoiceMenu choiceMenu = loader.getController();
        choiceMenu.setGuiMultiplayerApp(this);
        choiceMenu.initializeMenu(multiPlayerMatch);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        this.stage = stage;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Inizializza oggetti gui gioco a round iniziati
    public void initializeMatchRoundsGui(MultiPlayerMatch match) {
        initializeDice();
        roundDice.setVisible(false);
        console.setEditable(false);
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
    private void initializeDice(){
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
    private void createMatchRoundsGui(MultiPlayerMatch match)throws IOException{
        //Inizializza componenti
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Match.fxml"));
        Parent root = loader.load();
        GuiMultiplayerApp guiController = loader.getController();
        guiController.setMultiPlayerMatch(match);
        guiController.setGuiView(this.guiView);
        guiController.setMultiTokenMatch(this.multiTokenMatch);
        guiView.getMultiplayerApps().replace(multiTokenMatch, guiController);
        guiController.initializeMatchRoundsGui(match);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("fxml/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void createPointsGui(MultiPlayerMatch match)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/points.fxml"));
        Parent root = loader.load();
        PointsWindowController pointsWindowController = loader.getController();
        pointsWindowController.setPoints(pointsWindow.getPoints());
        pointsWindowController.setMatch(match);
        pointsWindowController.initializeScores();
        matchAnchorPane.getChildren().add(root);
        root.setLayoutX(346);
        root.setLayoutY(143);
    }

    //Eventi componenti gui di mosse della partita
    public void onDieClick(MouseEvent actionEvent){

        if (!this.multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())) {
            return;
        }
        if (toPlace) {
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
                        selectedDie.getImageView().getStyleClass().add("selected");
                        this.console.setText("scegli un dado del tracciato dei round");
                        return;
                    } else {
                        matchView.getInput().setChoosenDie(selectedDie.getDie());
                        try {
                            guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
                        } catch (RemoteException e) {
                            console.setText(e.getMessage());
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
                this.console.setText("hai selezionato un dado ");

            }

        } else {
            this.console.setText("hai deselezionato un dado ");
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
                        matchView.getSelectedDie().getImageView().getStyleClass().remove("selected");
                        matchView.setSelectedDie(dieView);
                        matchView.getSelectedDie().getImageView().getStyleClass().add("selected");
                        break;
                    }
                }
                try {
                    guiView.getController().placeDie(guiView.getUserToken(), multiTokenMatch,selectedCell.getCell(), matchView.getSelectedDie().getDie());
                } catch (RemoteException e) {
                    console.setText(e.getMessage());
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
                                    matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView().getStyleClass().remove("selected");
                                    console.setText("prima cella di destinazione selezionata");
                                    return;
                                }else{
                                    matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell2().getRow()][matchView.getInput().getOriginCell2().getColumn()].getImageView().getStyleClass().remove("selected");
                                    matchView.getInput().setDestinationCell2(selectedCell.getCell());
                                }
                            }else{
                                matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView().getStyleClass().remove("selected");
                                matchView.getInput().setDestinationCell1(selectedCell.getCell());
                            }
                            try {
                                guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch,matchView.getInput(),matchView.getSelectedToolCard().getToolCard());
                            } catch (RemoteException e) {
                                console.setText(e.getMessage());
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
                        if(matchView.getInput().getDestinationCell1() != null) {
                            if(matchView.getInput().getOriginCell2() != null){
                                matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell2().getRow()][matchView.getInput().getOriginCell2().getColumn()].getImageView().getStyleClass().remove("selected");
                            }
                            matchView.getInput().setOriginCell2(selectedCell.getCell());
                            selectedCell.getImageView().getStyleClass().add("selected");
                            console.setText("seconda cella di partenza selezionata");
                            return;
                        }
                    }
                    if(matchView.getInput().getOriginCell1() != null){
                        matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView().getStyleClass().remove("selected");
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
            console.setText("hai selezionato la carta tool " + matchView.getSelectedToolCard().getToolCard().getName());
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
                    console.setText(e.getMessage());
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
                return;
            }
        }else{
            console.setText("hai deselezionato la carta tool " + matchView.getSelectedToolCard().getToolCard().getName());
            matchView.getSelectedToolCard().getImageView().getStyleClass().remove("selected");
            matchView.setSelectedToolCard(null);
            if(matchView.getInput().getOriginCell1() != null) {
                matchView.retrievePlayer(multiPlayerMatch.getTurnPlayer()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView().getStyleClass().remove("selected");
            }
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
                if(matchView.getInput().getChoosenDie() != null) {
                    matchView.retrieveDieView(matchView.getInput().getChoosenDie()).getImageView().getStyleClass().remove("selected");
                    matchView.getInput().setRoundTrackDie(selectedRoundDie.getDie());
                    try {
                        guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
                    } catch (RemoteException e) {
                        console.setText(e.getMessage());
                    }
                }
            }
        }
    }
    public void endTurn(ActionEvent actionEvent){
        toPlace = false;
        twoMoves = false;
        choice = false;
        if(matchView.getSelectedDie() != null) {
            matchView.getSelectedDie().getImageView().getStyleClass().remove("selected");
            matchView.setSelectedDie(null);
        }
        if(matchView.getSelectedToolCard() != null) {
            matchView.getSelectedDie().getImageView().getStyleClass().remove("selected");
            matchView.setSelectedToolCard(null);
        }
        try {
            guiView.getController().endTurn(guiView.getUserToken(), this.multiTokenMatch);
        }catch(RemoteException e){
            console.setText(e.getMessage());
        }
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

    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        if(match.getTurnHandler().isLastTurn()){
            pointsWindow = new PointsWindowController();
            pointsWindow.setPoints(new HashMap<>());
        }
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

        for (Die die : match.getMatchDice().getDraftPool()){
            DieView dieView = matchView.getDice().get(match.getMatchDice().getDraftPool().indexOf(die));
            dieView.setDie(die);
            dieView.getImageView().setImage(dieView.imagePath());
            dieView.getImageView().setVisible(true);
        }
        if(match.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            if(toPlaceToolCards().contains(toolCard.getName())){
                if(toolCard.getName().equals("diluente per pasta salda") || toolCard.getName().equals("taglierina circolare")){
                    DieView dieView = matchView.getDice().get(match.getMatchDice().getDraftPool().size()-1);
                    matchView.setSelectedDie(dieView);
                    matchView.getSelectedDie().getImageView().getStyleClass().add("selected");
                }else {
                    for (DieView dieView : matchView.getDice()) {
                        if (dieView.getDie() == matchView.getInput().getChoosenDie()) {
                            matchView.setSelectedDie(dieView);
                            matchView.getSelectedDie().getImageView().getStyleClass().add("selected");
                            toPlace = true;
                        }

                    }
                }
            }
            favorTokens.setText("" + match.getTurnPlayer().getFavorTokens());
            matchView.getSelectedToolCard().getImageView().getStyleClass().remove("selected");
            matchView.setSelectedToolCard(null);
            matchView.setInput(null);
            twoMoves = false;
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
                    }
                }

            }
            roundDice.setVisible(false);
        }



    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException{
        pointsWindow.getPoints().put(player.getUser().getName(), points);
    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        try {
            createPointsGui(match);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
