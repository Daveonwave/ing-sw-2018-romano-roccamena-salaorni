package gui;

import gui.objects.ObjectiveCardView;
import gui.objects.WindowView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class FXWindowChoiceMenu {

    private List<WindowView> windows;
    private ObjectiveCardView privateObjective;
    private GuiMultiplayerApp guiMultiplayerApp;

    @FXML
    ImageView firstChoiceWindow;
    @FXML
    ImageView secondChoiceWindow;
    @FXML
    ImageView thirdChoiceWindow;
    @FXML
    ImageView fourthChoiceWindow;
    @FXML
    ImageView extractedPrivateObjective;
    @FXML
    Text windowsText;


    public ImageView associateWindows(int index) {
        switch (index) {
            case 1:
                return firstChoiceWindow;
            case 2:
                return secondChoiceWindow;
            case 3:
                return thirdChoiceWindow;
            case 4:
                return fourthChoiceWindow;
        }
        return null;
    }

    public WindowView retrieveWindows(Object source) {
        if (source.equals(firstChoiceWindow)) return windows.get(0);
        if (source.equals(secondChoiceWindow)) return windows.get(1);
        if (source.equals(thirdChoiceWindow)) return windows.get(2);
        if (source.equals(fourthChoiceWindow)) return windows.get(3);
        return null;
    }

    public void initializeMenu(MultiPlayerMatch match, GuiMultiplayerApp guiMultiplayerApp){
        this.guiMultiplayerApp = guiMultiplayerApp;

        for(Player player : match.getPlayers()){
            String name = player.getUser().getName();

            if (name.equals(guiMultiplayerApp.getGuiView().getUserName())){
                windows = new ArrayList<>();
                privateObjective = new ObjectiveCardView(extractedPrivateObjective,player.getPrivateObjectiveCards().get(0));

                for( int i = 0; i<4; i++){
                    windows.add(new WindowView(associateWindows(i+1),player.getStartWindows().get(i),null));
                }
                break;
            }

        }
    }

    public void chooseWindow(MouseEvent mouseEvent){
        try {
            guiMultiplayerApp.getGuiView().getController().chooseWindow(guiMultiplayerApp.getGuiView().getUserToken(), guiMultiplayerApp.getMultiTokenMatch(),retrieveWindows(mouseEvent.getSource()).getWindow());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node)(mouseEvent.getSource())).getScene().getWindow();
        stage.close();
    }


}
