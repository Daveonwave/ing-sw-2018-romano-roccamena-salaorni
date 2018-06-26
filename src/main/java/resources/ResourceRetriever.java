package resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.ToolCard;
import mvc.model.objects.Window;
import util.FileHandler;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResourceRetriever {
    //Ottiene oggetti partita da file

    //Lettura da file oggetti partita
    public List<PrivateObjectiveCard> retrievePrivateObjectiveCards() throws IOException {
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PRIVATE_OBJECTIVE_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        return gson.fromJson(jsonFile, founderListType);
    }
    public List<PublicObjectiveCard> retrievePublicObjectiveCards() throws IOException {
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PUBLIC_OBJECTIVE_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        return gson.fromJson(jsonFile, founderListType);
    }
    public List<ToolCard> retrieveToolCards() throws IOException {
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<ToolCard>>(){}.getType();
        return gson.fromJson(jsonFile, founderListType);
    }
    public List<Window> retrieveWindows() throws IOException {
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();
        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME);
        Type founderListType = new TypeToken<ArrayList<Window>>(){}.getType();
        return gson.fromJson(jsonFile, founderListType);
    }

}
