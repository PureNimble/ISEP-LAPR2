package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

/**
 * The interface Email notification.
 */
public interface EmailNotification {
    /**
     * Send email.
     *
     * @param email   the email
     * @param subject the subject
     * @param body    the body
     */
    static void sendEmail(String email, String subject, String body) {

    }
}
