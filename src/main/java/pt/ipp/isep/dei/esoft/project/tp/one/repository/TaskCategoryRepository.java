package pt.ipp.isep.dei.esoft.project.tp.one.repository;

import pt.ipp.isep.dei.esoft.project.tp.one.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;

public class TaskCategoryRepository {

    private final List<TaskCategory> taskCategoryList = new ArrayList<>();

    /**
     * This method returns an exsiting Task Category by its description.
     *
     * @param taskCategoryDescription The description of the task category to be created.
     * @return The task category.
     * @throws IllegalArgumentException if the task category does not exist, which should never happen.
     */
    public TaskCategory getTaskCategoryByDescription(String taskCategoryDescription) {
        TaskCategory newTaskCategory = new TaskCategory(taskCategoryDescription);
        TaskCategory taskCategory = null;
        if (taskCategoryList.contains(newTaskCategory)) {
            taskCategory = taskCategoryList.get(taskCategoryList.indexOf(newTaskCategory));
        }
        if (taskCategory == null) {
            throw new IllegalArgumentException("Task Category requested for [" + taskCategoryDescription + "] does not exist.");
        }
        return taskCategory;
    }

    public void add(TaskCategory taskCategory) {
        if (taskCategoryList.contains(taskCategory)) {
            throw new IllegalArgumentException("Task Category [" + taskCategory + " alreday exists.");
        }
        taskCategoryList.add(taskCategory);
    }
}
