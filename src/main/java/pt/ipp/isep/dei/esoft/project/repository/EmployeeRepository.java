package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public Optional<Employee> add(Employee employee) {

        Optional<Employee> newEmployee = Optional.empty();
        boolean operationSuccess = false;

        if (validateEmployee(employee)) {
            newEmployee = Optional.of(employee);
            operationSuccess = employees.add(newEmployee.get());
        }

        if (!operationSuccess) {
            newEmployee = Optional.empty();
        }

        return newEmployee;
    }

    private boolean validateEmployee(Employee employee) {
        boolean isValid = !employees.contains(employee);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<Employee> getEmployees() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return employees;
    }
}
