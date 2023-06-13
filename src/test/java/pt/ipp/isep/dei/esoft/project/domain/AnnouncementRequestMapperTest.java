package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRequestMapperTest {


    private AnnouncementRequestDto announcementRequestDto, announcementRequestDTO1, announcementRequestDto2;

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



    @BeforeEach
    void setUpPropertys() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South",address);
        land = new Property(5, 1000,new Photos("url"), address2);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"), new Photos("urllll"), address);
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
        store = new Store("Test Store", 1, address2, 5551234, "test@store.com",9);
    }


    @BeforeEach
    void setUpPublishedAnnouncements() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Elvis",224,address,1274567809,"elvis@gmail.com", 0);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store,  roles, address);
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com",9);

        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, house, propertyType, comission, business, employee1, new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L),77,AnnouncementState.available, store1);
        publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, land, propertyType, comission, business, employee,new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L),88,AnnouncementState.available,store);

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
        announcementRequest = new AnnouncementRequest("", date, typeOfBusiness, house, propertyType, business, employee,new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L));
        announcementRequest1 = new AnnouncementRequest("", date, typeOfBusiness, land, propertyType, business, employee, new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L));
        announcementRequest2 = new AnnouncementRequest("", date, typeOfBusiness, appartment, propertyType2, business1, employee1, new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L));
    }

    @BeforeEach
    void setUpAnnouncementRequestDto() {
        announcementRequestDto = new AnnouncementRequestDto("",date, typeOfBusiness, house, propertyType, business, employee,new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L));
        announcementRequestDTO1 = new AnnouncementRequestDto("",date, typeOfBusiness1, land, propertyType, business, employee, new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L));
        announcementRequestDto2 = new AnnouncementRequestDto("",date, typeOfBusiness, appartment, propertyType2, business1, employee1, new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L));
    }


    @Test
    void toDto() {

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();

        List<AnnouncementRequest> announcementRequests = new ArrayList<>();
        announcementRequests.add(announcementRequest);
        announcementRequests.add(announcementRequest1);
        announcementRequests.add(announcementRequest2);


        List<AnnouncementRequestDto> announcementRequestDtos = new ArrayList<>();
        announcementRequestDtos.add(announcementRequestDto);
        announcementRequestDtos.add(announcementRequestDTO1);
        announcementRequestDtos.add(announcementRequestDto2);

        assertEquals(announcementRequestDtos,announcementRequestMapper.toDto(announcementRequests));
    }

    @Test
    void toDtoObject() {

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();

        assertEquals(announcementRequestDto,announcementRequestMapper.toDtoObject("",employee,house,typeOfBusiness,propertyType,business,date,0,new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L)),"messsage");

    }

    @Test
    void getAnnouncementRequestDtoByDescription() {

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();

        List<AnnouncementRequestDto> announcementRequestDtos = new ArrayList<>();
        announcementRequestDtos.add(announcementRequestDto);
        announcementRequestDtos.add(announcementRequestDTO1);
        announcementRequestDtos.add(announcementRequestDto2);

        assertEquals(announcementRequestDto,announcementRequestMapper.getAnnouncementRequestDtoByDescription(announcementRequestDtos,0));


    }
}