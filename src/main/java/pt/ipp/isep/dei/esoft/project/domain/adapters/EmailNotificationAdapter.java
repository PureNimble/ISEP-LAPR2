package pt.ipp.isep.dei.esoft.project.domain.adapters;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.emailServices.*;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.*;
import java.util.Properties;

/**
 * The type Email notification adapter.
 */
public class EmailNotificationAdapter implements EmailNotification {

    /**
     * The Message repository.
     */
    static MessageRepository messageRepository = null;

    private static final String FILE_NAME = "EmailNotification.txt";


    /**
     * Send email.
     *
     * @param email   the email
     * @param subject the subject
     * @param body    the body
     */
    public static void sendEmail(String email, String subject, String body) {
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

    /**
     * Is valid email domain boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmailDomain(String email) {
        return isEmailDomainValid(email);
    }

    /**
     * Is email domain valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
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

    /**
     * Remove booking request.
     *
     * @param message the message
     */
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
