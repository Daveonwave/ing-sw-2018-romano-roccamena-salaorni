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
    private List<ObjectiveCardView> publicObjectives;
    private List<DieView> draftPool;
    private List<PlayerView> players;
    private DieView selectedDie;
    private List<RoundView> rounds;
    private ToolCardInput input;
    private ToolCardView selectedToolCard;

    /**
     *
     * @param toolCards tool cards view
     * @param publicObjectives public objective view
     * @param draftPool draft pool view
     * @param players players view
     * @param selectedDie selected die
     * @param rounds rounds view
     * @param input tool card's input
     */
    public MatchView(List<ToolCardView> toolCards, List<ObjectiveCardView> publicObjectives, List<DieView> draftPool,
                     List<PlayerView> players, DieView selectedDie, List<RoundView> rounds, ToolCardInput input) {
        this.toolCards = toolCards;
        this.publicObjectives = publicObjectives;
        this.draftPool = draftPool;
        this.players = players;
        this.selectedDie = selectedDie;
        this.rounds = rounds;
        this.input = input;
    }

    public List<ToolCardView> getToolCards() {
        return toolCards;
    }
    public List<ObjectiveCardView> getPublicObjectives() {
        return publicObjectives;
    }
    public List<DieView> getDraftPool() {
        return draftPool;
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
        for (DieView dieView : draftPool) {
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

        List<String> toolCardGroup = new ArrayList<>();
        toolCardGroup.add("pennello per eglomise");
        toolCardGroup.add("alesatore per lamina di rame");
        toolCardGroup.add("lathekin");
        toolCardGroup.add("taglierina manuale");

        return toolCardGroup;
    }

    /**
     * creates a list of tool cards whose needs a die from the draft pool
     * @return list of those tool cards
     */
    public List<String> draftPoolToolCards() {
        List<String> toolCardGroup = new ArrayList<>();

        toolCardGroup.add("pinza sgrossatrice");
        toolCardGroup.add("taglierina circolare");
        toolCardGroup.add("pennello per pasta salda");
        toolCardGroup.add("tampone diamantato");
        toolCardGroup.add("diluente per pasta salda");

        return toolCardGroup;
    }

    /**
     * creates a list of tool cards whose effet don't need any draftPool
     * @return list of those tool cards
     */
    public List<String> noSelectionToolCards() {
        List<String> toolCardGroup = new ArrayList<>();

        toolCardGroup.add("martelletto");
        toolCardGroup.add("tenaglia a rotelle");
        toolCardGroup.add("riga di sughero");

        return toolCardGroup;
    }

    /**
     * creates a list of tool cards whose effect forces the player to place a die
     * @return list of those tool cards
     */
    public List<String> toPlaceToolCards() {
        List<String> toolCardGroup = new ArrayList<>();

        toolCardGroup.add("pinza sgrossatrice");
        toolCardGroup.add("riga di sughero");
        toolCardGroup.add("tampone diamantato");

        return toolCardGroup;
    }
}
