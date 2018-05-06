package mvc.view.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.controller.AppController;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GUIHandler extends Application {

    @FXML
    private TextField input;
    @FXML
    private Label output;
    @FXML
    private Button login,rmi,socket;


    @Override
    public void start(Stage primaryStage) throws IOException{
        ViewUpdater viewUpdater = new ViewUpdater(new AppController());
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\view.ser"))){
            oos.writeObject(viewUpdater);
        }catch(Exception e){
            e.printStackTrace();
        }
        viewUpdater.changeScene("login.fxml",primaryStage);
    }

    public void  login(ActionEvent actionEvent) throws IOException{
        ViewUpdater viewUpdater = loadView();
        viewUpdater.login(input.getText());
        output.setText(viewUpdater.getOutput());
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        viewUpdater.changeScene("menu.fxml",stage);
    }

    public ViewUpdater loadView(){
        ViewUpdater viewUpdater = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\view.ser"))){
            viewUpdater = (ViewUpdater) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return viewUpdater;
    }



    public static void main(String[] args) throws RemoteException{
        launch(args);
    }
}
