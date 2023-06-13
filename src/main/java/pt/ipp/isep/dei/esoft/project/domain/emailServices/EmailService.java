package pt.ipp.isep.dei.esoft.project.domain.emailServices;

public interface EmailService {
    void sendEmail(String email, String subject, String body);
}
