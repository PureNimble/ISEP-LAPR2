package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SendSms {
    public void createFile (String fileName) {
        try {
            File myObj = new File("filename.txt");
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
