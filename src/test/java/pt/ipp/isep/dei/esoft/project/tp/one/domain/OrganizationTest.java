package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {

    @Test
    void testEqualsSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization, organization);
    }

    @Test
    void testEqualsDifferentClass() {
        Organization organization = new Organization("123456789");
        assertNotEquals("", organization);
    }

    @Test
    void testEqualsNull() {
        Organization organization = new Organization("123456789");
        assertNotEquals(null, organization);
    }

    @Test
    void testEqualsDifferentObject() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization, organization1);
    }


    @Test
    void testHashCodeSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization.hashCode(), organization.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
        //same hashcode
    void testHashCodeSameObjectSameVATNumber() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
    void ensureHashCodeFailsForDifferentVatNumbers() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456788");
        assertNotEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
    void ensureEqualsFailsForDifferentObjectType() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456788");
        assertNotEquals(organization, organization1);
    }

    @Test
    void ensureEqualsFailsWhenComparingNull() {
        Organization organization = new Organization("123456789");
        assertNotEquals(organization, null);
    }

    @Test
    void ensureEqualsSuccessWhenComparingSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization, organization);
    }

    @Test
    void testThatCreateTaskWorks() {
        Organization organization = new Organization("123456789");

        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");

        Task expected = new Task("Task Description", "Task Category Description", "informal description",
                "technical description", 1, 1d, taskCategory, employee);

        Optional<Task> task =
                organization.createTask("Task Description", "Task Category Description", "informal description",
                        "technical description", 1, 1d, taskCategory, employee);

        assertNotNull(task);
        assertTrue(task.isPresent());
        assertEquals(expected, task.get());
    }

    @Test
    void ensureAddingDuplicateTaskFails() {
        //Arrange
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        //Add the first task
        Optional<Task> originalTask =
                organization.createTask("Task Description", "Task Category Description", "informal description",
                        "technical description", 1, 1d, taskCategory, employee);

        //Act
        Optional<Task> duplicateTask =
                organization.createTask("Task Description", "Task Category Description", "informal description",
                        "technical description", 1, 1d, taskCategory, employee);

        //Assert
        assertTrue(duplicateTask.isEmpty());
    }


    @Test
    void ensureEmploysFails() {
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");

        assertFalse(organization.employs(employee));

    }

    @Test
    void ensureEmploysSuccess() {
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        organization.addEmployee(employee);
        assertTrue(organization.employs(employee));
    }

    @Test
    void ensureAnyEmployeeHasEmailFails() {
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        organization.addEmployee(employee);
        assertFalse(organization.anyEmployeeHasEmail("jane.doe@this.company.com"));


    }

    @Test
    void ensureAnyEmployeeHasEmailWorks() {
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        organization.addEmployee(employee);
        assertTrue(organization.anyEmployeeHasEmail("john.doe@this.company.com"));
    }

    @Test
    void ensureAddDuplicateEmployeeFails() {
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        assertTrue(organization.addEmployee(employee));
        assertFalse(organization.addEmployee(employee));
    }

    @Test
    void ensureAddEmployeeWorks() {
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        assertTrue(organization.addEmployee(employee));
    }

    @Test
    void ensureCloneWorks() {
        Organization organization = new Organization("123456789");
        Employee employee = new Employee("john.doe@this.company.com");
        organization.addEmployee(employee);
        organization.createTask("Task Description", "Task Category Description", "informal description",
                "technical description", 1, 1d, new TaskCategory("Task Category Description"), employee);

        Organization clone = organization.clone();
        assertEquals(organization, clone);
    }
}