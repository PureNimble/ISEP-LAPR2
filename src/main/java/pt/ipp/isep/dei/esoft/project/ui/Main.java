package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

import java.io.IOException;

/**
 * The type Main.
 */
public class Main{

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Repositories.getInstance().serialize();
    }
}
