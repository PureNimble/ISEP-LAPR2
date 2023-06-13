package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRequestTest {

    Photos photo = new Photos("urlll");
    Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);

    @Test
    void testToString() {
        Date date = new Date();
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("sale");
        Property property = new Property(123, 289,photo,address);
        PropertyType propertyType = new PropertyType("apartment");
        Business business = new Business(34566);
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address, 5551234, "test@store.com", 9);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest("announcement",date, typeOfBusiness, property, propertyType, business, employee, client);

        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),property.toString());

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
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com",9);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, property, propertyType, business, employee,client);

        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),durationOfContract,property.toString());

        assertEquals(expectedOutput, announcementRequest.toStringRent());
    }
}