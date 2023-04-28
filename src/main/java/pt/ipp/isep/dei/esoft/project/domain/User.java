package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 The User class represents a user with personal information and contact details.
 */
public class User {
    private String email; // The email address of the user.
    private int passportNumber; // The passport number of the user.
    private int taxNumber; // The tax number of the user.
    private String name; // The name of the user.
    private Address address; // The address of the user.
    private int phoneNumber; // The phone number of the user.

    /**
     * Constructs a new User object with an email, passport number, tax number, name, address, and phone number.
     *
     * @param email          The email address of the user.
     * @param passportNumber The passport number of the user.
     * @param taxNumber      The tax number of the user.
     * @param name           The name of the user.
     * @param address        The address of the user.
     * @param phoneNumber    The phone number of the user.
     */
    public User(String email, int passportNumber, int taxNumber, String name, Address address, int phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a new User object with an email, passport number, tax number, name, and phone number.
     *
     * @param email          The email address of the user.
     * @param passportNumber The passport number of the user.
     * @param taxNumber      The tax number of the user.
     * @param name           The name of the user.
     * @param phoneNumber    The phone number of the user.
     */
    public User(String email, int passportNumber, int taxNumber, String name, int phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the passport number of the user.
     *
     * @return The passport number of the user.
     */
    public int getPassportNumber() {
        return passportNumber;
    }

    /**
     * Returns the tax number of the user.
     *
     * @return The tax number of the user.
     */
    public int getTaxNumber() {
        return taxNumber;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the address of the user.
     *
     * @return The address of the user.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string representation of the user.
     */
    public String toString() {
        return String.format("%s resident em %s que possui como documentos de identificação %s %s de email:%s e de numero de telefone %s , ", name, address, passportNumber, taxNumber, email, phoneNumber);
    }

    /**
     * Determines whether the specified object is equal to this User object.
     *
     * @param o the object to compare to this User object
     * @return true if the specified object is equal to this User object; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return passportNumber == user.passportNumber && taxNumber == user.taxNumber && phoneNumber == user.phoneNumber && email.equals(user.email) && name.equals(user.name) && address.equals(user.address);
    }

    /**
     * Returns a hash code value for this User object.
     *
     * @return a hash code value for this User object
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, passportNumber, taxNumber, name, address, phoneNumber);
    }

    /**
     * Compares the email of this User object with the specified email.
     *
     * @param email the email to compare to the email of this User object
     * @return 0 if the specified email is equal to the email of this User object; 1 otherwise
     */
    public int compare(String email) {
        if (email.equals(this.email)) {
            return 0;
        }
        return 1;
    }

    /**
     * Creates and returns a new User object that is a copy of this User object.
     *
     * @return a new User object that is a copy of this User object
     */
    public User clone() {
        return new User(this.email, this.passportNumber, this.taxNumber, this.name, this.address, this.phoneNumber);
    }
}
