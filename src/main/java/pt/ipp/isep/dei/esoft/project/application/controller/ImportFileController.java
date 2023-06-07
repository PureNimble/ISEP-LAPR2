package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.repository.UserRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportFileController {

     FileReaderClass fileReaderClass = new FileReaderClass();

     StoreRepository storeRepository = null;

     UserRepository userRepository =null;

     PublishedAnnouncementRepository publishedAnnouncementRepository = null;


     public ImportFileController(){
         getFileReaderClass();
         getStoreRepository();
         getUserRepository();
         getPublishedAnnouncementRepository();
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


    /**
     * Returns an instance of the UserRepository, creating a new one if it is null.
     *
     * @return the UserRepository instance
     */
    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            userRepository = repositories.getUserRepository();
        }
        return userRepository;

    }


    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
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

    public ArrayList<String[]> readOwnerInformations(File file){
        FileReaderClass fileReaderClass = getFileReaderClass();

        return fileReaderClass.readOwnerInformations(readOwnerInformations(file));
    }

    public void addUser(File file){
        UserRepository userRepository = getUserRepository();

        userRepository.createOwnerByFileReading(readOwnerInformations(file));
    }

    public void addPublishAnnouncement(File file){

        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();

        publishedAnnouncementRepository.createPublishAnnouncementByFileReading(readInformations(file));

    }



}
