package resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

/**
 * Reader from file of match elements
 */
public class ResourceRetriever {
    //Ottiene oggetti partita da file

    //Lettura da file oggetti partita

    /**
     * Deserializes and reads private objective cards from privates.json
     * @return list with all public objectives cards
     * @throws IOException
     */
    public List<PrivateObjectiveCard> retrievePrivateObjectiveCards() {
        FileHandler fileHandler = new FileHandler();

        String jsonFile = null;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PrivateObjectiveCard.class, new PrivateObjectiveCardAdapter());
        Gson gson = gsonBuilder.create();

        try {
            jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/privatesAdapted.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


        //builder.registerTypeAdapter(PrivateObjectiveCard.class, )*/

        /*Todo: problema = quando si fa la deserializzazione (line 46) il metodo cerca una classe con un no-args
        *       constructor, ma PrivateObjectiveCard ha un costruttore con argomenti.
        *       SOLUZIONE: o usare un TypeAdapter di GSON oppure mettere un no-args constructor o rendere la classe
        *       PublicObjectiveCard non astratta.
        */

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        List<PrivateObjectiveCard> result = gson.fromJson(jsonFile, founderListType);
        return result;
    }


    /**
     * Deserializes and reads public objective cards from publics.json
     * @return list with all private objective cards
     * @throws IOException
     */
    public List<PublicObjectiveCard> retrievePublicObjectiveCards() throws IOException {
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PUBLIC_OBJECTIVE_CARDS_FILE_NAME + "adapter");

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        return gson.fromJson(jsonFile, founderListType);
    }

    /**
     * Deserializes and reads tool cards from tools.json
     * @return list with all tool cards
     * @throws IOException
     */
    public List<ToolCard> retrieveToolCards() throws IOException {
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME);

        Type founderListType = new TypeToken<ArrayList<ToolCard>>(){}.getType();
        return gson.fromJson(jsonFile, founderListType);
    }

    /**
     * Deserializes and reads windows from windows.json
     * @return list with all windows
     * @throws IOException
     */
    public List<Window> retrieveWindows() {
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();
        String jsonFile = null;
        try {
            jsonFile = fileHandler.fileRead(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type founderListType = new TypeToken<ArrayList<Window>>(){}.getType();
        return gson.fromJson(jsonFile, founderListType);
    }


}
