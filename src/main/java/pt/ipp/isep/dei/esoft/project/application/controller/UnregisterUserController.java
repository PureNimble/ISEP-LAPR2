package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;


/**
 * A controller class for handling user registration.
 */
public class UnregisterUserController {

    private StateRepository stateRepository = null;

    /**
     * Constructs a new instance of the UnregisterUserController.
     */
    public UnregisterUserController() {
    }

    /**
     * Returns the state repository.
     *
     * @return the state repository
     */
    private StateRepository getStateRepository() {
        return null;
    }

    /**
     * Registers a new person with the given information.
     *
     * @param name the name of the person
     * @param passportNumber the passport number of the person
     * @param taxNumber the tax number of the person
     * @param state the state of the person
     * @param district the district of the person
     * @param city the city of the person
     * @param street the street of the person
     * @param zipCode the zip code of the person
     * @param emailAddress the email address of the person
     * @param phoneNumber the phone number of the person
     */
    public void registerPerson (int name, int passportNumber, int taxNumber, int state, int district, int city, int street, int zipCode, String emailAddress, int phoneNumber) {
    }

    /**
     * Returns a list of all states.
     *
     * @return a list of all states
     */
    public List<State> getState() {
        return stateRepository.getStates();
    }

    /**
     * Returns the district with the given description in the specified state.
     *
     * @param districtDescription the description of the district
     * @param state the state to search in
     * @return the district with the given description in the specified state
     */
    public District getDistrictByDescription(String districtDescription,State state) {
        return stateRepository.getDistrictByDescription(districtDescription,state);
    }

    /**
     * Returns the city with the given description in the specified district.
     *
     * @param cityDescription the description of the city
     * @param district the district to search in
     * @return the city with the given description in the specified district
     */
    public City getCityByDescription(String cityDescription, District district) {
        return stateRepository.getCityByDescription(cityDescription,district);
    }

    public State getStateByDescription(String stateDescription) {
        return stateRepository.getStateByDescription(stateDescription);
    }

}