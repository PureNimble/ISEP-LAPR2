package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskCategoryRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class CreateTaskController {

    private OrganizationRepository organizationRepository = null;
    private TaskCategoryRepository taskCategoryRepository = null;

    private AuthenticationRepository authenticationRepository = null;


    //Repository instances are obtained from the Repositories class
    public CreateTaskController() {
        getOrganizationRepository();
        getTaskCategoryRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateTaskController(OrganizationRepository organizationRepository,
                                TaskCategoryRepository taskCategoryRepository,
                                AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.authenticationRepository = this.authenticationRepository;
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

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
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
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Employee(email.getEmail());
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
