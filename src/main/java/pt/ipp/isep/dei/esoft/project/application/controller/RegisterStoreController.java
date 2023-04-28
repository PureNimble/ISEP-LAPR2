package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StateRepository;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

public class RegisterStoreController {



    private StoreRepository storeRepository = null;

    private StateRepository stateRepository = null;


    public RegisterStoreController(){
        getStoreRepository();
        getStateRepository();
    }


    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    private StateRepository getStateRepository() {
        if (stateRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            stateRepository = repositories.getStateRepository();
        }
        return stateRepository;
    }

    public Optional<Store> registerStore(String designation, Address address, int id,int passportNumber,String email) {

        Store store = getStoreByDescription(designation);

        Optional<Store> newStore = Optional.empty();

        newStore = getStoreRepository().add(store);

        return newStore;
    }

    private Store getStoreByDescription(String storeDescription) {
        StoreRepository storeRepository = getStoreRepository();

        //Get the TaskCategory by its description
        Store storeByDescription =
                getStoreRepository().getStoreByDescription(storeDescription);

        return storeByDescription;

    }

    public List<State> getState() {
        StateRepository stateRepository = getStateRepository();
        return stateRepository.getStates();
    }

    public List<Store> getStore() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStores();
    }

    public City getCityByDescription(String cityDescription, District district) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        City cityByDescription =
                stateRepository.getCityByDescription(cityDescription,district);
        return cityByDescription;

    }
    public District getDistrictByDescription(String districtDescription,State state) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        District districtByDescription =
                stateRepository.getDistrictByDescription(districtDescription,state);
        return districtByDescription;
    }

    public List<District> getDistrict(State state){
        return state.getDistricts();
    }

    public List<City> getCities(District district){
        return district.getCities();
    }

}
