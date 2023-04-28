package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**

 A class representing a store with a designation.
 */
public class Store {

    private String designation;



    public Store(String designation) {
        this.designation = designation;
    }



    public Optional<Employee> createEmployee(String email, String name,
                                             int phone, List<Role> roles, Store store, Address address, int passportNumber, int taxNumber) {

        Optional<Employee> optionalValue = Optional.empty();

        Employee employee = new Employee(email,passportNumber,taxNumber,name,phone,store,roles,address);

        if (addEmployee(employee)){
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
        return designation.equals(store.designation);
    }


    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    public String getDesignation() {
        return designation;
    }

    public Store clone() {
        return new Store(this.designation);
    }
}
