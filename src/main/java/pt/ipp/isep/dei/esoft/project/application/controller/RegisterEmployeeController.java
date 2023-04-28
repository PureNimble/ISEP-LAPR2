package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterEmployeeController {

    private RoleRepository roleRepository = null;

    private AuthenticationRepository authenticationRepository = null;

    private StoreRepository storeRepository = null;

    private StateRepository stateRepository = null;

    private EmployeeRepository employeeRepository = null;

    private UserRepository userRepository = null;

    public RegisterEmployeeController() {
        getRoleRepository();
        getAuthenticationRepository();
        getStoreRepository();
        getStateRepository();
        getStateRepository();
        getEmployeeRepository();
        getUserRepository();
    }

    private RoleRepository getRoleRepository() {
        if (roleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            roleRepository = repositories.getRoleRepository();
        }
        return roleRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
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


    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;


    }


    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            userRepository = repositories.getUserRepository();
        }
        return userRepository;

    }

    public void addUser(User user){
        UserRepository userRepository = getUserRepository();

        userRepository.add(user);

    }


    private Store getStoreByDescription(String storeDescription) {
        StoreRepository storeRepository = getStoreRepository();

        //Get the TaskCategory by its description
        Store storeByDescription =
                getStoreRepository().getStoreByDescription(storeDescription);

        return storeByDescription;

    }


    public Optional<Employee> createEmployee(String email, String name,
                                             int phone, List<Role> rolesByDescription, String storeByDescription, Address address, int passportNumber, int taxNumber) {

        Store store = getStoreByDescription(storeByDescription);


        Optional<Employee> newEmployee = Optional.empty();

        if (getStoreRepository().getStores().contains(store)) {
            newEmployee = store.createEmployee(email,name,phone,rolesByDescription,store,address,passportNumber,taxNumber);
        }
        return newEmployee;
    }


    public List<Role> getRolesByDescription(List<String> rolesByDescription){

        List<Role> rolesEmployee = new ArrayList<>();

        for (String roles: rolesByDescription) {
            rolesEmployee.add(new Role(roles));
        }
        return rolesEmployee;
    }



    public State getStateByDescription(String stateDescription) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        State stateByDescription =
                stateRepository.getStateByDescription(stateDescription);
        return stateByDescription;

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





    public List<Store> getStore() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStores();
    }

    public List<Employee> getEmployee() {
        EmployeeRepository employeeRepository = getEmployeeRepository();
        return employeeRepository.getEmployees();
    }


    public List<State> getState() {
        StateRepository stateRepository = getStateRepository();
        return stateRepository.getStates();
    }

    public List<Role> getRoles() {
        RoleRepository roleRepository = getRoleRepository();
        return roleRepository.getRoles();
    }


}
