package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final String vatNumber;
    List<Employee> employeeList = new ArrayList<>();
    List<Task> taskList = new ArrayList<>();
    private String name;
    private String website;
    private String phone;
    private String email;

    public Organization(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public boolean employs(Employee employee) {
        return employeeList.contains(employee);
    }

    public Task createTask(String reference, String designation, String informalDescription, String technicalDescription, Integer duration, Double cost, TaskCategory taskCategory, Employee employee) {
        Task task = new Task(reference, designation, informalDescription, technicalDescription, duration, cost, taskCategory, employee);

        if (addTask(task))
            return task;
        else
            throw new IllegalArgumentException("Task already exists for [" + reference + "].");
    }

    private boolean addTask(Task task) {
        boolean success = false;
        if (validate(task)) {
            success = taskList.add(task);

        }
        return success;

    }

    private boolean validate(Task task) {

        return tasksDoNotContains(task);
    }

    private boolean tasksDoNotContains(Task task) {
        return !taskList.contains(task);
    }


    //TODO: missing in the diagrams
    public boolean anyEmployeeHasEmail(String email) {
        boolean result = false;
        for (Employee employee : employeeList) {
            if (employee.hasEmail(email)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }

    //add employee to organization
    public boolean addEmployee(Employee employee) {
        boolean success = false;
        if (validateEmployee(employee)) {
            success = employeeList.add(employee);
        }
        return success;
    }

    private boolean validateEmployee(Employee employee) {
        return employeesDoNotContain(employee);
    }

    private boolean employeesDoNotContain(Employee employee) {
        return !employeeList.contains(employee);
    }
}
