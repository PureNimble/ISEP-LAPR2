package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportFileController;

import java.io.File;
import java.util.ArrayList;
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
        Scanner scanner = new Scanner(System.in);
        String caminhoInicial = "src/main/legacyRealStateFiles/";
        ArrayList<String> listaFicheiros = new ArrayList<>();
        int escolha = -1;


            File realStateFile = new File(caminhoInicial);
            if (realStateFile.exists() && realStateFile.isDirectory()) {
                File[] files = realStateFile.listFiles();
                for (File file : files) {
                    if (file.getName().endsWith(".csv")) {
                        listaFicheiros.add(file.getName());
                    } else {
                        System.out.printf("Found a file on the folder that is not a .csv format file: [%s]\n", file.getName());
                    }
                }
            }

            do {

                try {
                    System.out.println("\nChoose a csv file to read:\n");

                    for(int i = 0; i < listaFicheiros.size(); i++) {
                        if(listaFicheiros.get(i) != null){
                            System.out.println(i + 1 + ". " + listaFicheiros.get(i));
                        }
                    }
                    System.out.print("\nOption: ");
                    escolha = scanner.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value");
                    scanner.nextLine();
                }

            } while (escolha < 0 || escolha > listaFicheiros.size() - 1);

            String selectedFile = caminhoInicial + listaFicheiros.get(escolha);

        return selectedFile;

    /*
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
    */

    }

    private void submitsData() {

        importFileController.addPublishAnnouncement(file);
        importFileController.addStore(file);
        importFileController.addUser(file);

        System.out.println("The file was read with sucess");

    }



}
