package gui.objects;

import mvc.model.objects.Die;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCardInput;

import java.util.ArrayList;
import java.util.List;

/**
 * class that contains all the view objects that a player uses during a match
 */
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

    /**
     *
     * @param toolCards tool cards view
     * @param publicObjective public objective view
     * @param privateObjective private objective view
     * @param dice draft pool view
     * @param players players view
     * @param selectedDie selected die
     * @param rounds rounds view
     * @param input tool card's input
     * @param selectedToolCard selected tool card
     */
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

    public void setInput(ToolCardInput input) {
        this.input = input;
    }
    public void setSelectedToolCard(ToolCardView selectedToolCard) {
        this.selectedToolCard = selectedToolCard;
    }


    /**
     * gets the corresponding die view object from a die model object
     * @param die model's die
     * @return die view
     */
    public DieView retrieveDieView(Die die) {
        for (DieView dieView : dice) {
            if (dieView.getDie().getColor().equals(die.getColor())&& dieView.getDie().getShade() == die.getShade())
                return dieView;
        }
        return null;
    }

    /**
     * gets the corresponding player view object from a player model object
     * @param player model's player
     * @return player view
     */
    public PlayerView retrievePlayer(Player player) {
        for (PlayerView playerView : players) {
            if (player.getUser().getName().equals(playerView.getPlayer().getUser().getName())) return playerView;
        }
        return null;
    }

    /**
     * gets the player associated to the view instance
     * @param name player's name
     * @return view of the player
     */
    public PlayerView retrieveThisPlayer(String name) {
        for (PlayerView playerView : players) {
            if (playerView.getPlayer().getUser().getName().equals(name)) return playerView;
        }

        return null;
    }

    /**
     * creates a list of tool cards whose effect needs a die already placed on the window
     * @return list of those tool cards
     */
    public List<String> windowToolCards() {

        List<String> toolCards = new ArrayList<>();
        toolCards.add("pennello per eglomise");
        toolCards.add("alesatore per lamina di rame");
        toolCards.add("lathekin");
        toolCards.add("taglierina manuale");

        return toolCards;
    }

    /**
     * creates a list of tool cards whose needs a die from the draft pool
     * @return list of those tool cards
     */
    public List<String> draftPoolToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("pinza sgrossatrice");
        toolCards.add("taglierina circolare");
        toolCards.add("pennello per pasta salda");
        toolCards.add("tampone diamantato");
        toolCards.add("diluente per pasta salda");

        return toolCards;
    }

    /**
     * creates a list of tool cards whose effet don't need any dice
     * @return list of those tool cards
     */
    public List<String> noSelectionToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("martelletto");
        toolCards.add("tenaglia a rotelle");
        toolCards.add("riga di sughero");

        return toolCards;
    }

    /**
     * creates a list of tool cards whose effect forces the player to place a die
     * @return list of those tool cards
     */
    public List<String> toPlaceToolCards() {
        List<String> toolCards = new ArrayList<>();

        toolCards.add("pinza sgrossatrice");
        toolCards.add("riga di sughero");
        toolCards.add("tampone diamantato");

        return toolCards;
    }
}
