package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StoreRepositoryTest {
    private StoreRepository storeRepository;
    private List<Store> stores;

    private Role role;

    private PublishedAnnouncement publishedAnnouncement, publishedAnnouncement1;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType;

    private PropertyType propertyType2;

    private Employee employee, employee1;


    private Date date = new Date();

    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private Comission comission;

    private Business business, business1;

    private Client client;


    private Address address2;

    private Store store;


    @BeforeEach
    void setUp() {
        stores = new ArrayList<>();
        storeRepository = new StoreRepository();
    }

    @BeforeEach
    void setUpPropertys() {
        setUpPropertyTypes();
        setUpBusiness();
        setUpAddress();
        setUpStore();
        setEmployees();
        setUpTypeOfBusiness();
        setUpRoles();
        setUpPublishedAnnouncements();
        setUpComission();
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South",address2);
        land = new Property(5, 1000,address2);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"),address2);
    }

    @BeforeEach
    void setUpPropertyTypes() {
        propertyType = new PropertyType("House");
        propertyType2 = new PropertyType("Land");
    }

    @BeforeEach
    void setUpTypeOfBusiness() {
        typeOfBusiness = new TypeOfBusiness("Sale");
        typeOfBusiness1 = new TypeOfBusiness("Rent");
    }

    @BeforeEach
    void setUpComission() {
        double comissionValue = 50.0;
        comission = new Comission(comissionValue);

    }

    @BeforeEach
    void setUpBusiness() {
        Double price = 1000.32;
        business = new Business(price);
        Double price1 = 102.213;
        business1 = new Business(price1);
    }

    @BeforeEach
    void setUpClient() {
        client = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address2, 1234567890);
    }

    @BeforeEach
    void setUpRoles() {
        role = new Role("Agent");
    }

    @BeforeEach
    void setUpAddress() {
        address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
    }

    @BeforeEach
    void setUpStore() {
        store = new Store("Test Store", 1, address2, 5551234, "test@store.com",0,1 );
    }


    @BeforeEach
    void setUpPublishedAnnouncements() {
        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, house, propertyType, comission, business, 67, employee, client, AnnouncementState.available, store);
        publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, land, propertyType, comission, business, 81, employee1, client, AnnouncementState.available, store);

    }

    @BeforeEach
    void setEmployees() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee("emailExample@this.app", 12, 12, "nome", 1234567, store, roles, address2);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);

    }

    @Test
    void getStoreByDescription() {

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com",0,1);
        Store store2 = new Store("Test Store", 2, address, 5551234, "test@store.com",0,1);

        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);

        Store result = storeRepository.getStoreByDescription("2");

        assertEquals(store2, result);
    }

    @Test
    void add() {

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store1", 1, address, 123456789, "store1@test.com",0,1);
        Optional<Store> result = storeRepository.add(store);

        assertTrue(storeRepository.getStores().contains(store));
        assertTrue(result.isPresent());
        assertEquals(store, result.get());
    }

    @Test
    void getStores() {

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com",0,1);
        Store store2 = new Store("Test Store", 2, address, 5551234, "test@store.com",0,1);
        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);

        assertEquals(stores, storeRepository.getStores());
    }

    @Test
    void getStoresProperty() {
        // Arrange
        Address address1 = new Address("Street1", 12345, new District("Test District1"), new City("Test City1"), new State("Test State1"));
        Address address2 = new Address("Street2", 67890, new District("Test District2"), new City("Test City2"), new State("Test State2"));
        Store store1 = new Store("Store1", 1, address1, 123456789, "store1@test.com", 0,1);
        Store store2 = new Store("Store2", 2, address2, 5551234, "store2@test.com", 0,1);
        Date date1 = new Date(2023, Calendar.JANUARY, 1);
        Date date2 = new Date(2023, Calendar.FEBRUARY,1);
        Date date3 = new Date(2023,Calendar.MARCH,1);
        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
        TypeOfBusiness typeOfBusiness2 = new TypeOfBusiness("Rent");
        Client client1 = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address2, 1234567890);
        Client client2 = new Client("luna@isep.ipp.pt", 123456789, 987654321, "Pedro", address2, 1234567890);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee1 = new Employee("emailExample@this.app", 12, 12, "nome", 1234567, store1, roles, address2);
        Employee employee2 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store2, roles, address2);


        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);
        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();
        PublishedAnnouncement announcement1 = new PublishedAnnouncement(date1, typeOfBusiness1, land, propertyType, comission, business, 80, employee2, client1, AnnouncementState.available, store2);
        PublishedAnnouncement announcement2 = new PublishedAnnouncement(date2, typeOfBusiness2, land, propertyType, comission, business, 81, employee2, client1, AnnouncementState.available, store1);
        PublishedAnnouncement announcement3 = new PublishedAnnouncement(date3, typeOfBusiness1, land, propertyType, comission, business, 82, employee1, client2, AnnouncementState.available, store1);;
        publishedAnnouncements.add(announcement1);
        publishedAnnouncements.add(announcement2);
        publishedAnnouncements.add(announcement3);

        // Act
        storeRepository.getStoresProperty(publishedAnnouncements);

        // Assert
        assertEquals(2, store1.getListing());
        assertEquals(1, store2.getListing());
    }

    @Test
    void getStoresByMostAvailableListings() {
        // Arrange
        List<Store> stores = new ArrayList<>();  // Instantiate the stores list
        Address address1 = new Address("Street1", 12345, new District("Test District1"), new City("Test City1"), new State("Test State1"));
        Address address2 = new Address("Street2", 67890, new District("Test District2"), new City("Test City2"), new State("Test State2"));
        Store store1 = new Store("Store1", 1, address1, 123456789, "store1@test.com", 2, 1);
        Store store2 = new Store("Store2", 2, address2, 5551234, "store2@test.com", 3, 1);
        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);

        // Act
        List<Store> sortedStores = storeRepository.getStoresByMostAvailableListings();

        // Assert
        assertEquals(2, sortedStores.size());
        assertEquals(store1, sortedStores.get(0));  // Corrected assertion order
        assertEquals(store2, sortedStores.get(1));  // Corrected assertion order
    }

    @Test
    void createStoreByFileReading() {
    }
}