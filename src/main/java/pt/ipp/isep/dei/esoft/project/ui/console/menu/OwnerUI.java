package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AnnouncementRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**

 The OwnerUI class implements the Runnable interface.

 It provides the owner user interface for a system.
 */
public class OwnerUI implements Runnable {

    /**

     The OwnerUI class is responsible for displaying the main menu of the owner application
     and executing the user-selected options. It implements the Runnable interface.
     */
    public OwnerUI() {
    }
    /**

     The run method is called when a thread is started for the OwnerUI instance.
     It displays the main menu options for the owner application and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Request an Announcement", new AnnouncementRequestUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\nOwner Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
