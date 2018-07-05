package resources.writers;

import mvc.creators.ObjectivesCreator;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.PublicObjectiveCard;
import resources.ResourceFileInfo;
import util.FileHandler;

import java.io.IOException;
import java.util.List;

/**
 * Writes objective cards on memory
 */
public class ObjectivesWriter {

    public static void main(String[] args) throws IOException {
        //Crea carte
        List<PrivateObjectiveCard> privates = ObjectivesCreator.createPrivateObjectiveCards();
        List<PublicObjectiveCard> publics = ObjectivesCreator.createPublicObjectiveCards();

        //Converte e serializza
        FileHandler fileHandler = new FileHandler();

        fileHandler.fileWriteObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PRIVATE_OBJECTIVE_CARDS_FILE_NAME, privates);
        fileHandler.fileWriteObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PUBLIC_OBJECTIVE_CARDS_FILE_NAME, publics);
    }
}
