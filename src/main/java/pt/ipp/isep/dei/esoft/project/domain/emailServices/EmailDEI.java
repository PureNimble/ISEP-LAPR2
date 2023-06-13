package pt.ipp.isep.dei.esoft.project.domain.emailServices;

/**
 * The type Email dei.
 */
public class EmailDEI {

    /**
     * Is valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean isValid(String email) {
        String domain = getEmailDomain(email);
        return domain.equals("isep.ipp.pt");
    }

    private String getEmailDomain(String email) {
        int atIndex = email.lastIndexOf("@");
        if (atIndex != -1) {
            return email.substring(atIndex + 1);
        }
        throw new IllegalArgumentException("Invalid email address: " + email);
    }
}
