package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfferControllerTest {

    String name = "John Doe";
    double offerAmount = 200000;

    Date date = new Date();
    Comission comission1 = new Comission(25.00);
    Role role = new Role("Agent");

    AvailableEquipment equipment1 = new AvailableEquipment("Air Conditioning");
    Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));

    Property property1 = new Property(274,2576, new Photos("url"),address1);

    Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0);

    Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890L, store1, (List<Role>) role, address1);


    PropertyType propertyType1 = new PropertyType("House");
    TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
    Business business1 = new Business(200000);
    Date date1 = new GregorianCalendar(2023, Calendar.JUNE, 20).getTime();
    Client client1 = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address1, 1234567890);
    AnnouncementState state1 = AnnouncementState.available;
    PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date1, typeOfBusiness1, property1, propertyType1, comission1, business1, agent1, client1, 1, state1, store1);

    Offer offer = new Offer("Pedro", 130000, publishedAnnouncement1, OfferState.pending, new Client("pedro@gmail.com", 123456789, 123456789, "Pedro", new Address("13000 SD-244", 57751, new District("Mount Rushmore"), new City("Keystone"), new State("South Dakota")), 1234567890));

    @Test
    void getOfferFromRepository() {
        PlaceOfferController controller = new PlaceOfferController();
        List<Offer> expectedOffers = createSampleOffers();
        OfferRepository repository = new OfferRepository();
        repository.add(expectedOffers.get(0));
        repository.add(expectedOffers.get(1));
        Repositories.getInstance().setOfferRepository(repository);
        controller.offerRepository = repository;

        List<Offer> offers = controller.getOffers();

        assertEquals(expectedOffers, offers);

    }

    @Test
    void createNewOfferToAgentValid() {
        PlaceOfferController controller = new PlaceOfferController();

        OfferRepository repository = new OfferRepository();
        Repositories.getInstance().setOfferRepository(repository);
        controller.offerRepository = repository;

        Optional<Offer> result = controller.createNewOfferToAgent(name, client1, 130000, publishedAnnouncement1,OfferState.pending);

        assertTrue(result.isPresent());
        Offer offer = result.get();
        assertEquals(name, offer.getName());
        assertEquals(offerAmount, offer.getOrderAmount());
        assertEquals(publishedAnnouncement1, offer.getPublishedAnnouncement());
    }

    @Test
    void createNewOfferToAgentInvalid() {
        PlaceOfferController controller = new PlaceOfferController();

        Offer existingOffer = offer;
        OfferRepository repository = new OfferRepository();
        repository.add(existingOffer);
        Repositories.getInstance().setOfferRepository(repository);
        controller.offerRepository = repository;

        Optional<Offer> result = controller.createNewOfferToAgent(name, client1, 130000, publishedAnnouncement1,OfferState.pending);

        assertFalse(result.isPresent());
    }

    private List<Offer> createSampleOffers() {
        List<Offer> offers = new ArrayList<>();
        offers.add(createSampleOffer("John Doe", 20000, null));
        offers.add(createSampleOffer("Jane Smith", 30000, null));
        return offers;
    }

    private Offer createSampleOffer(String name, double offerAmount, PublishedAnnouncement publishedAnnouncement) {
        Offer offer = new Offer();
        offer.setName(name);
        offer.setOrderAmount(offerAmount);
        offer.setPublishedAnnouncement(publishedAnnouncement);
        return offer;
    }




}