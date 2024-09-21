package pt.ipp.isep.dei.esoft.project.domain.emailServices;

/**
 * The interface Email service.
 */
public interface EmailService {
    /**
     * Send email.
     *
     * @param email   the email
     * @param subject the subject
     * @param body    the body
     */
    void sendEmail(String email, String subject, String body);
}
