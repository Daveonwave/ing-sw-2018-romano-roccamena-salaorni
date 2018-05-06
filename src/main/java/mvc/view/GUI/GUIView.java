package mvc.view.GUI;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

// classe che estende AppView e richiama i metodi del controller
public class GUIView extends AppView {

    private String userToken = "";
    private String output;

    //costruttori
    public GUIView() throws RemoteException {
        super();
    }

    public GUIView(AppControllerStub appController) throws RemoteException {
        super(appController);
    }

    //getter e setter
    public String getUserToken() {
        return userToken;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }


    public void login(String name)throws RemoteException{
        userToken = getAppController().login(name, this);
    }

    @Override
    public void respondError(String message) throws RemoteException {

    }

    @Override
    public void respondAck(String message) throws RemoteException {
       this.output = message;
    }

    @Override
    public void onMatchStart(String tokenMatch, Match match) throws RemoteException {

    }

    @Override
    public void onChooseWindows(String tokenMatch, Match match) throws RemoteException {

    }

    @Override
    public void onTurnStart(String tokenMatch, Match match) throws RemoteException {

    }

    @Override
    public void onTurnEnd(String tokenMatch, Match match) throws RemoteException {

    }

    @Override
    public void onPlaceDie(String tokenMatch, Match match, Cell cell, Die die) throws RemoteException {

    }

    @Override
    public void onUseTool(String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {

    }

    @Override
    public void onGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException {

    }

    @Override
    public void onMatchEnd(String tokenMatch, Match match) throws RemoteException {

    }



}
