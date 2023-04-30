package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.PublishAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**

 The AgentUI class is responsible for displaying the main menu of the agent application

 and executing the user-selected options. It implements the Runnable interface.
 */
public class AgentUI implements Runnable {

    /**

     Constructor for the AgentUI class.
     */
    public AgentUI() {
    }
    /**

     The run method is called when a thread is started for the AgentUI instance.
     It displays the main menu options for the agent application and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Publish announcement", new PublishAnnouncementUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
