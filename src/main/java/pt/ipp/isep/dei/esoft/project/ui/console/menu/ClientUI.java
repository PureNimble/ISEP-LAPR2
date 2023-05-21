package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AnnouncementRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.SendMessageUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Client ui.
 */
public class ClientUI implements Runnable{

    /**
     * Instantiates a new Client ui.
     */
    public ClientUI(){}

    /**

     The run method is called when a thread is started for the ClientUI instance.
     It displays the main menu options for the client application and executes the selected option.
     */

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Send a message to an agent", new SendMessageUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\nClient Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
