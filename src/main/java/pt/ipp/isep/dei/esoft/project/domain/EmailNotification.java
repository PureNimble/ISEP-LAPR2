package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The interface Email notification.
 */
public interface EmailNotification {
    void sendEmail(String from, String to, String subject, String body);
}
