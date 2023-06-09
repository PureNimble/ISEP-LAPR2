package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportFileController;
import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;

import java.io.File;
import java.util.ArrayList;

/**
 * The type Read file ui.
 */
public class ReadFileUI implements Runnable {

    /**
     * Controller for importing files and performing operations on them.
     */
    private ImportFileController importFileController = new ImportFileController();

    /**
     * Runs the file import and processing UI.
     */
    public void run() {
        
        String filePath = "src/legacyRealStateUSAMoodle.csv";
        File file = new File(filePath);
        

       importFileController.addStore(file);

       importFileController.addPublishAnnouncement(file);

    }


}
