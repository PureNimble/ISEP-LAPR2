package pt.ipp.isep.dei.esoft.project.tp.one.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.TaskCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskCategoryRepositoryTest {

    @Test void getTaskCategoryByDescription() {
    }

    //Test Task Category Repository for empty list
    @Test void getTaskCategoryByDescriptionEmptyList() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        assertThrows(IllegalArgumentException.class,
                () -> taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription));
    }

    //test Task Category Repository for null list
    @Test void getTaskCategoryByDescriptionNullList() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        assertThrows(IllegalArgumentException.class,
                () -> taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription));
    }

    //test Task Category Repository for adding existing Task Category
    @Test void ensureTaskCategoryDoesNotAcceptDuplicates() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategory taskCategory = new TaskCategory(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);

        assertThrows(IllegalArgumentException.class, () -> taskCategoryRepository.add(taskCategory));
    }

    @Test void ensureNewTaskCategorySuccessfullyAdded() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategory taskCategory = new TaskCategory(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
    }

    @Test void ensureGetTaskCategoryForExistingTaskCategory() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategory taskCategory = new TaskCategory(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
        TaskCategory taskCategory1 = taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription);
        assertEquals(taskCategory, taskCategory1);
    }

    @Test void ensureGetTaskCategoryFailsForNonExistingTaskCategory() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategory taskCategory = new TaskCategory(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
        String taskCategoryDescription1 = "Task Category Description 1";
        assertThrows(IllegalArgumentException.class,
                () -> taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription1));

    }

    @Test void ensureGetTaskCategoriesReturnsAnImmutableList() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategory taskCategory = new TaskCategory(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);

        assertThrows(UnsupportedOperationException.class,
                () -> taskCategoryRepository.getTaskCategories().add(new TaskCategory("Task Category Description 1")));

    }

    @Test void ensureGetTaskCategoriesReturnsTheCorrectList() {
        //Arrange
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategory taskCategory = new TaskCategory(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
        int expectedSize = 1;

        //Act
        int size = taskCategoryRepository.getTaskCategories().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(taskCategory, taskCategoryRepository.getTaskCategories().get(size - 1));
    }
}