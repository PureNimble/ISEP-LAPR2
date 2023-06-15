package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRequestTest {

    Photos photo = new Photos("urlll");
    Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
    Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);

    private AnnouncementRequest announcementRequest;

    @BeforeEach
    void setUp() {
        // Create a sample AnnouncementRequest instance for testing
        Date date = new Date();
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Test Business");
        Property property = new Property(123, 456, new Photos("test-url"), new Address("Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        PropertyType propertyType = new PropertyType("Test Property Type");
        Business business = new Business(100.0);
        Role role = new Role("Agent");

        int durationOfContract = 6;
        Store store = new Store("Test Store", 1, address, 5551234, "test@store.com", 9,1);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee agent = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        String status = "Pending";
        Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);

        announcementRequest = new AnnouncementRequest(status, date, typeOfBusiness, property, propertyType, business, durationOfContract, agent, client);
    }
    @Test
    void testToString() {
        Date date = new Date();
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("sale");
        Property property = new Property(123, 289,photo,address);
        PropertyType propertyType = new PropertyType("apartment");
        Business business = new Business(34566);
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address, 5551234, "test@store.com", 9,1);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest("announcement",date, typeOfBusiness, property, propertyType, business, employee, client);

        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s\n",

                date.toString(), typeOfBusiness.toString(), propertyType, business.toString(), property.toString());

        assertEquals(expectedOutput, announcementRequest.toString());
    }

    @Test
    void toStringRent() {
        Date date = new Date();
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("rent");
        Property property = new Property(100,167, photo,address);
        PropertyType propertyType = new PropertyType("house");
        Business business = new Business(900);
        int durationOfContract = 12;
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com",9,1);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, property, propertyType, business, durationOfContract, employee, client);

        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s\n",

                date.toString(), typeOfBusiness.toString(), propertyType, business.toString(), durationOfContract, property.toString());

        assertEquals(expectedOutput, announcementRequest.toStringRent());
    }

    @Test
    void getTypeOfBusiness() {
        TypeOfBusiness typeOfBusiness = announcementRequest.getTypeOfBusiness();
        Assertions.assertNotNull(typeOfBusiness);
        // Add more assertions as needed
    }

    @Test
    void getPropertyType() {
        PropertyType propertyType = announcementRequest.getPropertyType();
        Assertions.assertNotNull(propertyType);
        // Add more assertions as needed
    }

    @Test
    void getBusiness() {
        Business business = announcementRequest.getBusiness();
        Assertions.assertNotNull(business);
        // Add more assertions as needed
    }

    @Test
    void getDurationOfContract() {
        int durationOfContract = announcementRequest.getDurationOfContract();
        Assertions.assertEquals(6, durationOfContract);
        // Add more assertions as needed
    }

    @Test
    void getProperty() {
        Property property = announcementRequest.getProperty();
        Assertions.assertNotNull(property);
        // Add more assertions as needed
    }

    @Test
    void getDate() {
        Date date = announcementRequest.getDate();
        Assertions.assertNotNull(date);
        // Add more assertions as needed
    }

    @Test
    void getAgent() {
        Employee agent = announcementRequest.getAgent();
        Assertions.assertNotNull(agent);
        // Add more assertions as needed
    }

    @Test
    void getStatus() {
        String status = announcementRequest.getStatus();
        Assertions.assertEquals("Pending", status);
        // Add more assertions as needed
    }

    @Test
    void setDate() {
        Date newDate = new Date();
        announcementRequest.setDate(newDate);
        Assertions.assertEquals(newDate, announcementRequest.getDate());
    }

    @Test
    void setTypeOfBusiness() {
        TypeOfBusiness newTypeOfBusiness = new TypeOfBusiness("New Business");
        announcementRequest.setTypeOfBusiness(newTypeOfBusiness);
        Assertions.assertEquals(newTypeOfBusiness, announcementRequest.getTypeOfBusiness());
    }

    @Test
    void setProperty() {
        Property newProperty = new Property(789, 987, new Photos("new-url"), new Address("New St", 54321, new District("New District"), new City("New City"), new State("New State")));
        announcementRequest.setProperty(newProperty);
        Assertions.assertEquals(newProperty, announcementRequest.getProperty());
    }

    @Test
    void getClient() {
        Client client = announcementRequest.getClient();
        Assertions.assertNotNull(client);
        // Add more assertions as needed
    }

    @Test
    void setClient() {
        Client newClient = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);
        announcementRequest.setClient(newClient);
        Assertions.assertEquals(newClient, announcementRequest.getClient());
    }

    @Test
    void setPropertyType() {
        PropertyType newPropertyType = new PropertyType("New Property Type");
        announcementRequest.setPropertyType(newPropertyType);
        Assertions.assertEquals(newPropertyType, announcementRequest.getPropertyType());
    }

    @Test
    void setBusiness() {
        Business newBusiness = new Business(200.0);
        announcementRequest.setBusiness(newBusiness);
        Assertions.assertEquals(newBusiness, announcementRequest.getBusiness());
    }

    @Test
    void setDurationOfContract() {
        int newDurationOfContract = 12;
        announcementRequest.setDurationOfContract(newDurationOfContract);
        Assertions.assertEquals(newDurationOfContract, announcementRequest.getDurationOfContract());
    }

    @Test
    void setAgent() {
        Role role = new Role("Agent");
        Store store = new Store("Test Store", 1, address, 5551234, "test@store.com", 9,1);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee newAgent = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        announcementRequest.setAgent(newAgent);
        Assertions.assertEquals(newAgent, announcementRequest.getAgent());
    }

    @Test
    void setStatus() {
        String newStatus = "Approved";
        announcementRequest.setStatus(newStatus);
        Assertions.assertEquals(newStatus, announcementRequest.getStatus());
    }
}
