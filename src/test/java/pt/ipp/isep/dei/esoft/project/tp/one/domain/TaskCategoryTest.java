package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskCategoryTest {

    //Tests for equals and hashcode
    @Test void testEqualsSameObject() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        assertEquals(taskCategory, taskCategory);
    }

    @Test void testEqualsDifferentClass() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        assertNotEquals("", taskCategory);
    }

    @Test void testEqualsNull() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        assertNotEquals(null, taskCategory);
    }

    @Test void testEqualsDifferentObject() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        TaskCategory taskCategory1 = new TaskCategory("Task Category Description 1");
        assertNotEquals(taskCategory, taskCategory1);
    }

    @Test void testEqualsSameObjectDifferentDescription() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        TaskCategory taskCategory1 = new TaskCategory("Task Category Description 1");
        assertNotEquals(taskCategory, taskCategory1);
    }

    @Test void testEqualsSameObjectSameDescription() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        TaskCategory taskCategory1 = new TaskCategory("Task Category Description");
        assertEquals(taskCategory, taskCategory1);
    }

    @Test void testHashCodeSameObject() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        assertEquals(taskCategory.hashCode(), taskCategory.hashCode());
    }

    @Test void testHashCodeDifferentObject() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        TaskCategory taskCategory1 = new TaskCategory("Task Category Description 1");
        assertNotEquals(taskCategory.hashCode(), taskCategory1.hashCode());
    }

    @Test void testHashCodeSameObjectDifferentDescription() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        TaskCategory taskCategory1 = new TaskCategory("Task Category Description 1");
        assertNotEquals(taskCategory.hashCode(), taskCategory1.hashCode());
    }

    @Test void testHashCodeSameObjectSameDescription() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        TaskCategory taskCategory1 = new TaskCategory("Task Category Description");
        assertEquals(taskCategory.hashCode(), taskCategory1.hashCode());
    }

    @Test void testEqualsForDifferentObjectType() {
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        assertNotEquals(taskCategory, new Object());
    }


}