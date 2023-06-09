    package pt.ipp.isep.dei.esoft.project.domain.emailServices;
    import java.util.regex.Pattern;

    public class EmailGMAIL {

        private static final String GMAIL_ADDRESS_REGEX = "^([a-zA-Z0-9]+[.-])*[a-zA-Z0-9]+@gmail\\.com$";

        private String emailAddress;

        public EmailGMAIL(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public boolean isValidGmailAddress() {
            Pattern pattern = Pattern.compile(GMAIL_ADDRESS_REGEX);
            return pattern.matcher(emailAddress).matches();
        }
    }
