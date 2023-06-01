package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfferControllerTest {

    String name = "John Doe";
    double offerAmount = 200000;

    Date date = new Date();
    Comission com = new Comission(25.00);
    Property property = new Property(2, 2);
    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);
    PublishedAnnouncement publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business);
    Offer offer = new Offer(name, offerAmount, publishedAnnouncement1, OfferState.pending);

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

        Optional<Offer> result = controller.createNewOfferToAgent(name, offerAmount, publishedAnnouncement1, OfferState.pending);

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

        Optional<Offer> result = controller.createNewOfferToAgent(name, offerAmount, publishedAnnouncement1, OfferState.pending);

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