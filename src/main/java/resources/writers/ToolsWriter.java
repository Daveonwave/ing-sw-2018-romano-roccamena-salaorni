package resources.writers;

import com.google.gson.Gson;
import util.FileHandler;
import mvc.model.objects.ToolCard;
import mvc.model.objects.toolcards.*;
import resources.ResourceFileInfo;

import java.util.ArrayList;
import java.util.List;

public class ToolsWriter {
    //Creatore di carte strumento

    public static void main(String[] args) {
        List<ToolCard> toolCards = new ArrayList<ToolCard>();

        //Crea ogni carta strumento
        toolCards.add(new AlesatorePerLaminaDiRame());
        toolCards.add(new DiluentePerPastaCalda());
        toolCards.add(new Lathekin());
        toolCards.add(new Martelletto());
        toolCards.add(new PennelloPerEglomise());
        toolCards.add(new PennelloPerPastaSalda());
        toolCards.add(new PinzaSgrossatrice());
        toolCards.add(new RigaDiSughero());
        toolCards.add(new TaglierinaCircolare());    //TODO: aggiungi ultime 3

        //Converte e serializza
        Gson gson = new Gson();
        String text =  gson.toJson(toolCards);

        FileHandler fileHandler = new FileHandler();
        fileHandler.fileWrite(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME, text);
    }
}
