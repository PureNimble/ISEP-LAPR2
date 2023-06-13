package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementTest {

    Photos photos = new Photos("urlll");
    Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
    Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9);
    Photos photos1 = new Photos("ulll");
    Address address1 = new Address("12 St", 13426, new District("District"), new City("City"), new State("State"));
    Photos photos2 = new Photos("ulvrtbll");
    Address address2 = new Address("12 ihiuech St", 13416, new District("District d"), new City("City fd"), new State("State d"));

    List<Role> roles = new ArrayList<>();
    Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
    Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);

    private final TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
    private final PropertyType propertyType = new PropertyType("House");
    private final Comission comission = new Comission(34);
    private final Business business = new Business(100.0);
    private final Property property = new Property(3455, 56, photos,address);
    private final int durationOfContract = 6;
    private final Date date = new Date();

    @Test
    void testToString() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business,employee, client, 99, AnnouncementState.available,store);
        String expectedString = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Comission Selected:%s" +
                        "Price:%s\n" +
                        "%s",
                date.toString(), typeOfBusiness.toString(), propertyType, comission.toString(), business.toString(), property.toString());
        assertEquals(expectedString, announcement.toString());
    }

    @Test
    void toStringRent() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract,employee, client, 22, AnnouncementState.available,store);
        String expectedString = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Comission Selected:%s" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s",
                date.toString(), typeOfBusiness.toString(), propertyType, comission.toString(), business.toString(), durationOfContract, property.toString());
        assertEquals(expectedString, announcement.toStringRent());
    }
}