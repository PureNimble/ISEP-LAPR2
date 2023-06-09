package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.DisplayEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

/**
 * The type Display employee ui.
 */
public class DisplayEmployeeUI implements Runnable{

    /**
     * The DisplayEmployeeController instance used for retrieving and sorting employee data.
     */
    private final DisplayEmployeeController controller = new DisplayEmployeeController();
    /**
     * Runs the employee display functionality.
     * Retrieves a list of employees, sorts them alphabetically, and displays them.
     */
    public void run() {
        System.out.println("List of Employees: ");

        var storeEmployeeList = controller.getEmployeesAllphabeticallySorted();

        for (int i = 0; i < storeEmployeeList.size(); i++){
            Employee employee = storeEmployeeList.get(i);
            System.out.println(i+1 + " . " + employee.toString() + "\n");
        }
    }
}
