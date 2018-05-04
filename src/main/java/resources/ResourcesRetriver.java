package resources;

import com.google.gson.Gson;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.ToolCard;
import mvc.model.objects.Window;
import mvc.model.objects.enums.*;

import java.util.List;

public class ResourcesRetriver {
    //Ottiene gli oggetti da file


    //TODO: implementazione
    public static List<Window> retriveWindows(List<WindowPattern> extractedWindows){


        Gson gson = new Gson();

        return null;
    }

    //TODO: implementazione
    public static List<ToolCard> retriveToolCards(List<Tool> extractedTools){
        return null;
    }

    //TODO: implementazione
    public static List<PublicObjectiveCard> retrivePublicObjectiveCards(List<PublicObjective> extractedPublicObjectives){
        return null;
    }

    //TODO: implementazione
    public static List<PrivateObjectiveCard> retrivePrivateObjectiveCards(List<PrivateObjective> extractedPrivateObjectives){
        return null;
    }
}
