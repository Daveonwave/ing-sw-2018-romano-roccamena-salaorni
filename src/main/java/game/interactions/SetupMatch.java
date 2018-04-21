package game.interactions;

import game.components.Die;
import game.components.PrivateObjectiveCard;
import game.components.PublicObjectiveCard;
import game.components.ToolCard;

import java.util.ArrayList;
import java.util.List;

public class SetupMatch{
    // inizializza il gioco all'inizio (estrazione carte obiettivo e tool,...)

    private ArrayList<PublicObjectiveCard> publicObjectiveCards;
    private ArrayList<PrivateObjectiveCard> privateObjectiveCards;
    private ToolCard toolCards[];
    private Die dies[];

    //crea randomicamente 3 carte obiettivo pubblico
    public ArrayList<PublicObjectiveCard> choosePublicObjectives(){
        publicObjectiveCards = new ArrayList<PublicObjectiveCard>();



        //TODO: implemetare scelta randomica carte;
        return(publicObjectiveCards);
    }

    public ArrayList<PrivateObjectiveCard> choosePrivateObjectives(){
        privateObjectiveCards = new ArrayList<PrivateObjectiveCard>();

        //TODO: implementare scelta randomica;
        return privateObjectiveCards;
    }

}
