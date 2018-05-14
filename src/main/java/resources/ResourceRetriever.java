package resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.FileHandler;
import mvc.model.objects.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResourceRetriever {
    //Ottiene gli oggetti da file

    //Costruttori
    public ResourceRetriever() {}

    //TODO: implementazione
    //Ottiene i vari oggetti di una partita
    public List<PublicObjectiveCard> retrievePublicObjectiveCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PUBLIC_OBJECTIVE_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<PublicObjectiveCard>>(){}.getType();
        List<PublicObjectiveCard> result = gson.fromJson(jsonFile, founderListType);

        return result;
    }
    public List<PrivateObjectiveCard> retrievePrivateObjectiveCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PRIVATE_OBJECTIVE_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        List<PrivateObjectiveCard> result = gson.fromJson(jsonFile, founderListType);

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
