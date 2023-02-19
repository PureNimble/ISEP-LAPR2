package pt.ipp.isep.dei.esoft.project.tp.one.domain;

public class Task {
    private final String designation;
    private final String informalDescription;
    private final String reference;
    private final String technicalDescription;
    private final Integer duration;
    private final Double cost;

    private final TaskCategory taskCategory;

    private final Employee employee;

    public Task(String reference, String designation, String informalDescription, String technicalDescription, Integer duration, Double cost, TaskCategory taskCategory, Employee employee) {
        this.designation = designation;
        this.informalDescription = informalDescription;
        this.reference = reference;
        this.technicalDescription = technicalDescription;
        this.duration = duration;
        this.cost = cost;
        this.taskCategory = taskCategory;
        this.employee = employee;
    }
}
