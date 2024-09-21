package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

/**
 * The EmployeeRepository class represents a repository for Employee objects. It provides methods to add and return a
 * defensive copy of the list of Employee objects.
 */
public class EmployeeRepository implements Serializable {
    /**
     * A list of employees associated with a specific entity.
     */
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

    /**
     * Get employees allphabetically sorted list.
     *
     * @return the list
     */
    public List<Employee> getEmployeesAllphabeticallySorted(){
        List<Employee> resultList = new ArrayList<Employee>();

        List<Employee> tempList = new ArrayList<Employee>();
        for (Employee employee: employees){
            
            tempList.add(employee);
        }
        tempList.sort(Comparator.comparing(Employee::getEmployeeName));
        resultList.addAll(tempList);

        return resultList;
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
        return !employees.contains(employee);
    }

}
