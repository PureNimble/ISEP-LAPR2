package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;

import java.io.File;

public class ImportFileController {

     FileReaderClass fileReaderClass = new FileReaderClass();

     public ImportFileController(){
         getFileReaderClass();
     }


    public FileReaderClass getFileReaderClass() {
        return fileReaderClass;
    }





    public String[][] getStoreInformations(File file){
         return getFileReaderClass().readStoreInformations(file);
    }
}
