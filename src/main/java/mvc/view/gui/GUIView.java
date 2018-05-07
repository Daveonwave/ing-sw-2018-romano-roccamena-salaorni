package mvc.view.gui;

import java.rmi.RemoteException;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

public class GUIView extends AppView {
    //View grafica dell'applicazione

    private String output;

    //Costruttori
    public GUIView(AppControllerStub appController) {
        super(appController);
    }

    //Setter/Getter
    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    //Login
    public void login(String name)throws RemoteException{
        setUserToken(getAppController().login(name, this));
    }
    //Logut?

    //Risposta al controllore
    public void respondError(String message) throws RemoteException {

    }
    public void respondAck(String message) throws RemoteException {
       this.output = message;
    }

    //Osservazione partita
    public void onMatchStart(String tokenMatch, Match match) throws RemoteException {

    }
    public void onChooseWindows(String tokenMatch, Match match) throws RemoteException {

    }
    public void onTurnStart(String tokenMatch, Match match) throws RemoteException {

    }
    public void onTurnEnd(String tokenMatch, Match match) throws RemoteException {

    }
    public void onPlaceDie(String tokenMatch, Match match, Cell cell, Die die) throws RemoteException {

    }
    public void onUseTool(String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {

    }
    public void onGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException {

    }
    public void onMatchEnd(String tokenMatch, Match match) throws RemoteException {

    }

}
