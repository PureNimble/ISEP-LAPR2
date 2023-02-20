package pt.ipp.isep.dei.esoft.project.tp.one.application.controller;

import pt.ipp.isep.dei.esoft.project.tp.one.domain.Employee;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Organization;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Task;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.TaskCategoryRepository;

import java.util.List;
import java.util.Optional;

public class CreateTaskController {

    private OrganizationRepository organizationRepository = null;
    private TaskCategoryRepository taskCategoryRepository = null;


    //Repository instances are obtained from the Repositories class
    public CreateTaskController() {
        getOrganizationRepository();
        getTaskCategoryRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateTaskController(OrganizationRepository organizationRepository,
                                TaskCategoryRepository taskCategoryRepository) {
        this.organizationRepository = organizationRepository;
        this.taskCategoryRepository = taskCategoryRepository;
    }

    private TaskCategoryRepository getTaskCategoryRepository() {
        if (taskCategoryRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            taskCategoryRepository = repositories.getTaskCategoryRepository();
        }
        return taskCategoryRepository;
    }


    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    public Optional<Task> createTask(String reference, String description, String informalDescription,
                                     String technicalDescription, Integer duration, Double cost,
                                     String taskCategoryDescription) {

        TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

        Employee employee = getEmployeeFromSession();
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByEmployee(employee);

        Optional<Task> newTask = Optional.empty();

        if (organization.isPresent()) {
            newTask = organization.get()
                    .createTask(reference, description, informalDescription, technicalDescription, duration, cost,
                            taskCategory, employee);
        }
        return newTask;
    }

    private Employee getEmployeeFromSession() {
        //TODO: this is a fake employee, replace it with the employee from the session
        return new Employee("john.doe@this.company.com");
    }

    private TaskCategory getTaskCategoryByDescription(String taskCategoryDescription) {
        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();

        //Get the TaskCategory by its description
        TaskCategory taskCategoryByDescription =
                getTaskCategoryRepository().getTaskCategoryByDescription(taskCategoryDescription);
        return taskCategoryByDescription;

    }


    //return the list of task categories
    public List<TaskCategory> getTaskCategories() {
        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();
        return taskCategoryRepository.getTaskCategories();
    }
}
