package resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mvc.builders.MatchBuilder;
import mvc.model.objects.*;
import mvc.model.objects.enums.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResourcesRetriever {
    //Ottiene gli oggetti da file

    private static final String WINDOWS_PATH = ""; // esempio di path C:\\Users\\utente\\Desktop\\ciao.txt
    private static final String TOOLCARDS_PATH = "";
    private static final String PUBLICOBJECTIVECARDS_PATH = "";
    private static final String PRIVATEOBJECTIVECARDS_PATH = "";

    //Costruttori
    public ResourcesRetriever() {}

    //Deserializza da json le finestre TODO: implementazione
    public List<Window> retrieveWindows(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.resourcesFileReader(WINDOWS_PATH);

        Type founderListType = new TypeToken<ArrayList<Window>>(){}.getType();
        List<Window> result = gson.fromJson(jsonFile, founderListType);

        return result;
    }

    //Deserializza da json le carte strumento TODO: implementazione
    public List<ToolCard> retrieveToolCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.resourcesFileReader(TOOLCARDS_PATH);

        Type founderListType = new TypeToken<ArrayList<ToolCard>>(){}.getType();
        List<ToolCard> result = gson.fromJson(jsonFile, founderListType);

        return result;

    }

    //Deserializza da json gli obiettivi pubblici TODO: implementazione
    public List<PublicObjectiveCard> retrievePublicObjectiveCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.resourcesFileReader(PUBLICOBJECTIVECARDS_PATH);

        Type founderListType = new TypeToken<ArrayList<PublicObjectiveCard>>(){}.getType();
        List<PublicObjectiveCard> result = gson.fromJson(jsonFile, founderListType);

        return result;
    }

    //Deserializza da json gli obiettivi privati TODO: implementazione
    public List<PrivateObjectiveCard> retrievePrivateObjectiveCards(){
        FileHandler fileHandler = new FileHandler();
        Gson gson = new Gson();

        String jsonFile = fileHandler.resourcesFileReader(PRIVATEOBJECTIVECARDS_PATH);

        Type founderListType = new TypeToken<ArrayList<PrivateObjectiveCard>>(){}.getType();
        List<PrivateObjectiveCard> result = gson.fromJson(jsonFile, founderListType);

        return result;

    }


}
