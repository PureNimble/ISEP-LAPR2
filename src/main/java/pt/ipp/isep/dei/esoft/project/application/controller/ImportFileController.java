package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.FileReaderClass;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.repository.UserRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Import file controller.
 */
public class ImportFileController {

    /**
     * The File reader class.
     */
    FileReaderClass fileReaderClass = new FileReaderClass();

    /**
     * The Store repository.
     */
    StoreRepository storeRepository = null;

    /**
     * The User repository.
     */
    UserRepository userRepository =null;

    /**
     * The Published announcement repository.
     */
    PublishedAnnouncementRepository publishedAnnouncementRepository = null;


    /**
     * Instantiates a new Import file controller.
     */
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

    /**
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    /**
     * Gets file reader class.
     *
     * @return the file reader class
     */
    public FileReaderClass getFileReaderClass() {
        return fileReaderClass;
    }

    /**
     * Read informations array list.
     *
     * @param file the file
     * @return the array list
     */
    public ArrayList<String[]> readInformations(String file){
        FileReaderClass fileReaderClass = getFileReaderClass();

        return fileReaderClass.readInformations(file);

    }

    /**
     * Read store informations array list.
     *
     * @param file the file
     * @return the array list
     */
    public ArrayList<String[]> readStoreInformations(String file){
        FileReaderClass fileReaderClass = getFileReaderClass();

        return fileReaderClass.readStoreInformations(readInformations(file));

    }

    /**
     * Add store.
     *
     * @param file the file
     */
    public List<Store> addStore(String file){
         StoreRepository storeRepository = getStoreRepository();

        return storeRepository.createStoreByFileReading(readStoreInformations(file));
    }

    /**
     * Read owner informations array list.
     *
     * @param file the file
     * @return the array list
     */
    public ArrayList<String[]> readOwnerInformations(String file){
        FileReaderClass fileReaderClass = getFileReaderClass();

        return fileReaderClass.readOwnerInformations(readInformations(file));
    }

    /**
     * Add user.
     *
     * @param file the file
     */
    public List<Client> addUser(String file){
        UserRepository userRepository = getUserRepository();

        return userRepository.createOwnerByFileReading(readOwnerInformations(file));
    }

    /**
     * Add publish announcement.
     *
     * @param file the file
     */
    public void addPublishAnnouncement(String file){

        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();

        publishedAnnouncementRepository.createPublishAnnouncementByFileReading(readInformations(file),addStore(file),addUser(file));

    }



}
