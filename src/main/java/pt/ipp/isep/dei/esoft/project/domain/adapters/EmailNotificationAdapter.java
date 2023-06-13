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

    static MessageRepository messageRepository = null;


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
        return isEmailDomainValid(email);
    }

    protected static boolean isEmailDomainValid(String email) {
        EmailDEI emailDEI = new EmailDEI();
        EmailGMAIL emailGMAIL = new EmailGMAIL();
        EmailHOTMAIL emailHOTMAIL = new EmailHOTMAIL();
        EmailYAHOO emailYAHOO = new EmailYAHOO();

        try {
            return emailDEI.isValid(email) ||
                    emailGMAIL.isValid(email) ||
                    emailHOTMAIL.isValid(email) ||
                    emailYAHOO.isValid(email);
        } catch (IllegalArgumentException e) {
            return false;
        }
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

}
