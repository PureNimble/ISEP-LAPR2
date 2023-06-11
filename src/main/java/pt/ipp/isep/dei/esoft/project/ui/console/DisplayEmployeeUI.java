package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.DisplayEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.StoreEmployeeDTO;

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
        System.out.println("\nList of Employees:\n");

        var storeEmployeeList = controller.toDTO();
        int storeCount = 1, employeeCount = 1;
        for (int i = 0; i < storeEmployeeList.size(); i++){
            StoreEmployeeDTO StoreEmployeeDTO = storeEmployeeList.get(i);
            if (StoreEmployeeDTO.getStoreDesignation() == null) {
                System.out.println("    " + employeeCount + " . " + StoreEmployeeDTO.toStringEmployee() + "\n");
                employeeCount++;
            } else {
                System.out.println(storeCount + " . " + StoreEmployeeDTO.toStringStore() + "\n");
                storeCount++;
                employeeCount = 1;
            }            
        }
    }
}
