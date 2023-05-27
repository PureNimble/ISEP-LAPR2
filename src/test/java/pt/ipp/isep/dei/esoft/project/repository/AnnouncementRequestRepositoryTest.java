package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRequestRepositoryTest {



    private Date date = new Date();

    private  Date date1 = new Date(2004,1,24);

    private Date date2 = new Date(2016,2,24);
    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private Role role;

    private Address address2;

    private Store store;

    private Employee employee, employee1, employee2;

    private Business business, business1;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType, propertyType1, propertyType2;
    private AnnouncementRequest announcementRequest, announcementRequest1, announcementRequest2;

    private AnnouncementRequestDto announcementRequestDto;

    @BeforeEach
    void setUpPropertys() {
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South");
        land = new Property(5, 1000);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"));
    }

    @BeforeEach
    void setUpPropertyTypes() {
        propertyType = new PropertyType("House");
        propertyType1 = new PropertyType("Appartment");
        propertyType2 = new PropertyType("Land");
    }

    @BeforeEach
    void setUpTypeOfBusiness() {
        typeOfBusiness = new TypeOfBusiness("Sale");
        typeOfBusiness1 = new TypeOfBusiness("Rent");
    }


    @BeforeEach
    void setUpBusiness() {
        double price = 1000.32;
        business = new Business(price);
        double price1 = 102.213;
        business1 = new Business(price1);
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
        store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
    }

    @BeforeEach
    void setEmployees() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee("employee@this.app", 12, 12, "nome", 1234567, store, roles, address2);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);
        employee2 = new Employee("employee2@this.app", 12, 12, "nome", 19191919, store, roles, address2);
    }


    @BeforeEach
    void setUpAnnouncementRequest() {
            announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, house, propertyType, business, employee);
            announcementRequest1 = new AnnouncementRequest("",date, typeOfBusiness1, land, propertyType, business, employee);
            announcementRequest2 = new AnnouncementRequest("",date, typeOfBusiness, appartment, propertyType2, business1, employee);

    }

    @BeforeEach
    void setUpAnnouncementRequestDto() {
        announcementRequestDto = new AnnouncementRequestDto("",date, typeOfBusiness, house, propertyType, business, employee);
    }

    @Test
    void add() {
        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest("",new Date(), new TypeOfBusiness("Sale"), new Property(345,789), new PropertyType("House"), new Business(89999), employee);
        Optional<AnnouncementRequest> addedAnnouncementRequest = repository.add(announcementRequest);
        Assertions.assertTrue(addedAnnouncementRequest.isPresent());
        Assertions.assertEquals(announcementRequest, addedAnnouncementRequest.get());
        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();
        Assertions.assertTrue(announcementRequests.contains(announcementRequest));
    }

    @Test
    void announcementRequest() {

        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest("",new Date(), new TypeOfBusiness("Sale"), new Property(345,789), new PropertyType("House"), new Business(89999), employee);
        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();
        Assertions.assertFalse(announcementRequests.contains(announcementRequest));
    }

    @Test
    void getAnnouncementsRequest() {


        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        AnnouncementRequest announcementRequest1 = new AnnouncementRequest(
                "",
                new Date(),
                new TypeOfBusiness("Rent"),
                new Property(567,89),
                new PropertyType("House"),
                new Business(45666),
                employee
        );

        AnnouncementRequest announcementRequest2 = new AnnouncementRequest(
                "",
                new Date(),
                new TypeOfBusiness("Rent"),
                new Property(967,89),
                new PropertyType("Land"),
                new Business(666),
                employee
        );

        repository.add(announcementRequest1);
        repository.add(announcementRequest2);

        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();

        Assertions.assertSame(announcementRequests, repository.getAnnouncementsRequest());
        Assertions.assertEquals(announcementRequests.size(), 2);
        Assertions.assertTrue(announcementRequests.contains(announcementRequest1));
        Assertions.assertTrue(announcementRequests.contains(announcementRequest2));
    }

    @Test
    void getAnnouncementRequestsByMostRecentTest() {

        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        List<AnnouncementRequest> expected = new ArrayList<>();
        expected.add(announcementRequest);
        expected.add(announcementRequest1);
        expected.add(announcementRequest2);

        repository.add(announcementRequest);
        repository.add(announcementRequest1);
        repository.add(announcementRequest2);

        Collections.reverse(expected);

        assertEquals(expected,repository.getAnnouncementRequestsByMostRecent(employee));

    }

    @Test
    void getAnnouncementRequestByDescriptionTest() {

        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        repository.add(announcementRequest);
        repository.add(announcementRequest1);
        repository.add(announcementRequest2);


        assertEquals(announcementRequest1,repository.getAnnouncementRequestByDescription(1));

    }

    @Test
    void rejectAnnouncementRequestTest() {
        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        repository.add(announcementRequest);
        repository.add(announcementRequest1);
        repository.add(announcementRequest2);


        repository.rejectAnnouncementRequest(announcementRequestDto);

        assertEquals("false",announcementRequest.getStatus());


    }
}