package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OfferRepositoryTest {

    String name = "John Doe";
    String name1 = "Mary Jane";
    double offerAmount = 200000;
    double offerAmount1 = 250000;

    int offerID = 1;
    int offerID1 = 2;
    Date date = new Date();
    Comission com = new Comission(25.00);
    Comission com1 = new Comission(15.00);
    Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    List<Role> roles;
    Store store = new Store("Holloway",10234,address2,1234567890,"holloway@gmail.com", 0,1 );

    Employee employee =  new Employee("age@this.app", 123446789, 987658321, "Miguelito", 1234587890L, store, roles , address2);

    Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    Property property = new Property(2, 2,address);
    Property property1 = new Property(1,1,address2);

    PropertyType propertyType = new PropertyType("House");
    PropertyType propertyType1 = new PropertyType("Apartment");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);

    Client client = new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L);
    Client client1 = new Client("client1@this.app", 122456789,1234545890,"client1",address,1234534589L);;
    PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business, 543, employee, client, AnnouncementState.available, store);
    PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, property1, propertyType1, com1, business, 345678, employee, client, AnnouncementState.available, store);
    Offer offer = new Offer(name, offerAmount, publishedAnnouncement, OfferState.pending,client1, offerID);
    Offer offer1 = new Offer(name1, offerAmount1, publishedAnnouncement1, OfferState.pending,client, offerID);

    @Test
    void addValidOffer() {
        OfferRepository repository = new OfferRepository();

        Optional<Offer> result = repository.add(offer);

        assertTrue(result.isPresent());
        assertEquals(offer, result.get());
        List<Offer> offers = repository.getOffers();
        assertTrue(offers.contains(offer));
    }

    @Test
    void addInvalidOffer() {
        OfferRepository repository = new OfferRepository();
        Offer offer1 = offer;
        Offer offer2 = offer;
        repository.add(offer1);

        Optional<Offer> result = repository.add(offer2);

        assertFalse(result.isPresent());
        List<Offer> offers = repository.getOffers();
        assertTrue(offers.contains(offer2));
    }

    @Test
    public void addOverlapingOffers() {
        OfferRepository repository = new OfferRepository();
        Offer offer1 = offer;
        Offer offer2 = offer1;
        offer2.setOrderAmount(250000);
        repository.add(offer1);

        Optional<Offer> result = repository.add(offer2);

        assertFalse(result.isPresent());
        List<Offer> offers = repository.getOffers();
        assertTrue(offers.contains(offer2));
    }

    @Test
    void getOffersEmptyList() {
        OfferRepository repository = new OfferRepository();

        List<Offer> offers = repository.getOffers();

        assertTrue(offers.isEmpty());
    }

    @Test
    void getOffersNonEmptyList() {
        OfferRepository repository = new OfferRepository();
        repository.add(offer1);
        repository.add(offer);

        List<Offer> offers = repository.getOffers();

        assertEquals(2, offers.size());
        assertTrue(offers.contains(offer1));
        assertTrue(offers.contains(offer));
    }

    @Test
    void add() {
        OfferRepository repository = new OfferRepository();

        Optional<Offer> result = repository.add(offer);

        assertTrue(result.isPresent());
        assertEquals(offer, result.get());
        List<Offer> offers = repository.getOffers();
        assertTrue(offers.contains(offer));
    }

    @Test
    void validateOffer() {
        OfferRepository repository = new OfferRepository();
        repository.add(offer);

        boolean result = repository.validateOffer(offer);

        assertFalse(result);
    }

    @Test
    void hasPendingOffersByEmail() {
        OfferRepository repository = new OfferRepository();
        repository.add(offer);

        boolean result = repository.hasPendingOffersByEmail(offer.getClient().getClientEmail());

        assertTrue(result);
    }

    @Test
    void getOffers() {
        OfferRepository repository = new OfferRepository();
        repository.add(offer);
        repository.add(offer1);

        List<Offer> offers = repository.getOffers();

        assertEquals(2, offers.size());
        assertTrue(offers.contains(offer));
        assertTrue(offers.contains(offer1));
    }

    @Test
    void getPendingOffers() {
        OfferRepository repository = new OfferRepository();
        repository.add(offer);
        repository.add(offer1);

        List<Offer> pendingOffers = repository.getPendingOffers();

        assertEquals(2, pendingOffers.size());
        assertTrue(pendingOffers.contains(offer));
        assertTrue(pendingOffers.contains(offer1));
    }

    @Test
    void declineOffer() {
        OfferRepository repository = new OfferRepository();
        repository.add(offer);

        repository.declineOffer(offer);

        assertEquals(OfferState.rejected, offer.getOfferState());
    }
