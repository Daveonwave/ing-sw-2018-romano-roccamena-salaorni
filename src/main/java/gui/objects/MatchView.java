package gui.objects;

import mvc.model.objects.Die;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCardInput;

import java.util.List;

public class MatchView {

    private List<ToolCardView> toolCards;
    private List<ObjectiveCardView> publicObjective;
    private ObjectiveCardView privateObjective;
    private List<DieView> dice;
    private List<PlayerView> players;
    private DieView selectedDie;
    private List<RoundView> rounds;
    private ToolCardInput input;
    private ToolCardView selectedToolCard;


    public MatchView(List<ToolCardView> toolCards, List<ObjectiveCardView> publicObjective, ObjectiveCardView privateObjective, List<DieView> dice, List<PlayerView> players, DieView selectedDie, List<RoundView> rounds, ToolCardInput input, ToolCardView selectedToolCard) {
        this.toolCards = toolCards;
        this.publicObjective = publicObjective;
        this.privateObjective = privateObjective;
        this.dice = dice;
        this.players = players;
        this.selectedDie = selectedDie;
        this.rounds = rounds;
        this.input = input;
        this.selectedToolCard = selectedToolCard;
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
    public List<PlayerView> getPlayers() {
        return players;
    }
    public DieView getSelectedDie() {
        return selectedDie;
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


    //Ottiene oggetti di gioco
    public DieView retrieveDieView(Die die) {
        for (DieView dieView : dice) {
            if (dieView.getDie().getColor().equals(die.getColor())&& dieView.getDie().getShade() == die.getShade())
                return dieView;
        }
        return null;
    }
    public PlayerView retrievePlayer(Player player) {
        for (PlayerView playerView : players) {
            if (player.getUser().getName().equals(playerView.getPlayer().getUser().getName())) return playerView;
        }
        return null;
    }
    public PlayerView retrieveThisPlayer(String name) {
        for (PlayerView playerView : players) {
            if (playerView.getPlayer().getUser().getName().equals(name)) return playerView;
        }

        return null;
    }
}
