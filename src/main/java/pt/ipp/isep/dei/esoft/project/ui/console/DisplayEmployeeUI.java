package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.DisplayEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

public class DisplayEmployeeUI implements Runnable{


    private final DisplayEmployeeController controller = new DisplayEmployeeController();

    public void run() {
        System.out.println("List of Employees: ");

        var storeEmployeeList = controller.getEmployeesAllphabeticallySorted();

        for (int i = 0; i < storeEmployeeList.size(); i++){
            Employee employee = storeEmployeeList.get(i);
            System.out.println(i+1 + ". " + employee.toString() + "\n");
        }
    }
}
