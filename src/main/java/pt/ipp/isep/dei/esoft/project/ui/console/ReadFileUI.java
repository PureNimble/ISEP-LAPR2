package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;

import java.io.File;

public class ReadFileUI implements Runnable {


    private FileReaderClass fileReaderClass = new FileReaderClass();


    public void run() {

        File file = new File("C:\\Users\\Utilizador\\IdeaProjects\\lei-23-s2-1dh-g37\\src\\legacyRealStateUSAMoodle.csv");

       String[][] informations = fileReaderClass.readStoreInformations(file);

        System.out.println(informations[1][0]);

    }


}
