package gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.PlayerPoints;

import java.util.Map;

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

    public PointsWindowController(Map<String, PlayerPoints> points, MultiPlayerMatch match) {
        this.points = points;
        this.match = match;
    }

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


    public Label associatePlayer(int index){
        switch (index){
            case 0: return player1;
            case 1: return player2;
            case 2: return player3;
            case 3: return player4;

        }
        return null;
    }
    public Label associatePrivateObjective(int index){
        switch (index){
            case 0: return player1PrivateObjective;
            case 1: return player2PrivateObjective;
            case 2: return player3PrivateObjective;
            case 3: return player4PrivateObjective;

        }
        return null;
    }
    public Label associatePublicObjective(int index){
        switch (index){
            case 0: return player1PublicObjective;
            case 1: return player2PublicObjective;
            case 2: return player3PublicObjective;
            case 3: return player4PublicObjective;

        }
        return null;
    }
    public Label associateOpenSpaces(int index){
        switch (index){
            case 0: return player1OpenSpaces;
            case 1: return player2OpenSpaces;
            case 2: return player3OpenSpaces;
            case 3: return player4OpenSpaces;
        }
        return null;
    }
    public Label associateFavorTokens(int index){
        switch (index){
            case 0: return player1FavorTokens;
            case 1: return player2FavorTokens;
            case 2: return player3FavorTokens;
            case 3: return player4FavorTokens;
        }
        return null;
    }
    public Label associateTotalScore(int index){
        switch (index){
            case 0: return player1TotalPoints;
            case 1: return player2TotalPoints;
            case 2: return player3TotalPoints;
            case 3: return player4TotalPoints;

        }
        return null;
    }

    public void calculateScores() {
        String winnerName = "";
        int winnerPoints = 0;
        for(Player player : match.getPlayers()){
            int index = match.getPlayers().indexOf(player);
            PlayerPoints playerPoints = points.get(player.getUser().getName());
            associatePlayer(index).setText(player.getUser().getName());
            associatePrivateObjective(index).setText("+ " + playerPoints.getPrivateObjectivePoints());
            associatePublicObjective(index).setText("+ " + playerPoints.getPublicObjectivePoints());
            associateOpenSpaces(index).setText("- " + playerPoints.getOpenSpacesLostPoints());
            associateFavorTokens(index).setText("+ " + playerPoints.getFavorTokensPoints());
            associateTotalScore(index).setText("" + playerPoints.getTotalPoints());
            if(playerPoints.getTotalPoints() > winnerPoints){
                winnerPoints = playerPoints.getTotalPoints();
                winnerName = player.getUser().getName();
            }
        }
        winner.setText("il vincitore è: "+ winnerName);
    }

    public void endGame(MouseEvent mouseEvent) {
        Stage stage =(Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
