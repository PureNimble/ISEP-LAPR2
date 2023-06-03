package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The {@code Store} class represents a store with a designation, an ID, an address, an email, and a phone number.
 * It also has methods to create an employee and add it to the store.
 */
public class Store {

    /**

     The designation of the store.
     */
    private String designation;
    /**

     The ID of the store.
     */
    private int id;
    /**

     The address of the store.
     */
    private Address address;
    /**

     The email of the store.
     */
    private String email;
    /**

     The phone number of the store.
     */
    private long phoneNumber;

    /**

     The number of properties of the store
     */
    private int listing;
    

    /**
     * Constructs a {@code Store} object with a designation, an ID, an address, a phone number, and an email.
     *
     * @param designation the designation of the store
     * @param id          the ID of the store
     * @param address     the address of the store
     * @param phoneNumber the phone number of the store
     * @param email       the email of the store
     */
    public Store(String designation, int id, Address address, long phoneNumber, String email, int listing) {
        this.designation = designation;
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.listing = listing;
    }

    /**
     * Constructs an empty {@code Store} object.
     */
    public Store() {
    }

    /**
     * Returns the ID of the store.
     *
     * @return the ID of the store
     */
    public int getId() {
        return id;
    }

    /**
     * Sets designation.
     *
     * @param designation the designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the address of the store.
     *
     * @return the address of the store
     */
    public Address getAddress() {
        return address;
    }

    /**

     Returns a string representation of the store.
     @return a string representation of the store
     */
    public String toString() {
        return String.format("Store: %s %s located at %s with: %s properties", id, designation, address.toString(), listing);
    }

    /**
     * Creates an employee with the given information and adds it to the store, if the employee is not already in the
     * store.
     *
     * @param email          the email of the employee
     * @param name           the name of the employee
     * @param phone          the phone number of the employee
     * @param roles          the roles of the employee
     * @param store          the store where the employee will work
     * @param address        the address of the employee
     * @param passportNumber the passport number of the employee
     * @param taxNumber      the tax number of the employee
     * @return an {@code Optional} containing the created employee, or an empty {@code Optional} if the employee couldnot be added to the store
     */
    public Optional<Employee> createEmployee(String email, String name,
                                             long phone, List<Role> roles, Store store, Address address, int passportNumber, int taxNumber) {

        Optional<Employee> optionalValue = Optional.empty();

        Employee employee = new Employee(email, passportNumber, taxNumber, name, phone, store, roles, address);

        if (addEmployee(employee)) {
            optionalValue = Optional.of(employee);

        }
        return optionalValue;
    }

    /**

     Adds an employee to the list of employees in the store.
     @param employee The employee object to add to the store.
     @return True if the employee is added to the store, false otherwise.
     */
    private boolean addEmployee(Employee employee) {
        boolean success = false;
        RegisterEmployeeController controller = new RegisterEmployeeController();
        List<Employee> employees = controller.getEmployee();
        if (validate(employee)) {
// A clone of the employee is added to the list of employees, to avoid side effects and outside manipulation.
            success = employees.add(employee);
        }
        return success;
    }
    /**

     Validates if an employee already exists in the store.
     @param employee The employee object to validate.
     @return True if the employee does not exist in the store, false otherwise.
     */
    private boolean validate(Employee employee) {
        return employeesDoNotContain(employee);
    }
    /**

     Checks if the list of employees in the store contains an employee object.
     @param employee The employee object to check.
     @return True if the employee does not exist in the store, false otherwise.
     */
    private boolean employeesDoNotContain(Employee employee) {
        RegisterEmployeeController controller = new RegisterEmployeeController();
        return !controller.getEmployee().contains(employee);
    }
    /**

     Overrides the equals method to compare store objects based on their id fields.
     @param o The object to compare.
     @return True if the object is a store object and has the same id field, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return this.id == store.id;
    }
    /**

     Overrides the hashCode method to calculate the hash code of a store object based on its designation field.
     @return The hash code of the store object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    /**
     * Gets the designation of the store.
     *
     * @return The designation of the store.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Gets listing.
     *
     * @return
     */
    public int getListing() {
        return listing;
    }

    /**
     * Sets listing.
     *
     * @param listing
     */
    public void setListing(int listing) {
        this.listing = listing;
    }
}
