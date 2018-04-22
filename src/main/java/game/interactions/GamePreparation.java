package game.interactions;

import game.components.*;
import game.components.base.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePreparation {
    // inizializza il gioco all'inizio (estrazione carte obiettivo e tool,...)
    private static final int ACTIVE_PUBLIC_OBJECTIVES = 3;
    private static final int ACTIVE_TOOL_CARDS = 3;
    private static final int DICE_OF_SINGLE_COLOR = 18;

    private ArrayList<PublicObjective> activePublicObjectiveCards;
    private ArrayList<PrivateObjective> activePrivateObjectiveCards;
    private ArrayList<WindowPattern> activeWindowPatterns;
    private ArrayList<Tool> activeToolCards;
    private ArrayList<Die> diceBag;

    //crea randomicamente 3 carte obiettivo pubblico
    public ArrayList<PublicObjective> choosePublicObjectives(){
        activePublicObjectiveCards = new ArrayList<PublicObjective>();
        int countExtracted = 1;

        while(countExtracted <= ACTIVE_PUBLIC_OBJECTIVES){
            PublicObjective extractedCard = PublicObjective.randomPublicObjective();

            if(!activePublicObjectiveCards.contains(extractedCard)){
                activePublicObjectiveCards.add(extractedCard);
                countExtracted++;
            }
        }
        return(activePublicObjectiveCards);
    }

    //crea randomicamente tante carte obiettivo privato quanti sono i giocatori
    public ArrayList<PrivateObjective> choosePrivateObjectives(int numberOfPlayers){
        activePrivateObjectiveCards = new ArrayList<PrivateObjective>();
        int countExtracted = 1;

        while(countExtracted <= numberOfPlayers){
            PrivateObjective extractedCard = PrivateObjective.randomPrivateObjective();

            if(!activePrivateObjectiveCards.contains(extractedCard)){
                activePrivateObjectiveCards.add(extractedCard);
                countExtracted++;
            }
        }
        return activePrivateObjectiveCards;
    }

    //crea randomicamente 3 carte tool da usare nel match
    public ArrayList<Tool> chooseToolCards(){
        activeToolCards = new ArrayList<Tool>();
        int countExtracted = 1;

        while(countExtracted < ACTIVE_TOOL_CARDS){
            Tool extractedCard = Tool.randomTool();

            if(!activeToolCards.contains(extractedCard)){
                activeToolCards.add(extractedCard);
                countExtracted++;
            }
        }
        return activeToolCards;
    }

    //crea randomicamente il numero necessario di window pattern (4 per giocatore)
    public ArrayList<WindowPattern> chooseWindowPatterns(int numberOfPlayers){
        activeWindowPatterns = new ArrayList<WindowPattern>();
        int countExtracted = 1;

        while(countExtracted <= numberOfPlayers * 4){
            WindowPattern extractedWindowPattern = WindowPattern.randomWindowPattern();

            if(!activeWindowPatterns.contains(extractedWindowPattern)){
                activeWindowPatterns.add(extractedWindowPattern);
                countExtracted++;
            }
        }
        return activeWindowPatterns;
    }

    //instanzia 90 dadi (18 per colore) con una shade casuale già data (emulando il sacchetto dei dadi)
    public ArrayList<Die> createDiceBag(){
        diceBag = new ArrayList<Die>();

        for(int i = 1; i <= DICE_OF_SINGLE_COLOR; i++){
            diceBag.add(new Die(DieColor.YELLOW, Shade.randomShade()));
            diceBag.add(new Die(DieColor.RED, Shade.randomShade()));
            diceBag.add(new Die(DieColor.GREEN, Shade.randomShade()));
            diceBag.add(new Die(DieColor.BLUE, Shade.randomShade()));
            diceBag.add(new Die(DieColor.PURPLE, Shade.randomShade()));
        }
        Collections.shuffle(diceBag);

        return diceBag;
    }





    public static void main(String[] args) {

        GamePreparation gamePreparation = new GamePreparation();
        ArrayList<Die> diceBag = gamePreparation.createDiceBag();

        for(Die dado : diceBag){
            System.out.println(dado.getColor().toString() + " " + dado.getShade().toString());
        }


    }
}
