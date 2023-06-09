package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.util.regex.Pattern;

public class EmailHOTMAIL {
    private static final String HOTMAIL_ADDRESS_REGEX = "^([a-zA-Z0-9]+[.-])*[a-zA-Z0-9]+@hotmail\\.com$";

    private String emailAddress;

    public EmailHOTMAIL(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isValidHotmailAddress() {
        Pattern pattern = Pattern.compile(HOTMAIL_ADDRESS_REGEX);
        return pattern.matcher(emailAddress).matches();
    }
}