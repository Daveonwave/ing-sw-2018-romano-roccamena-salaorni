package gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.exceptions.AppViewException;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.PlayerPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * controller for the points window scene
 */
public class PointsWindowController {

    private Map<String, PlayerPoints> points;
    private MultiPlayerMatch match;

    @FXML
    Label player1;
    @FXML
    Label player2;
    @FXML
    Label player3;
    @FXML
    Label player4;
    @FXML
    Label winner;

    @FXML
    Label player1PrivateObjective;
    @FXML
    Label player1PublicObjective;
    @FXML
    Label player1OpenSpaces;
    @FXML
    Label player1FavorTokens;
    @FXML
    Label player1TotalPoints;

    @FXML
    Label player2PrivateObjective;
    @FXML
    Label player2PublicObjective;
    @FXML
    Label player2OpenSpaces;
    @FXML
    Label player2FavorTokens;
    @FXML
    Label player2TotalPoints;

    @FXML
    Label player3PrivateObjective;
    @FXML
    Label player3PublicObjective;
    @FXML
    Label player3OpenSpaces;
    @FXML
    Label player3FavorTokens;
    @FXML
    Label player3TotalPoints;

    @FXML
    Label player4PrivateObjective;
    @FXML
    Label player4PublicObjective;
    @FXML
    Label player4OpenSpaces;
    @FXML
    Label player4FavorTokens;
    @FXML
    Label player4TotalPoints;



    public Map<String, PlayerPoints> getPoints() {
        return points;
    }

    public MultiPlayerMatch getMatch() {
        return match;
    }

    public void setPoints(Map<String, PlayerPoints> points) {
        this.points = points;
    }

    public void setMatch(MultiPlayerMatch match) {
        this.match = match;
    }

    /**
     * gets the corresponding label for the name of the player
     * @param index player number
     * @return name label
     * @throws AppViewException
     */
    private Label associatePlayer(int index) throws AppViewException{
        switch (index){
            case 0: return player1;
            case 1: return player2;
            case 2: return player3;
            case 3: return player4;
            default: throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }
    }

    /**
     * gets the corresponding label for a player's private objective points
     * @param index player number
     * @return private objective points label
     * @throws AppViewException
     */
    private Label associatePrivateObjective(int index) throws AppViewException{
        switch (index){
            case 0: return player1PrivateObjective;
            case 1: return player2PrivateObjective;
            case 2: return player3PrivateObjective;
            case 3: return player4PrivateObjective;
            default: throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding label for a player's public objective points
     * @param index player number
     * @return public objective points label
     * @throws AppViewException
     */
    private Label associatePublicObjective(int index) throws AppViewException{
        switch (index){
            case 0: return player1PublicObjective;
            case 1: return player2PublicObjective;
            case 2: return player3PublicObjective;
            case 3: return player4PublicObjective;
            default: throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }
    }

    /**
     * gets the corresponding label for a player's points lost by open spaces
     * @param index player number
     * @return points lost by open spaces label
     * @throws AppViewException
     */
    private Label associateOpenSpaces(int index) throws AppViewException{
        switch (index){
            case 0: return player1OpenSpaces;
            case 1: return player2OpenSpaces;
            case 2: return player3OpenSpaces;
            case 3: return player4OpenSpaces;
            default: throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding label for a player's favor tokens points
     * @param index player number
     * @return favor tokens points label
     * @throws AppViewException
     */
    private Label associateFavorTokens(int index) throws AppViewException{
        switch (index){
            case 0: return player1FavorTokens;
            case 1: return player2FavorTokens;
            case 2: return player3FavorTokens;
            case 3: return player4FavorTokens;
            default: throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding label for a player's total score
     * @param index player number
     * @return total points label
     * @throws AppViewException
     */
    private Label associateTotalScore(int index) throws AppViewException{
        switch (index){
            case 0: return player1TotalPoints;
            case 1: return player2TotalPoints;
            case 2: return player3TotalPoints;
            case 3: return player4TotalPoints;
            default: throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);
        }
    }

    /**
     * initializes the points window scene associating every label to the corresponding player and showing the final scores
     * @throws AppViewException
     */
    public void initializeScores() throws AppViewException {
        List<String> winners = new ArrayList<>();
        int winnerPoints = -21;
        for(Player player : match.getPlayers()){
            int index = match.getPlayers().indexOf(player);
            PlayerPoints playerPoints = points.get(player.getUser().getName());
            associatePlayer(index).setText(player.getUser().getName());
            associatePrivateObjective(index).setText("+ " + playerPoints.getPrivateObjectivePoints());
            associatePublicObjective(index).setText("+ " + playerPoints.getPublicObjectivePoints());
            associateOpenSpaces(index).setText("" + playerPoints.getOpenSpacesLostPoints());

            associateFavorTokens(index).setText("+ " + playerPoints.getFavorTokensPoints());
            associateTotalScore(index).setText("" + playerPoints.getTotalPoints());
            if(playerPoints.getTotalPoints() == winnerPoints){
                winners.add(player.getUser().getName());
            }
            if(playerPoints.getTotalPoints() > winnerPoints){
                winnerPoints = playerPoints.getTotalPoints();
                winners.clear();
                winners.add(player.getUser().getName());
            }

        }

        if(winners.size() == 1){
            winner.setText("il vincitore è " + winners.get(0));
        }else{
            winner.setText("pareggio tra " + winners.get(0));
            for (int i = 1; i<winners.size() ;i++){
                winner.setText(winner.getText() + " e " + winners.get(i));
            }
        }
    }

    /**
     * closes the match window stage
     * @param mouseEvent
     */
    public void endGame(MouseEvent mouseEvent) {
        Stage stage =(Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
