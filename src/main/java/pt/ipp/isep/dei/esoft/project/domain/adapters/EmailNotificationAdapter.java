package pt.ipp.isep.dei.esoft.project.domain.adapters;

import pt.ipp.isep.dei.esoft.project.domain.EmailNotification;
import pt.ipp.isep.dei.esoft.project.domain.emailServices.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmailNotificationAdapter implements EmailNotification {
    private EmailDEI emailDEI;
    private EmailGMAIL emailGMAIL;
    private EmailHOTMAIL emailHOTMAIL;
    private EmailYAHOO emailYAHOO;

    public EmailNotificationAdapter(String deiEmailAddress, String gmailEmailAddress, String hotmailEmailAddress, String yahooEmailAddress) {
        this.emailDEI = new EmailDEI(deiEmailAddress);
        this.emailGMAIL = new EmailGMAIL(gmailEmailAddress);
        this.emailHOTMAIL = new EmailHOTMAIL(hotmailEmailAddress);
        this.emailYAHOO = new EmailYAHOO(yahooEmailAddress);
    }

    @Override
    public void sendEmail(String from, String to, String subject, String body) {
        String domain = getEmailDomain(to);

        switch (domain) {
            case "isep.ipp.pt":
                if (!emailDEI.isValidDEIAddress()) {
                    throw new IllegalArgumentException("Invalid DEI email address: " + to);
                }
                sendEmailViaDEI(from, to, subject, body);
                break;
            case "gmail.com":
                if (!emailGMAIL.isValidGmailAddress()) {
                    throw new IllegalArgumentException("Invalid Gmail address: " + to);
                }
                sendEmailViaGmail(from, to, subject, body);
                break;
            case "hotmail.com":
                if (!emailHOTMAIL.isValidHotmailAddress()) {
                    throw new IllegalArgumentException("Invalid Hotmail address: " + to);
                }
                sendEmailViaHotmail(from, to, subject, body);
                break;
            case "yahoo.com":
                if (!emailYAHOO.isValidYahooAddress()) {
                    throw new IllegalArgumentException("Invalid Yahoo address: " + to);
                }
                sendEmailViaYahoo(from, to, subject, body);
                break;
            default:
                throw new IllegalArgumentException("Unsupported email domain: " + domain);
        }
    }

    private String getEmailDomain(String email) {
        int atIndex = email.lastIndexOf("@");
        if (atIndex != -1) {
            return email.substring(atIndex + 1);
        }
        throw new IllegalArgumentException("Invalid email address: " + email);
    }

    private void sendEmailViaDEI(String from, String to, String subject, String body) {
        // Implement code to send the email using the DEI email service

        // Create the email content
        String emailContent = createEmailContent(from, to, subject, body);

        // Write the email content to a file
        String fileName = "Email - " + getEmailAddressWithoutDomain(to) + ".txt";
        writeToFile(fileName, emailContent);
    }

    private void sendEmailViaGmail(String from, String to, String subject, String body) {
        // Implement code to send the email using the Gmail email service

        // Create the email content
        String emailContent = createEmailContent(from, to, subject, body);

        // Write the email content to a file
        String fileName = "Email - " + getEmailAddressWithoutDomain(to) + ".txt";
        writeToFile(fileName, emailContent);
    }

    private void sendEmailViaHotmail(String from, String to, String subject, String body) {
        // Implement code to send the email using the Hotmail email service

        // Create the email content
        String emailContent = createEmailContent(from, to, subject, body);

        // Write the email content to a file
        String fileName = "Email - " + getEmailAddressWithoutDomain(to) + ".txt";
        writeToFile(fileName, emailContent);
    }

    private void sendEmailViaYahoo(String from, String to, String subject, String body) {
        // Implement code to send the email using the Yahoo email service

        // Create the email content
        String emailContent = createEmailContent(from, to, subject, body);

        // Write the email content to a file
        String fileName = "Email - " + getEmailAddressWithoutDomain(to) + ".txt";
        writeToFile(fileName, emailContent);
    }

    private String getEmailAddressWithoutDomain(String email) {
        int atIndex = email.lastIndexOf("@");
        if (atIndex != -1) {
            return email.substring(0, atIndex);
        }
        throw new IllegalArgumentException("Invalid email address: " + email);
    }

    private String createEmailContent(String from, String to, String subject, String body) {
        StringBuilder content = new StringBuilder();
        content.append("From: ").append(from).append("\n");
        content.append("To: ").append(to).append("\n");
        content.append("Subject: ").append(subject).append("\n");
        content.append("Body: ").append(body).append("\n");
        return content.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write email content to file: " + fileName, e);
        }
    }
}














