package mvc.view.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

public class GUIView extends AppView {
    //View grafica dell'applicazione

    private String output;
    private GUIHandler guiHandler;
    private List<ToolCardView> toolCards;
    private List<ObjectiveCardView> publicObjective;
    private ObjectiveCardView privateObjective;
    private List<DieView> dice;
    private List<PlayerView> players;
    private DieView selectedDie;
    private List<RoundView> rounds;
    private ToolCardInput input;
    private ToolCardView selectedToolCard;

    //Costruttori
    public GUIView(AppControllerStub appController) {
        super(appController);
    }

    //Setter/Getter

    public List<PlayerView> getPlayers() {
        return players;
    }

    public GUIHandler getGuiHandler() {
        return guiHandler;
    }
    public String getOutput() {
        return output;
    }
    public List<ToolCardView> getToolCards() {
        return toolCards;
    }
    public List<ObjectiveCardView> getPublicObjective() {
        return publicObjective;
    }
    public ObjectiveCardView getPrivateObjective() {
        return privateObjective;
    }
    public List<DieView> getDice() {
        return dice;
    }
    public List<RoundView> getRounds() {
        return rounds;
    }
    public ToolCardInput getInput() {
        return input;
    }
    public ToolCardView getSelectedToolCard() {
        return selectedToolCard;
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
    public void setToolCards(List<ToolCardView> toolCards) {
        this.toolCards = toolCards;
    }
    public void setPublicObjective(List<ObjectiveCardView> publicObjective) {
        this.publicObjective = publicObjective;
    }
    public void setPrivateObjective(ObjectiveCardView privateObjective) {
        this.privateObjective = privateObjective;
    }
    public void setDice(List<DieView> dice) {
        this.dice = dice;
    }
    public void setPlayers(List<PlayerView> players) {
        this.players = players;
    }
    public void setSelectedDie(DieView selectedDie) {
        this.selectedDie = selectedDie;
    }
    public void setRounds(List<RoundView> rounds) {
        this.rounds = rounds;
    }
    public void setInput(ToolCardInput input) {
        this.input = input;
    }
    public void setSelectedToolCard(ToolCardView selectedToolCard) {
        this.selectedToolCard = selectedToolCard;
    }

    //Operazioni su utente
    public String login(String name)throws RemoteException{
        String userToken = "";

        try {
            userToken = getAppController().login(name,this);
        } catch (RemoteException e) {
            e.printStackTrace();
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
       this.guiHandler.initializeGameGui();
        int window = 2;
        for (Player player: match.getPlayers()){
                players.add(new PlayerView(new WindowView(guiHandler.associateWindow(window), player.getWindow(),guiHandler.associateCells(player.getWindow().getCells(),window)),player));
                window += 1;
                if(player.getUser().getAppView().equals(this)){
                    this.privateObjective.setCard(player.getPrivateObjectiveCards().get(0));
                }
        }

        for (ToolCard toolCard: match.getToolCards()){
            this.toolCards.add(new ToolCardView(guiHandler.associateToolCard(match.getToolCards().indexOf(toolCard)),toolCard));
        }
        for (Die die : match.getMatchDice().getDraftPool()){
            this.dice.add(new DieView(guiHandler.associateDice(match.getMatchDice().getDraftPool().indexOf(die)),die));
        }

        for (int i = 1; i<11; i++){
            this.rounds.add(i-1, new RoundView(this.guiHandler.associateRound(i), null, i));
        }
        for(DieView die: this.dice){
            die.getImageView().setVisible(true);
        }
        for(PublicObjectiveCard card: match.getPublicObjectiveCards()){
            this.publicObjective.add(new ObjectiveCardView(guiHandler.associatePublicObjective(match.getPublicObjectiveCards().indexOf(card)),card));
        }

    }
    public DieView retrieveDieView(List<DieView> dice, Die die){
        for(DieView dieView: dice){
            if (die.equals(dieView.getDie())) return dieView;
        }
        return null;
    }
    public PlayerView retrievePlayer(List<PlayerView> players, Player player){
        for (PlayerView playerView : players){
            if (player.equals(playerView.getPlayer())) return playerView;
        }
        return null;
    }
    public PlayerView retrieveThisPlayer(){
        for (PlayerView playerView: players){
            if(playerView.getPlayer().getUser().getAppView().equals(this)) return playerView;
        }
        return null;
    }

    //Risposta al controllore
    public void respondError(String message) throws RemoteException {
        this.output = message;
        if(this.guiHandler.console!=null) {
            this.guiHandler.console.setText(message);
        }
    }
    public void respondAck(String message) throws RemoteException {
        this.output = message;
        if(this.guiHandler.console!=null) {
            this.guiHandler.console.setText(message);
        }
    }

    //Osservazione partita
    public void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //TODO: implementazione
    }
    public void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //TODO: implementazione
    }

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
            return;
        }
        if (match.getTurnHandler().isRoundFirstTurn()){
            for (Die die : match.getMatchDice().getDraftPool()){
                DieView dieView = this.dice.get(match.getMatchDice().getDraftPool().indexOf(die));
                dieView.setDie(die);
                dieView.getImageView().setImage(dieView.imagePath());
                dieView.getImageView().setVisible(true);
            }

        }

    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        if (match.getTurnHandler().isRoundLastTurn()){
            this.rounds.get(match.getTurnHandler().getRound()).getImageView().setVisible(true);

            List<Die> roundtrackDice = match.getRoundTrack().retrieveDice(match.getTurnHandler().getRound()-1);
            for(Die die: roundtrackDice) {
                rounds.get(match.getTurnHandler().getRound()-1).getDieViews().add(new DieView(null,die));
            }
        }

    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        DieView dieView = retrieveDieView(dice,die);
        retrievePlayer(players,match.getTurnPlayer()).getWindow().getCells()[cell.getRow()][cell.getColumn()].getImageView().setImage(dieView.getImageView().getImage());
        if(match.getTurnPlayer().getUser().getAppView().equals(this)){
            this.selectedDie = null;
        }
        dieView.getImageView().setVisible(false);
    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        for (Die die : match.getMatchDice().getDraftPool()){
            DieView dieView = this.dice.get(match.getMatchDice().getDraftPool().indexOf(die));
            dieView.setDie(die);
            dieView.getImageView().setImage(dieView.imagePath());
            dieView.getImageView().setVisible(true);
        }
        for (CellView[] cells: retrievePlayer(players, match.getTurnPlayer()).getWindow().getCells()){
            for (CellView cell: cells){
                if(cell.getCell().getDie()!= null){
                    cell.getImageView().setImage(new DieView(null, cell.getCell().getDie()).getImageView().getImage());
                }
            }

        }

        if(match.getTurnPlayer().getUser().getAppView().equals(this)){
            this.setSelectedToolCard(null);
            if(this.guiHandler.noSelectionToolCards().contains(toolCard.getName())){
                this.getSelectedDie().setDie(input.getChoosenDie());
                this.guiHandler.setToPlace(true);
            }

        }
    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {

    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
}
