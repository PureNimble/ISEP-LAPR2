package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.ReadFileUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterStoreUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a Store", new RegisterStoreUI()));
        options.add(new MenuItem("Register an Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Read informations from a csv.file",new ReadFileUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
