package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.util.regex.Pattern;

public class EmailDEI {
    private static final String DEI_ADDRESS_REGEX = "^([a-zA-Z0-9]+[.-])*[a-zA-Z0-9]+@isep\\.ipp\\.pt$";

    private String emailAddress;

    public EmailDEI(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isValidDEIAddress() {
        Pattern pattern = Pattern.compile(DEI_ADDRESS_REGEX);
        return pattern.matcher(emailAddress).matches();
    }
}