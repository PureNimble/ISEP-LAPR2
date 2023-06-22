package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.IOException;
import java.util.*;

/**
 * The type Bootstrap.
 */
public class Bootstrap {

    /**
     * Run.
     */

    public void run() throws IOException {

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

        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        Role role = new Role("Agent");
        Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
        Address address2 = new Address("199 W 45th St",10036,new District("Manhattan"),new City("New York"),new State("New York"));
        Address address3 = new Address("9641 Sunset Blvd", 90210, new District("Beverly Hills"), new City("Los Angeles"), new State("California"));
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0, 3);
        Store store2 = new Store("Maltip",104224,address2,1133542456,"maltip@gmail.com", 0, 2);
        Store store3 = new Store("Elvis",224,address3,1274567809,"elvis@gmail.com", 0, 1);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        
        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890, store1,  roles, address1);
        Employee agent2 = new Employee("agent2@gmail.com",12345677,12231311,"Vasco ",1123456789, store1, roles, address2);
        Employee agent3 = new Employee("agent3@this.app", 123456432, 123456789, "Manuel", 1234567892, store2,  roles, address3);
        Employee agent4 = new Employee("agent4@hotmail.app",12345123,124356789,"Pedro",1643267806, store3, roles, address2);
        Employee agent5 = new Employee("agent5@gmail.com", 123456321, 17634589, "Jorge", 1345678901, store2,  roles, address3);
        Employee agent6 = new Employee("agent6@hotmail.app",12345687,192837465,"Ruben",1765432345, store2, roles, address1);

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
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0, 3);
        Store store2 = new Store("Maltip",104224,address2,1133542456,"maltip@gmail.com", 0, 2);
        Store store3 = new Store("Elvis",224,address3,1274567809,"elvis@gmail.com", 0, 1);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890, store1,  roles, address1);
        Employee agent2 = new Employee("agent2@gmail.com",12345677,12231311,"Vasco ",1123456789, store1, roles, address2);
        Employee agent3 = new Employee("agent3@this.app", 123456432, 123456789, "Manuel", 1234567892, store2,  roles, address3);
        Employee agent4 = new Employee("agent4@hotmail.app",12345123,124356789,"Pedro",1643267806, store3, roles, address2);
        Employee agent5 = new Employee("agent5@gmail.com", 123456321, 17634589, "Jorge", 1345678901, store2,  roles, address3);
        Employee agent6 = new Employee("agent6@hotmail.app",12345687,192837465,"Ruben",1765432345, store2, roles, address1);


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

        House house = new House(5400,100,5,6,2,equipment1,"Y","Y","North",new Photos(strings),address1);

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

        PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date1, typeOfBusiness1, house, propertyType1, comission1, business1, agent1, client1, state1, store1);
        PublishedAnnouncement publishedAnnouncement2 = new PublishedAnnouncement(date2, typeOfBusiness2, residence2, propertyType2, comission, business2, agent2, client2, state1, store1);
        PublishedAnnouncement publishedAnnouncement3 = new PublishedAnnouncement(date3, typeOfBusiness1, property1, propertyType1, comission1, business3, agent3, client3, state1, store1);
        PublishedAnnouncement publishedAnnouncement4 = new PublishedAnnouncement(date4, typeOfBusiness1, residence2, propertyType2, comission, business4, agent4, client4, state1, store2);
        PublishedAnnouncement publishedAnnouncement5 = new PublishedAnnouncement(date5, typeOfBusiness1, property1, propertyType1, comission1, business1, agent5, client5, state1, store2);
        PublishedAnnouncement publishedAnnouncement6 = new PublishedAnnouncement(date6, typeOfBusiness1, residence2, propertyType3, comission, business2, agent6, client2, state2, store3);

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

        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_NETWORK_MANAGER, AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin", AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Store Manager", "storemanager@this.app", "store", AuthenticationController.ROLE_STORE_MANAGER);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd", AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Network Manager", "manager@this.app", "manager", AuthenticationController.ROLE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "client", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Owner", "owner@this.app", "owner", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Pedro", "pedro@gmail.com", "pedro", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Luna", "luna@outlook.com", "luna", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Vasco", "vasco@gmail.com", "vasco", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Rafael", "rafael@gmail.com", "rafael", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Diogo", "Diogo@sapo.pt", "diogo", AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("Agent 02","agent2@gmail.com","agent2", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 03","agent3@this.app","agent3", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 04","agent4@hotmail.app","agent4", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 05","agent5@gmail.com","agent5", AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Agent 06","agent6@hotmail.app","agent6", AuthenticationController.ROLE_AGENT);

        userRepository.add(new Client("owner@this.app", 143256743, 222222222,"Owner",new Address("Test Avenue", 12345, new District("TEST"), new City("TEST"), new State("TEST")), 1234567891));
        userRepository.add(new Client("client@this.app", 111111111, 111111111,"Client",new Address("Test Avenue", 00000, new District("TEST"), new City("TEST"), new State("TEST")), 1234567890));
        userRepository.add(new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890));
        userRepository.add(new Client("luna@outlook.com", 234567890, 234567890, "Luna", new Address("200 Santa Monica Pier", 90401, new District("Santa Monica"), new City("Los Angeles"), new State("California")), 1029384756));
        userRepository.add(new Client("vasco@gmail.com", 345678901, 345678901, "Vasco", new Address("1200 Getty Center Drive", 90049, new District("Crestwood Hills"), new City("Los Angeles"), new State("California")), 1910384765));
        userRepository.add(new Client("rafael@gmail.com", 456789012, 456789012, "Rafael", new Address("1000 5th Avenue", 10028, new District("Manhattan"), new City("New York"), new State("New York")), 1425432897));
        userRepository.add(new Client("Diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1389462074));
    }


    private void addStates() {

        StateRepository stateRepository = Repositories.getInstance().getStateRepository();

        List<City> alDistrict1Cities = new ArrayList<>();
        alDistrict1Cities.add(new City("Birmingham"));
        alDistrict1Cities.add(new City("Hoover"));
        alDistrict1Cities.add(new City("Vestavia Hills"));
        alDistrict1Cities.add(new City("Mountain Brook"));
        alDistrict1Cities.add(new City("Trussville"));

        List<City> alDistrict2Cities = new ArrayList<>();
        alDistrict2Cities.add(new City("Mobile"));
        alDistrict2Cities.add(new City("Prichard"));
        alDistrict2Cities.add(new City("Daphne"));
        alDistrict2Cities.add(new City("Fairhope"));
        alDistrict2Cities.add(new City("Spanish Fort"));

        List<City> alDistrict3Cities = new ArrayList<>();
        alDistrict3Cities.add(new City("Montgomery"));
        alDistrict3Cities.add(new City("Prattville"));
        alDistrict3Cities.add(new City("Wetumpka"));
        alDistrict3Cities.add(new City("Millbrook"));
        alDistrict3Cities.add(new City("Tallassee"));

        List<City> alDistrict4Cities = new ArrayList<>();
        alDistrict4Cities.add(new City("Huntsville"));
        alDistrict4Cities.add(new City("Madison"));
        alDistrict4Cities.add(new City("Decatur"));
        alDistrict4Cities.add(new City("Athens"));
        alDistrict4Cities.add(new City("Hartselle"));

        List<City> alDistrict5Cities = new ArrayList<>();
        alDistrict5Cities.add(new City("Tuscaloosa"));
        alDistrict5Cities.add(new City("Northport"));
        alDistrict5Cities.add(new City("Moundville"));
        alDistrict5Cities.add(new City("Brookwood"));
        alDistrict5Cities.add(new City("Coker"));

        List<District> alabamaDistricts = new ArrayList<>();
        alabamaDistricts.add(new District("Jefferson County", alDistrict1Cities));
        alabamaDistricts.add(new District("Mobile County", alDistrict2Cities));
        alabamaDistricts.add(new District("Montgomery County", alDistrict3Cities));
        alabamaDistricts.add(new District("Madison County", alDistrict4Cities));
        alabamaDistricts.add(new District("Tuscaloosa County", alDistrict5Cities));

        stateRepository.add(new State("Alabama AL", alabamaDistricts));

        // Estado 1: Califórnia (CA)
        List<City> caDistrict1Cities = new ArrayList<>();
        caDistrict1Cities.add(new City("Los Angeles"));
        caDistrict1Cities.add(new City("Long Beach"));
        caDistrict1Cities.add(new City("Santa Monica"));
        caDistrict1Cities.add(new City("Beverly Hills"));
        caDistrict1Cities.add(new City("Hollywood"));

        List<City> caDistrict2Cities = new ArrayList<>();
        caDistrict2Cities.add(new City("San Francisco"));
        caDistrict2Cities.add(new City("Oakland"));
        caDistrict2Cities.add(new City("Berkeley"));
        caDistrict2Cities.add(new City("Palo Alto"));
        caDistrict2Cities.add(new City("San Mateo"));

        List<City> caDistrict3Cities = new ArrayList<>();
        caDistrict3Cities.add(new City("San Diego"));
        caDistrict3Cities.add(new City("La Jolla"));
        caDistrict3Cities.add(new City("Carlsbad"));
        caDistrict3Cities.add(new City("Encinitas"));
        caDistrict3Cities.add(new City("Chula Vista"));

        List<City> caDistrict4Cities = new ArrayList<>();
        caDistrict4Cities.add(new City("Sacramento"));
        caDistrict4Cities.add(new City("Roseville"));
        caDistrict4Cities.add(new City("Folsom"));
        caDistrict4Cities.add(new City("Elk Grove"));
        caDistrict4Cities.add(new City("Rocklin"));

        List<City> caDistrict5Cities = new ArrayList<>();
        caDistrict5Cities.add(new City("San Jose"));
        caDistrict5Cities.add(new City("Santa Clara"));
        caDistrict5Cities.add(new City("Sunnyvale"));
        caDistrict5Cities.add(new City("Mountain View"));
        caDistrict5Cities.add(new City("Cupertino"));

        List<District> californiaDistricts = new ArrayList<>();
        californiaDistricts.add(new District("Los Angeles County", caDistrict1Cities));
        californiaDistricts.add(new District("Bay Area", caDistrict2Cities));
        californiaDistricts.add(new District("San Diego County", caDistrict3Cities));
        californiaDistricts.add(new District("Sacramento County", caDistrict4Cities));
        californiaDistricts.add(new District("Santa Clara County", caDistrict5Cities));


        stateRepository.add(new State("California CA", californiaDistricts));

// Estado 2: Texas (TX)
        List<City> txDistrict1Cities = new ArrayList<>();
        txDistrict1Cities.add(new City("Houston"));
        txDistrict1Cities.add(new City("Sugar Land"));
        txDistrict1Cities.add(new City("Pasadena"));
        txDistrict1Cities.add(new City("The Woodlands"));
        txDistrict1Cities.add(new City("Pearland"));

        List<City> txDistrict2Cities = new ArrayList<>();
        txDistrict2Cities.add(new City("Dallas"));
        txDistrict2Cities.add(new City("Fort Worth"));
        txDistrict2Cities.add(new City("Arlington"));
        txDistrict2Cities.add(new City("Plano"));
        txDistrict2Cities.add(new City("Irving"));

        List<City> txDistrict3Cities = new ArrayList<>();
        txDistrict3Cities.add(new City("Austin"));
        txDistrict3Cities.add(new City("Round Rock"));
        txDistrict3Cities.add(new City("Cedar Park"));
        txDistrict3Cities.add(new City("Georgetown"));
        txDistrict3Cities.add(new City("Pflugerville"));

        List<City> txDistrict4Cities = new ArrayList<>();
        txDistrict4Cities.add(new City("San Antonio"));
        txDistrict4Cities.add(new City("New Braunfels"));
        txDistrict4Cities.add(new City("San Marcos"));
        txDistrict4Cities.add(new City("Seguin"));
        txDistrict4Cities.add(new City("Boerne"));

        List<City> txDistrict5Cities = new ArrayList<>();
        txDistrict5Cities.add(new City("El Paso"));
        txDistrict5Cities.add(new City("Juarez"));
        txDistrict5Cities.add(new City("Las Cruces"));
        txDistrict5Cities.add(new City("Socorro"));
        txDistrict5Cities.add(new City("Horizon City"));

        List<District> texasDistricts = new ArrayList<>();
        texasDistricts.add(new District("Harris County", txDistrict1Cities));
        texasDistricts.add(new District("Dallas County", txDistrict2Cities));
        texasDistricts.add(new District("Travis County", txDistrict3Cities));
        texasDistricts.add(new District("Bexar County", txDistrict4Cities));
        texasDistricts.add(new District("El Paso County", txDistrict5Cities));

        stateRepository.add(new State("Texas TX", texasDistricts));

        // Estado 3: Florida (FL)
        List<City> flDistrict1Cities = new ArrayList<>();
        flDistrict1Cities.add(new City("Miami"));
        flDistrict1Cities.add(new City("Fort Lauderdale"));
        flDistrict1Cities.add(new City("Hollywood"));
        flDistrict1Cities.add(new City("Pompano Beach"));
        flDistrict1Cities.add(new City("Miramar"));

        List<City> flDistrict2Cities = new ArrayList<>();
        flDistrict2Cities.add(new City("Orlando"));
        flDistrict2Cities.add(new City("Kissimmee"));
        flDistrict2Cities.add(new City("Winter Park"));
        flDistrict2Cities.add(new City("Altamonte Springs"));
        flDistrict2Cities.add(new City("Oviedo"));

        List<City> flDistrict3Cities = new ArrayList<>();
        flDistrict3Cities.add(new City("Tampa"));
        flDistrict3Cities.add(new City("St. Petersburg"));
        flDistrict3Cities.add(new City("Clearwater"));
        flDistrict3Cities.add(new City("Largo"));
        flDistrict3Cities.add(new City("Brandon"));

        List<City> flDistrict4Cities = new ArrayList<>();
        flDistrict4Cities.add(new City("Jacksonville"));
        flDistrict4Cities.add(new City("St. Augustine"));
        flDistrict4Cities.add(new City("Fernandina Beach"));
        flDistrict4Cities.add(new City("Orange Park"));
        flDistrict4Cities.add(new City("Ponte Vedra Beach"));

        List<City> flDistrict5Cities = new ArrayList<>();
        flDistrict5Cities.add(new City("Tallahassee"));
        flDistrict5Cities.add(new City("Leon"));
        flDistrict5Cities.add(new City("Gadsden"));
        flDistrict5Cities.add(new City("Wakulla"));
        flDistrict5Cities.add(new City("Franklin"));

        List<District> floridaDistricts = new ArrayList<>();
        floridaDistricts.add(new District("Miami-Dade County", flDistrict1Cities));
        floridaDistricts.add(new District("Orange County", flDistrict2Cities));
        floridaDistricts.add(new District("Hillsborough County", flDistrict3Cities));
        floridaDistricts.add(new District("Duval County", flDistrict4Cities));
        floridaDistricts.add(new District("Leon County", flDistrict5Cities));

        stateRepository.add(new State("Florida FL", floridaDistricts));

// Estado 4: New York (NY)
        List<City> nyDistrict1Cities = new ArrayList<>();
        nyDistrict1Cities.add(new City("New York City"));
        nyDistrict1Cities.add(new City("Manhattan"));
        nyDistrict1Cities.add(new City("Brooklyn"));
        nyDistrict1Cities.add(new City("Queens"));
        nyDistrict1Cities.add(new City("Bronx"));

        List<City> nyDistrict2Cities = new ArrayList<>();
        nyDistrict2Cities.add(new City("Buffalo"));
        nyDistrict2Cities.add(new City("Niagara Falls"));
        nyDistrict2Cities.add(new City("Amherst"));
        nyDistrict2Cities.add(new City("Cheektowaga"));
        nyDistrict2Cities.add(new City("Lockport"));

        List<City> nyDistrict3Cities = new ArrayList<>();
        nyDistrict3Cities.add(new City("Rochester"));
        nyDistrict3Cities.add(new City("Irondequoit"));
        nyDistrict3Cities.add(new City("Greece"));
        nyDistrict3Cities.add(new City("Webster"));
        nyDistrict3Cities.add(new City("Penfield"));

        List<City> nyDistrict4Cities = new ArrayList<>();
        nyDistrict4Cities.add(new City("Albany"));
        nyDistrict4Cities.add(new City("Schenectady"));
        nyDistrict4Cities.add(new City("Troy"));
        nyDistrict4Cities.add(new City("Colonie"));
        nyDistrict4Cities.add(new City("Guilderland"));

        List<City> nyDistrict5Cities = new ArrayList<>();
        nyDistrict5Cities.add(new City("Syracuse"));
        nyDistrict5Cities.add(new City("Utica"));
        nyDistrict5Cities.add(new City("Rome"));
        nyDistrict5Cities.add(new City("Cicero"));
        nyDistrict5Cities.add(new City("Clay"));

        List<District> newYorkDistricts = new ArrayList<>();
        newYorkDistricts.add(new District("New York County", nyDistrict1Cities));
        newYorkDistricts.add(new District("Erie County", nyDistrict2Cities));
        newYorkDistricts.add(new District("Monroe County", nyDistrict3Cities));
        newYorkDistricts.add(new District("Albany County", nyDistrict4Cities));
        newYorkDistricts.add(new District("Onondaga County", nyDistrict5Cities));

        stateRepository.add(new State("New York NY", newYorkDistricts));


        // Estado 5: Illinois (IL)
        List<City> ilDistrict1Cities = new ArrayList<>();
        ilDistrict1Cities.add(new City("Chicago"));
        ilDistrict1Cities.add(new City("Naperville"));
        ilDistrict1Cities.add(new City("Aurora"));
        ilDistrict1Cities.add(new City("Evanston"));
        ilDistrict1Cities.add(new City("Skokie"));

        List<City> ilDistrict2Cities = new ArrayList<>();
        ilDistrict2Cities.add(new City("Springfield"));
        ilDistrict2Cities.add(new City("Decatur"));
        ilDistrict2Cities.add(new City("Bloomington"));
        ilDistrict2Cities.add(new City("Champaign"));
        ilDistrict2Cities.add(new City("Urbana"));

        List<City> ilDistrict3Cities = new ArrayList<>();
        ilDistrict3Cities.add(new City("Rockford"));
        ilDistrict3Cities.add(new City("Belvidere"));
        ilDistrict3Cities.add(new City("Loves Park"));
        ilDistrict3Cities.add(new City("Machesney Park"));
        ilDistrict3Cities.add(new City("Freeport"));

        List<City> ilDistrict4Cities = new ArrayList<>();
        ilDistrict4Cities.add(new City("Peoria"));
        ilDistrict4Cities.add(new City("Bloomington"));
        ilDistrict4Cities.add(new City("Pekin"));
        ilDistrict4Cities.add(new City("Washington"));
        ilDistrict4Cities.add(new City("East Peoria"));

        List<City> ilDistrict5Cities = new ArrayList<>();
        ilDistrict5Cities.add(new City("Naperville"));
        ilDistrict5Cities.add(new City("Aurora"));
        ilDistrict5Cities.add(new City("Joliet"));
        ilDistrict5Cities.add(new City("Bolingbrook"));
        ilDistrict5Cities.add(new City("Plainfield"));

        List<District> illinoisDistricts = new ArrayList<>();
        illinoisDistricts.add(new District("Cook County", ilDistrict1Cities));
        illinoisDistricts.add(new District("Sangamon County", ilDistrict2Cities));
        illinoisDistricts.add(new District("Winnebago County", ilDistrict3Cities));
        illinoisDistricts.add(new District("Peoria County", ilDistrict4Cities));
        illinoisDistricts.add(new District("Will County", ilDistrict5Cities));

        stateRepository.add(new State("Illinois IL", illinoisDistricts));

// Estado 6: Colorado (CO)
        List<City> coDistrict1Cities = new ArrayList<>();
        coDistrict1Cities.add(new City("Denver"));
        coDistrict1Cities.add(new City("Aurora"));
        coDistrict1Cities.add(new City("Lakewood"));
        coDistrict1Cities.add(new City("Thornton"));
        coDistrict1Cities.add(new City("Westminster"));

        List<City> coDistrict2Cities = new ArrayList<>();
        coDistrict2Cities.add(new City("Colorado Springs"));
        coDistrict2Cities.add(new City("Pueblo"));
        coDistrict2Cities.add(new City("Canon City"));
        coDistrict2Cities.add(new City("Fountain"));
        coDistrict2Cities.add(new City("Woodland Park"));

        List<City> coDistrict3Cities = new ArrayList<>();
        coDistrict3Cities.add(new City("Boulder"));
        coDistrict3Cities.add(new City("Longmont"));
        coDistrict3Cities.add(new City("Louisville"));
        coDistrict3Cities.add(new City("Lafayette"));
        coDistrict3Cities.add(new City("Superior"));

        List<City> coDistrict4Cities = new ArrayList<>();
        coDistrict4Cities.add(new City("Fort Collins"));
        coDistrict4Cities.add(new City("Loveland"));
        coDistrict4Cities.add(new City("Greeley"));
        coDistrict4Cities.add(new City("Windsor"));
        coDistrict4Cities.add(new City("Estes Park"));

        List<City> coDistrict5Cities = new ArrayList<>();
        coDistrict5Cities.add(new City("Aspen"));
        coDistrict5Cities.add(new City("Vail"));
        coDistrict5Cities.add(new City("Glenwood Springs"));
        coDistrict5Cities.add(new City("Steamboat Springs"));
        coDistrict5Cities.add(new City("Rifle"));

        List<District> coloradoDistricts = new ArrayList<>();
        coloradoDistricts.add(new District("Denver County", coDistrict1Cities));
        coloradoDistricts.add(new District("El Paso County", coDistrict2Cities));
        coloradoDistricts.add(new District("Boulder County", coDistrict3Cities));
        coloradoDistricts.add(new District("Larimer County", coDistrict4Cities));
        coloradoDistricts.add(new District("Pitkin County", coDistrict5Cities));

        stateRepository.add(new State("Colorado CO", coloradoDistricts));

// Estado 7: Pennsylvania (PA)
        List<City> paDistrict1Cities = new ArrayList<>();
        paDistrict1Cities.add(new City("Philadelphia"));
        paDistrict1Cities.add(new City("Pittsburgh"));
        paDistrict1Cities.add(new City("Allentown"));
        paDistrict1Cities.add(new City("Erie"));
        paDistrict1Cities.add(new City("Reading"));

        List<City> paDistrict2Cities = new ArrayList<>();
        paDistrict2Cities.add(new City("Harrisburg"));
        paDistrict2Cities.add(new City("York"));
        paDistrict2Cities.add(new City("Lancaster"));
        paDistrict2Cities.add(new City("Hershey"));
        paDistrict2Cities.add(new City("Carlisle"));

        List<City> paDistrict3Cities = new ArrayList<>();
        paDistrict3Cities.add(new City("Scranton"));
        paDistrict3Cities.add(new City("Wilkes-Barre"));
        paDistrict3Cities.add(new City("Bethlehem"));
        paDistrict3Cities.add(new City("Easton"));
        paDistrict3Cities.add(new City("Hazleton"));

        List<City> paDistrict4Cities = new ArrayList<>();
        paDistrict4Cities.add(new City("Allentown"));
        paDistrict4Cities.add(new City("Bethlehem"));
        paDistrict4Cities.add(new City("Easton"));
        paDistrict4Cities.add(new City("Whitehall"));
        paDistrict4Cities.add(new City("Emmaus"));

        List<City> paDistrict5Cities = new ArrayList<>();
        paDistrict5Cities.add(new City("Pittsburgh"));
        paDistrict5Cities.add(new City("Monroeville"));
        paDistrict5Cities.add(new City("Greensburg"));
        paDistrict5Cities.add(new City("Cranberry Township"));
        paDistrict5Cities.add(new City("Washington"));

        List<District> pennsylvaniaDistricts = new ArrayList<>();
        pennsylvaniaDistricts.add(new District("Philadelphia County", paDistrict1Cities));
        pennsylvaniaDistricts.add(new District("Dauphin County", paDistrict2Cities));
        pennsylvaniaDistricts.add(new District("Lackawanna County", paDistrict3Cities));
        pennsylvaniaDistricts.add(new District("Lehigh County", paDistrict4Cities));
        pennsylvaniaDistricts.add(new District("Allegheny County", paDistrict5Cities));

        stateRepository.add(new State("Pennsylvania PA", pennsylvaniaDistricts));

    }

    private void addStores() {
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();

        Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
        Address address2 = new Address("199 W 45th St",10036,new District("Manhattan"),new City("New York"),new State("New York"));
        Address address3 = new Address("9641 Sunset Blvd", 90210, new District("Beverly Hills"), new City("Los Angeles"), new State("California"));
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 21, 3);
        Store store2 = new Store("Maltip",104224,address2,1133542456,"maltip@gmail.com", 30, 2);
        Store store3 = new Store("Elvis",224,address3,1274567809,"elvis@gmail.com", 15, 1);


//        storeRepository.add(store1);
//
//        storeRepository.add(store2);
//        storeRepository.add(store3);


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
//
//        offerRepository.add(new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1));
//        offerRepository.add(new Offer("Diogo",97000, publishedAnnouncement2, OfferState.accepted, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),2));
//        offerRepository.add(new Offer("Luna", 135600,publishedAnnouncement3,OfferState.accepted, new Client("luna@outlook.com", 234567890, 234567890, "Luna", new Address("200 Santa Monica Pier", 90401, new District("Santa Monica"), new City("Los Angeles"), new State("California")), 1987654321),3));
//        offerRepository.add(new Offer("Vasco", 140000,publishedAnnouncement4,OfferState.accepted, new Client("vasco@gmail.com", 345678901, 345678901, "Vasco", new Address("1200 Getty Center Drive", 90049, new District("Crestwood Hills"), new City("Los Angeles"), new State("California")), 1112345689),4));
//        offerRepository.add(new Offer("Rafael", 98000,publishedAnnouncement5,OfferState.accepted, new Client("rafael@gmail.com", 456789012, 456789012, "Rafael", new Address("1000 5th Avenue", 10028, new District("Manhattan"), new City("New York"), new State("New York")), 1425432897),5));
    }

    private void addMessages(PublishedAnnouncement publishedAnnouncement1, PublishedAnnouncement publishedAnnouncement2, PublishedAnnouncement publishedAnnouncement3, PublishedAnnouncement publishedAnnouncement4, PublishedAnnouncement publishedAnnouncement5, Date date1, Date date2, Date date3, Date date4, Date date5) {
        MessageRepository messageRepository = Repositories.getInstance().getMessageRepository();

        messageRepository.add(new Message("Pedro", 1234567890, "Olá, estou interessado na propriedade!", date1, 11, 12, publishedAnnouncement1, MessageState.UNANSWERED,false));
        messageRepository.add(new Message("Luna", 1345678901, "Interessado.", date2, 20,21,publishedAnnouncement2, MessageState.UNANSWERED, false));
        messageRepository.add(new Message("Diogo", 1987654321, "Estou com interesse na propriedade!", date3,15,17,publishedAnnouncement3, MessageState.UNANSWERED, false));
        messageRepository.add(new Message("Vasco",1112345689, "Quero!", date4,9,10,publishedAnnouncement4, MessageState.UNANSWERED, false));
        messageRepository.add(new Message("Rafael", 1425432897, "Podemos já fazer negócio?", date5,14,15,publishedAnnouncement5, MessageState.UNANSWERED, true));
        messageRepository.add(new Message("Vasco",1112345689, "Your booking request has been decline", date4,9,10,publishedAnnouncement5, MessageState.ANSWERED, false));
        messageRepository.add(new Message("Vasco",1112345689, "Your booking request has been accepted", date4,9,10,publishedAnnouncement3, MessageState.ANSWERED, true));
        messageRepository.add(new Message("Rafael", 1425432897, "Your booking request has been accepted", date5,14,15,publishedAnnouncement2, MessageState.ANSWERED, true));

    }
}