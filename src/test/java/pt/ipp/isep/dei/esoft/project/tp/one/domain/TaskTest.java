package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskTest {

    @Test
    void testEqualsSameObject() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);

        assertEquals(task, task);

    }

    @Test
    void testEqualsDifferentClass() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);

        assertNotEquals(task, new Object());
    }

    @Test
    void testEqualsNull() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);

        assertNotEquals(task, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);
        Task task1 = new Task("reference1", "description1", "informal description1", "technical description1", 2, 2d, taskCategory, employee);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectDifferentDescription() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);
        Task task1 = new Task("reference1", "description", "informal description1", "technical description1", 2, 2d, taskCategory, employee);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);
        Task task1 = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);

        assertEquals(task, task1);
    }

    @Test
    void testHashCodeSameObject() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);

        assertEquals(task.hashCode(), task.hashCode());

    }

    @Test
    void testHashCodeDifferentObject() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d, taskCategory, employee);
        Task task1 = new Task("reference1", "description1", "informal description1", "technical description1", 2, 2d, taskCategory, employee);

        assertNotEquals(task.hashCode(), task1.hashCode());

    }


}