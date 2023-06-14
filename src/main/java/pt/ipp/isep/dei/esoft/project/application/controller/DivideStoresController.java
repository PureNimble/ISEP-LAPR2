package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.List;

public class DivideStoresController {

    /**
     * The StoreRepository instance.
     */
    private StoreRepository storeRepository = null;


    /**
     * Instantiates a new Divide Stores controller.
     */

    public DivideStoresController(){
        getStoreRepository();
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
     * Returns a string that represents the binary number of the subset.
     *
     * @return The sublists of the partition string corresponding of the subset with the minimum difference
     */

    private String findPartition(){
        StoreRepository storeRepository = getStoreRepository();

        return storeRepository.findPartition();
    }








}