//    @Test
//    void getOffersByHighestAmount() {
//        OfferRepository repository = new OfferRepository();
//        Offer offer1 = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1);
//        Offer offer = new Offer("Diogo",97000, publishedAnnouncement1, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),24);
//        offer.setOrderAmount(1000);
//        offer1.setOrderAmount(2000);
//        repository.add(offer);
//        repository.add(offer1);
//
//        List<Offer> offers = repository.getOffersByHighestAmount();
//
//        assertEquals(2, offers.size());
//        assertEquals(offer1, offers.get(0));
//        assertEquals(offer, offers.get(1));
//    }
//
//    @Test
//    void getOffersByAreaAscendingUsingBubbleSortAlgorithm() {
//        OfferRepository repository = new OfferRepository();
//        Offer offer1 = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1);
//        Offer offer = new Offer("Diogo",97000, publishedAnnouncement1, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),24);
//        property.setArea(100);
//        property1.setArea(200);
//        repository.add(offer);
//        repository.add(offer1);
//
//        List<Offer> offers = repository.getOffersByAreaAscendingUsingBubbleSortAlgorithm();
//
//        assertEquals(2, offers.size());
//        assertEquals(offer, offers.get(0));
//        assertEquals(offer1, offers.get(1));
//    }
//
//    @Test
//    void getOffersByAreaDescendingUsingBubbleSortAlgorithm() {
//        OfferRepository repository = new OfferRepository();
//        Offer offer1 = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1);
//        Offer offer = new Offer("Diogo",97000, publishedAnnouncement1, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),24);
//        property.setArea(100);
//        property1.setArea(200);
//        repository.add(offer);
//        repository.add(offer1);
//
//        List<Offer> offers = repository.getOffersByAreaDescendingUsingBubbleSortAlgorithm();
//
//        assertEquals(2, offers.size());
//        assertEquals(offer1, offers.get(0));
//        assertEquals(offer, offers.get(1));
//    }
//
//    @Test
//    void getOffersByAreaAscendingUsingSortSelection() {
//        OfferRepository repository = new OfferRepository();
//        Offer offer1 = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1);
//        Offer offer = new Offer("Diogo",97000, publishedAnnouncement1, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),24);
//        property.setArea(100);
//        property1.setArea(200);
//        repository.add(offer);
//        repository.add(offer1);
//
//        List<Offer> offers = repository.getOffersByAreaAscendingUsingSortSelection();
//
//        assertEquals(2, offers.size());
//        assertEquals(offer, offers.get(0));
//        assertEquals(offer1, offers.get(1));
//    }
//
//    @Test
//    void getOffersByAreaDescendingUsingSortSelection() {
//        OfferRepository repository = new OfferRepository();
//        Offer offer1 = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1);
//        Offer offer = new Offer("Diogo",97000, publishedAnnouncement1, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),24);
//        property.setArea(100);
//        property1.setArea(200);
//        repository.add(offer);
//        repository.add(offer1);
//
//        List<Offer> offers = repository.getOffersByAreaDescendingUsingSortSelection();
//
//        assertEquals(2, offers.size());
//        assertEquals(offer1, offers.get(0));
//        assertEquals(offer, offers.get(1));
//    }
//
//    @Test
//    void getOffersByMostRecent() {
//        OfferRepository repository = new OfferRepository();
//        Offer offer1 = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1);
//        Offer offer2 = new Offer("Diogo",97000, publishedAnnouncement1, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),24);
//        repository.add(offer1);
//        repository.add(offer2);
//
//
//        List<Offer> offers = repository.getOffersByMostRecent();
//
//        assertEquals(2, offers.size());
//
//        assertEquals(offer2, offers.get(1));
//        assertEquals(offer1, offers.get(2));
//    }

    @Test
    void declineOtherOffers() {
        OfferRepository repository = new OfferRepository();
        Offer offer = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.accepted, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890),1);
        Offer offer1 = new Offer("Diogo",97000, publishedAnnouncement1, OfferState.pending, new Client("diogo@sapo.pt", 567890123, 567890123, "Diogo", new Address("20 W 34th Street", 10001, new District("Manhattan"), new City("New York"), new State("New York")), 1345678901),24);

        offer.setOfferState(OfferState.accepted);
        repository.add(offer1);
        repository.add(offer);

        List<Offer> offersList = repository.getOffers();

        repository.declineOtherOffers(offer, offersList);

        assertEquals(OfferState.accepted, offer.getOfferState());
        assertEquals(OfferState.rejected, offer1.getOfferState());
    }
}