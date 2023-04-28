package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**

 A class representing a store with a designation.
 */
public class Store {

    private String designation;

    /**

     Constructs a store with the given designation.
     @param designation the designation of the store
     */
    public Store(String designation) {
        this.designation = designation;
    }
    /**

     Creates a new employee and adds it to the list of employees of the store.
     @param email the email address of the employee
     @param name the name of the employee
     @param phone the phone number of the employee
     @param role the role of the employee
     @param store the store the employee belongs to
     @param address the address of the employee
     @param passportNumber the passport number of the employee
     @param taxNumber the tax number of the employee
     @return an optional value containing the created employee, or empty if the creation failed
     */
    public Optional<Employee> createEmployee(String email, String name, int phone, Role role, Store store, Address address, int passportNumber, int taxNumber) {
        Optional<Employee> optionalValue = Optional.empty();
        Employee employee = new Employee(email, name, store, phone, role, address, taxNumber, passportNumber);
        if (addEmployee(employee)){
            optionalValue = Optional.of(employee);
        }
        return optionalValue;
    }
    /**

     Adds an employee to the list of employees of the store.
     @param employee the employee to add
     @return true if the employee was added successfully, false otherwise
     */
    private boolean addEmployee(Employee employee) {
        boolean success = false;
        CreateTaskController controller = new CreateTaskController();
        List<Employee> employees = controller.getEmployee();
        if (validate(employee)) {
// A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = employees.add(employee);
        }
        return success;
    }
    /**

     Validates whether an employee can be added to the list of employees of the store.
     @param employee the employee to validate
     @return true if the employee can be added, false otherwise
     */
    private boolean validate(Employee employee) {
        return tasksDoNotContain(employee);
    }
    /**

     Checks whether the list of employees of the store contains an employee.
     @param employee the employee to check
     @return true if the employee is not in the list, false otherwise
     */
    private boolean tasksDoNotContain(Employee employee) {
        CreateTaskController controller = new CreateTaskController();
        return !controller.getEmployee().contains(employee);
    }
    /**

     Compares this store to another object for equality.
     @param o the object to compare to
     @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return designation.equals(store.designation);
    }
    /**

     Returns a hash code value for the store.
     @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }
    /**

     Returns the designation of the store.
     @return the designation
     */
    public String getDesignation() {
        return designation;
    }
    /**

     Creates and returns a copy of the store.
     @return the copy of the store
     */
    public Store clone() {
        return new Store(this.designation);
    }
}
