package resources.writers;

import com.google.gson.Gson;
import io.FileHandler;
import mvc.model.objects.*;
import mvc.model.objects.Window;
import mvc.model.objects.enums.WindowPattern;
import resources.ResourceFileInfo;
import resources.ResourceRetriever;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WindowsWriter {
    //Creatore di risorse di finestre

    public static void main(String[] args) {
            //Crea finestre
            List<Window> windows = new ResourceRetriever().createWindows();

            //Converte e serializza
            Gson gson = new Gson();
            String text =  gson.toJson(windows);

            FileHandler fileHandler = new FileHandler();
            fileHandler.fileWrite(ResourceFileInfo.RESOURCE_FILES_PATH + "/" + ResourceFileInfo.WINDOWS_FILE_NAME, text);

    }
}
