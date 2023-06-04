package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.*;

public class Bootstrap {

  //  FileReaderRepository fileReaderRepository = new FileReaderRepository();
    //Add some task categories to the repository as bootstrap
    public void run() {
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
        try {
            addAgents();
       } catch (Exception e) {
            throw new RuntimeException(e);
        }
        addPublishedAnnouncement();
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
        Role role = new Role("Agent");
        Address address1 = new Address("Test Strett", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address2 = new Address("Avenue Walmart",22334,new District("Jefferson"),new City("Ottawa"),new State("Arizona"));
        Store store1 = new Store("Holloway",10234,address2,9383811,"holloway@gmail.com", 11);
        Store store2 = new Store("Maltip",104224,address2,9678910,"maltip@gmail.com", 16);
        Store store3 = new Store("Elvis",224,address2,9437782,"elvis@gmail.com", 3);
        
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 5551234, store1,  roles, address1);
        Employee agent2 = new Employee("agent2@this.app",12345677,12231311,"Vasco ",555661, store1, roles, address1);
        Employee agent3 = new Employee("agent3@this.app", 123456432, 123456789, "Manuel", 1234567, store2,  roles, address1);
        Employee agent4 = new Employee("agent4@this.app",12345123,124356789,"Pedro",1324567, store3, roles, address1);
        Employee agent5 = new Employee("agent5@this.app", 123456321, 17634589, "Jorge", 1432567, store2,  roles, address1);
        Employee agent6 = new Employee("agent6@this.app",12345687,192837465,"Ruben",9876543, store2, roles, address1);

        employeeRepository.add(agent1);
        employeeRepository.add(agent2);
        employeeRepository.add(agent3);
        employeeRepository.add(agent4);
        employeeRepository.add(agent5);
        employeeRepository.add(agent6);
    }

    private void addPublishedAnnouncement(){

        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        Role role = new Role("Agent");
        Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
        Address address2 = new Address("199 W 45th St",10036,new District("Manhattan"),new City("New York"),new State("New York"));
        Address address3 = new Address("9641 Sunset Blvd", 90210, new District("Beverly Hills"), new City("Los Angeles"), new State("California"));
        Store store1 = new Store("Holloway",10234,address2,9383811,"holloway@gmail.com", 11);
        Store store2 = new Store("Maltip",104224,address2,9678910,"maltip@gmail.com", 16);
        Store store3 = new Store("Elvis",224,address2,9437782,"elvis@gmail.com", 3);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 5551234, store1,  roles, address1);
        Employee agent2 = new Employee("agent2@this.app",12345677,12231311,"Vasco ",555661, store1, roles, address2);
        Employee agent3 = new Employee("agent3@this.app", 123456432, 123456789, "Manuel", 1234567, store2,  roles, address3);
        Employee agent4 = new Employee("agent4@this.app",12345123,124356789,"Pedro",1324567, store3, roles, address2);
        Employee agent5 = new Employee("agent5@this.app", 123456321, 17634589, "Jorge", 1432567, store2,  roles, address3);
        Employee agent6 = new Employee("agent6@this.app",12345687,192837465,"Ruben",9876543, store2, roles, address1);


        PublishedAnnouncementRepository publishedAnnouncementRepository = Repositories.getInstance().getPublishedAnnouncementRepository();
        Comission comission = new Comission(25.00);
        Comission comission1 = new Comission(10.00);

        Property property1 = new Property(274,2576, new Photos("https://i.maxwork.pt/l-view/listings/12325/6516582/f608e960-5797-4a79-8232-8b46739fb035.jpg"));
        Property property2 = new Property(313,5498,new Photos("https://i.maxwork.pt/l-view/listings/12015/6532217/13acae9a-aa32-466c-9980-e132a780c4bf.jpg"));

        AvailableEquipment equipment1 = new AvailableEquipment("Forno");
        AvailableEquipment equipment2 = new AvailableEquipment("Máquina");

        Residence residence1 = new Residence(273, 234, 13,  123, 123, equipment1, new Photos("https://i.maxwork.pt/l-view/listings/12325/6516582/f608e960-5797-4a79-8232-8b46739fb035.jpg"));
        Residence residence2 = new Residence(21373, 212334, 1,  123, 123, equipment2, new Photos("https://i.maxwork.pt/l-view/listings/12325/6516582/f608e960-5797-4a79-8232-8b46739fb035.jpg"));
        
        PropertyType propertyType1 = new PropertyType("House");
        PropertyType propertyType2 = new PropertyType("Appartment");
        PropertyType propertyType3 = new PropertyType("Land");

        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
        TypeOfBusiness typeOfBusiness2 = new TypeOfBusiness("Rent");

        Business business1 = new Business(200000);
        Business business2 = new Business(135000);
        Business business3 = new Business(246000);
        Business business4 = new Business(98540);

        Date date1 = new GregorianCalendar(2023, Calendar.JUNE, 1, 20,04,30).getTime();
        Date date2 = new GregorianCalendar(2023, Calendar.MAY, 28,10,51,13).getTime();
        Date date3 = new GregorianCalendar(2023,Calendar.MAY,30,17,31,42).getTime();


        PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date1, typeOfBusiness1, residence2, propertyType2, comission1, business2, agent3, address1);
        PublishedAnnouncement publishedAnnouncement2 = new PublishedAnnouncement(date2, typeOfBusiness2, residence2, propertyType1, comission1, business4, agent5, address2);
        PublishedAnnouncement publishedAnnouncement3 = new PublishedAnnouncement(date3, typeOfBusiness1, property1, propertyType3, comission, business3, agent1, address1);
        PublishedAnnouncement publishedAnnouncement4 = new PublishedAnnouncement(date3, typeOfBusiness1, residence2, propertyType1, comission, business2, agent2, address3);
        PublishedAnnouncement publishedAnnouncement5 = new PublishedAnnouncement(date3, typeOfBusiness1, residence1, propertyType1, comission1, business1, agent4, address3);
        PublishedAnnouncement publishedAnnouncement6 = new PublishedAnnouncement(date3, typeOfBusiness1, residence2, propertyType1, comission, business3, agent2, address2);

        publishedAnnouncementRepository.add(publishedAnnouncement1);
        publishedAnnouncementRepository.add(publishedAnnouncement2);
        publishedAnnouncementRepository.add(publishedAnnouncement3);
        publishedAnnouncementRepository.add(publishedAnnouncement6);
        publishedAnnouncementRepository.add(publishedAnnouncement4);
        publishedAnnouncementRepository.add(publishedAnnouncement5);

        addOffers(publishedAnnouncement1, publishedAnnouncement2, publishedAnnouncement3);
    }

    private void addAvailableEquipment(){

        AvailableEquipmentRepository availableEquipmentRepository = Repositories.getInstance().getAvailableEquipmentRepository();

        availableEquipmentRepository.add(new AvailableEquipment("Air Conditioning"));
        
        availableEquipmentRepository.add(new AvailableEquipment("Central Heating"));

        availableEquipmentRepository.add(new AvailableEquipment("None"));

        availableEquipmentRepository.add(new AvailableEquipment("Other"));

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

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER, AuthenticationController.ROLE_OWNER);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_NETWORK_MANAGER, AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin", AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd", AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Network Manager", "manager@this.app", "manager", AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Owner", "owner@this.app", "owner", AuthenticationController.ROLE_OWNER);

        authenticationRepository.addUserWithRole("Agent 02","agent2@this.app","agent2", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 03","agent3@this.app","agent3", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 04","agent4@this.app","agent4", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 05","agent5@this.app","agent5", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 06","agent6@this.app","agent6", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "client", AuthenticationController.ROLE_CLIENT);
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

        storeRepository.add(new Store("Holloway",10234,address,9383811,"holloway@gmail.com", 10));
        storeRepository.add(new Store("Maltip",104224,address,9678910,"123sttore@gmail.com", 12));
        storeRepository.add(new Store("Elvis",224,address,9437782,"dadaw@gmail.com", 11));
        storeRepository.add(new Store("Trap",4554,address,9827612,"trappp@gmail.com", 3));
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
       // comissionRepository.add(new Comission(Integer.parseInt("Other")));
    }

    private void addOffers(PublishedAnnouncement publishedAnnouncement1, PublishedAnnouncement publishedAnnouncement2, PublishedAnnouncement publishedAnnouncement3) {
        OfferRepository offerRepository = Repositories.getInstance().getOfferRepository();

        offerRepository.add(new Offer("Pedro", 200000, publishedAnnouncement1, OfferState.pending));
        offerRepository.add(new Offer("Miguel",1935000, publishedAnnouncement2, OfferState.pending));
        offerRepository.add(new Offer("Rúben", 3456, publishedAnnouncement1, OfferState.accepted));
        offerRepository.add(new Offer("Zé",19000, publishedAnnouncement1, OfferState.accepted));
        offerRepository.add(new Offer("Luna", 135600,publishedAnnouncement3,OfferState.pending));
        offerRepository.add(new Offer("Toby",35000, publishedAnnouncement1, OfferState.pending));
        offerRepository.add(new Offer("Geremias",546372, publishedAnnouncement2, OfferState.pending));
        offerRepository.add(new Offer("Vasco", 230000,publishedAnnouncement3,OfferState.accepted));
        offerRepository.add(new Offer("Rafael", 98000,publishedAnnouncement3,OfferState.accepted));
    }

    private void addMessages(PublishedAnnouncement publishedAnnouncement1, PublishedAnnouncement publishedAnnouncement2, PublishedAnnouncement publishedAnnouncement3) {
        MessageRepository messageRepository = Repositories.getInstance().getMessageRepository();

        Date date1 = new Date(21-05-2023);
        Date date2 = new Date(30-05-2023);
        Date date3 = new Date(04-06-2023);
        Date date4 = new Date(01-06-2023);

        messageRepository.add(new Message("Pedro", 1234567890, "Olá, estou interessado na propriedade!", date1, 11, 12, publishedAnnouncement1));
        messageRepository.add(new Message("Miguel", 1029384756, "Interessado.", date4, 20,21,publishedAnnouncement3));
        messageRepository.add(new Message("Luna", 1209348756, "Estou com interesse na propriedade!", date2,15,17,publishedAnnouncement3));
        messageRepository.add(new Message("Sofia",1038295647, "Quero!", date3,9,10,publishedAnnouncement2));
        messageRepository.add(new Message("Vasco", 1357924680, "Podemos já fazer negócio?", date2,14,15,publishedAnnouncement3));
    }
}