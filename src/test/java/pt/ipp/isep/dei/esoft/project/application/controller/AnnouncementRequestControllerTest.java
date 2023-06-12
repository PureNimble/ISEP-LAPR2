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

class AnnouncementRequestControllerTest {

    private PropertyTypeRepository propertyTypeRepository;
    private TypeOfBusinessRepository repository1;
    private TypeOfBusinessRepository repository3;
    private AvailableEquipmentRepository availableEquipmentRepository;
    private AvailableEquipment equipment1;
    private AvailableEquipment equipment2;

    private AnnouncementRequestController controller = new AnnouncementRequestController();

    EmployeeRepository employeeRepository = new EmployeeRepository();

    AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();

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

    @Test
    void getListAgents() {

        List<Employee> employeeList = new ArrayList<>();
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Elvis",224,address,1274567809,"elvis@gmail.com", 0);
        List<Role> roles = new ArrayList<>();
        roles.add(role);


        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, address);
        employeeList.add(employee1);

        employeeRepository.add(employee1);


        List<Employee> agents = controller.getListAgents();

        assertEquals(employeeList, agents);


    }

    @Test
    void getAnnouncementRequest() {

        List<AnnouncementRequest> announcementRequests = new ArrayList<>();

        Date date = new Date();

        Address address = new Address("vevt", 1234, new District("District"), new City("City"), new State("State"));
        House house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Y", "N", "South", new Photos("url"), address);

        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Double price = 1000.32;
        Business business = new Business(price);
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Elvis",224,address2,1274567809,"elvis@gmail.com", 0);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, house, propertyType, business, employee);
        announcementRequests.add(announcementRequest);

        announcementRequestRepository.add(announcementRequest);


        assertEquals(announcementRequests, controller.getAnnouncementRequest());
    }

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

    @Test
    void createAnnouncementRequest() {

        //The result is the same but the test keeps not working
        Date date = new Date();

        Address address = new Address("vevt", 1234, new District("District"), new City("City"), new State("State"));
        House house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Y", "N", "South", new Photos("url"), address);
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Double price = 1000.32;
        Business business = new Business(price);


        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Elvis",224,address2,1274567809,"elvis@gmail.com", 0);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequestObject = new AnnouncementRequest("",date, typeOfBusiness, house, propertyType, business, employee);

        Optional<AnnouncementRequest> announcementRequest = Optional.of(announcementRequestObject);

        Optional<AnnouncementRequest> announcementRequest1 = controller.createAnnouncementRequest(date, typeOfBusiness, house, propertyType, business, 5, employee);

        assertEquals(announcementRequest.get(), announcementRequest1.get());

    }
}