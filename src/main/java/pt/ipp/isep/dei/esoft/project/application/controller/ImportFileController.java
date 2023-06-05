package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportFileController {

     FileReaderClass fileReaderClass = new FileReaderClass();

     StoreRepository storeRepository = new StoreRepository();


     public ImportFileController(){
         getFileReaderClass();
     }


    /**

     Returns an instance of the StoreRepository.

     If the instance does not exist, it creates one using the Repositories class.

     @return The StoreRepository instance
     */
    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    public FileReaderClass getFileReaderClass() {
        return fileReaderClass;
    }

    public ArrayList<String[]> readInformations(File file){
        FileReaderClass fileReaderClass = getFileReaderClass();

        return fileReaderClass.readInformations(file);

    }

    public ArrayList<String[]> readStoreInformations(File file){
        FileReaderClass fileReaderClass = getFileReaderClass();

        return fileReaderClass.readStoreInformations(readInformations(file));

    }

    public void addStore(File file){
         StoreRepository storeRepository = getStoreRepository();

        storeRepository.createStoreByFileReading(readStoreInformations(file));
    }


}
