package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**

 The EmployeeRepository class represents a repository for Employee objects. It provides methods to add and return a

 defensive copy of the list of Employee objects.
 */
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    /**

     Adds a new Employee to the repository.

     @param employee The Employee object to be added to the repository.

     @return An optional containing the Employee object if the operation was successful, otherwise an empty Optional.
     */
    public Optional<Employee> add(Employee employee) {

        Optional<Employee> newEmployee = Optional.empty();
        boolean operationSuccess = false;

// Validate the Employee object and add it to the repository if it is valid
        if (validateEmployee(employee)) {
            newEmployee = Optional.of(employee);
            operationSuccess = employees.add(newEmployee.get());
        }

// Return an empty Optional if the operation was not successful
        if (!operationSuccess) {
            newEmployee = Optional.empty();
        }

        return newEmployee;
    }

    /**

     Validates an Employee object to ensure that it does not already exist in the repository.
     @param employee The Employee object to be validated.
     @return true if the Employee object is valid, false otherwise.
     */
    private boolean validateEmployee(Employee employee) {
        boolean isValid = !employees.contains(employee);
        return isValid;
    }
    /**

     Returns a defensive (immutable) copy of the list of Employee objects in the repository.
     @return An immutable list of Employee objects.
     */
    public List<Employee> getEmployees() {
// This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(employees);
    }
}
