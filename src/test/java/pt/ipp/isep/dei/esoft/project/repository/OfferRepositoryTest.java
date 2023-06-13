package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OfferRepositoryTest {

    String name = "John Doe";
    String name1 = "Mary Jane";
    double offerAmount = 200000;
    double offerAmount1 = 250000;

    Date date = new Date();
    Comission com = new Comission(25.00);
    Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    List<Role> roles;
    Store store = new Store("Holloway",10234,address2,1234567890,"holloway@gmail.com", 0);

    Employee employee =  new Employee("age@this.app", 123446789, 987658321, "Miguelito", 1234587890L, store, roles , address2);

    Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    Property property = new Property(2, 2,address);

    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);

    Client client = new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L);
    PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business,employee,client,543,AnnouncementState.available,store);
    Offer offer = new Offer(name, offerAmount, publishedAnnouncement1, OfferState.pending,client);
    Offer offer1 = new Offer(name1, offerAmount1, publishedAnnouncement1, OfferState.pending,client);

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

}