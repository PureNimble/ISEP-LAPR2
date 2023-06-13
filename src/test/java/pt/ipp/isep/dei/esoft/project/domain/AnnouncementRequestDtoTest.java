package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnouncementRequestDtoTest {


    private AnnouncementRequestDto announcementRequestDto, announcementRequestDTO1, announcementRequestDto2;

    private Date date = new Date();

    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType, propertyType1, propertyType2;

    private Employee employee, employee1, employee2;

    private Business business, business1, business2;

    private Role role, role1;

    private Address address, address1, address2;

    private Store store, store1, store2;



    @BeforeEach
    void setUpPropertys() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address1 = new Address("Test", 45172, new District("District"), new City("City"), new State("State"));
        Address address2 = new Address("Streett", 45172, new District("Test"), new City("Test"), new State("Tes"));

        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South",new Photos("url"),address);
        land = new Property(5, 1000,new Photos("urlll"), address1);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"), new Photos("urrrrl"), address2);
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
    void setEmployees() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee("employee@this.app", 12, 12, "nome", 1234567, store, roles, address);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);
        employee2 = new Employee("employee2@this.app", 12, 12, "nome", 19191919, store, roles, address2);
    }


    @BeforeEach
    void setUpAnnouncementRequestDto() {
        announcementRequestDto = new AnnouncementRequestDto("announcement",date, typeOfBusiness, house, propertyType, business, employee,new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L));
        announcementRequestDTO1 = new AnnouncementRequestDto("announcemnt1",date, typeOfBusiness1, land, propertyType, business, employee, new Client("clientttt@this.app", 123256789,1232567890,"clientttt",address2,1234563890L));
        announcementRequestDto2 = new AnnouncementRequestDto("announcement3",date, typeOfBusiness, appartment, propertyType2, business1, employee1, new Client("client@this.app", 123456389,1234567390,"clie",address2,12345367890L));
    }

    @Test
    void testToStringTest() {


        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),house.toString());


        assertEquals(expectedOutput, announcementRequestDto.toString());

    }

    @Test
    void getDateTest() {

       Date result = announcementRequestDTO1.getDate();
       Date expect = new Date();

       assertEquals(expect,result);

    }

    @Test
    void getTypeOfBusinessTest() {

        TypeOfBusiness result = announcementRequestDto.getTypeOfBusiness();
        TypeOfBusiness expected = new TypeOfBusiness("Sale");

        assertEquals(expected,result);

    }

    @Test
    void getPropertyTest() {

        Property result = announcementRequestDto.getProperty();
        Property expected = house;

        assertEquals(expected,result);
    }

    @Test
    void getPropertyTypeTest() {

        PropertyType result = announcementRequestDto2.getPropertyType();
        PropertyType expected = new PropertyType("Land");

        assertEquals(expected,result);
    }

    @Test
    void getBusinessTest() {

        Business  result = announcementRequestDto2.getBusiness();
        Business expected = new Business(102.213);

        assertEquals(expected,result);
    }

    @Test
    void getDurationOfContractTest() {

        int  result = announcementRequestDto2.getDurationOfContract();
        int expected = 0;

        assertEquals(expected,result);

    }

    @Test
    void getAgentTest() {

        Employee result = announcementRequestDto.getAgent();
        Employee expected = employee;

        assertEquals(expected,result);


    }

    @Test
    void getStatusTest() {
        String  result = announcementRequestDto2.getStatus();
        String expected = "";

        assertEquals(expected,result);
    }
}