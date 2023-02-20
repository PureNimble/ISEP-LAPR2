package pt.ipp.isep.dei.esoft.project.tp.one.application.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Employee;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Organization;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Task;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.TaskCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


/**
 * The Create Task Controller Integration Tests.
 * <p>
 * The class CreateTaskController does not perform anything by itself and relies on other classes to work. Therefore,
 * all these tests are integration tests and not unit tests. That is why this class is named IT from Integration Tests.
 */
class CreateTaskControllerIT {

    @Test
    void ensureCreateTaskWorks() {

        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        OrganizationRepository organizationRepository = new OrganizationRepository();

        //Fill Task Category Repository
        taskCategoryRepository.add(new TaskCategory("Task Category Description"));

        //Fill Organization Repository
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        organization.addEmployee(employee);
        organizationRepository.add(organization);


        CreateTaskController controller = new CreateTaskController(organizationRepository, taskCategoryRepository);

        //Act
        Optional<Task> newTask =
                controller.createTask("reference", "description", "informal description", "tecnical " + "description",
                        1, 1d, "Task" + " Category Description");
    }

    @Test
    void ensureGetCategoriesWork() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        OrganizationRepository organizationRepository = new OrganizationRepository();

        //Fill Task Category Repository
        TaskCategory taskCategoryOne = new TaskCategory("Task Category Description One");
        taskCategoryRepository.add(taskCategoryOne);

        TaskCategory taskCategoryTwo = new TaskCategory("Task Category Description Two");
        taskCategoryRepository.add(taskCategoryTwo);

        ArrayList<TaskCategory> expected = new ArrayList<TaskCategory>();
        expected.add(taskCategoryOne);
        expected.add(taskCategoryTwo);

        //Fill Organization Repository
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        organization.addEmployee(employee);
        organizationRepository.add(organization);

        CreateTaskController controller = new CreateTaskController(organizationRepository, taskCategoryRepository);

        //Act
        List<TaskCategory> taskCategories = controller.getTaskCategories();

        assertArrayEquals(expected.toArray(), taskCategories.toArray());
    }

    /**
     * This test ensures that the CreateTaskController works with the singleton Repositories class.
     * This type of tests should be avoided because they share the Repositories state with other tests.
     */
    @Test
    void ensureCreateTaskWorksWithSingleton() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        TaskCategoryRepository taskCategoryRepository = repositories.getTaskCategoryRepository();
        OrganizationRepository organizationRepository = repositories.getOrganizationRepository();

        //Fill Task Category Repository
        taskCategoryRepository.add(new TaskCategory("Task Category Description"));

        //Fill Organization Repository
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        organization.addEmployee(employee);
        organizationRepository.add(organization);


        CreateTaskController controller = new CreateTaskController();

        //Act
        Optional<Task> newTask =
                controller.createTask("reference", "description", "informal description", "tecnical " + "description",
                        1, 1d, "Task" + " Category Description");
    }

    //TODO: test the controller createTask using mockito to mock the repositories.
}