package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**

 The RegisterEmployeeController class is responsible for handling the registration of new employees in the system.
 */
public class RegisterEmployeeController {

    private RoleRepository roleRepository = null;

    private AuthenticationRepository authenticationRepository = null;

    private StoreRepository storeRepository = null;

    private StateRepository stateRepository = null;

    private EmployeeRepository employeeRepository = null;

    private UserRepository userRepository = null;
    /**

     Constructs a new RegisterEmployeeController object.
     Initializes repositories using the respective getters.
     */

    public RegisterEmployeeController() {
        getRoleRepository();
        getAuthenticationRepository();
        getStoreRepository();
        getStateRepository();
        getStateRepository();
        getEmployeeRepository();
        getUserRepository();
    }
    /**

     Returns an instance of the RoleRepository.

     If the instance does not exist, it creates one using the Repositories class.

     @return The RoleRepository instance
     */
    private RoleRepository getRoleRepository() {
        if (roleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            roleRepository = repositories.getRoleRepository();
        }
        return roleRepository;
    }
/**

    Returns an instance of the AuthenticationRepository.

    If the instance does not exist, it creates one using the Repositories class.

            @return The AuthenticationRepository instance
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

     Returns an instance of the StateRepository.

     If the instance does not exist, it creates one using the Repositories class.

     @return The StateRepository instance
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
     * Returns an instance of the EmployeeRepository, creating a new one if it is null.
     *
     * @return the EmployeeRepository instance
     */
    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;


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
     * Adds the given User to the UserRepository.
     *
     * @param user the User to add
     */
    public void addUser(User user){
        UserRepository userRepository = getUserRepository();

        userRepository.add(user);

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
     * Creates a new employee with the given parameters and associates it with a Store.
     *
     * @param email the email address of the employee
     * @param name the name of the employee
     * @param phone the phone number of the employee
     * @param rolesByDescription the roles of the employee as a list of Role objects
     * @param storeByDescription the description of the Store to associate the employee with
     * @param address the address of the employee
     * @param passportNumber the passport number of the employee
     * @param taxNumber the tax number of the employee
     * @return an optional containing the new employee, or an empty optional if the Store does not exist
     */
    public Optional<Employee> createEmployee(String email, String name,
                                             int phone, List<Role> rolesByDescription, String storeByDescription, Address address, int passportNumber, int taxNumber) {

        Store store = getStoreByDescription(storeByDescription);


        Optional<Employee> newEmployee = Optional.empty();

        if (getStoreRepository().getStores().contains(store)) {
            newEmployee = store.createEmployee(email,name,phone,rolesByDescription,store,address,passportNumber,taxNumber);
        }
        return newEmployee;
    }

    /**
     * Returns a list of Role objects that match the given list of role descriptions.
     *
     * @param rolesByDescription the list of role descriptions to retrieve
     * @return the list of Role objects that match the descriptions
     */
    public List<Role> getRolesByDescription(List<String> rolesByDescription){

        List<Role> rolesEmployee = new ArrayList<>();

        for (String roles: rolesByDescription) {
            rolesEmployee.add(new Role(roles));
        }
        return rolesEmployee;
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
     * Returns a list of all Stores.
     *
     * @return a list of all Stores
     */
    public List<Store> getStore() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStores();
    }
    /**
     * Returns a list of all Employees.
     *
     * @return a list of all Employees
     */
    public List<Employee> getEmployee() {
        EmployeeRepository employeeRepository = getEmployeeRepository();
        return employeeRepository.getEmployees();
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

    /**
     * Returns a list of all Roles.
     *
     * @return a list of all Roles
     */
    public List<Role> getRoles() {
        RoleRepository roleRepository = getRoleRepository();
        return roleRepository.getRoles();
    }


}
