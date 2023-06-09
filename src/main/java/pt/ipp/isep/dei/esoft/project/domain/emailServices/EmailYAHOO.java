package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.util.regex.Pattern;

public class EmailYAHOO {
    private static final String YAHOO_ADDRESS_REGEX = "^([a-zA-Z0-9]+[.-])*[a-zA-Z0-9]+@yahoo\\.com$";

    private String emailAddress;

    public EmailYAHOO(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isValidYahooAddress() {
        Pattern pattern = Pattern.compile(YAHOO_ADDRESS_REGEX);
        return pattern.matcher(emailAddress).matches();
    }
}