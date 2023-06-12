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
        Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
        Address address2 = new Address("199 W 45th St",10036,new District("Manhattan"),new City("New York"),new State("New York"));
        Address address3 = new Address("9641 Sunset Blvd", 90210, new District("Beverly Hills"), new City("Los Angeles"), new State("California"));
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0);
        Store store2 = new Store("Maltip",104224,address2,1133542456,"maltip@gmail.com", 0);
        Store store3 = new Store("Elvis",224,address3,1274567809,"elvis@gmail.com", 0);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        
        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890, store1,  roles, address1);
        Employee agent2 = new Employee("agent2@this.app",12345677,12231311,"Vasco ",1123456789, store1, roles, address2);
        Employee agent3 = new Employee("agent3@this.app", 123456432, 123456789, "Manuel", 1234567892, store2,  roles, address3);
        Employee agent4 = new Employee("agent4@this.app",12345123,124356789,"Pedro",1643267806, store3, roles, address2);
        Employee agent5 = new Employee("agent5@this.app", 123456321, 17634589, "Jorge", 1345678901, store2,  roles, address3);
        Employee agent6 = new Employee("agent6@this.app",12345687,192837465,"Ruben",1765432345, store2, roles, address1);

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
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0);
        Store store2 = new Store("Maltip",104224,address2,1133542456,"maltip@gmail.com", 0);
        Store store3 = new Store("Elvis",224,address3,1274567809,"elvis@gmail.com", 0);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890, store1,  roles, address1);
        Employee agent2 = new Employee("agent2@this.app",12345677,12231311,"Vasco ",1123456789, store1, roles, address2);
        Employee agent3 = new Employee("agent3@this.app", 123456432, 123456789, "Manuel", 1234567892, store2,  roles, address3);
        Employee agent4 = new Employee("agent4@this.app",12345123,124356789,"Pedro",1643267806, store3, roles, address2);
        Employee agent5 = new Employee("agent5@this.app", 123456321, 17634589, "Jorge", 1345678901, store2,  roles, address3);
        Employee agent6 = new Employee("agent6@this.app",12345687,192837465,"Ruben",1765432345, store2, roles, address1);


        PublishedAnnouncementRepository publishedAnnouncementRepository = Repositories.getInstance().getPublishedAnnouncementRepository();
        Comission comission = new Comission(25.00);
        Comission comission1 = new Comission(10.00);

        List<String> strings = new ArrayList<>();

        strings.add("https://i.maxwork.pt/l-view/listings/12325/6516582/f608e960-5797-4a79-8232-8b46739fb035.jpg");
        strings.add("https://i.maxwork.pt/l-view/listings/12325/6516582/582185b1-40df-42b2-acbb-1fea14d37576.jpg");

        Property property1 = new Property(274,2576, new Photos(strings),address1);
        Property property2 = new Property(313,5498,new Photos("https://i.maxwork.pt/l-view/listings/12015/6532217/13acae9a-aa32-466c-9980-e132a780c4bf.jpg"),address2);


        AvailableEquipment equipment1 = new AvailableEquipment("Air Conditioning");
        AvailableEquipment equipment2 = new AvailableEquipment("Central Heating");
        AvailableEquipment equipment3 = new AvailableEquipment("None");
        AvailableEquipment equipment4 = new AvailableEquipment("Pool");

        List<String> strings1 = new ArrayList<>();
        strings1.add("https://i.maxwork.pt/l-view/listings/12291/6528121/5d6cf1eb-3131-48cd-b816-7bdad18e7787.jpg");
        strings1.add("https://i.maxwork.pt/l-view/listings/12291/6528121/a271ac18-1c04-4dec-bf32-b263fd5b6bae.jpg");
        strings1.add("https://i.maxwork.pt/l-view/listings/12291/6528121/5890a06d-3580-4066-99d2-c3cb9bec78ac.jpg");
        strings1.add("https://i.maxwork.pt/l-view/listings/12291/6528121/19597d8c-a2f8-44d5-85b9-cdfddc6c673b.jpg");

        Residence residence1 = new Residence(273, 234, 13,  123, 123, equipment1, new Photos("https://i.maxwork.pt/l-view/listings/12325/6516582/f608e960-5797-4a79-8232-8b46739fb035.jpg"),address1);
        Residence residence2 = new Residence(21373, 212334, 1,  123, 123, equipment3, new Photos(strings1),address2);
        
        PropertyType propertyType1 = new PropertyType("House");
        PropertyType propertyType2 = new PropertyType("Appartment");
        PropertyType propertyType3 = new PropertyType("Land");

        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
        TypeOfBusiness typeOfBusiness2 = new TypeOfBusiness("Rent");

        Business business1 = new Business(200000);
        Business business2 = new Business(135000);
        Business business3 = new Business(246000);
        Business business4 = new Business(98540);

        Date date1 = new GregorianCalendar(2023, Calendar.JUNE, 20).getTime();
        Date date2 = new GregorianCalendar(2023, Calendar.JUNE, 21).getTime();
        Date date3 = new GregorianCalendar(2023,Calendar.JUNE,22).getTime();
        Date date4 = new GregorianCalendar(2023, Calendar.JUNE, 23).getTime();
        Date date5 = new GregorianCalendar(2023, Calendar.JUNE, 24).getTime();
        Date date6 = new GregorianCalendar(2023,Calendar.JUNE,25).getTime();


        Client client1 = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address1, 1234567890);
        Client client2 = new Client("luna@hotmail.com", 987654321, 123456789, "Luna", address2, 1987654321);
        Client client3 = new Client("diogo@yahoo.com", 876543219, 234567891, "Diogo", address3, 1345678901);
        Client client4 = new Client("vasco@gmail.com", 765432198, 345678901, "Vasco", address1, 1112345689);
        Client client5 = new Client("rafael@gmail.com", 757356345,238433284,"Rafael",address2,1425432897);

        AnnouncementState state1 = AnnouncementState.available;
        AnnouncementState state2 = AnnouncementState.sold;

        PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date1, typeOfBusiness1, property1, propertyType1, comission1, business1, agent1, client1, 1, state1, store1);
        PublishedAnnouncement publishedAnnouncement2 = new PublishedAnnouncement(date2, typeOfBusiness2, residence2, propertyType2, comission, business2, agent2, client2, 2, state1, store1);
        PublishedAnnouncement publishedAnnouncement3 = new PublishedAnnouncement(date3, typeOfBusiness1, property1, propertyType1, comission1, business3, agent3, client3,3, state1, store1);
        PublishedAnnouncement publishedAnnouncement4 = new PublishedAnnouncement(date4, typeOfBusiness1, residence2, propertyType2, comission, business4, agent4, client4,4, state1, store2);
        PublishedAnnouncement publishedAnnouncement5 = new PublishedAnnouncement(date5, typeOfBusiness1, property1, propertyType1, comission1, business1, agent5, client5,5, state1, store2);
        PublishedAnnouncement publishedAnnouncement6 = new PublishedAnnouncement(date6, typeOfBusiness1, residence2, propertyType3, comission, business2, agent6, client2,6, state2, store3);

        publishedAnnouncementRepository.add(publishedAnnouncement1);
        publishedAnnouncementRepository.add(publishedAnnouncement2);
        publishedAnnouncementRepository.add(publishedAnnouncement3);
        publishedAnnouncementRepository.add(publishedAnnouncement6);
        publishedAnnouncementRepository.add(publishedAnnouncement4);
        publishedAnnouncementRepository.add(publishedAnnouncement5);

        addOffers(publishedAnnouncement1, publishedAnnouncement2, publishedAnnouncement3, publishedAnnouncement4, publishedAnnouncement5);
        addMessages(publishedAnnouncement1, publishedAnnouncement2, publishedAnnouncement3, publishedAnnouncement4, publishedAnnouncement5, date1, date2, date3, date4, date5);
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
        UserRepository userRepository = Repositories.getInstance().getUserRepository();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_NETWORK_MANAGER, AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin", AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd", AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Network Manager", "manager@this.app", "manager", AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "client", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Owner", "owner@this.app", "owner", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Pedro", "pedro@gmail.com", "pedro", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Luna", "luna@outlook.com", "luna", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Vasco", "vasco@yahoo.com", "vasco", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Rafael", "rafael@yopmail.com", "rafael", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Diogo", "Diogo@sapo.pt", "diogo", AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("Agent 02","agent2@this.app","agent2", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 03","agent3@this.app","agent3", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 04","agent4@this.app","agent4", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 05","agent5@this.app","agent5", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 06","agent6@this.app","agent6", AuthenticationController.ROLE_AGENT);

        userRepository.add(new Client("owner@this.app", 143256743, 222222222,"Owner",new Address("Test Avenue", 12345, new District("TEST"), new City("TEST"), new State("TEST")), 1234567891));
        userRepository.add(new Client("client@this.app", 111111111, 111111111,"Client",new Address("Test Avenue", 00000, new District("TEST"), new City("TEST"), new State("TEST")), 1234567890));
        userRepository.add(new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890));
        userRepository.add(new Client("luna@outlook.com", 234567890, 234567890, "Luna", new Address("200 Santa Monica Pier", 90401, new District("Santa Monica"), new City("Los Angeles"), new State("California")), 1029384756));
        userRepository.add(new Client("vasco@yahoo.com", 345678901, 345678901, "Vasco", new Address("1200 Getty Center Drive", 90049, new District("Crestwood Hills"), new City("Los Angeles"), new State("California")), 1910384765));
        userRepository.add(new Client("rafael@yopmail.com", 456789012, 456789012, "Rafael", new Address("1000 5th Avenue", 10028, new District("Manhattan"), new City("New York"), new State("New York")), 1425432897));
        userRepository.add(new Client("Diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1389462074));
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

        Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
        Address address2 = new Address("199 W 45th St",10036,new District("Manhattan"),new City("New York"),new State("New York"));
        Address address3 = new Address("9641 Sunset Blvd", 90210, new District("Beverly Hills"), new City("Los Angeles"), new State("California"));
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0);
        Store store2 = new Store("Maltip",104224,address2,1133542456,"maltip@gmail.com", 0);
        Store store3 = new Store("Elvis",224,address3,1274567809,"elvis@gmail.com", 0);

        storeRepository.add(store1);
        storeRepository.add(store2);
        storeRepository.add(store3);
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

    private void addOffers(PublishedAnnouncement publishedAnnouncement1, PublishedAnnouncement publishedAnnouncement2, PublishedAnnouncement publishedAnnouncement3, PublishedAnnouncement publishedAnnouncement4, PublishedAnnouncement publishedAnnouncement5) {
        OfferRepository offerRepository = Repositories.getInstance().getOfferRepository();

        offerRepository.add(new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.pending, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890)));
        offerRepository.add(new Offer("Diogo",97000, publishedAnnouncement2, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901)));
        offerRepository.add(new Offer("Luna", 135600,publishedAnnouncement3,OfferState.pending, new Client("luna@outlook.com", 234567890, 234567890, "Luna", new Address("200 Santa Monica Pier", 90401, new District("Santa Monica"), new City("Los Angeles"), new State("California")), 1987654321)));
        offerRepository.add(new Offer("Vasco", 230000,publishedAnnouncement4,OfferState.accepted, new Client("vasco@yahoo.com", 345678901, 345678901, "Vasco", new Address("1200 Getty Center Drive", 90049, new District("Crestwood Hills"), new City("Los Angeles"), new State("California")), 1112345689)));
        offerRepository.add(new Offer("Rafael", 98000,publishedAnnouncement5,OfferState.accepted, new Client("rafael@yopmail.com", 456789012, 456789012, "Rafael", new Address("1000 5th Avenue", 10028, new District("Manhattan"), new City("New York"), new State("New York")), 1425432897)));
    }

    private void addMessages(PublishedAnnouncement publishedAnnouncement1, PublishedAnnouncement publishedAnnouncement2, PublishedAnnouncement publishedAnnouncement3, PublishedAnnouncement publishedAnnouncement4, PublishedAnnouncement publishedAnnouncement5, Date date1, Date date2, Date date3, Date date4, Date date5) {
        MessageRepository messageRepository = Repositories.getInstance().getMessageRepository();

        messageRepository.add(new Message("Pedro", 1234567890, "Olá, estou interessado na propriedade!", date1, 11, 12, publishedAnnouncement1, MessageState.UNANSWERED,false));
        messageRepository.add(new Message("Luna", 1345678901, "Interessado.", date2, 20,21,publishedAnnouncement2, MessageState.UNANSWERED, false));
        messageRepository.add(new Message("Diogo", 1987654321, "Estou com interesse na propriedade!", date3,15,17,publishedAnnouncement3, MessageState.UNANSWERED, false));
        messageRepository.add(new Message("Vasco",1112345689, "Quero!", date4,9,10,publishedAnnouncement4, MessageState.ANSWERED, false));
        messageRepository.add(new Message("Rafael", 1425432897, "Podemos já fazer negócio?", date5,14,15,publishedAnnouncement5, MessageState.ANSWERED, false));
    }
}