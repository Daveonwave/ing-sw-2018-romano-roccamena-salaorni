package resources.writers;

import com.google.gson.Gson;
import mvc.creators.WindowsCreator;
import mvc.model.objects.Window;
import resources.ResourceFileInfo;
import util.FileHandler;

import java.io.IOException;
import java.util.List;

/**
 * Writes tool cards on memory
 */
public class WindowsWriter {
    //Creatore di risorse di finestre

    public static void main(String[] args) throws IOException {
            //Crea finestre
            List<Window> windows = WindowsCreator.createWindows();

            //Converte e serializza
            FileHandler fileHandler = new FileHandler();

            fileHandler.fileWriteObject(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME, windows);

    }
}
