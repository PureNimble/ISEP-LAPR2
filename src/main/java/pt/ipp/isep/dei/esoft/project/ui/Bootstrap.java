package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addRoles();
        addUsers();
        addStates();
        addStores();
        addUsers();
        addPropertyTypes();
        addComission();
        addTypeOfBusiness();
        addAvailableEquipment();

    }

    private void addAvailableEquipment(){

        AvailableEquipmentRepository availableEquipmentRepository = Repositories.getInstance().getAvailableEquipmentRepository();

        availableEquipmentRepository.add(new AvailableEquipment("Air conditioning"));
        availableEquipmentRepository.add(new AvailableEquipment("Central Heating"));

    }


    private void addRoles() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
        roleRepository.add(new Role("Admin"));
        roleRepository.add(new Role("Agent"));
        roleRepository.add(new Role("Store Manager"));
        roleRepository.add(new Role("Store Network Manager"));

    }

    private void addTypeOfBusiness(){

        TypeOfBusinessRepository typeOfBusinessRepository = Repositories.getInstance().getTypeOfBusinessRepository();

        typeOfBusinessRepository.add(new TypeOfBusiness("Buy"));
        typeOfBusinessRepository.add(new TypeOfBusiness("Rent"));

    }


    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization

        Address adress = new Address("",555,new District("dwadw"),new City("dwada"),new State("WADDA"));
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER, AuthenticationController.ROLE_OWNER);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Owner", "owner@this.app", "owner", AuthenticationController.ROLE_OWNER);



    }


    private void addStates() {

        StateRepository stateRepository = Repositories.getInstance().getStateRepository();
        List<City> cities = new ArrayList<>();
        cities.add(new City("Hoover"));
        cities.add(new City("Mulga"));
        cities.add(new City("Clay"));
        cities.add(new City("Cardiff"));

        List<District> districts = new ArrayList<>();
        districts.add(new District("Jefferson",cities));
        stateRepository.add(new State("Alabama AL",districts));

        List<City> cities1 = new ArrayList<>();
        cities1.add(new City("Marana"));
        cities1.add(new City("Three Points"));
        cities1.add(new City("Summit"));
        cities1.add(new City("Oracle"));
        cities1.add(new City("Vail"));


        List<District> districts1 = new ArrayList<>();
        districts1.add(new District("Pima",cities1));
        stateRepository.add(new State("Arizona AZ",districts1));

    }

    private void addStores() {
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();

        Address address = new Address("Avenue Walmart",22334,new District("Jefferson"),new City("Ottawa"),new State("Arizona"));

        storeRepository.add(new Store("Holloway",10234,address,9383811,"holloway@gmail.com"));
        storeRepository.add(new Store("Maltip",104224,address,9678910,"123sttore@gmail.com"));
        storeRepository.add(new Store("Elvis",224,address,9437782,"dadaw@gmail.com"));
        storeRepository.add(new Store("Trap",4554,address,9827612,"trappp@gmail.com"));


    }

    private void addPropertyTypes() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        PropertyTypeRepository propertyTypeRepostiory = Repositories.getInstance().getPropertyTypeRepository();
        propertyTypeRepostiory.add(new PropertyType("House"));
        propertyTypeRepostiory.add(new PropertyType("Land"));
        propertyTypeRepostiory.add(new PropertyType("Appartment"));


    }

    private void addComission(){
        ComissionRepository comissionRepository = Repositories.getInstance().getComissionRepository();

        comissionRepository.add(new Comission(50));
        comissionRepository.add(new Comission(20));
        comissionRepository.add(new Comission(10.5));
        comissionRepository.add(new Comission(23.7));

    }


}
