package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.City;
import pt.ipp.isep.dei.esoft.project.domain.District;
import pt.ipp.isep.dei.esoft.project.domain.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateRepository {

    private List<State> states = new ArrayList<>();

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

    public District getDistrictByDescription(String districtDescription, State state){

        District newDistrict = new District(districtDescription);
        District district = null;

        if (state.getDistricts().contains(newDistrict)) {
            district = state.getDistricts().get(state.getDistricts().indexOf(newDistrict));
        }
        if (state == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + districtDescription + "] does not exist.");
        }
        return district;
    }



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

    private boolean validateState(State state) {
        boolean isValid = !states.contains(state);
        return isValid;
    }

    public List<State> getStates() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(states);
    }
}
