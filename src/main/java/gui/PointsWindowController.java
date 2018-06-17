package gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.PlayerPoints;

import java.awt.*;
import java.util.Map;

public class PointsWindowController {

    private static Map<String, PlayerPoints> points;
    private static MultiPlayerMatch match;

    @FXML
    Label player1, player2, player3, player4, winner;

    @FXML
    Label player1PrivateObjective, player1PublicObjective, player1OpenSpaces, player1FavorTokens, player1TotalPoints;

    @FXML
    Label player2PrivateObjective, player2PublicObjective, player2OpenSpaces, player2FavorTokens, player2TotalPoints;

    @FXML
    Label player3PrivateObjective, player3PublicObjective, player3OpenSpaces, player3FavorTokens, player3TotalPoints;

    @FXML
    Label player4PrivateObjective, player4PublicObjective, player4OpenSpaces, player4FavorTokens, player4TotalPoints;

    public void associatePoints(String playerName, PlayerPoints playerPoints){
        points.put(playerName, playerPoints);
    }
    public void associateMatch(MultiPlayerMatch multiPlayerMatch){
        match = multiPlayerMatch;
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

    public void calculateScores(MouseEvent mouseEvent) {
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
