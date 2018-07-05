package resources.writers;

import com.google.gson.Gson;
import mvc.creators.ToolsCreator;
import mvc.model.objects.ToolCard;
import resources.ResourceFileInfo;
import util.FileHandler;

import java.io.IOException;
import java.util.List;

/**
 * Writes tool cards on memory
 */
public class ToolsWriter {
    //Creatore di carte strumento

    public static void main(String[] args) throws IOException {
        //Crea finestre
        List<ToolCard> cards = ToolsCreator.createToolCards();

        //Converte e serializza
        FileHandler fileHandler = new FileHandler();

        fileHandler.fileWriteObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME, cards);
    }
}
