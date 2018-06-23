package resources.writers;

import com.google.gson.Gson;
import mvc.creators.WindowsCreator;
import mvc.model.objects.Window;
import resources.ResourceFileInfo;
import util.FileHandler;

import java.io.IOException;
import java.util.List;

public class WindowsWriter {
    //Creatore di risorse di finestre

    public static void main(String[] args) throws IOException {
            //Crea finestre
            List<Window> windows = WindowsCreator.createWindows();

            //Converte e serializza
            Gson gson = new Gson();
            String text =  gson.toJson(windows);

            FileHandler fileHandler = new FileHandler();
            fileHandler.fileWrite(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME, text);

    }
}
