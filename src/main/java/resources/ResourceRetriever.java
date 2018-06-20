package resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.ToolCard;
import mvc.model.objects.Window;
import util.FileHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResourceRetriever {
    //Ottiene oggetti partita da file

    //Costruttori
    public ResourceRetriever() {}

    //Lettura da file oggetti partita
    public List<PrivateObjectiveCard> retrievePrivateObjectiveCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PRIVATE_OBJECTIVE_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        List<PrivateObjectiveCard> result = gson.fromJson(jsonFile, founderListType);

        return result;

    }
    public List<PublicObjectiveCard> retrievePublicObjectiveCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PUBLIC_OBJECTIVE_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        List<PublicObjectiveCard> result = gson.fromJson(jsonFile, founderListType);

        return result;

    }
    public List<ToolCard> retrieveToolCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<ToolCard>>(){}.getType();
        List<ToolCard> result = gson.fromJson(jsonFile, founderListType);

        return result;

    }

    public List<Window> retrieveWindows(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();
        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME);
        Type founderListType = new TypeToken<ArrayList<Window>>(){}.getType();
        List<Window> result = gson.fromJson(jsonFile, founderListType);

        return result;
    }
}
