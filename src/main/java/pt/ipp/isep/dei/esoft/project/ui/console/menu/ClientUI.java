package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AnnouncementRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.SendMessageUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClientUI implements Runnable{

    public ClientUI(){}

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Send message to agent", new SendMessageUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\nClient Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
