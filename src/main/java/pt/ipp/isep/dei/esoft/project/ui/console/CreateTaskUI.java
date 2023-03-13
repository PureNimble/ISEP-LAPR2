package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Create Task UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateTaskUI implements Runnable {

    private final CreateTaskController controller = new CreateTaskController();
    private String taskReference;
    private String taskDescription;
    private String taskInformalDescription;
    private String taskTechnicalDescription;
    private Integer taskDuration;
    private Double taskCost;
    private String taskCategoryDescription;
    private String empployeeEmail;

    private CreateTaskController getController() {
        return controller;
    }

    public void run() {
        System.out.println("Create Task");

        taskCategoryDescription = displayAndSelectTaskCategory();

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Task> task = getController().createTask(taskReference, taskDescription, taskInformalDescription,
                taskTechnicalDescription, taskDuration, taskCost, taskCategoryDescription);

        if (task.isPresent()) {
            System.out.println("Task successfully created!");
        } else {
            System.out.println("Task not created!");
        }
    }

    private void requestData() {

        //Request the Task Reference from the console
        taskReference = requestTaskReference();

        //Request the Task Description from the console
        taskDescription = requestTaskDescription();

        //Request the Task Informal Description from the console
        taskInformalDescription = requestTaskInformalDescription();

        //Request the Task Technical Description from the console
        taskTechnicalDescription = requestTaskTechnicalDescription();

        //Request the Task Duration from the console
        taskDuration = requestTaskDuration();

        //Request the Task Cost from the console
        taskCost = requestTaskCost();
    }

    private Double requestTaskCost() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Cost:");
        return input.nextDouble();

    }

    private Integer requestTaskDuration() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Duration:");
        return input.nextInt();
    }

    private String requestTaskTechnicalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Technical Description:");
        return input.nextLine();
    }

    private String requestTaskInformalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Informal Description:");
        return input.nextLine();
    }

    private String requestTaskDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Description:");
        return input.nextLine();
    }

    private String requestTaskReference() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Reference:");
        return input.nextLine();
    }

    private String displayAndSelectTaskCategory() {
        //Display the list of task categories
        List<TaskCategory> taskCategories = controller.getTaskCategories();

        int listSize = taskCategories.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayTaskCategoryOptions(taskCategories);
            System.out.println("Select a task category:");
            answer = input.nextInt();
        }

        String description = taskCategories.get(answer - 1).getDescription();
        return description;

    }

    private void displayTaskCategoryOptions(List<TaskCategory> taskCategories) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (TaskCategory taskCategory : taskCategories) {
            System.out.println(i + " - " + taskCategory.getDescription());
            i++;
        }
    }
}
