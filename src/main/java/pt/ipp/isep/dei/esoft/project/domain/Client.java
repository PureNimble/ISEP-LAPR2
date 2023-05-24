package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The User class represents a user and extends the Person class.
 */
public class Client extends Person {

    /**
     * Constructs a User object with the specified email, passport number, tax number, name, address, and phone number.
     *
     * @param email          the email address of the user.
     * @param passportNumber the passport number of the user.
     * @param taxNumber      the tax number of the user.
     * @param name           the name of the user.
     * @param address        the address of the user.
     * @param phoneNumber    the phone number of the user.
     */
    public Client(String email, int passportNumber, int taxNumber, String name, Address address, int phoneNumber) {
        super(email, passportNumber, taxNumber, name, address, phoneNumber);
    }

    /**
     * Constructs a User object with the specified email, passport number, tax number, name, and phone number.
     *
     * @param email          the email address of the user.
     * @param passportNumber the passport number of the user.
     * @param taxNumber      the tax number of the user.
     * @param name           the name of the user.
     * @param phoneNumber    the phone number of the user.
     */
    public Client(String email, int passportNumber, int taxNumber, String name, int phoneNumber) {
        super(email, passportNumber, taxNumber, name, phoneNumber);
    }

    /**
     * Compares the email of the User with the specified email.
     *
     * @param email the email to compare with.
     * @return the result of the comparison. Returns 0 if the emails are equal, a value less than 0 if the email of the User is less than the specified email, and a value greater than 0 if the email of the User is greater than the specified email.
     */
    public int compare(String email) {
        return super.compare(email);
    }


    public String getEmail(){
        return super.getEmail();
    }

    public String getName() { return  super.getName(); }
}

