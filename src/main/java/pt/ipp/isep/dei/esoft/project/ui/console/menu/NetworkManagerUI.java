package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.DisplayEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListDealsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Network manager ui.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class NetworkManagerUI implements Runnable {
    /**
     * Instantiates a new Network manager ui.
     */
    public NetworkManagerUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("See all employees working in every store", new DisplayEmployeeUI()));
        options.add(new MenuItem("See deals made",new ListDealsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nNetwork Manager Menu:\n");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
