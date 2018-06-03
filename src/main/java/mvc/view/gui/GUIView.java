package mvc.view.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
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
    private DieView selectedDie;

    //Costruttori
    public GUIView(AppControllerStub appController) {
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

    public DieView getSelectedDie() {
        return selectedDie;
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
    public void setSelectedDie(DieView selectedDie) {
        this.selectedDie = selectedDie;
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

    public void createGame(MultiPlayerMatch match){

        guiHandler.d1.setVisible(false);
        guiHandler.d2.setVisible(false);
        guiHandler.d3.setVisible(false);
        guiHandler.d4.setVisible(false);
        guiHandler.d5.setVisible(false);
        guiHandler.d6.setVisible(false);
        guiHandler.d7.setVisible(false);
        guiHandler.d8.setVisible(false);
        guiHandler.d9.setVisible(false);

        int window = 2;
        for (Player player: match.getPlayers()){
            if (!player.getUser().getAppView().equals(this)){
                opponents.add(new OpponentView(new WindowView(guiHandler.associateWindow(window), player.getWindow(),guiHandler.associateCells(player.getWindow().getCells(),window)),player));
                window += 1;
            }
            else{
                this.window = new WindowView(new ImageView(),player.getWindow(),guiHandler.associateCells(player.getWindow().getCells(),1));
                this.privateObjective.setCard(player.getPrivateObjectiveCards().get(0));
            }
        }
        for (ToolCard toolCard: match.getToolCards()){
            this.toolCards.add(new CardView(guiHandler.associateToolCard(match.getToolCards().indexOf(toolCard)),toolCard));
        }
        for (Die die : match.getMatchDice().getDraftPool()){
            this.dice.add(new DieView(guiHandler.associateDice(match.getMatchDice().getDraftPool().indexOf(die)),die));
        }
        for(DieView die: this.dice){
            die.getImageView().setVisible(true);
        }
        for(PublicObjectiveCard card: match.getPublicObjectiveCards()){
            this.publicObjective.add(new CardView(guiHandler.associatePublicObjective(match.getPublicObjectiveCards().indexOf(card)),card));
        }

    }
    public DieView retrieveDieView(List<DieView> dice, Die die){
        for(DieView dieView: dice){
            if (die.equals(dieView.getDie())) return dieView;
        }
        return null;
    }
    public OpponentView retrieveOpponent(List<OpponentView> opponents, Player player){
        for (OpponentView opponentView: opponents){
            if (player.equals(opponentView.getPlayer())) return opponentView;
        }
        return null;
    }

    //Risposta al controllore
    public void respondError(String message) throws RemoteException {
        this.output = message;
    }

    public void respondAck(String message) throws RemoteException {
       this.output = message;
    }

    //Osservazione partita
    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {


    }
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        this.getGuiHandler().setReady(true);
        try {
            this.getGuiHandler().startGame(match, tokenMatch);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        if(match.getTurnHandler().isFirstTurn()){
            createGame(match);
        }

    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        DieView dieView = retrieveDieView(dice,die);
        if (match.getTurnPlayer().getUser().getAppView().equals(this)){
            this.window.getCells()[cell.getRow()][cell.getColumn()].setImageView(retrieveDieView(this.dice,die).getImageView());
        }
        else{
            retrieveOpponent(opponents,match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()].setImageView(dieView.getImageView());

        }
        dieView.getImageView().setVisible(false);
        dice.remove(dieView);
    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {

    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {

    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

}
