package mvc.view.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import mvc.controller.AppController;
import mvc.model.objects.*;
import mvc.view.AppView;

public class GUIView extends AppView {
    //View grafica dell'applicazione

    private String output;
    private GUIHandler guiHandler;
    private List<CardView> toolCards;
    private List<CardView> publicObjective;
    private CardView privateObjective;
    private List<DieView> dice;
    private WindowView window;
    private List<OpponentView> opponents;

    //Costruttori
    public GUIView(AppController appController) {
        super(appController);
    }

    //Setter/Getter

    public List<OpponentView> getOpponents() {
        return opponents;
    }

    public GUIHandler getGuiHandler() {
        return guiHandler;
    }
    public String getOutput() {
        return output;
    }
    public List<CardView> getToolCards() {
        return toolCards;
    }
    public List<CardView> getPublicObjective() {
        return publicObjective;
    }
    public CardView getPrivateObjective() {
        return privateObjective;
    }
    public List<DieView> getDice() {
        return dice;
    }
    public WindowView getWindow() {
        return window;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    public void setGuiHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }
    public void setToolCards(List<CardView> toolCards) {
        this.toolCards = toolCards;
    }
    public void setPublicObjective(List<CardView> publicObjective) {
        this.publicObjective = publicObjective;
    }
    public void setPrivateObjective(CardView privateObjective) {
        this.privateObjective = privateObjective;
    }
    public void setDice(List<DieView> dice) {
        this.dice = dice;
    }
    public void setWindow(WindowView window) {
        this.window = window;
    }
    public void setOpponents(List<OpponentView> opponents) {
        this.opponents = opponents;
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

    public void getWindows() throws RemoteException{
        List<WindowView> windows = new ArrayList<>(4);
        for(int i = 0; i<4; i++) {
            windows.add(i, new WindowView(guiHandler.associateWindowButton(i+1),new ImageView(), this.getAppController().getModel().retrieveUser(this.getUserToken()).getPlayers().get(0).getStartWindows().get(i),null));
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
        int window = 2;
        for (Player player: match.getPlayers()){
            if (!player.getUser().getAppView().equals(this)){
                opponents.add(new OpponentView(new WindowView(null,guiHandler.associateWindow(window), player.getWindow(),guiHandler.associateCells(player.getWindow().getCells(),window)),player));
                window += 1;
            }
        }
        for (ToolCard toolCard: match.getToolCards()){
            this.toolCards.add(new CardView(guiHandler.associateToolCard(match.getToolCards().indexOf(toolCard)),new ImageView(),toolCard));
        }

    }
    public void onChooseWindows(String tokenMatch, Match match) throws RemoteException {
        this.getGuiHandler().setReady(true);
        try {
            this.getGuiHandler().startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
