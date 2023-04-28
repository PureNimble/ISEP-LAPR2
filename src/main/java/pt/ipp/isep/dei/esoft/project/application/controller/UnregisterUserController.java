package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;


public class UnregisterUserController {

    private StateRepository stateRepository = null;



    public UnregisterUserController() {
    }

    private StateRepository getStateRepository() {
        return null;
    }

    public void registerPerson (int name, int passportNumber, int taxNumber, int state, int district, int city, int street, int zipCode, String emailAddress, int phoneNumber) {
    }

    public List<State> getState() {
        return stateRepository.getStates();
    }

    public District getDistrictByDescription(String districtDescription,State state) {
        return stateRepository.getDistrictByDescription(districtDescription,state);
    }

    public City getCityByDescription(String cityDescription, District district) {
        return stateRepository.getCityByDescription(cityDescription,district);
    }
}