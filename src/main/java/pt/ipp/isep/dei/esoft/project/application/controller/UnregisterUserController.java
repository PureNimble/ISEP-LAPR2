package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;


/**
 * A controller class for handling user registration.
 */
public class UnregisterUserController {
    /**
     * The StateRepository instance.
     */
    private StateRepository stateRepository = null;
    /**
     * The AuthenticationRepository instance.
     */
    private AuthenticationRepository authenticationRepository = null;
    /**
     * The UserRepository instance.
     */
    private UserRepository userRepository = null;

    /**
     * Constructs a new instance of the UnregisterUserController.
     */
    public UnregisterUserController() {
        getAuthenticationRepository();
        getStateRepository();
        getUserRepository();
    }


    /**
     * Returns an instance of the AuthenticationRepository.
     * <p>
     * If the instance does not exist, it creates one using the Repositories class.
     *
     * @return The AuthenticationRepository instance
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Returns an instance of the UserRepository.
     * <p>
     * If the instance does not exist, it creates one using the Repositories class.
     *
     * @return The UserRepository instance
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
     * Returns an instance of the StateRepository.
     * <p>
     * If the instance does not exist, it creates one using the Repositories class.
     *
     * @return The StateRepository instance
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
     * Register client.
     *
     * @param name               the name
     * @param email              the email
     * @param passportCardNumber the passport card number
     * @param taxNumber          the tax number
     * @param telephoneNumber    the telephone number
     * @param address            the address
     */
    public void registerClient(String name, String email, int passportCardNumber, int taxNumber, long telephoneNumber, Address address) {
        if (address == null) {
            getUserRepository().add(new Client(email, passportCardNumber, taxNumber, name, telephoneNumber));
        }else {
            getUserRepository().add(new Client(email, passportCardNumber, taxNumber, name, address,telephoneNumber));
        }
    }


    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
       return getAuthenticationRepository().passwordGenerator();
    }

    /**
     * Register user.
     *
     * @param name     the name
     * @param email    the email
     * @param password the password
     * @param role     the role
     */
    public void registerUser(String name, String email, String password, String role) {
        getAuthenticationRepository().addUserWithRole(name, email, password, role);
    }

    /**
     * Returns the State that matches the given description.
     *
     * @param stateDescription the description of the State to retrieve
     * @return the State that matches the description, or null if not found
     */
    public State getStateByDescription(String stateDescription) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        State stateByDescription =
                stateRepository.getStateByDescription(stateDescription);
        return stateByDescription;

    }

    /**
     * Returns the City that matches the given description and District.
     *
     * @param cityDescription the description of the City to retrieve
     * @param district        the District that the City should belong to
     * @return the City that matches the description and District, or null if not found
     */
    public City getCityByDescription(String cityDescription, District district) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        City cityByDescription =
                stateRepository.getCityByDescription(cityDescription, district);
        return cityByDescription;

    }


    /**
     * Returns the District that matches the given description and State.
     *
     * @param districtDescription the description of the District to retrieve
     * @param state               the State that the District should belong to
     * @return the District that matches the description and State, or null if not found
     */
    public District getDistrictByDescription(String districtDescription, State state) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        District districtByDescription =
                stateRepository.getDistrictByDescription(districtDescription, state);
        return districtByDescription;
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
     * Returns a list of all Districts belonging to the given State.
     *
     * @param state the State to retrieve Districts for
     * @return a list of all Districts belonging to the State
     */
    public List<District> getDistrict(State state) {
        return state.getDistricts();
    }

    /**
     * Returns a list of all Cities belonging to the given District.
     *
     * @param district the District to retrieve Cities for
     * @return a list of all Cities belonging to the District
     */
    public List<City> getCities(District district) {
        return district.getCities();
    }

}