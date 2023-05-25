package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.List;
import java.util.Optional;


public class PlaceOfferController {

    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    private AuthenticationRepository authenticationRepository = null;

    private OfferRepository offerRepository = null;

    public PlaceOfferController() {
        getPublishedAnnouncementRepository();
        getAuthenticationRepository();
        getOfferRepository();
    }

    public PlaceOfferController(AuthenticationRepository authenticationRepository, OfferRepository offerRepository, PublishedAnnouncementRepository publishedAnnouncementRepository) {
        this.offerRepository = offerRepository;
        this.authenticationRepository = authenticationRepository;
        this.publishedAnnouncementRepository = publishedAnnouncementRepository;
    }

    private OfferRepository getOfferRepository() {
        if (offerRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the OfferRepository
            offerRepository = repositories.getOfferRepository();
        }
        return offerRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PublishedAnnouncementRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    public List <Offer> getOffers() {
        OfferRepository offerRepository = getOfferRepository();
        return offerRepository.getOffers();
    }

    public Optional<Offer> createNewOfferToAgent (double orderAmount, PublishedAnnouncement publishedAnnouncement) {
        Offer offerSent = new Offer();
        offerSent.setOrderAmount(orderAmount);
        offerSent.setPublishedAnnouncement(publishedAnnouncement);
        return offerRepository.add(offerSent);
    }
}
