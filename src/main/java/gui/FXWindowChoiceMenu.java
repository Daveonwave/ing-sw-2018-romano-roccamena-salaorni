package gui;

import gui.objects.ObjectiveCardView;
import gui.objects.WindowView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class FXWindowChoiceMenu {


    List<WindowView> windows;

    @FXML
    ImageView choiceWindow1;
    @FXML
    ImageView choiceWindow2;
    @FXML
    ImageView choiceWindow3;
    @FXML
    ImageView choiceWindow4;
    @FXML
    ImageView extractedPrivateObjective;
    @FXML
    ImageView windowDifficulty1;
    @FXML
    ImageView windowDifficulty2;
    @FXML
    ImageView windowDifficulty3;
    @FXML
    ImageView windowDifficulty4;

    @FXML
    Label windowName1;
    @FXML
    Label windowName2;
    @FXML
    Label windowName3;
    @FXML
    Label windowName4;




    public ImageView associateWindows(int index) {
        switch (index) {
            case 1:
                return choiceWindow1;
            case 2:
                return choiceWindow2;
            case 3:
                return choiceWindow3;
            case 4:
                return choiceWindow4;
        }
        return null;
    }

    public WindowView retrieveWindows(Object source) {
        if (source.equals(choiceWindow1)) return windows.get(0);
        if (source.equals(choiceWindow2)) return windows.get(1);
        if (source.equals(choiceWindow3)) return windows.get(2);
        if (source.equals(choiceWindow4)) return windows.get(3);
        return null;
    }

    public void initializeMenu(MultiPlayerMatch match){


        for(Player player : match.getPlayers()){
            String name = player.getUser().getName();

            if (name.equals(GuiMultiplayerApp.getGuiView().getUserName())){
                windows = new ArrayList<>();
                new ObjectiveCardView(extractedPrivateObjective, player.getPrivateObjectiveCards().get(0));

                for( int i = 0; i<4; i++){
                    windows.add(new WindowView(associateWindows(i+1),player.getStartWindows().get(i),null));
                }
                break;
            }

        }
    }

    public void chooseWindow(MouseEvent mouseEvent){
        try {
            GuiMultiplayerApp.getGuiView().getController().chooseWindow(GuiMultiplayerApp.getGuiView().getUserToken(), GuiMultiplayerApp.getMultiTokenMatch(),retrieveWindows(mouseEvent.getSource()).getWindow());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node)(mouseEvent.getSource())).getScene().getWindow();
        stage.close();
    }


}
