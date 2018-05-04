package mvc.view.GUI;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EventListener;
import java.util.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainMenuController {

    @FXML
    public void changeScene(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("menu2.fxml"));
        Scene scene = new Scene(root);
        Stage window =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
