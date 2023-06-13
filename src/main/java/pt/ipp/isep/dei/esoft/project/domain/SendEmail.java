package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The type Send email.
 */
public class SendEmail {
    /**
     * Create file.
     *
     * @param fileName the file name
     */
    public void createFile(String fileName) {
        try {
            File myObj = new File(fileName); // Use the fileName parameter here
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Write file.
     *
     * @param fileName    the file name
     * @param textToWrite the text to write
     */
    public void writeFile (String fileName, String textToWrite) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(textToWrite);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}