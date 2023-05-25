package pt.ipp.isep.dei.esoft.project.domain;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderClass {
    public String[][] readStoreInformations(File file) {

        String[] storeInformations = new String[1000];

        String[][] arrayInformations = new String[10000][5];

        try {
            int linhas = 0;

            Scanner ler = new Scanner(file);

            while (ler.hasNextLine()) {

                String[] itens = ler.nextLine().split(";");

                storeInformations[linhas] = itens[0];

                int i = 25;
                for (int item = 1; item < 6; item++) {

                    arrayInformations[linhas][item - 1] = itens[i];
                    i++;
                }
                linhas++;

            }
            ler.close();
        } catch (FileNotFoundException fileNotFoundException) {

        }

        return arrayInformations;
    }


}
