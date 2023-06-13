package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.io.*;
import java.util.Properties;

public class HotmailService implements EmailService {

    private static final String FILE_NAME = "HotmailNotification.txt";

    public void sendEmail(String email, String subject, String body) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Get the agent's email from the properties file
        String from = properties.getProperty("from");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_NAME, true))) {
            writer.println("From: " + from);
            writer.println("To: " + email);
            writer.println("Subject: " + subject);
            writer.println("Body: " + body);
            writer.println();
            writer.println("-----------------------------------------------------------------------");
            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write email to file: " + e.getMessage());
        }
    }
}
