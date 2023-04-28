package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 The Employee class represents an employee object that works at a specific store and has a specific role.

 It extends the Person class and inherits its properties (email, name, phoneNumber, address, taxNumber, passportNumber).
 */
public class Employee {

    /**

     The store where the employee works.
     */
    private final Store store;
    /**

     The role that the employee performs at the store.
     */
    private final Role role;
    /**

     Creates a new Employee object with the given parameters.
     @param email the employee's email
     @param name the employee's name
     @param store the store where the employee works
     @param phoneNumber the employee's phone number
     @param role the employee's role at the store
     @param address the employee's address
     @param taxNumber the employee's tax number
     @param passportNumber the employee's passport number
     */
    public Employee(String email, String name, Store store, int phoneNumber, Role role, Address address, int taxNumber, int passportNumber) {
        super(email, passportNumber, taxNumber, name, address, phoneNumber);
        this.store = store;
        this.role = role;
    }
    /**

     Returns the store where the employee works.
     @return the store where the employee works
     */
    public Store getStore() {
        return store;
    }
    /**

     Returns the role that the employee performs at the store.
     @return the employee's role at the store
     */
    public Role getRole() {
        return role;
    }
    /**

     Returns a string representation of the Employee object.
     @return a string representation of the Employee object
     */
    @Override
    public String toString() {
        return String.format(super.toString() + "funcionário da loja %s que desempenha o papel de %s", store.getDesignation(), role.getDescription());
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o the reference object with which to compare
     @return true if this object is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return store.equals(employee.store) && role.equals(employee.role);
    }
    /**

     Returns a hash code value for the object.
     @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), store, role);
    }
}
