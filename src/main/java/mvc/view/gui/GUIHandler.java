package mvc.view.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.controller.AppController;

import java.io.*;
import java.rmi.RemoteException;

public class GUIHandler extends Application {
    //Gestore della gui dell'applicazione

    private static GUIView guiView;

    @FXML
    private TextField input;
    @FXML
    private Label output;
    @FXML
    private Button login, rmi, socket;
    @FXML
    Text text;

    //Inizializza la gui con la schermata di login
    public void start(Stage primaryStage) throws IOException{
        guiView = new GUIView(new AppController());
        changeScene("menu.fxml",primaryStage);
    }

    //Bottone di login: salva le istanze di view,controller e model in modo che siano riutilizzabili
    public void  login(ActionEvent actionEvent) throws IOException{
        output.setText(guiView.login(input.getText()));
        System.out.println(guiView.getUserToken());
        input.setVisible(false);
        login.setVisible(false);
        text.setText("Scegliere il tipo di connessione:");
    }

    //Cambia scena nella gui caricandola da un nuovo file FXML
    public void changeScene(String fxml, Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws RemoteException{
        launch(args);
    }
}
