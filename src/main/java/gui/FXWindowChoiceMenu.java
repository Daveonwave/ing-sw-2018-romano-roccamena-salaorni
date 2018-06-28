package gui;

import gui.objects.ObjectiveCardView;
import gui.objects.WindowView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import mvc.exceptions.AppViewException;
import mvc.model.objects.MultiPlayerMatch;
import mvc.model.objects.Player;
import mvc.model.objects.Window;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * controller class for the window choice scene
 */
public class FXWindowChoiceMenu {

    private GuiMultiplayerApp guiMultiplayerApp;
    private List<WindowView> windows;

    @FXML
    AnchorPane windowChoiceAnchorPane;
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
    @FXML
    Text windowChoiceText;


    public void setGuiMultiplayerApp(GuiMultiplayerApp guiMultiplayerApp) {
        this.guiMultiplayerApp = guiMultiplayerApp;
    }
    public void setWindows(List<WindowView> windows) {
        this.windows = windows;
    }
    public List<WindowView> getWindows() {
        return windows;
    }

    /**
     * gets the corresponding image view for the window to choose
     * @param index window number
     * @return image view of the right window
     */
    private ImageView associateWindows(int index) throws AppViewException{
        switch (index) {
            case 1:
                return choiceWindow1;
            case 2:
                return choiceWindow2;
            case 3:
                return choiceWindow3;
            case 4:
                return choiceWindow4;
            default: throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }

    }

    /**
     * gets the corresponding label for the window to choose
     * @param index window number
     * @return image view of the right window
     */
    private Label associateWindowName(int index) throws AppViewException{
        switch (index){
            case 0:
                return windowName1;
            case 1:
                return windowName2;
            case 2:
                return windowName3;
            case 3:
                return windowName4;
            default: throw new AppViewException(FXGuiConstant.LABEL_EXCEPTION_MESSAGE);

        }
    }

    /**
     * gets the corresponding image view for the difficulty of the window to choose
     * @param index window number
     * @return image view of the right difficulty
     */
    private ImageView associateWindowDifficulty(int index) throws AppViewException{
        switch (index){
            case 0:
                return windowDifficulty1;
            case 1:
                return windowDifficulty2;
            case 2:
                return windowDifficulty3;
            case 3:
                return windowDifficulty4;
            default: throw new AppViewException(FXGuiConstant.IMAGE_VIEW_EXCEPTION_MESSAGE);
        }
    }

    /**
     * gets the image for the difficulty of the window
     * @param difficulty difficulty of the window
     * @return image of the right difficulty
     */
    private Image applyDifficultyImage(int difficulty) throws AppViewException{
        String path;
        switch (difficulty){
            case 3:
                path = "objects/images/favor tokens/favor tokens 3.png";
                break;
            case 4:
                path = "objects/images/favor tokens/favor tokens 4.png";
                break;
            case 5:
                path = "objects/images/favor tokens/favor tokens 5.png";
                break;
            case 6:
                path = "objects/images/favor tokens/favor tokens 6.png";
                break;
            default: throw new AppViewException(FXGuiConstant.IMAGE_EXCEPTION_MESSAGE);
        }

        return new Image(getClass().getResourceAsStream(path));
    }

    /**
     * based on the clicked window, gets the right window view
     * @param source source of the click event
     * @return window view
     */
    private WindowView retrieveWindows(Object source) throws AppViewException{
        if (source.equals(choiceWindow1)) return windows.get(0);
        if (source.equals(choiceWindow2)) return windows.get(1);
        if (source.equals(choiceWindow3)) return windows.get(2);
        if (source.equals(choiceWindow4)) return windows.get(3);
        throw new AppViewException(FXGuiConstant.WINDOW_EXCEPTION_MESSAGE);
    }

    /**
     * initialize the scene for the starting window choice
     * @param match match of the model
     */
    public void initializeMenu(MultiPlayerMatch match) throws AppViewException{
        for(Player player : match.getPlayers()){
            String name = player.getUser().getName();

            if (name.equals(guiMultiplayerApp.getGuiView().getUserName())){
                windows = new ArrayList<>();
                new ObjectiveCardView(extractedPrivateObjective, player.getPrivateObjectiveCards().get(0));

                for( int i = 0; i<4; i++){
                    Window window = player.getStartWindows().get(i);
                    windows.add(new WindowView(associateWindows(i+1),window,null));
                    associateWindowName(i).setText(window.getName());
                    associateWindowDifficulty(i).setImage(applyDifficultyImage(window.getDifficulty()));
                }
                break;
            }

        }
    }

    /**
     * calls the controller method to choose a window
     * @param mouseEvent click event
     */
    public void chooseWindow(MouseEvent mouseEvent){
        try {
            guiMultiplayerApp.getGuiView().getController().chooseWindow(guiMultiplayerApp.getGuiView().getUserToken(), guiMultiplayerApp.getMultiTokenMatch(),retrieveWindows(mouseEvent.getSource()).getWindow());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        windowChoiceText.setText("In attesa degli altri giocatori...");
        windowChoiceAnchorPane.setDisable(true);
    }


}
