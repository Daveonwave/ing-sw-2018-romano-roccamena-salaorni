package game;

import game.components.Die;
import game.components.PrivateObjectiveCard;
import game.components.ToolCard;

import java.util.ArrayList;

public class SetupMatch {
    // inizializza il gioco all'inizio (estrazione carte obiettivo e tool,...)

    private ArrayList<PrivateObjectiveCard> publicObjectiveCards;
    private ArrayList<PrivateObjectiveCard> privateObjectiveCards;
    private ToolCard toolCards[];
    private Die dies[];

    public ArrayList<PrivateObjectiveCard> choosePublicObjectives(){
        publicObjectiveCards = new ArrayList<PrivateObjectiveCard>();

        //TODO: implemetare scelta randomica carte;
        return publicObjectiveCards;
    }

    public ArrayList<PrivateObjectiveCard> choosePrivateObjectives(){
        privateObjectiveCards = new ArrayList<PrivateObjectiveCard>();

        //TODO: implementare scelta randomica;
        return privateObjectiveCards;
    }

}
