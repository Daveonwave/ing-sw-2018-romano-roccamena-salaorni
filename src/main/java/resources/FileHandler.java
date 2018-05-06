package resources;

import javax.imageio.event.IIOWriteProgressListener;
import java.io.*;

public class FileHandler {
    //Gestisce la scrittura e lettura da file

    //Scrive su file
    public void resourcesFileWriter(String filePath, String textToWrite) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(textToWrite);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(bufferedWriter != null)
                    bufferedWriter.close();

                if(fileWriter != null)
                    fileWriter.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    //Legge da file e restituisce una stringa
    public String resourcesFileReader(String filePath){
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        StringBuilder result =  null;

        try{
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            result = new StringBuilder();

            while((line = bufferedReader.readLine()) != null){
                result.append(line);
                //result.append(System.lineSeparator());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

}
