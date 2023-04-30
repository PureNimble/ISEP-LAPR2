package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.City;
import pt.ipp.isep.dei.esoft.project.domain.District;
import pt.ipp.isep.dei.esoft.project.domain.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**

 The StateRepository class provides a repository for storing and accessing States, Districts, and Cities.
 */
public class StateRepository {

    private List<State> states = new ArrayList<>();

    /**

     Retrieves a State object based on its description.

     @param stateDescription the description of the State object to retrieve.

     @return the State object with the specified description.

     @throws IllegalArgumentException if the specified State does not exist in the repository.
     */
    public State getStateByDescription(String stateDescription){

        State newState = new State(stateDescription);
        State state = null;
        if (states.contains(newState)) {
            state = states.get(states.indexOf(newState));
        }
        if (state == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + stateDescription + "] does not exist.");
        }
        return state;
    }

    /**

     Retrieves a District object based on its description and the State it belongs to.

     @param districtDescription the description of the District object to retrieve.

     @param state the State object that the District belongs to.

     @return the District object with the specified description.

     @throws IllegalArgumentException if the specified District does not exist in the repository.
     */
    public District getDistrictByDescription(String districtDescription, State state){

        District newDistrict = new District(districtDescription);
        District district = null;

        if (state.getDistricts().contains(newDistrict)) {
            district = state.getDistricts().get(state.getDistricts().indexOf(newDistrict));
        }
        if (district == null) {
            throw new IllegalArgumentException(
                    "District requested for [" + districtDescription + "] does not exist.");
        }
        return district;
    }

    /**

     Retrieves a City object based on its description and the District it belongs to.

     @param cityDescription the description of the City object to retrieve.

     @param district the District object that the City belongs to.

     @return the City object with the specified description.

     @throws IllegalArgumentException if the specified City does not exist in the repository.
     */
    public City getCityByDescription(String cityDescription, District district){

        City newCity = new City(cityDescription);
        City city = null;
        if (district.getCities().contains(newCity)) {
            city = district.getCities().get(district.getCities().indexOf(newCity));
        }
        if (city == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + cityDescription + "] does not exist.");
        }
        return city;
    }

    /**

     Adds a State object to the repository.

     @param state the State object to add to the repository.

     @return an Optional containing the added State object, or an empty Optional if the operation failed.
     */
    public Optional<State> add(State state){

        Optional<State> newState = Optional.empty();
        boolean operationSuccess = false;

        if (validateState(state)) {
            newState = Optional.of(state.clone());
            operationSuccess = states.add(newState.get());
        }

        if (!operationSuccess) {
            newState = Optional.empty();
        }

        return newState;

    }

    /**

     Validates whether a State object can be added to the repository.
     @param state the State object to validate.
     @return true if the State object is not already in the repository, false otherwise.
     */
    private boolean validateState(State state) {
        boolean isValid = !states.contains(state);
        return isValid;
    }

    /**

     Retrieves a defensive copy of the List of all States in the repository.
     @return a List containing all the States in the repository.
     */
    public List<State> getStates() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(states);
    }
}
