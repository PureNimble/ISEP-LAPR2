package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import java.util.Objects;

public class Task {
    //TODO: refactor on diagrams from designation to description
    private final String description;
    private final String informalDescription;
    private final String reference;
    private final String technicalDescription;
    private final Integer duration;
    private final Double cost;

    private final TaskCategory taskCategory;

    private final Employee employee;

    public Task(String reference, String description, String informalDescription, String technicalDescription,
                Integer duration, Double cost, TaskCategory taskCategory, Employee employee) {
        this.description = description;
        this.informalDescription = informalDescription;
        this.reference = reference;
        this.technicalDescription = technicalDescription;
        this.duration = duration;
        this.cost = cost;
        this.taskCategory = taskCategory;
        this.employee = employee;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return reference.equals(task.reference) && employee.equals(task.employee);
    }

    @Override public int hashCode() {
        return Objects.hash(reference, employee);
    }
}
