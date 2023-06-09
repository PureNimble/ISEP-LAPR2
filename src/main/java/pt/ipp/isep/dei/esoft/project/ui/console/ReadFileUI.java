package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportFileController;
import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;

import java.io.File;
import java.util.ArrayList;

public class ReadFileUI implements Runnable {


    private ImportFileController importFileController = new ImportFileController();




    public void run() {
        
        String filePath = "src/legacyRealStateUSAMoodle.csv";
        File file = new File(filePath);
        

       importFileController.addStore(file);

       importFileController.addPublishAnnouncement(file);





    }


}
