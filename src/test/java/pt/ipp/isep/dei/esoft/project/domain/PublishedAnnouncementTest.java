package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementTest {

    List<Role> roles = new ArrayList<>();
    Role role1 = new Role("Agent");
    @BeforeEach
    void setUp() {

        roles.add(role1);

    }
    Photos photos = new Photos("urlll");
    Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
    Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9);
    Photos photos1 = new Photos("ulll");
    Address address1 = new Address("12 St", 13426, new District("District"), new City("City"), new State("State"));
    Photos photos2 = new Photos("ulvrtbll");
    Address address2 = new Address("12 ihiuech St", 13416, new District("District d"), new City("City fd"), new State("State d"));

    Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
    Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);

    int propertyID = 99;

    private final TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
    private final PropertyType propertyType = new PropertyType("House");
    private final Comission comission = new Comission(34);
    private final Business business = new Business(100.0);
    private final Property property = new Property(3455, 56, photos,address);
    private final int durationOfContract = 6;
    private final Date date = new Date();


    @Test
    void testToString() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        String expectedString = String.format("Date: %s\n\n" + "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                        "Client: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                        "Property ID: %s\n" +
                        "Type Of Business: %s\n" +
                        "Property Type: %s\n" +
                        "Comission Selected: %s" +
                        "Price: %s\n" +
                        "%s\n",

                date.toString(), employee.getEmployeeName(), employee.getEmployeeEmail(), employee.getPhoneNumber(), client.getName(), client.getEmail(), client.getPhoneNumber(), propertyID, typeOfBusiness.toString(), propertyType, comission.toString(), business.toString(), property.toString());
    }

        @Test
        void toStringRent() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract,employee, client, 22, AnnouncementState.available,store);
            String expectedString = String.format("Date: %s\n\n" +  "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                            "Client: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                            "Property ID: %s\n" +
                            "Type Of Business: %s\n" +
                            "Property Type: %s\n" +
                            "Comission Selected: %s" +
                            "Price: %s\n" +
                            "Duration Of The Contract: %s\n" +
                            "%s\n",

                    date.toString(), employee.getEmployeeName(),
                    employee.getEmployeeEmail(), employee.getPhoneNumber(),
                    client.getName(), client.getEmail(), client.getPhoneNumber(),
                    propertyID, typeOfBusiness.toString(),
                    propertyType, comission.toString(), business.toString(), durationOfContract, property.toString());
    }

    @Test
    void getProperty() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(property, announcement.getProperty());
    }

    @Test
    void getDate() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(date, announcement.getDate());
    }

//    @Test
//    void getState() {
//        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
//        assertEquals(address.getState(), announcement.getState());
//    }
//
//    @Test
//    void getCity() {
//        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
//        assertEquals(address.getCity(), announcement.getCity());
//    }

    @Test
    void getClient() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(client, announcement.getClient());
    }

    @Test
    void setClient() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        Client newClient = new Client("newclient@this.app", 987654321, 987654321, "newclient", address1, 9876543210L);
        announcement.setClient(newClient);
        assertEquals(newClient, announcement.getClient());
    }

    @Test
    void getAnnouncementState() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(AnnouncementState.available, announcement.getAnnouncementState());
    }

    @Test
    void setAnnouncementState() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        announcement.setAnnouncementState(AnnouncementState.available);
        assertEquals(AnnouncementState.available, announcement.getAnnouncementState());
    }

    @Test
    void getTypeOfBusiness() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(typeOfBusiness, announcement.getTypeOfBusiness());
    }

    @Test
    void getPropertyType() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(propertyType, announcement.getPropertyType());
    }

    @Test
    void getComission() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(comission, announcement.getComission());
    }

    @Test
    void getBusiness() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(business, announcement.getBusiness());
    }

    @Test
    void getDurationOfContract() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract, employee, client, 22, AnnouncementState.available, store);
        assertEquals(durationOfContract, announcement.getDurationOfContract());
    }

    @Test
    void setDate() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(new Date(), typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        Date newDate = new Date();
        announcement.setDate(newDate);
        assertEquals(newDate, announcement.getDate());
    }

    @Test
    void setTypeOfBusiness() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, new TypeOfBusiness("Buy"), property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        TypeOfBusiness newTypeOfBusiness = new TypeOfBusiness("Rent");
        announcement.setTypeOfBusiness(newTypeOfBusiness);
        assertEquals(newTypeOfBusiness, announcement.getTypeOfBusiness());
    }

    @Test
    void setProperty() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, new Property(123, 45, photos1, address1), propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        Property newProperty = new Property(789, 10, photos2, address2);
        announcement.setProperty(newProperty);
        assertEquals(newProperty, announcement.getProperty());
    }

    @Test
    void setPropertyType() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, new PropertyType("Apartment"), comission, business, employee, client, 99, AnnouncementState.available, store);
        PropertyType newPropertyType = new PropertyType("Villa");
        announcement.setPropertyType(newPropertyType);
        assertEquals(newPropertyType, announcement.getPropertyType());
    }

    @Test
    void setComission() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, new Comission(50), business, employee, client, 99, AnnouncementState.available, store);
        Comission newComission = new Comission(25);
        announcement.setComission(newComission);
        assertEquals(newComission, announcement.getComission());
    }

    @Test
    void setBusiness() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, new Business(500.0), employee, client, 99, AnnouncementState.available, store);
        Business newBusiness = new Business(750.0);
        announcement.setBusiness(newBusiness);
        assertEquals(newBusiness, announcement.getBusiness());
    }

    @Test
    void setDurationOfContract() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, 12, employee, client, 22, AnnouncementState.available, store);
        int newDurationOfContract = 24;
        announcement.setDurationOfContract(newDurationOfContract);
        assertEquals(newDurationOfContract, announcement.getDurationOfContract());
    }

    @Test
    void getAgent() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(employee, announcement.getAgent());
    }

    @Test
    void setAgent() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        Employee newAgent = new Employee("newagent@example.com", 987654321, 123456789, "New Agent", 5554321, store, roles, new Address("456 Main St", 54321, new District("New District"), new City("New City"), new State("New State")));
        announcement.setAgent(newAgent);
        assertEquals(newAgent, announcement.getAgent());
    }

    @Test
    void getStore() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(store, announcement.getStore());
    }

    @Test
    void setStore() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        Store newStore = new Store("Store B", 2, address2, 5554321, "storeb@example.com", 8);
        announcement.setStore(newStore);
        assertEquals(newStore, announcement.getStore());
    }

    @Test
    void getPropertyID() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        assertEquals(99, announcement.getPropertyID());
    }

    @Test
    void setPropertyID() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, employee, client, 99, AnnouncementState.available, store);
        int newPropertyID = 123;
        announcement.setPropertyID(newPropertyID);
        assertEquals(newPropertyID, announcement.getPropertyID());
    }
}