package pt.ipp.isep.dei.esoft.project.domain.adapters;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.emailServices.*;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class EmailNotificationAdapter implements EmailNotification {

    public static void sendEmail(String email, String subject, String body) {
        String fileName = "EmailNotification - " + email + ".txt";
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Get the agent's email from the properties file
        String from = properties.getProperty("from");
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("From: " + from);
            writer.println("To: " + email);
            writer.println("Subject: " + subject);
            writer.println("Body: " + body);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write email to file: " + e.getMessage());
        }
    }
    public static boolean isValidEmailDomain(String email) {
        EmailDomainValidator[] validators = {
                new EmailDEI(),
                new EmailGMAIL(),
                new EmailHOTMAIL(),
                new EmailYAHOO()
        };

        for (EmailDomainValidator validator : validators) {
            try {
                if (validator.isValid(email)) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        return false;
    }

    public static void removeBookingRequest(Message message) {
        MessageRepository messageRepository = getMessageRepository();
        messageRepository.removeMessage(message);
    }

    private static MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            Repositories repositories = Repositories.getInstance();

            messageRepository = repositories.getMessageRepository();
        }
        return messageRepository;
    }

    static MessageRepository messageRepository = null;



}
