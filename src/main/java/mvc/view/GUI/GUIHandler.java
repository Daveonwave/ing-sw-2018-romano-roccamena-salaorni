package mvc.view.GUI;

import ingsw2018.App;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.controller.AppController;
import mvc.model.AppModel;

import java.io.*;
import java.rmi.RemoteException;
import java.security.Guard;

//classe che gestisce la GUI e i cambiamenti di finestra
public class GUIHandler extends Application {

    @FXML
    private TextField input;
    @FXML
    private Label output;
    @FXML
    private Button login,rmi,socket;

    //classe che inizializza la gui con la schermata di login
    @Override
    public void start(Stage primaryStage) throws IOException{
        changeScene("login.fxml",primaryStage);
    }

    //motodo che viene richimato quando si preme il tasto di login. Salva le istanze di View,Controller e Model dentro ad un file in modo che siano riutilizzabili in fasi successive
    public void  login(ActionEvent actionEvent) throws IOException{
        GUIView guiView = new GUIView();
        AppController appController = new AppController();
        AppModel appModel = appController.getModel();
        guiView.setAppController(appController);
        guiView.login(input.getText());
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        save(guiView,"C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\view.ser");
        save(appController,"C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\controller.ser");
        save(appModel,"C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\model.ser");
        changeScene("menu.fxml",stage);
    }

    //motodo che cambia scena nella GUI caricandola da un nuovo file FXML
    public void changeScene(String fxml,Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //carica la view da file e gli associa controller e model
    public GUIView loadView(){
        GUIView guiView = null;
        AppController appController = loadController();
        appController.setModel(loadModel());
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\view.ser"))){
            guiView = (GUIView) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        guiView.setAppController(appController);
        return guiView;
    }
    //carica il controller da file
    public AppController loadController(){
        AppController appController = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\controller.ser"))){
            appController = (AppController) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return appController;

    }
    //carica il model da file
    public AppModel loadModel(){
        AppModel appModel = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\GUI\\model.ser"))){
            appModel = (AppModel) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return appModel;

    }

    //salva su file un oggetto
    public void save(Object object,String file){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(object);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws RemoteException{
        launch(args);
    }
}
