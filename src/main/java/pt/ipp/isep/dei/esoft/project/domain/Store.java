package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
x
public class Store {

    private String designation;

    private int id;

    private Address address;

    private String email;

    private int phoneNumber;

    public Store(String designation, int id, Address address, int phoneNumber, String email) {
        this.designation = designation;
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Store() {
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public String toString() {
        return String.format("Loja: %s %s localizada em %s", id, designation, address.toString());
    }

    public Optional<Employee> createEmployee(String email, String name,
                                             int phone, List<Role> roles, Store store, Address address, int passportNumber, int taxNumber) {

        Optional<Employee> optionalValue = Optional.empty();

        Employee employee = new Employee(email, passportNumber, taxNumber, name, phone, store, roles, address);

        if (addEmployee(employee)) {
            optionalValue = Optional.of(employee);

        }
        return optionalValue;
    }

    private boolean addEmployee(Employee employee) {
        boolean success = false;
        RegisterEmployeeController controller = new RegisterEmployeeController();
        List<Employee> employees = controller.getEmployee();
        if (validate(employee)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = employees.add(employee);
        }
        return success;

    }

    private boolean validate(Employee employee) {
        return tasksDoNotContain(employee);
    }


    private boolean tasksDoNotContain(Employee employee) {
        RegisterEmployeeController controller = new RegisterEmployeeController();
        return !controller.getEmployee().contains(employee);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return this.id == store.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    public String getDesignation() {
        return designation;
    }


}
