package util;

import java.io.*;

public class FileHandler {
    //Gesttore di scrittura e lettura da file

    //Scrive su file
    public void fileWrite(String path, String text) throws IOException{

        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        try(FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(text);

        }
    }
    //Legge da file
    public String fileRead(String path) throws IOException{

        try(FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            StringBuilder result = new StringBuilder();

            while((line = bufferedReader.readLine()) != null){
                result.append(line);
                result.append(System.lineSeparator());
            }
            return result.toString();
        }

    }

}
