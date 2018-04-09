package game.match.args;

import game.components.*;

import java.io.Serializable;

public class MatchInput implements Serializable {
    //Inpput di una partita

    private Match match;
    private Player player;
    private Die die;
    private Cell cell;
    private Window window;
    private ToolCard toolCard;

    //Creatori
    public MatchInput(Match match, Player player, Die die, Cell cell, Window window, ToolCard toolCard) {
        this.player = player;
        this.die = die;
        this.cell = cell;
        this.window = window;
        this.toolCard = toolCard;
        this.match = match;
    }

    //Getter/Setter
    public void setMatch(Match match) {
        this.match = match;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setDie(Die die) {
        this.die = die;
    }
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    public void setWindow(Window window) {
        this.window = window;
    }
    public void setToolCard(ToolCard toolCard) {
        this.toolCard = toolCard;
    }

    public Match getMatch() {
        return match;
    }
    public Player getPlayer() {
        return player;
    }
    public Die getDie() {
        return die;
    }
    public Cell getCell() {
        return cell;
    }
    public Window getWindow() {
        return window;
    }
    public ToolCard getToolCard() {
        return toolCard;
    }
}
