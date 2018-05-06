package mvc.view.gui;

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

public class GUIHandler extends Application {
    //Gestore della gui dell'applicazione

    @FXML
    private TextField input;
    @FXML
    private Label output;
    @FXML
    private Button login,rmi,socket;

    //Inizializza la gui con la schermata di login
    public void start(Stage primaryStage) throws IOException{
        changeScene("login.fxml",primaryStage);
    }

    //Bottone di login: salva le istanze di view,controller e model in modo che siano riutilizzabili
    public void  login(ActionEvent actionEvent) throws IOException{
        GUIView guiView = new GUIView();
        AppController appController = new AppController();
        AppModel appModel = appController.getModel();
        guiView.setAppController(appController);
        guiView.login(input.getText());
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        save(guiView,"C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\gui\\view.ser");
        save(appController,"C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\gui\\controller.ser");
        save(appModel,"C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\gui\\model.ser");
        changeScene("menu.fxml",stage);
    }

    //Cambia scena nella gui caricandola da un nuovo file FXML
    public void changeScene(String fxml,Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Carica la view da file e gli associa controller e model
    public GUIView loadView(){
        GUIView guiView = null;
        AppController appController = loadController();
        appController.setModel(loadModel());
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\gui\\view.ser"))){
            guiView = (GUIView) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        guiView.setAppController(appController);
        return guiView;
    }
    //Carica il controller da file
    public AppController loadController(){
        AppController appController = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\gui\\controller.ser"))){
            appController = (AppController) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return appController;

    }
    //carica il model da file
    public AppModel loadModel(){
        AppModel appModel = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LorenzoR\\IdeaProjects\\Sagrada\\ing-sw-2018-romano-roccamena-salaorni\\src\\main\\java\\mvc\\view\\gui\\model.ser"))){
            appModel = (AppModel) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return appModel;

    }

    //Salva su file un oggetto
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
