package resources.writers;

import com.google.gson.Gson;
import mvc.creators.ToolsCreator;
import mvc.model.objects.Window;
import resources.ResourceRetriever;
import util.FileHandler;
import mvc.model.objects.ToolCard;
import mvc.model.objects.toolcards.*;
import resources.ResourceFileInfo;

import java.util.ArrayList;
import java.util.List;

public class ToolsWriter {
    //Creatore di carte strumento

    public static void main(String[] args) {
        //Crea finestre
        List<ToolCard> cards = ToolsCreator.createToolCards();

        //Converte e serializza
        Gson gson = new Gson();
        String text =  gson.toJson(cards);

        FileHandler fileHandler = new FileHandler();
        fileHandler.fileWrite(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME, text);
    }
}
