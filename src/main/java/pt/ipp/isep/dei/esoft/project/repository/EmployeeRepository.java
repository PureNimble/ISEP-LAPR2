package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

/**
 * The EmployeeRepository class represents a repository for Employee objects. It provides methods to add and return a
 * defensive copy of the list of Employee objects.
 */
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    /**
     * Adds a new Employee to the repository.
     *
     * @param employee The Employee object to be added to the repository.
     * @return An optional containing the Employee object if the operation was successful, otherwise an empty Optional.
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
     * Returns a list of all employees managed by this repository.
     *
     * @return a list of all employees managed by this repository
     */
    public List<Employee> getEmployees() {
// This is a defensive copy, so that the repository cannot be modified from the outside.
        return employees;
    }


    /**
     * Get employee by email employee.
     *
     * @param email the email
     * @return the employee
     */
    public Employee getEmployeeByEmail(String email){
        for (Employee employee: employees) {
            if (employee.getEmployeeEmail().equals(email)){
                return  employee;
            }
        }
        return null;
    }

    /**
     * Get employee by string employee.
     *
     * @param employeeString the employee string
     * @return the employee
     */
    public  Employee getEmployeeByString(String employeeString){
        for (Employee employee: employees) {
            if (employee.toString().equals(employeeString)){
                return  employee;
            }
        }
        return null;
    }

    public List<Employee> getEmployeesAllphabeticallySorted(List<Store> storesList){
        List<Employee> resultList = new ArrayList<Employee>();

        for (Store stores: storesList){
            List<Employee> tempList = new ArrayList<Employee>();
            for (Employee employee: employees){
                if (employee.getStore().equals(stores)){
                    tempList.add(employee);
                }
            }
            tempList.sort(Comparator.comparing(Employee::getEmployeeName));
            resultList.addAll(tempList);
        }

        return resultList;
    }

}
