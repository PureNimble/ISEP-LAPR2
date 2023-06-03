package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PublishAnnouncementControllerTest {
    private PropertyTypeRepository propertyTypeRepository;
    private ComissionRepository repository;
    private TypeOfBusinessRepository repository1;
    private AvailableEquipmentRepository availableEquipmentRepository;
    private AvailableEquipment equipment1;
    private AvailableEquipment equipment2;
    private TypeOfBusinessRepository repository3;
    private UserRepository userRepository;
    private PublishAnnouncementController controller = new PublishAnnouncementController();

    private PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();


    @BeforeEach
    void setUp4() {
        availableEquipmentRepository = new AvailableEquipmentRepository();
        equipment1 = new AvailableEquipment("air conditioning");
        equipment2 = new AvailableEquipment("central heating");
        availableEquipmentRepository.add(equipment1);
        availableEquipmentRepository.add(equipment2);
    }

    @Test
    void getAvailableEquipmentByDescription() {
        AvailableEquipment equipment = availableEquipmentRepository.getAvailableEquipmentByDescription("air conditioning");
        assertNotNull(equipment);
        assertEquals(equipment1, equipment);
    }

    @Test
    void getAvailableEquipment() {
        List<AvailableEquipment> equipmentList = availableEquipmentRepository.getAvailableEquipments();
        assertEquals(2, equipmentList.size());
        assertEquals(2, availableEquipmentRepository.getAvailableEquipments().size());
    }

    @BeforeEach
    public void setUp6() {
        userRepository = new UserRepository();
    }

    @Test
    void getUser() {
        UserRepository userRepository = new UserRepository();
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));

        Client client1 = new Client("person@exampl.com", 123456, 567890, "Person", address, 8937634);
        Client client2 = new Client("persona@exampl.com", 1234565, 5678909, "Personaa", address, 89376534);

        userRepository.add(client1);
        userRepository.add(client2);

        List<Client> clients = userRepository.getUsers();

        assertEquals(2, clients.size());
        assertTrue(clients.contains(client1));
        assertTrue(clients.contains(client2));
    }

    @BeforeEach
    void setUp() {
        repository3 = new TypeOfBusinessRepository();
    }

    @Test
    void getTypeOfBusinessByDescription() {
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
        repository3.add(typeOfBusiness);

        TypeOfBusiness result = repository3.getTypeOfBusinessByDescription("Rent");

        Assertions.assertEquals(typeOfBusiness, result);
    }

    @Test
    void getTypeOfBusiness() {
        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Rent");
        TypeOfBusiness typeOfBusiness2 = new TypeOfBusiness("Sale");
        repository3.add(typeOfBusiness1);
        repository3.add(typeOfBusiness2);

        List<TypeOfBusiness> result = repository3.getTypeOfBusinesses();

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(typeOfBusiness1));
        Assertions.assertTrue(result.contains(typeOfBusiness2));

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(typeOfBusiness1));
        Assertions.assertTrue(result.contains(typeOfBusiness2));
    }

    @BeforeEach
    public void setUp2() {
        repository = new ComissionRepository();
    }

    @Test
    void getComissionByDescription() {
        Comission comission1 = new Comission(0.05);
        Comission comission2 = new Comission(0.1);
        repository.add(comission1);
        repository.add(comission2);
        assertEquals(comission1, repository.getComissionByDescription(0.05));
        assertEquals(comission2, repository.getComissionByDescription(0.1));
    }

    @BeforeEach
    public void setUp1() {
        repository = new ComissionRepository();
    }


    @Test
    void getComission() {
        Comission comission = new Comission(0.05);
        repository.add(comission);
        assertThrows(IllegalArgumentException.class, () -> repository.getComissionByDescription(0.1));
    }

    @BeforeEach
    public void setUp5() {
        propertyTypeRepository = new PropertyTypeRepository();
    }

    @Test
    void getPropertyTypeByDescription() {
        PropertyType propertyType1 = new PropertyType("House");
        propertyTypeRepository.add(propertyType1);
        PropertyType propertyType2 = new PropertyType("Apartment");
        propertyTypeRepository.add(propertyType2);
        PropertyType retrievedPropertyType = propertyTypeRepository.getPropertyTypeByDescription("House");
        Assertions.assertEquals(propertyType1, retrievedPropertyType);
        retrievedPropertyType = propertyTypeRepository.getPropertyTypeByDescription("Apartment");
        Assertions.assertEquals(propertyType2, retrievedPropertyType);
    }

    @Test
    void getPropertyType() {
        PropertyTypeRepository repository = new PropertyTypeRepository();
        PropertyType propertyType1 = new PropertyType("Residential");
        PropertyType propertyType2 = new PropertyType("Commercial");
        repository.add(propertyType1);
        repository.add(propertyType2);

        List<PropertyType> propertyTypes = repository.getPropertyTypes();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> propertyTypes.add(new PropertyType("Industrial")));

        Assertions.assertTrue(propertyTypes.contains(propertyType1));
        Assertions.assertTrue(propertyTypes.contains(propertyType2));
    }

    @Test
    void getPublishedAnnoucement() {

        //The result is the same but the test keeps not working
        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

        Date date = new Date();

        House house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South", new Photos("pjh"), "W234R");
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Double comissionValue = 5.0;
        Comission comission = new Comission(comissionValue);
        Double price = 1000.32;
        Business business = new Business(price);

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));

        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store,  roles, address);


        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(date,typeOfBusiness,house,propertyType,comission,business, employee1);
        publishedAnnouncements.add(publishedAnnouncement);

        publishedAnnouncementRepository.add(publishedAnnouncement);



       assertEquals(publishedAnnouncements,controller.getPublishedAnnoucement());

    }

    @BeforeEach
    void setUp3() {
        repository1 = new TypeOfBusinessRepository();
    }

    @Test
    void getBusinessByDescription() {
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
        repository1.add(typeOfBusiness);

        TypeOfBusiness result = repository1.getTypeOfBusinessByDescription("Rent");

        Assertions.assertEquals(typeOfBusiness, result);
    }

    @Test
    void createPublishmentAnnouncement() {

        //The result is the same but the test keeps not working
        Date date = new Date();

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));

        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store,  roles, address);


        House house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South", new Photos("pjh"), "E45R6");
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Double comissionValue = 5.0;
        Comission comission = new Comission(comissionValue);
        Double price = 1000.32;
        Business business = new Business(price);


        PublishedAnnouncement publishedAnnouncementObject = new PublishedAnnouncement(date, typeOfBusiness, house, propertyType, comission, business, employee1);

        Optional<PublishedAnnouncement> publishedAnnouncement = Optional.of(publishedAnnouncementObject);

        Optional<PublishedAnnouncement> announcement = controller.createPublishmentAnnouncement(date, typeOfBusiness, house, propertyType, comission, business, 5,employee1);


        assertEquals(publishedAnnouncement, announcement);

    }
}