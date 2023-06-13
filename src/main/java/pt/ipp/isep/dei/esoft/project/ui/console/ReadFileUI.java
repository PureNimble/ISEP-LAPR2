package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportFileController;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The type Read file ui.
 */
public class ReadFileUI implements Runnable {

    /**
     * Controller for importing files and performing operations on them.
     */
    private ImportFileController importFileController = new ImportFileController();

    private String file;

    /**
     * Runs the file import and processing UI.
     */
    public void run() {

        requestData();

        submitsData();

    }

    private void requestData(){
        file = requestFileDescription();
    }

    private String requestFileDescription() {

        Scanner input = new Scanner(System.in);
        String file = "src/";



            System.out.println("Insert file that you want to be read: ");
            file = file + input.nextLine();

            if (!file.contains(".csv")){
                System.out.println("The file that you are trying to read it is not a .csv format file");
                System.out.println("Please,select another one");
                file = input.nextLine();
            }




        return file;

    }

    private void submitsData() {

        importFileController.addPublishAnnouncement(file);
        importFileController.addStore(file);
        importFileController.addUser(file);

        System.out.println("The file was read with sucess");

    }



}
