package pt.ipp.isep.dei.esoft.project.tp.one.application.controller;

import pt.ipp.isep.dei.esoft.project.tp.one.domain.Employee;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Organization;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Task;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.TaskCategoryRepository;

public class CreateTaskController {

    public Task createTask(String reference, String designation, String informalDescription, String technicalDescription, Integer duration, Double Cost, String taskCategoryDescription) {

        TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

        Repositories repositories = Repositories.getInstance();
        OrganizationRepository organizationRepository = repositories.getOrganizationRepository();

        Employee employee = getEmployeeFromSession();
        Organization organization = organizationRepository.getOrganizationByEmployee(employee);

        Task newTask = organization.createTask(reference, designation, informalDescription, technicalDescription, duration, Cost, taskCategory, employee);

        return newTask;
    }

    private Employee getEmployeeFromSession() {
        //TODO: this is a fake employee, replace it with the employee from the session
        return new Employee("john.doe@this.company.com");
    }

    private TaskCategory getTaskCategoryByDescription(String taskCategoryDescription) {
        Repositories repositories = Repositories.getInstance();

        //Get the TaskCategoryRepository
        TaskCategoryRepository taskCategoryRepository = repositories.getTaskCategoryRepository();

        //Get the TaskCategory by its description
        TaskCategory taskCategoryByDescription = taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription);
        return taskCategoryByDescription;

    }
}
