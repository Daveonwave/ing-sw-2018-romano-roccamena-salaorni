package mvc.view.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

public class GUIView extends AppView {
    //View grafica dell'applicazione

    private String output;
    private GUIHandler guiHandler;
    private CardView cardView;
    private CellView cellView;
    private DieView dieView;
    private WindowView windowView;

    //Costruttori
    public GUIView(AppControllerStub appController) {
        super(appController);
    }

    //Setter/Getter

    public GUIHandler getGuiHandler() {
        return guiHandler;
    }
    public String getOutput() {
        return output;
    }
    public CardView getCardView() {
        return cardView;
    }
    public CellView getCellView() {
        return cellView;
    }
    public DieView getDieView() {
        return dieView;
    }
    public WindowView getWindowView() {
        return windowView;
    }

    public void setGuiHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }
    public void setOutput(String output) {
        this.output = output;
    }
    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }
    public void setCellView(CellView cellView) {
        this.cellView = cellView;
    }
    public void setDieView(DieView dieView) {
        this.dieView = dieView;
    }
    public void setWindowView(WindowView windowView) {
        this.windowView = windowView;
    }

    //Operazioni su utente
    public String login(String name)throws RemoteException{
        String userToken = "";

        try {
            userToken = getAppController().login(name, this);
        } catch (RemoteException e) {
            //Gestione errore
        }

        setUserToken(userToken);
        setUserName(name);

        return this.output;
    }
    public void logout() throws RemoteException{
        try {
            getAppController().logout(getUserToken());
        } catch (RemoteException e) {
            //Gestione errore
        }
    }

    //Risposta al controllore
    public void respondError(String message) throws RemoteException {
        this.output = message;
    }
    public void respondAck(String message) throws RemoteException {
       this.output = message;
    }

    //Osservazione partita
    public void onMatchStart(String tokenMatch, Match match) throws RemoteException {
        this.getGuiHandler().setReady(true);
        try {
            this.getGuiHandler().startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
