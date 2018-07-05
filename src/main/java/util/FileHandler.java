package util;

import java.io.*;

/**
 * Input/output file handler
 */
public class FileHandler {
    //Gestore di scrittura e lettura da file

    /**
     * Write text on a file given its path
     * @param path Path of the file
     * @param text Text to write
     * @throws IOException File communicaion error
     */
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
    /**
     * Write object on a file given its path
     * @param path Path of the file
     * @param obj Object to save
     * @throws IOException File communication error
     */
    public void fileWriteObject(String path, Object obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(obj);

        oos.flush();
        oos.close();
        fos.close();
    }
    /**
     * Read text from a file given its path
     * @param path Path of the file
     * @return
     * @throws IOException File comunication error
     */
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
    /**
     * Read text from a file given its path
     * @param path Path of the file
     * @return
     * @throws IOException File communication error
     * @throws ClassNotFoundException File communication error
     */
    public Object fileReadObject(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);

        return ois.readObject();
    }
}
