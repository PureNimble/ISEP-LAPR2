package pt.ipp.isep.dei.esoft.project.domain;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderClass {
    public ArrayList<String[]> readInformations(File file) {

        ArrayList<String[]> fileInformations = new ArrayList<>();
        try {
            Scanner ler = new Scanner(file);
            ler.useDelimiter(";");
            while (ler.hasNext()) {
                String line = ler.nextLine();
                line = line.replaceAll("\\s+", "").replaceAll("\n", "").replaceAll("\"", ""); //To remove all whitespaces and new lines and quotes
                fileInformations.add(line.split(";"));
            }
            ler.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }

        return fileInformations;

    }

    public ArrayList<String[]> readStoreInformations(ArrayList<String[]> fileInformations) {

        ArrayList<String[]> storeInformations = new ArrayList<>();

        for (String[] information: fileInformations) {

            String[] storeAux = new String[5];
            for (int i = 0; i < 5 ; i++) {
                storeAux[i] = information[25+i];
            }

            storeInformations.add(storeAux);
        }

        return storeInformations;
    }

    public ArrayList<String[]> readOwnerInformations(ArrayList<String[]> fileInformations) {

        ArrayList<String[]> ownerInformations = new ArrayList<>();

        for (String[] information: fileInformations) {

            String[] ownerAux = new String[5];
            for (int i = 0; i < 5 ; i++) {
                ownerAux[i] = information[1+i];
            }

            ownerInformations.add(ownerAux);
        }

        return ownerInformations;
    }


}
