package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementRepositoryTest {

    private PublishedAnnouncement publishedAnnouncement, publishedAnnouncement1;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType;

    private PropertyType propertyType2;

    private Employee employee, employee1;

    private AnnouncementRequest announcementRequest, announcementRequest1, announcementRequest2;

    private Date date = new Date();

    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private Comission comission;

    private Business business, business1;

    private Role role;

    private Address address2;

    private Store store;

    private AnnouncementRequestDto announcementRequestDto;


    @BeforeEach
    void setUpPropertys() {
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South");
        land = new Property(5, 1000);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"));
        setUpPropertyTypes();
        setUpBusiness();
        setUpAddress();
        setUpStore();
        setEmployees();
        setUpTypeOfBusiness();
        setUpRoles();
        setUpAnnouncementRequest();
        setUpPublishedAnnouncements();
        setUpComission();
        setUpAnnouncementRequestDto();
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
    void setUpPublishedAnnouncements() {
        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, house, propertyType, comission, business);
        publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, land, propertyType, comission, business);

    }

    @BeforeEach
    void setEmployees() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee("emailExample@this.app", 12, 12, "nome", 1234567, store, roles, address2);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);

    }


    @BeforeEach
    void setUpAnnouncementRequest() {
        announcementRequest = new AnnouncementRequest("", date, typeOfBusiness, house, propertyType, business, employee);
        announcementRequest1 = new AnnouncementRequest("", date, typeOfBusiness, land, propertyType, business, employee);
        announcementRequest2 = new AnnouncementRequest("", date, typeOfBusiness, appartment, propertyType2, business1, employee1);
    }

    @BeforeEach
    void setUpAnnouncementRequestDto() {
        announcementRequestDto = new AnnouncementRequestDto("",date, typeOfBusiness, house, propertyType, business, employee);
    }

    @Test
    void add() {
        PublishedAnnouncementRepository repository = new PublishedAnnouncementRepository();

        Optional<PublishedAnnouncement> addedAnnouncement = repository.add(publishedAnnouncement);
        assertTrue(addedAnnouncement.isPresent());
        PublishedAnnouncement publishedAnnouncementTest = addedAnnouncement.get();
        assertEquals(publishedAnnouncementTest, publishedAnnouncementTest);

        List<PublishedAnnouncement> announcements = repository.getPublishedAnnouncements();
        assertTrue(announcements.contains(publishedAnnouncementTest));

    }

    @Test
    void publishedAnnouncement() {
        PublishedAnnouncementRepository repository = new PublishedAnnouncementRepository();

        repository.add(publishedAnnouncement);
        repository.add(publishedAnnouncement1);

        List<PublishedAnnouncement> announcements = repository.getPublishedAnnouncements();
        assertTrue(announcements.contains(publishedAnnouncement));
        assertTrue(announcements.contains(publishedAnnouncement1));
    }


    @Test
    void publishedAnnouncementRequestTest() {

        PublishedAnnouncementRepository repository = new PublishedAnnouncementRepository();



        List<AnnouncementRequest> announcementRequests = new ArrayList<>();
        announcementRequests.add(announcementRequest);
        announcementRequests.add(announcementRequest1);

        Optional<PublishedAnnouncement> publishedAnnouncementExpected = Optional.of(publishedAnnouncement);

        Optional<PublishedAnnouncement> publishedAnnouncementResult = repository.publishedAnnouncementRequest(announcementRequests,announcementRequestDto,comission);

        assertEquals("true",announcementRequest.getStatus());
        assertEquals(publishedAnnouncementExpected,publishedAnnouncementResult);

    }
}