package resources;

import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.ToolCard;
import mvc.model.objects.Window;
import util.FileHandler;

import java.io.IOException;
import java.util.List;

/**
 * Reader from file of match elements
 */
public class ResourceRetriever {
    //Ottiene oggetti partita da file

    /**
     * Deserializes and reads private objective cards from privates.ser
     * @return list with all public objectives cards
     * @throws IOException
     */
    public List<PrivateObjectiveCard> retrievePrivateObjectiveCards() throws IOException, ClassNotFoundException {
        FileHandler fileHandler = new FileHandler();

        return (List<PrivateObjectiveCard>) fileHandler.fileReadObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PRIVATE_OBJECTIVE_CARDS_FILE_NAME);
    }

    /**
     * Deserializes and reads public objective cards from publics.ser
     * @return list with all private objective cards
     * @throws IOException
     */
    public List<PublicObjectiveCard> retrievePublicObjectiveCards() throws IOException, ClassNotFoundException {
        FileHandler fileHandler = new FileHandler();

        return (List<PublicObjectiveCard>) fileHandler.fileReadObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.PUBLIC_OBJECTIVE_CARDS_FILE_NAME);
    }

    /**
     * Deserializes and reads tool cards from tools.ser
     * @return list with all tool cards
     * @throws IOException
     */
    public List<ToolCard> retrieveToolCards() throws IOException, ClassNotFoundException {
        FileHandler fileHandler = new FileHandler();

        return (List<ToolCard>) fileHandler.fileReadObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.TOOL_CARDS_FILE_NAME);
    }

    /**
     * Deserializes and reads windows from windows.ser
     * @return list with all windows
     * @throws IOException
     */
    public List<Window> retrieveWindows() throws IOException, ClassNotFoundException {
        FileHandler fileHandler = new FileHandler();

        return (List<Window>) fileHandler.fileReadObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME);
    }
}
