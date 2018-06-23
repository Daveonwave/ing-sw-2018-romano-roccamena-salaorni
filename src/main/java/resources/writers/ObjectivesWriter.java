package resources.writers;

import com.google.gson.Gson;
import mvc.creators.ObjectivesCreator;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.PublicObjectiveCard;
import resources.ResourceFileInfo;
import util.FileHandler;

import java.io.IOException;
import java.util.List;

public class ObjectivesWriter {

    public static void main(String[] args) throws IOException {
        //Crea finestre
        List<PrivateObjectiveCard> privates = ObjectivesCreator.createPrivateObjectiveCards();
        List<PublicObjectiveCard> publics = ObjectivesCreator.createPublicObjectiveCards();

        //Converte e serializza
        Gson gson = new Gson();
        String text =  gson.toJson(privates);

        FileHandler fileHandler = new FileHandler();
        fileHandler.fileWrite(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PRIVATE_OBJECTIVE_CARDS_FILE_NAME, text);

        text =  gson.toJson(publics);
        fileHandler.fileWrite(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PUBLIC_OBJECTIVE_CARDS_FILE_NAME, text);

    }
}
