package io;

import java.io.*;

public class FileHandler {
    //Gesttore di scrittura e lettura da file

    //Scrive su file
    public void fileWrite(String path, String text) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(text);
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
    //Legge da file
    public String fileRead(String path){
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        StringBuilder result =  null;

        try{
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            result = new StringBuilder();

            while((line = bufferedReader.readLine()) != null){
                result.append(line);
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
