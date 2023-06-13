package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    String name = "John Doe";
    long phoneNumber = 1234567890;
    String description = "Test message";
    Date date = new Date();
    Photos photos = new Photos("urlll");
    int initialTime = 10;
    int endTime = 11;
    Comission com = new Comission(25.00);
    Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));

    Property property = new Property(2, 2,address);
    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);
    Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9);

    List<Role> roles = new ArrayList<>();
    Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
    Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);

    PublishedAnnouncement p1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business,employee,client,77,AnnouncementState.available,store);
    Message message = new Message(name, phoneNumber, description, date, initialTime, endTime, p1,MessageState.ANSWERED,true);

    @Test
    void getName() {
        String actualName = message.getName();

        assertEquals(name, actualName);
    }

    @Test
    void setName() {
        String newName = "John Doe";
        message.setName(newName);
        String actualName = message.getName();

        assertEquals(newName, actualName);
    }

    @Test
    void getPhoneNumber() {
        long actualPhoneNumber = message.getPhoneNumber();

        assertEquals(phoneNumber, actualPhoneNumber);
    }

    @Test
    void setPhoneNumber() {
        long newPhoneNumber = 1234567890;
        message.setPhoneNumber(newPhoneNumber);
        long actualPhoneNumber = message.getPhoneNumber();

        assertEquals(newPhoneNumber, actualPhoneNumber);
    }

    @Test
    void getDescription() {
        String actualDescription = message.getDescription();

        assertEquals(description, actualDescription);
    }

    @Test
    void setDescription() {
        String newDescription = "Test message";
        message.setDescription(newDescription);
        String actualDescription = message.getDescription();

        assertEquals(newDescription, actualDescription);
    }

    @Test
    void getInitialDate() {
        Date newDate = new Date();
        message.setInitialDate(new Date());
        Date actualDate = message.getInitialDate();

        assertEquals(newDate, actualDate);
    }

    @Test
    void setInitialDate() {
        Date newInitialDate = new Date();
        message.setInitialDate(newInitialDate);
        Date actualInitialDate = message.getInitialDate();

        assertEquals(newInitialDate, actualInitialDate);
    }

    @Test
    void getInitialTime() {
        int actualInitialTime = message.getInitialTime();

        assertEquals(initialTime, actualInitialTime);
    }

    @Test
    void setInitialTime() {
        int newInitialTime = 10;
        message.setInitialTime(newInitialTime);
        int actualInitialTime = message.getInitialTime();

        assertEquals(newInitialTime, actualInitialTime);
    }

    @Test
    void getEndTime() {
        int actualEndTime = message.getEndTime();

        assertEquals(endTime, actualEndTime);
    }

    @Test
    void setEndTime() {
        int newEndTime = 11;
        message.setEndTime(newEndTime);
        int actualEndTime = message.getEndTime();

        assertEquals(newEndTime, actualEndTime);
    }

    @Test
    void getPublishedAnnouncement() {
        PublishedAnnouncement actualPublishedAnnouncement = message.getPublishedAnnouncement();

        assertEquals(p1, actualPublishedAnnouncement);
    }

    @Test
    void setPublishedAnnouncement() {
        Comission com = new Comission(25.00);
        Property property = new Property(2, 2,photos,address);
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Business business = new Business(200);
        PublishedAnnouncement p1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business,employee,client,99,AnnouncementState.available,store);
        message.setPublishedAnnouncement(p1);
        PublishedAnnouncement actualPublishedAnnouncement = message.getPublishedAnnouncement();

        assertEquals(p1, actualPublishedAnnouncement);
    }

    @Test
    void testToString() {
        String actualString = message.toString();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date1 = format.format(date);

        String expectedString = "Message: \nThe client John Doe, with phone number 1234567890, wants to schedule a visit from 10 until 11 at " + date1 + ". \n\nDescription: \nTest message \n\nProperty: \n" + p1.toString();
        assertEquals(expectedString, actualString);

    }

    @Test
    void testEquals() {
        Message message1 = new Message(name, phoneNumber, description, date, initialTime, endTime, p1,MessageState.ANSWERED,true);
        Message message2 = new Message(name, phoneNumber, description, date, initialTime, endTime, p1,MessageState.ANSWERED,true);

        boolean isEqual = message1.equals(message2);

        assertEquals(true, isEqual);
    }

    @Test
    void testHashCode() {
        int expectedHashCode = message.hashCode();

        int actualHashCode = message.hashCode();

        assertEquals(expectedHashCode, actualHashCode);

    }
}