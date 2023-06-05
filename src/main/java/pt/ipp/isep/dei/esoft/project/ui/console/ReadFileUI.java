package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportFileController;
import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;

import java.io.File;
import java.util.ArrayList;

public class ReadFileUI implements Runnable {


    private ImportFileController importFileController = new ImportFileController();




    public void run() {

        File file = new File("C:\\Users\\Utilizador\\IdeaProjects\\lei-23-s2-1dh-g37\\src\\legacyRealStateUSAMoodle.csv");

       importFileController.addStore(file);





    }


}
