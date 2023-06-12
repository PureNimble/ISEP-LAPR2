package pt.ipp.isep.dei.esoft.project.domain.emailServices;

public class EmailGMAIL {
    public boolean isValid(String email) {
        String domain = getEmailDomain(email);
        return domain.equals("gmail.com");
    }

    private String getEmailDomain(String email) {
        int atIndex = email.lastIndexOf("@");
        if (atIndex != -1) {
            return email.substring(atIndex + 1);
        }
        throw new IllegalArgumentException("Invalid email address: " + email);
    }
}
