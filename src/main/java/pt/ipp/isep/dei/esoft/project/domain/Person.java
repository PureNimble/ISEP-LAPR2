package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a person with identifying information such as email, passport and tax numbers,
 * name, address, and phone number.
 */
public class Person {

    private String email;
    private int passportNumber;
    private int taxNumber;
    private String name;
    private Address address;
    private long phoneNumber;

    /**
     * Constructs a Person object with all identifying information.
     *
     * @param email          The person's email address.
     * @param passportNumber The person's passport number.
     * @param taxNumber      The person's tax number.
     * @param name           The person's name.
     * @param address        The person's address.
     * @param phoneNumber    The person's phone number.
     */
    public Person(String email, int passportNumber, int taxNumber, String name, Address address, long phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a Person object with minimal identifying information.
     *
     * @param email          The person's email address.
     * @param passportNumber The person's passport number.
     * @param taxNumber      The person's tax number.
     * @param name           The person's name.
     * @param phoneNumber    The person's phone number.
     */
    public Person(String email, int passportNumber, int taxNumber, String name, long phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**

     Returns a String representation of this Person.
     @return a String representation of this Person.
     */
    public String toString(){
        return String.format("%s resident in %s that possess as identifying documents the passport, whose number is: %s, the tax number, which is: %s, the email: %s and the phone number: %s , ",name,address,passportNumber,taxNumber,email,phoneNumber);
    }
    /**

     Determines whether two Person objects are equal based on their identifying information.
     @param o the object to compare with this Person.
     @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return passportNumber == person.passportNumber && taxNumber == person.taxNumber && phoneNumber == person.phoneNumber && email.equals(person.email) && name.equals(person.name) && address.equals(person.address);
    }
    /**

     Generates a hash code for this Person based on its identifying information.
     @return a hash code for this Person.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, passportNumber, taxNumber, name, address, phoneNumber);
    }

    /**
     * Compares the email of this Person with a given email.
     *
     * @param email the email to compare with this Person's email.
     * @return 0 if the emails are equal, 1 otherwise.
     */
    public int compare(String email){
        if (email.equals(this.email)){
            return 0;
        }
        return 1;
    }
}