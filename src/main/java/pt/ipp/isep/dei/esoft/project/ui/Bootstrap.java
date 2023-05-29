package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.*;

public class Bootstrap {

  //  FileReaderRepository fileReaderRepository = new FileReaderRepository();
    //Add some task categories to the repository as bootstrap
    public void run() throws Exception {
//        try {
//            fileReaderRepository.readAndAddToList();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        addRoles();
        addUsers();
        addStates();
        addStores();
        addUsers();
        addPropertyTypes();
        addComission();
        addTypeOfBusiness();
        addAvailableEquipment();
//        try {
            addAgents();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        addPublishedAnnouncement();
//        addOffers();

    }


    private void addAgents() throws Exception {
//        List<FileReaderClass> fileReaderClasses = fileReaderRepository.getDataFromList();
//        for(FileReaderClass fileReaderClass: fileReaderClasses){
//            System.out.println(fileReaderClass.getProperty_location());
//            String[] array = fileReaderClass.getProperty_location().split(",");
//            String street = "";
//            String zipcode = array[array.length-1].replaceAll("[^\\d.]","");
//            if (array.length == 4) {
//                Address address = new Address(array[0], Integer.parseInt(zipcode), new District(array[array.length-3]), new City(array[array.length-2]), new State(array[array.length-1]));
//            } else {
//                for(int i=0;i<array.length-4;i++){
//                    street = street.concat(String.valueOf(array[i]));
//                }
//                Address address = new Address(street, Integer.parseInt(zipcode), new District(array[array.length-4]), new City(array[array.length-3]), new State(array[array.length-2]));
//            }
//            //addressRepository -> guardar num novo arraylist de adresses
//        }
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));

        Employee agent1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store,  roles, address);

        Employee agent2 = new Employee("agent2@this.app",12345677,12231311,"Agent 02",555661,store,roles,address);

        employeeRepository.add(agent1);
        employeeRepository.add(agent2);
    }

    private void addPublishedAnnouncement(){

        PublishedAnnouncementRepository publishedAnnouncementRepository = Repositories.getInstance().getPublishedAnnouncementRepository();
        Comission com = new Comission(25.00);
        Property property = new Property(2,2);
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Business business = new Business(200);
        Date date1 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date date2 = new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime();
        PublishedAnnouncement p1 = new PublishedAnnouncement(date1, typeOfBusiness, property, propertyType, com, business);
        PublishedAnnouncement p2 = new PublishedAnnouncement(date2, typeOfBusiness, property, propertyType, com, business);
        publishedAnnouncementRepository.add(p1);
        publishedAnnouncementRepository.add(p2);

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
        UserRepository userRepository = Repositories.getInstance().getUserRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER, AuthenticationController.ROLE_OWNER);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_NETWORK_MANAGER, AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Network Manager", "manager@this.app", "manager",
                AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Owner", "owner@this.app", "owner", AuthenticationController.ROLE_OWNER);

        authenticationRepository.addUserWithRole("Agent 02","agent2@this.app","agent02",AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                AuthenticationController.ROLE_CLIENT);


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
/* 
    private void addOffers() {

        OfferRepository offerRepository = Repositories.getInstance().getOfferRepository();

        Comission comission1 = new Comission(5.00);
        Comission comission2 = new Comission(10.00);

        Property property1 = new Property(185,2348);
        Property property2 = new Property(341,679);

        PropertyType propertyType1 = new PropertyType("House");
        PropertyType propertyType2 = new PropertyType("Apartment");

        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
        TypeOfBusiness typeOfBusiness2 = new TypeOfBusiness("Rent");

        Business business1 = new Business(200000);
        Business business2 = new Business(345);

        Date date1 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date date2 = new GregorianCalendar(2023,Calendar.JANUARY, 2).getTime();

        Address address1 = new Address("Avenue Walmart",22334,new District("Jefferson"),new City("Ottawa"),new State("Arizona"));
       Address address2 = new Address("Avenue Tesco", 90210, new District("Beverly Hills"), new City("Los Angeles"), new State("California"));

        Client client1 = new Client("pedro@this.app", 123456789,123456789,"Pedro",address1,123456789);
        Client client2 = new Client("miguel@this.app", 987654321,987654321, "Miguel", address2, 987654321);

        PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date1,typeOfBusiness1, property1, propertyType1, comission1, business1);
        PublishedAnnouncement publishedAnnouncement2 = new PublishedAnnouncement(date2, typeOfBusiness2, property2, propertyType2, comission2, business2);

       offerRepository.add(new Offer("Pedro", 200000, client1, publishedAnnouncement1 ));
        offerRepository.add(new Offer("Miguel", 3456, client2, publishedAnnouncement2));
    }
*/

}
