package gui;

import gui.objects.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mvc.exceptions.AppViewException;
import mvc.model.objects.Cell;
import mvc.model.objects.*;
import mvc.stubs.MultiplayerObserver;
import mvc.stubs.ViewResponder;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * handler of the gui of a multiplayer match
 */
public class GuiMultiplayerApp implements ViewResponder, MultiplayerObserver, Serializable {

    //Finestra gui di una partita multiplayer
    public static final String TITLE = "Sagrada Multiplayer";

    private GuiView guiView;
    private MultiPlayerMatch multiPlayerMatch;
    private String multiTokenMatch;
    private transient PointsWindowController pointsWindow;
    private  transient MatchView matchView;
    private Stage stage;
    private Pane choicePane;

    private boolean toPlace = false;
    private boolean twoMoves = false;
    private int round = 0;
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
    Label costToolCard1;
    @FXML
    Label costToolCard2;
    @FXML
    Label costToolCard3;
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
    private MultiPlayerMatch getMultiPlayerMatch() {
        return multiPlayerMatch;
    }
    private MatchView getMatchView() {
        return matchView;
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

    /**
     * gets the corresponding image view for the created windowView
     * @param index window number
     * @return window image view
     */
    private ImageView associateWindow(int index) throws AppViewException{
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
                throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding image view for the created tollCardView
     * @param index tool card number
     * @return tool card image view
     */
    private ImageView associateToolCard(int index) throws AppViewException{
        switch (index) {
            case 1:
                return toolCard1;
            case 2:
                return toolCard2;
            case 3:
                return toolCard3;
            default:
                throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }

    }

    /**
     * for each cell of the created windowView gets the corresponding image view
     * @param cell model's window cells
     * @param index window number
     * @return cells view
     */
    private CellView[][] associateCells(Cell[][] cell, int index) throws AppViewException{
        CellView[][] cells = new CellView[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new CellView(cell[i][j], null);
            }
        }
        switch (index) {
            case 1:
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
                throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }
        return cells;
    }

    /**
     * gets the corresponding image view for the created DieView
     * @param index die number
     * @return die image view
     */
    private ImageView associateDice(int index) throws AppViewException{
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
                throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding image view for the created public objective card
     * @param index public objective card number
     * @return public objective image view
     */
    private ImageView associatePublicObjective(int index) throws AppViewException{
        switch (index) {
            case 1:
                return publicObjective1;
            case 2:
                return publicObjective2;
            case 3:
                return publicObjective3;
            default:
                throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding image view for the created round
     * @param index round number
     * @return round image view
     */
    private ImageView associateRound(int index) throws AppViewException{
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
                throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }
    }

    /**
     * gets the corresponding image view for the created round die
     * @param index round die number
     * @return round die image view
     */
    private ImageView associateRoundDie(int index) throws AppViewException {
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
                throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding name label for the player created
     * @param index player number
     * @return name label
     */
    private Label associateNameLabel(int index) throws AppViewException{
        switch (index) {
            case 2:
                return player2Name;
            case 3:
                return player3Name;
            case 4:
                return player4Name;
            default:
                throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding cost label for the tool card in order to be changed when a tool card is used
     * @param index tool card number
     * @return corresponding label
     */
    private Label associateCostLabel(int index) throws AppViewException{
        switch (index) {
            case 0:
                return costToolCard1;
            case 1:
                return costToolCard2;
            case 2:
                return costToolCard3;
            default:
                throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }

    }

    /**
     * based on the clicked image view, gets the correponding cell view
     * @param source image view clicked
     * @return cell view
     */
    private CellView retrieveCell(Object source)throws AppViewException{
        WindowView windowView = matchView.retrieveThisPlayer(guiView.getUserName()).getWindow();
        ImageView [][] cells = new ImageView[4][5];
        cells[0][0] = player1Position11;
        cells[0][1] = player1Position12;
        cells[0][2] = player1Position13;
        cells[0][3] = player1Position14;
        cells[0][4] = player1Position15;
        cells[1][0] = player1Position21;
        cells[1][1] = player1Position22;
        cells[1][2] = player1Position23;
        cells[1][3] = player1Position24;
        cells[1][4] = player1Position25;
        cells[2][0] = player1Position31;
        cells[2][1] = player1Position32;
        cells[2][2] = player1Position33;
        cells[2][3] = player1Position34;
        cells[2][4] = player1Position35;
        cells[3][0] = player1Position41;
        cells[3][1] = player1Position42;
        cells[3][2] = player1Position43;
        cells[3][3] = player1Position44;
        cells[3][4] = player1Position45;
        for(int i=0;i<4; i++) {
            for (int j = 0; j < 5; j++) {
                if (source.equals(cells[i][j]))
                    return windowView.getCells()[i][j];
            }
        }
        throw new AppViewException(FXGuiConstant.CELL_EXCEPTION_MESSAGE);
    }

    /**
     * based on the clicked image view, gets the corresponding die view
     * @param source image view clicked
     * @return die view
     */
    private DieView retrieveDie(Object source) throws AppViewException{
        if (source.equals(d1)) return matchView.getDraftPool().get(0);
        if (source.equals(d2)) return matchView.getDraftPool().get(1);
        if (source.equals(d3)) return matchView.getDraftPool().get(2);
        if (source.equals(d4)) return matchView.getDraftPool().get(3);
        if (source.equals(d5)) return matchView.getDraftPool().get(4);
        if (source.equals(d6)) return matchView.getDraftPool().get(5);
        if (source.equals(d7)) return matchView.getDraftPool().get(6);
        if (source.equals(d8)) return matchView.getDraftPool().get(7);
        if (source.equals(d9)) return matchView.getDraftPool().get(8);

        throw new AppViewException(FXGuiConstant.DIE_EXCEPTION_MESSAGE);
    }

    /**
     * based on the clicked image view, get the corresponding tool card
     * @param source tool card clicked
     * @return tool card view
     */
    private ToolCardView retrieveToolCard(Object source) throws AppViewException{
        if (source.equals(toolCard1)) return matchView.getToolCards().get(0);
        if (source.equals(toolCard2)) return matchView.getToolCards().get(1);
        if (source.equals(toolCard3)) return matchView.getToolCards().get(2);
        throw new AppViewException(FXGuiConstant.TOOLCARD_EXCEPTION_MESSAGE);
    }

    /**
     * based on the clicked image view, get the corresponding round die
     * @param source round die clicked
     * @return round die view
     */
    private DieView retrieveRoundDie(Object source) throws AppViewException{
        List<DieView> dice = matchView.getRounds().get(round -1).getDieViews();
        if (source.equals(roundDie1)) return dice.get(0);
        if (source.equals(roundDie2)) return dice.get(1);
        if (source.equals(roundDie3)) return dice.get(2);
        if (source.equals(roundDie4)) return dice.get(3);
        if (source.equals(roundDie5)) return dice.get(4);
        if (source.equals(roundDie6)) return dice.get(5);
        if (source.equals(roundDie7)) return dice.get(6);
        if (source.equals(roundDie8)) return dice.get(7);
        if (source.equals(roundDie9)) return dice.get(8);
        throw new AppViewException(FXGuiConstant.DIE_EXCEPTION_MESSAGE);
    }

    /**
     * loads the window choice anchor pane from the fxml file and initializes it
     * @param match current match, which is saved in this instance of GuiMultiPlayerApp
     * @param tokenMatch token of the match
     * @throws IOException throws an exception if it fails to load the fxml file
     */
    private void createWindowChoiceGui(MultiPlayerMatch match, String tokenMatch) throws IOException {
        multiPlayerMatch = match;
        multiTokenMatch = tokenMatch;
        //Carica fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/WindowChoiceMenu.fxml"));
        Parent root = loader.load();
        FXWindowChoiceMenu choiceMenu = loader.getController();
        choiceMenu.setGuiMultiplayerApp(this);
        choiceMenu.initializeMenu(multiPlayerMatch);
        this.stage = new Stage();
        setScene(root);
    }

    /**
     * loads the match anchor pane from the fxml file and initializes it
     * @param match current match, which is saved in this instance of GuiMultiPlayerApp
     * @throws IOException throws an exception if it fails to load the fxml file
     */
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
        setScene(root);
        stage.setOnCloseRequest(event -> {
            try {
                guiView.getController().leaveMatch(guiView.getUserToken(),multiTokenMatch);
            } catch (RemoteException e) {
                // eccezione gestita
            }
            stage.close();
        });
    }

    /**
     * loads the points view window form the fxml file and initializes it
     * @param match current match
     * @throws IOException
     */
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

    /**
     * update the draft pool view dice based on the draft pool of the model
     * @param match current match
     */
    private void updateDraftPool(MultiPlayerMatch match) throws AppViewException{
        initializeDice();
        matchView.getDraftPool().clear();
        for (Die die : match.getMatchDice().getDraftPool()) {
            matchView.getDraftPool().add(new DieView(associateDice(match.getMatchDice().getDraftPool().indexOf(die) + 1), die));
        }
        for (DieView die : matchView.getDraftPool()) {
            die.getImageView().setVisible(true);
        }
    }

    /**
     * updates a player's window view from the match model
     * @param player player whose window you want  to update
     */
    private void updateCells(Player player) throws AppViewException{
        for (Cell[] cells : player.getWindow().getCells()){
            for (Cell cell: cells){
                if(cell.getDie()!= null){
                    CellView cellView = matchView.retrievePlayer(player).getWindow().getCells()[cell.getRow()][cell.getColumn()];
                    cellView.getCell().setDie(cell.getDie());
                    cellView.getImageView().setImage(cellView.imagePath());
                }else{
                    CellView cellView = matchView.retrievePlayer(player).getWindow().getCells()[cell.getRow()][cell.getColumn()];
                    cellView.getCell().setDie(null);
                    cellView.getImageView().setImage(null);
                }
            }
        }
    }

    /**
     * update the dice of the round tracker from the match model
     * @param match current match
     */
    private void updateRoundDice(MultiPlayerMatch match){
        for (RoundView roundView : matchView.getRounds()){
            if(roundView.getRound() < match.getTurnHandler().getRound()){
                roundView.getImageView().setVisible(true);
                roundView.getDieViews().clear();
                for (Die die: match.getRoundTrack().retrieveDice(roundView.getRound())){
                    roundView.getDieViews().add(new DieView(null,die));
                }
            }

        }
    }

    /**
     * updates the label stating the cost of a tool card
     * @param toolCard tool card used
     */
    private void updateToolCardCost(ToolCard toolCard) throws AppViewException{
        for(ToolCardView toolCardView : matchView.getToolCards()){
            if(toolCardView.getToolCard().getName().equals(toolCard.getName())){
                associateCostLabel(matchView.getToolCards().indexOf(toolCardView)).setText("Costo: 2FavorToken");
            }
        }
    }

    /**
     * creates a new scene from the fxml parent loaded and shows it in the saved stage
     * @param root Parent loaded from fxml file
     */
    private void setScene(Parent root){
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("fxml/style.css").toExternalForm());
        if(stage == null){
            stage = new Stage();
        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    /**
     * add the css style "selected" to an image view
     * @param imageView image view to add the style with
     */
    private void select(ImageView imageView){
        imageView.getStyleClass().add("selected");
    }

    /**
     * remove the css style "selected to an image view
     * @param imageView image view to remove the style with
     */
    private void deselect(ImageView imageView){
        imageView.getStyleClass().remove("selected");
    }

    /**
     * initializes the match window creating a view object for every model object
     * @param match current match
     */
    private void initializeMatchRoundsGui(MultiPlayerMatch match) throws AppViewException{
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

        console.setText("partita iniziata. Turno di "+ match.getTurnPlayer().getUser().getName());
        int window = 2;
        //Setta componenti view finestre e carte private
        matchView = new MatchView(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),null,new ArrayList<>(),null);
        for (Player player : match.getPlayers()) {
            if (player.getUser().getName().equals(guiView.getUserName())) {
                matchView.getPlayers().add(new PlayerView(new WindowView(associateWindow(1), player.getWindow(), associateCells(player.getWindow().getCells(), 1)), player));
                new ObjectiveCardView(privateObjective1, player.getPrivateObjectiveCards().get(0));
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
        updateDraftPool(match);

        for (int i = 1; i < 11; i++) {
            matchView.getRounds().add(new RoundView(associateRound(i), new ArrayList<>(), i));
        }
        for (PublicObjectiveCard card : match.getPublicObjectiveCards()) {
            matchView.getPublicObjectives().add(new ObjectiveCardView(associatePublicObjective(match.getPublicObjectiveCards().indexOf(card) + 1), card));
        }

    }

    /**
     * set every die view's image view to not visible
     */
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

    /**
     * determines the move to make when a die is clicked based on the current situation
     * @param mouseEvent mouse event
     */
    public void onDieClick(MouseEvent mouseEvent) throws AppViewException{

        if (!this.multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())) {
            return;
        }
        if (toPlace) {
            console.setText("devi prima posizionare il dado già selezionato");
            return;
        }
        if(choice){
            console.setText(FXGuiConstant.CHOICE_MESSAGE);
            return;
        }

        DieView selectedDie = retrieveDie(mouseEvent.getSource());

        if (selectedDie != matchView.getSelectedDie()) {
            onNewDieClick(selectedDie);
        } else {
            this.console.setText("hai deselezionato un dado ");
            deselect(matchView.getSelectedDie().getImageView());
            matchView.setSelectedDie(null);
        }
    }

    /**
     * determines the move to make if the clicked die is not the die already selected
     * @param selectedDie clicked die
     */
    public void onNewDieClick(DieView selectedDie){
        if (matchView.getSelectedToolCard() != null) {
            onDieClickWithToolCardSelected(selectedDie);
        } else {
            if(matchView.getSelectedDie() != null){
                deselect(matchView.getSelectedDie().getImageView());
            }
            matchView.setSelectedDie(selectedDie);
            select(matchView.getSelectedDie().getImageView());
            this.console.setText("hai selezionato un dado ");

        }
    }

    /**
     * determines the move to make if a tool card is selected
     * @param selectedDie clicked die
     */
    private void onDieClickWithToolCardSelected(DieView selectedDie){
        String name = matchView.getSelectedToolCard().getToolCard().getName();
        if (matchView.draftPoolToolCards().contains(name)) {
            if (name.equals(FXGuiConstant.TAGLIERINA_CIRCOLARE)) {
                matchView.getInput().setChosenDie(selectedDie.getDie());
                select(selectedDie.getImageView());
                this.console.setText("scegli un dado del tracciato dei round");
            } else {
               useToolCardWithDie(selectedDie);
            }
        } else {
            console.setText("il dado selezionato non va bene per questa tool card");
        }
    }

    /**
     * calls the mathod useToolCard from the controller
     * @param selectedDie clicked die
     */
    private void useToolCardWithDie(DieView selectedDie){
        for(DieView dieView : matchView.getDraftPool()) {
            if(dieView.getDie().getColor().equals(selectedDie.getDie().getColor()) && dieView.getDie().getShade() == selectedDie.getDie().getShade()){
                matchView.getInput().setChosenDie(dieView.getDie());
                break;
            }
        }
        try {
            guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
        } catch (RemoteException e) {
            //eccezione gestita
        }
    }

    /**
     * determines the move to make when a cell is clicked based on the current situation
     * @param mouseEvent mouse event
     */
    public void onCellClick(MouseEvent mouseEvent)throws AppViewException{
        if(!multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            return;
        }
        if(choice){
            console.setText(FXGuiConstant.CHOICE_MESSAGE);
            return;
        }

        CellView selectedCell = retrieveCell(mouseEvent.getSource());

        if(selectedCell.getCell().getDie()==null){
           onCellClickWithoutDie(selectedCell);
        } else {
            if (matchView.getSelectedToolCard() != null){
                onCellClickWithDieWithToolCard(selectedCell);
            } else {
                if(matchView.getSelectedDie() != null){
                    console.setText("la cella è già occupata da un altro dado");
                }
            }
        }
    }

    /**
     * determines the move to make if the clicked cell is empty
     * @param selectedCell clicked cell
     */
    private void onCellClickWithoutDie(CellView selectedCell){
        if(matchView.getSelectedDie() != null){
            toPlace = false;
            for(DieView dieView : matchView.getDraftPool()){
                if(dieView.getDie().getColor().equals(matchView.getSelectedDie().getDie().getColor()) && dieView.getDie().getShade() == matchView.getSelectedDie().getDie().getShade()){
                    deselect(matchView.getSelectedDie().getImageView());
                    matchView.setSelectedDie(dieView);
                    select(matchView.getSelectedDie().getImageView());
                    break;
                }
            }
            try {
                guiView.getController().placeDie(guiView.getUserToken(), multiTokenMatch,selectedCell.getCell(), matchView.getSelectedDie().getDie());
            } catch (RemoteException e) {
                //eccezione gestita
            }
        } else {
            if(matchView.getSelectedToolCard() != null && matchView.getInput().getOriginCell1()!= null) {
               onCellClickWithoutDieWithToolCard(selectedCell);
            }
        }
    }

    /**
     * determines the move to make if the clicked cell is empty and a tool card is selected
     * @param selectedCell clicked cell
     */
    private void onCellClickWithoutDieWithToolCard(CellView selectedCell){
        String name = matchView.getSelectedToolCard().getToolCard().getName();
        if (matchView.windowToolCards().contains(name)) {
            if(name.equals("lathekin") || twoMoves) {
                if(matchView.getInput().getDestinationCell1() == null) {
                    matchView.getInput().setDestinationCell1(selectedCell.getCell());
                    deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView());
                    console.setText("prima cella di destinazione selezionata");
                    return;
                }else{
                    matchView.getInput().setDestinationCell2(selectedCell.getCell());
                    deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell2().getRow()][matchView.getInput().getOriginCell2().getColumn()].getImageView());
                }
            }else{
                deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView());
                matchView.getInput().setDestinationCell1(selectedCell.getCell());
            }
            try {
                guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch,matchView.getInput(),matchView.getSelectedToolCard().getToolCard());
            } catch (RemoteException e) {
                //eccezione gestita
            }
        }
    }

    /**
     * determines the move to make if the clicked cell is not empty cell and a tool card is selected
     * @param selectedCell clicked cell
     */
    private void onCellClickWithDieWithToolCard(CellView selectedCell){
        String name = matchView.getSelectedToolCard().getToolCard().getName();
        if(matchView.windowToolCards().contains(name)){
            if((name.equals("lathekin") || twoMoves) && matchView.getInput().getDestinationCell1() != null) {
                if(matchView.getInput().getOriginCell2() != null){
                    deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell2().getRow()][matchView.getInput().getOriginCell2().getColumn()].getImageView());
                }
                matchView.getInput().setOriginCell2(selectedCell.getCell());
                select(selectedCell.getImageView());
                console.setText("seconda cella di partenza selezionata");
                return;
            }
            if(matchView.getInput().getOriginCell1() != null){
                deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView());
            }
            matchView.getInput().setOriginCell1(selectedCell.getCell());
            select(selectedCell.getImageView());
            console.setText("prima cella di partenza selezionata");
        }
    }

    /**
     * determines the move to make when a tool card is clicked based on the current situation
     * @param mouseEvent mouse event
     */
    public void onToolCardClick(MouseEvent mouseEvent) throws AppViewException{
        if(!multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            return;
        }
        if(toPlace){
            console.setText("devi prima posizionare il dado già selezionato");
            return;
        }
        if(choice){
            console.setText(FXGuiConstant.CHOICE_MESSAGE);
            return;
        }
        ToolCardView selectedToolCard = retrieveToolCard(mouseEvent.getSource());
        if(selectedToolCard != matchView.getSelectedToolCard()){
            onNewToolCardClick(selectedToolCard);
        }else{
            console.setText("hai deselezionato la carta tool " + matchView.getSelectedToolCard().getToolCard().getName());
            deselect(matchView.getSelectedToolCard().getImageView());
            matchView.setSelectedToolCard(null);
            if(matchView.getInput().getOriginCell1() != null) {
                deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView());
            }
            if(matchView.getInput().getOriginCell2() != null){
                deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell2().getRow()][matchView.getInput().getOriginCell2().getColumn()].getImageView());
            }
            matchView.setInput(null);
        }

    }

    /**
     * determines the move to make if the clicked tool card is not the one that is already selected
     * @param selectedToolCard tool card clicked
     */
    private void onNewToolCardClick(ToolCardView selectedToolCard){
        if(matchView.getSelectedToolCard() != null){
            deselect(matchView.getSelectedToolCard().getImageView());
        }
        matchView.setSelectedToolCard(selectedToolCard);
        console.setText("hai selezionato la carta tool " + matchView.getSelectedToolCard().getToolCard().getName());
        select(matchView.getSelectedToolCard().getImageView());
        if(matchView.getSelectedDie() != null) {
            deselect(matchView.getSelectedDie().getImageView());
            matchView.setSelectedDie(null);
        }
        matchView.setInput(new ToolCardInput(null, null, null,null, multiPlayerMatch.getTurnHandler().getRound(),null,null,null,0,false));
        String name = matchView.getSelectedToolCard().getToolCard().getName();
        if(matchView.noSelectionToolCards().contains(name)){
            try {
                guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
            } catch (RemoteException e) {
                //eccezione gestita
            }
        }else{
          onToolCardWithChoiceClick(selectedToolCard);
        }
    }

    /**
     * determines the move to make if the clicked tool card is a tool card which requires a choice by the player
     * @param selectedToolCard tool card clicked
     */
    private void onToolCardWithChoiceClick(ToolCardView selectedToolCard){
        if(selectedToolCard.getToolCard().getName().equals("pinza sgrossatrice")){
            choice = true;
            Pane pane = new Pane();
            this.choicePane = pane;
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
            this.choicePane = pane;
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
                    matchView.getInput().setChosenShade(Integer.parseInt(textField.getText()));
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
            this.choicePane = pane;
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
                    twoMoves = Integer.parseInt(textField.getText()) == 2;
                    console.setText("selezionare i dadi da spostare");
                    choice = false;
                    matchAnchorPane.getChildren().remove(pane);
                } else {
                    console.setText("input non valido");
                }
            });
        }
    }

    /**
     * determines the move to make when a round die is clicked based on the current situation
     * @param mouseEvent mouse event
     */
    public void onRoundDieClick(MouseEvent mouseEvent) throws AppViewException{
        if(!this.multiPlayerMatch.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            return;
        }

        DieView selectedRoundDie = retrieveRoundDie(mouseEvent.getSource());
        if(matchView.getSelectedToolCard() != null){
            String name = matchView.getSelectedToolCard().getToolCard().getName();
            if(name.equals(FXGuiConstant.TAGLIERINA_CIRCOLARE) && matchView.getInput().getChosenDie() != null) {
                deselect(matchView.retrieveDieView(matchView.getInput().getChosenDie()).getImageView());
                matchView.getInput().setRoundTrackDie(selectedRoundDie.getDie());
                matchView.getInput().setRoundTrackRound(round);
                try {
                    guiView.getController().useToolCard(guiView.getUserToken(), multiTokenMatch, matchView.getInput(), matchView.getSelectedToolCard().getToolCard());
                } catch (RemoteException e) {
                    //eccezione gestita
                }
            }
        }
    }

    /**
     * ends the turn when the end turn button is clicked
     * @param actionEvent action event
     */
    public void endTurn(ActionEvent actionEvent){

        try {
            guiView.getController().endTurn(guiView.getUserToken(), this.multiTokenMatch);
        }catch(RemoteException e){
            //eccezione gestita
        }
    }

    /**
     * based on the round's image view clicked, shows that round's dice
     * @param round round chosen
     */
    private void observeRoundDice(int round) throws AppViewException{
        this.round = round;
        for(DieView dieView : matchView.getRounds().get(round-1).getDieViews()){
           int index = matchView.getRounds().get(round-1).getDieViews().indexOf(dieView);
            try {
                associateRoundDie(index).setImage(dieView.imagePath());
            } catch (AppViewException e) {
                //eccezione gestita
            }
            associateRoundDie(index).setVisible(true);
        }
        int i = matchView.getRounds().get(round-1).getDieViews().size();
        while(i<9){
            associateRoundDie(i).setVisible(false);
            i++;
        }

    }

    /**
     * zooms on the chosen card copying his image to the "zoom" image view
     * @param mouseEvent on mouse entered event
     */
    public void zoomIn(MouseEvent mouseEvent){
        Object source = mouseEvent.getSource();
        if(source.equals(toolCard1)) zoom.setImage(toolCard1.getImage());
        if(source.equals(toolCard2)) zoom.setImage(toolCard2.getImage());
        if(source.equals(toolCard3)) zoom.setImage(toolCard3.getImage());
        if(source.equals(publicObjective1)) zoom.setImage(publicObjective1.getImage());
        if(source.equals(publicObjective2)) zoom.setImage(publicObjective2.getImage());
        if(source.equals(publicObjective3)) zoom.setImage(publicObjective3.getImage());
        if(source.equals(privateObjective1)) zoom.setImage(privateObjective1.getImage());
    }

    /**
     * zooms out of the chose card romoving his image from the "zoom" image view
     * @param actionEvent on mouse exited event
     */
    public void zoomOut(MouseEvent actionEvent){
        zoom.setImage(null);
    }

    /**
     * gets the corresponding image view for the clicked round
     * @param mouseEvent click event
     */
    public void roundTrackDice(MouseEvent mouseEvent) throws AppViewException{
        Object source = mouseEvent.getSource();
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

    /**
     * closes the panel showing round dice
     * @param mouseEvent click event
     */
    public void closeRoundDicePanel(MouseEvent mouseEvent){
        roundDice.setVisible(false);
    }

    /**
     * copies on the text area the error massage received from the controller
     * @param message message received
     * @param tokenMatch token of the match
     */
    public void respondError(String message, String tokenMatch) throws RemoteException{
        if(console != null){
            console.setText("[ERRORE] " + message);
        }
    }

    /**
     * copies on the text area the message received from the controller
     * @param message message received
     * @param tokenMatch token of the match
     */
    public void respondAck(String message, String tokenMatch) throws RemoteException {
        if(console != null){
            console.setText(message);
        }
    }

    /**
     * when a player leaves the match turns his name to color red
     * @param tokenMatch token of the match
     * @param player player who left the match
     */
    public void onPlayerLeave(String tokenMatch, Player player) throws RemoteException {
        if(player2Name.getText().equals(player.getUser().getName())) {
            player2Name.getStyleClass().remove(FXGuiConstant.REJOIN);
            player2Name.getStyleClass().add(FXGuiConstant.LEFT);
        }
        if(player3Name.getText().equals(player.getUser().getName())) {
            player3Name.getStyleClass().remove(FXGuiConstant.REJOIN);
            player3Name.getStyleClass().add(FXGuiConstant.LEFT);
        }

        if(player4Name.getText().equals(player.getUser().getName())) {
            player4Name.getStyleClass().remove(FXGuiConstant.REJOIN);
            player4Name.getStyleClass().add(FXGuiConstant.LEFT);
        }

    }

    /**
     * when a player rejoins the match turns his name to white. If the player receiving this command is the one who rejoined the match recreates the match scene
     * @param tokenMatch token of the match
     * @param player player who rejoined
     */
    public void onPlayerRejoin(String tokenMatch, Player player) throws RemoteException {
        if(player2Name.getText().equals(player.getUser().getName())) {
            player2Name.getStyleClass().remove(FXGuiConstant.LEFT);
            player2Name.getStyleClass().add(FXGuiConstant.REJOIN);
        }
        if(player3Name.getText().equals(player.getUser().getName())) {
            player3Name.getStyleClass().remove(FXGuiConstant.LEFT);
            player3Name.getStyleClass().add(FXGuiConstant.REJOIN);
        }
        if(player4Name.getText().equals(player.getUser().getName())) {
            player4Name.getStyleClass().remove(FXGuiConstant.LEFT);
            player4Name.getStyleClass().add(FXGuiConstant.REJOIN);
        }

        if(player.getUser().getName().equals(guiView.getUserName())){
            try {
                createMatchRoundsGui(multiPlayerMatch);
            } catch (IOException e) {
                //eccezione gestita
            }
            GuiMultiplayerApp newController = guiView.getMultiplayerApps().get(tokenMatch);
            for(Player player1: newController.getMultiPlayerMatch().getPlayers()){
               newController.updateCells(player1);
            }
            newController.updateRoundDice(newController.getMultiPlayerMatch());
            for(ToolCardView toolCardView : newController.getMatchView().getToolCards()){
                if(toolCardView.getToolCard().getFavorTokens() > 0)
                    associateCostLabel(newController.getMatchView().getToolCards().indexOf(toolCardView)).setText("Costo: 2FavorTokens");
            }
        }

    }

    /**
     * calls the method that creates the window choice scene
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     */
    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        try {
            createWindowChoiceGui(match,tokenMatch);
        } catch (IOException e) {
            //eccezione gestita
        }

    }

    /**
     * notifies that all players have chosen their window but has no real effect on the view
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     */
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //has no effect on th e gui because it's called right after OnMatchStart
    }

    /**
     * if it's the first turn calls the method that creates tha match scene. if it's the round's first turn updates the draft pool and shows the ended round's image view
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     */
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        multiPlayerMatch = match;
        if(match.getTurnHandler().isFirstTurn()){
            try {
                createMatchRoundsGui(match);
            }catch (IOException e){
                //eccezione gestita
            }
            return;
        }
        if (match.getTurnHandler().isRoundFirstTurn() && !match.getTurnHandler().isFirstTurn()){
            updateDraftPool(match);
            matchView.getRounds().get(match.getTurnHandler().getRound()-2).getImageView().setVisible(true);
            List<Die> roundtrackDice = match.getRoundTrack().retrieveDice(match.getTurnHandler().getRound()-1);
            for(Die die: roundtrackDice) {
                matchView.getRounds().get(match.getTurnHandler().getRound()-2).getDieViews().add(new DieView(null,die));
            }
        }
        if(match.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            console.setText(console.getText() + "\nHai 90 secondi per finire il tuo turno");
        }

    }

    /**
     * resets every selected view object. if it's the last round creates a new instance of the points window controller
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     */
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        if(matchView.getSelectedDie() != null) {
            deselect(matchView.getSelectedDie().getImageView());
            matchView.setSelectedDie(null);
        }
        if(matchView.getSelectedToolCard() != null) {
            deselect(matchView.getSelectedToolCard().getImageView());
            matchView.setSelectedToolCard(null);
        }
        if(matchView.getInput() != null) {
            if (matchView.getInput().getChosenDie() != null) {
                deselect(matchView.retrieveDieView(matchView.getInput().getChosenDie()).getImageView());
            }
            if (matchView.getInput().getOriginCell2() != null) {
                deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell2().getRow()][matchView.getInput().getOriginCell2().getColumn()].getImageView());
            }else {
                if (matchView.getInput().getOriginCell1() != null) {
                    deselect(matchView.retrieveThisPlayer(guiView.getUserName()).getWindow().getCells()[matchView.getInput().getOriginCell1().getRow()][matchView.getInput().getOriginCell1().getColumn()].getImageView());
                }
            }
            if (choice) {
                matchAnchorPane.getChildren().remove(choicePane);
            }
        }
        toPlace = false;
        choice = false;
        twoMoves = false;
        matchView.setSelectedDie(null);
        matchView.setSelectedToolCard(null);
        matchView.setInput(null);
        if(match.getTurnHandler().isLastTurn()){
            pointsWindow = new PointsWindowController();
            pointsWindow.setPoints(new HashMap<>());
        }
    }

    /**
     * updates the draft pool and the window of the player that has done the move
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @param cell cell in which the die has benn placed
     * @param die placed die
     * @throws RemoteException throws a match exception
     */
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        multiPlayerMatch = match;
        DieView dieView = matchView.retrieveDieView(die);
        matchView.retrievePlayer(match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()].getCell().setDie(dieView.getDie());
        matchView.retrievePlayer(match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()].getImageView().setImage(dieView.getImageView().getImage());
        if(match.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
             deselect(matchView.getSelectedDie().getImageView());
             matchView.setSelectedDie(null);
        }
        updateDraftPool(match);
    }

    /**
     * updates the draft pool, the window of the player that has done the move and also the round track's dice if the tool card is the right one
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @param toolCard tool card used
     * @throws RemoteException throws a match exception
     */
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException  {
        multiPlayerMatch = match;
        if(match.getTurnPlayer().getUser().getName().equals(guiView.getUserName())){
            if(matchView.toPlaceToolCards().contains(toolCard.getName())) {
                for (DieView dieView : matchView.getDraftPool()) {
                    if (dieView.getDie() == matchView.getInput().getChosenDie()) {
                        matchView.setSelectedDie(dieView);
                        select(matchView.getSelectedDie().getImageView());
                        toPlace = true;
                    }
                }
            }
            favorTokens.setText("" + match.getTurnPlayer().getFavorTokens());
            deselect(matchView.getSelectedToolCard().getImageView());
            matchView.setSelectedToolCard(null);
            matchView.setInput(null);
            twoMoves = false;
        }


        for (Die die : match.getMatchDice().getDraftPool()){
            DieView dieView = matchView.getDraftPool().get(match.getMatchDice().getDraftPool().indexOf(die));
            dieView.setDie(die);
            dieView.getImageView().setImage(dieView.imagePath());
            dieView.getImageView().setVisible(true);
        }
        if(match.getTurnPlayer().getUser().getName().equals(guiView.getUserName()) &&
                (toolCard.getName().equals("diluente per pasta salda") ||
                        toolCard.getName().equals(FXGuiConstant.TAGLIERINA_CIRCOLARE))){
            DieView dieView = matchView.getDraftPool().get(match.getMatchDice().getDraftPool().size() - 1);
            matchView.setSelectedDie(dieView);
            select(matchView.getSelectedDie().getImageView());
            toPlace = true;
        }
        updateCells(match.getTurnPlayer());

        if(toolCard.getName().equals(FXGuiConstant.TAGLIERINA_CIRCOLARE)){
            updateRoundDice(match);
            roundDice.setVisible(false);
        }

        updateToolCardCost(toolCard);

    }

    /**
     * gets the player points and saves them in the points window controller for future use
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @param player player associated to the points
     * @param points player's points
     * @throws RemoteException
     */
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException{
        pointsWindow.getPoints().put(player.getUser().getName(), points);
    }

    /**
     * calls the method that creates the points scene
     * @param tokenMatch token of the match
     * @param match match in which this event occurred
     * @throws RemoteException
     */
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        try {
            createPointsGui(match);
        } catch (IOException e) {
            //eccezione gestita
        }
        RadioButton endedMatch = null;
        for (RadioButton matchLeft: guiView.getGuiApp().getMatches()){
            if (matchLeft.getText().equals(tokenMatch)){
                matchLeft.setVisible(false);
                endedMatch = matchLeft;
                break;
            }
        }
        guiView.getGuiApp().getMatches().remove(endedMatch);
        guiView.getGuiApp().updateMatchesLeft();
    }
}
