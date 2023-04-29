package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

/**
 * The Employee class represents an employee in the system.
 * It extends the User class and adds a reference to the Store the employee works at and the roles they play.
 */
public class Employee extends User {

    private final Store store;
    private List<Role> roles;

    /**
     * Constructs an Employee object with the specified email, passport number, tax number, name, phone number, store,
     * roles, and address.
     *
     * @param email          the email of the employee
     * @param passportNumber the passport number of the employee
     * @param taxNumber      the tax number of the employee
     * @param name           the name of the employee
     * @param phoneNumber    the phone number of the employee
     * @param store          the store where the employee works
     * @param roles          the roles played by the employee
     * @param address        the address of the employee
     */
    public Employee(String email, int passportNumber, int taxNumber, String name, int phoneNumber, Store store,
                    List<Role> roles, Address address) {
        super(email, passportNumber, taxNumber, name, address, phoneNumber);
        this.store = store;
        this.roles = roles;
    }

    /**
     * Returns the Store where the employee works.
     *
     * @return the Store where the employee works
     */
    public Store getStore() {
        return store;
    }

    /**
     * Returns the list of roles played by the employee.
     *
     * @return the list of roles played by the employee
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Returns a string representation of the Employee object.
     *
     * @return a string representation of the Employee object
     */
    public String toString() {
        return String.format(super.toString() + "\nfuncionário da %s que desempenha o papel de %s", store.toString(), roles.toString());
    }

    /**
     * Compares this Employee object to the specified object. The result is true if and only if the argument is not null
     * and is an Employee object that has the same email, passport number, tax number, name, phone number, store,
     * and roles as this Employee object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return store.equals(employee.store) && roles.equals(employee.roles);
    }

    /**
     * Returns the hash code value for this Employee object.
     *
     * @return the hash code value for this Employee object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), store, roles);
    }
}
