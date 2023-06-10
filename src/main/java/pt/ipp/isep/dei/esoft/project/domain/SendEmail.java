package pt.ipp.isep.dei.esoft.project.domain;

import java.io.FileWriter;
import java.io.IOException;

public class SendEmail {

    public static void createFile(String filename, String content) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(content);
            System.out.println("File created: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    public static void writeToFile(String clientEmail, String content) {
        String filename = "Email - " + clientEmail + ".txt";
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            fileWriter.write(content);
            System.out.println("Content written to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}