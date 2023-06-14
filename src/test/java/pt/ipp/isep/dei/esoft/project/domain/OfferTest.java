package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    String name = "John Doe";
    double offerAmount = 200000;
    Photos photos = new Photos("urlll");

    int offerID = 1;
    Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
    Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9);

    OfferState offerState = OfferState.pending;

    List<Role> roles = new ArrayList<>();
    Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
    Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);


    Date date = new Date();
    Comission com = new Comission(25.00);
    Property property = new Property(2, 2,photos,address);
    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);
    PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business,employee,client,99,AnnouncementState.available,store);
    Offer offer = new Offer(name, offerAmount, publishedAnnouncement1, OfferState.pending,client, offerID);


    @Test
    void getName() {
        String actualName = offer.getName();

        assertEquals(name, actualName);
    }

    @Test
    void getPublishedAnnouncement() {
        PublishedAnnouncement actualPublishedAnnouncement = offer.getPublishedAnnouncement();

        assertEquals(publishedAnnouncement1, actualPublishedAnnouncement);
    }

    @Test
    void getOrderAmount() {
        double actualOrderAmount = offer.getOrderAmount();

        assertEquals(offerAmount, actualOrderAmount);
    }

    @Test
    void setName() {
        String newName = "John Doe";
        offer.setName(newName);
        String actualName = offer.getName();

        assertEquals(newName, actualName);
    }

    @Test
    void setPublishedAnnouncement() {
        Comission com = new Comission(25.00);
        Property property = new Property(2, 2,photos,address);
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Business business = new Business(200);
        PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business,employee,client,11,AnnouncementState.available,store);
        offer.setPublishedAnnouncement(publishedAnnouncement1);
        PublishedAnnouncement actualPublishedAnnouncement = offer.getPublishedAnnouncement();

        assertEquals(publishedAnnouncement1, actualPublishedAnnouncement);
    }

    @Test
    void setOrderAmount() {
        double newOrderAmount = 200000.0;
        offer.setOrderAmount(newOrderAmount);
        double actualOrderAmount = offer.getOrderAmount();

        assertEquals(newOrderAmount, actualOrderAmount);
    }

    @Test
    void getOfferState() {
        OfferState actualOfferState = offer.getOfferState();
        assertEquals(offerState, actualOfferState);
    }

    @Test
    void setOfferState() {
        OfferState newOfferState = OfferState.pending;
        offer.setOfferState(newOfferState);
        OfferState actualOfferState = offer.getOfferState();
        assertEquals(newOfferState, actualOfferState);
    }

    @Test
    void getClient() {
        Client actualClient = offer.getClient();
        assertEquals(client,actualClient);
    }

    @Test
    void setClient() {
        Client newClient = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);
        offer.setClient(newClient);
        Client actualClient = offer.getClient();
        assertEquals(newClient, actualClient);
    }

    @Test
    void getOfferID() {
        int actualOfferID = offer.getOfferID();
        assertEquals(offerID, actualOfferID);
    }

    @Test
    void setOfferID() {
        int newOfferID = 1;
        offer.setOfferID(newOfferID);
        int actualOfferID = offer.getOfferID();
        assertEquals(newOfferID, actualOfferID);
    }

    @Test
    void testEquals() {
        Offer offer1 = new Offer(name, offerAmount, publishedAnnouncement1, OfferState.pending,client, offerID);
        Offer offer2 = new Offer(name, offerAmount, publishedAnnouncement1, OfferState.pending,client, offerID);

        boolean isEqual = offer1.equals(offer2);

        assertEquals(true, isEqual);
    }

    @Test
    void testHashCode() {
        int expectedHashCode = offer.hashCode();

        int actualHashCode = offer.hashCode();

        assertEquals(expectedHashCode, actualHashCode);

    }
}