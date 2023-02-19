package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    List<Employee> employeeList = new ArrayList<>();
    List<Task> taskList = new ArrayList<>();
    private String name;
    private String vatNumber;
    private String website;
    private String phone;
    private String email;

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
}
