package game;

import game.components.Die;
import game.components.Match;
import game.components.ObjectiveCard;
import game.components.ToolCard;

import java.util.ArrayList;

public class SetupMatch {
    private ArrayList<ObjectiveCard> publicObjectiveCards;
    private ArrayList<ObjectiveCard> privateObjectiveCards;
    private ToolCard toolCards[];
    private Die dies[];

    public ArrayList<ObjectiveCard> choosePublicObjectives(){
        publicObjectiveCards = new ArrayList<ObjectiveCard>();

        //TODO: implemetare scelta randomica carte;
        return publicObjectiveCards;
    }

    public ArrayList<ObjectiveCard> choosePrivateObjectives(){
        privateObjectiveCards = new ArrayList<ObjectiveCard>();

        //TODO: implementare scelta randomica;
        return privateObjectiveCards;
    }

}
