package mvc.view.gui;

import com.google.gson.Gson;
import io.FileHandler;
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
    private Button login, rmi, socket;

    //Inizializza la gui con la schermata di login
    public void start(Stage primaryStage) throws IOException{
        changeScene("login.fxml",primaryStage);
    }

    //Bottone di login: salva le istanze di view,controller e model in modo che siano riutilizzabili
    public void  login(ActionEvent actionEvent) throws IOException{
        AppController appController = new AppController();
        GUIView guiView = new GUIView(appController);
        AppModel appModel = appController.getModel();

        guiView.login(input.getText());

        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        //Converte e serializza
        Gson gson = new Gson();

        FileHandler fileHandler = new FileHandler();
        String text =  "";

        text = gson.toJson(appModel);
        fileHandler.fileWrite(GUIFileInfo.GUI_FILES_PATH + "/" + GUIFileInfo.MODEL_FILE_NAME, text);

        text = gson.toJson(appModel);
        fileHandler.fileWrite(GUIFileInfo.GUI_FILES_PATH + "/" + GUIFileInfo.VIEW_FILE_NAME, text);

        text = gson.toJson(appModel);
        fileHandler.fileWrite(GUIFileInfo.GUI_FILES_PATH + "/" + GUIFileInfo.CONTROLLER_FILE_NAME, text);

        changeScene("menu.fxml",stage);
    }

    //Cambia scena nella gui caricandola da un nuovo file FXML
    public void changeScene(String fxml, Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //TODO: cambio gson in login
    //Carica componenti mvc
    public AppModel loadModel(){
        AppModel appModel = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GUIFileInfo.GUI_FILES_PATH + "/" + GUIFileInfo.MODEL_FILE_NAME));
            appModel = (AppModel) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return appModel;

    }
    public GUIView loadView(){
        GUIView guiView = null;
        AppController appController = loadController();
        appController.setModel(loadModel());
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GUIFileInfo.GUI_FILES_PATH + "/" + GUIFileInfo.VIEW_FILE_NAME));
            guiView = (GUIView) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        guiView.setAppController(appController);
        return guiView;
    }
    public AppController loadController(){
        AppController appController = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GUIFileInfo.GUI_FILES_PATH + "/" + GUIFileInfo.CONTROLLER_FILE_NAME));
            appController = (AppController) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return appController;

    }

    //TODO: cambio gson in login
    //Salva su file un oggetto
    public void save(Object object,String file){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException{
        launch(args);
    }
}
