package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StateRepository;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.List;
import java.util.Optional;
/**

 The RegisterStoreController class is responsible for registering a new store in the system.

 It provides a method for registering a store and uses lazy initialization to initialize its

 StoreRepository and StateRepository instance variables.
 */
public class RegisterStoreController {



    private StoreRepository storeRepository = null;

    private StateRepository stateRepository = null;


    public RegisterStoreController(){
        getStoreRepository();
        getStateRepository();
    }

    /**

     Initializes the StoreRepository instance variable using lazy initialization.
     @return The StoreRepository object associated with this controller.
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

     Initializes the StateRepository instance variable using lazy initialization.
     @return The StateRepository object associated with this controller.
     */
    private StateRepository getStateRepository() {
        if (stateRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            stateRepository = repositories.getStateRepository();
        }
        return stateRepository;
    }
    /**

     Registers a new store in the system.
     @param designation The designation of the store to be registered.
     @param address The address of the store to be registered.
     @param id The ID of the store to be registered.
     @param passportNumber The passport number of the store to be registered.
     @param email The email of the store to be registered.
     @return An Optional object containing the newly created store, or an empty Optional if the store could not be added.
     */
    public Optional<Store> registerStore(String designation, Address address, int id,int passportNumber,String email) {

        Store store = getStoreByDescription(designation);

        Optional<Store> newStore = Optional.empty();

        newStore = getStoreRepository().add(store);

        return newStore;
    }
    /**
     * Returns the Store that matches the given description.
     *
     * @param storeDescription the description of the Store to retrieve
     * @return the Store that matches the description, or null if not found
     */
    private Store getStoreByDescription(String storeDescription) {
        StoreRepository storeRepository = getStoreRepository();

        //Get the TaskCategory by its description
        Store storeByDescription =
                getStoreRepository().getStoreByDescription(storeDescription);

        return storeByDescription;

    }
    /**
     * Returns a list of all States.
     *
     * @return a list of all States
     */
    public List<State> getState() {
        StateRepository stateRepository = getStateRepository();
        return stateRepository.getStates();
    }
    /**
     * Returns a list of all Stores.
     *
     * @return a list of all Stores
     */
    public List<Store> getStore() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStores();
    }
    /**
     * Returns the City that matches the given description and District.
     *
     * @param cityDescription the description of the City to retrieve
     * @param district the District that the City should belong to
     * @return the City that matches the description and District, or null if not found
     */
    public City getCityByDescription(String cityDescription, District district) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        City cityByDescription =
                stateRepository.getCityByDescription(cityDescription,district);
        return cityByDescription;

    }
    /**
     * Returns the District that matches the given description and State.
     *
     * @param districtDescription the description of the District to retrieve
     * @param state the State that the District should belong to
     * @return the District that matches the description and State, or null if not found
     */
    public District getDistrictByDescription(String districtDescription,State state) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        District districtByDescription =
                stateRepository.getDistrictByDescription(districtDescription,state);
        return districtByDescription;
    }
    /**
     * Returns a list of all Districts belonging to the given State.
     *
     * @param state the State to retrieve Districts for
     * @return a list of all Districts belonging to the State
     */
    public List<District> getDistrict(State state){
        return state.getDistricts();
    }
    /**
     * Returns a list of all Cities belonging to the given District.
     *
     * @param district the District to retrieve Cities for
     * @return a list of all Cities belonging to the District
     */
    public List<City> getCities(District district){
        return district.getCities();
    }

}
