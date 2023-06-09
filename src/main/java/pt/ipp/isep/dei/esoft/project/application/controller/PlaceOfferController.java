package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The PlaceOfferController class is responsible for handling the operations related to placing offers.
 */
public class PlaceOfferController {

    /**
     * The Published announcement repository.
     */
    PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = null;

    /**
     * The Offer repository.
     */
    OfferRepository offerRepository = null;
    /**
     * The User repository.
     */
    UserRepository userRepository = null;

    /**
     * Constructs a new instance of PlaceOfferController and initializes the repositories.
     */
    public PlaceOfferController() {
        getPublishedAnnouncementRepository();
        getAuthenticationRepository();
        getOfferRepository();
        getUserRepository();
    }

    /**
     * Constructs a new instance of PlaceOfferController with the specified repositories.
     *
     * @param authenticationRepository        the authentication repository to be used
     * @param offerRepository                 the offer repository to be used
     * @param publishedAnnouncementRepository the published announcement repository to be used
     * @param userRepository                  the user repository
     */
    public PlaceOfferController(AuthenticationRepository authenticationRepository, OfferRepository offerRepository, PublishedAnnouncementRepository publishedAnnouncementRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.authenticationRepository = authenticationRepository;
        this.publishedAnnouncementRepository = publishedAnnouncementRepository;
        this.userRepository = userRepository;
    }
    /**

     Retrieves the offer repository instance. If it is not initialized, it will retrieve it from the Repositories instance.

     @return the offer repository instance
     */
    private OfferRepository getOfferRepository() {
        if (offerRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the OfferRepository
            offerRepository = repositories.getOfferRepository();
        }
        return offerRepository;
    }
    /**

     Retrieves the authentication repository instance. If it is not initialized, it will retrieve it from the Repositories instance.

     @return the authentication repository instance
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
    /**

     Retrieves the published announcement repository instance. If it is not initialized, it will retrieve it from the Repositories instance.

     @return the published announcement repository instance
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PublishedAnnouncementRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    /**
     * Gets user repository.
     *
     * @return the user repository
     */
    public UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }

    /**
     * Retrieves the list of offers from the offer repository.
     *
     * @return the list of offers
     */
    public List <Offer> getOffers() {
        OfferRepository offerRepository = getOfferRepository();
        return offerRepository.getOffers();
    }

    /**
     * Gets current session email.
     *
     * @return the current session email
     */
    public String getCurrentSessionEmail() {
        AuthenticationRepository authenticationRepository = getAuthenticationRepository();
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }

    /**
     * Gets client email.
     *
     * @return the client email
     */
    public Client getClientEmail() {
        String email = getCurrentSessionEmail();
        return getUserRepository().getClientEmail(email);
    }

    /**
     * Checks if the client's email has pending offers.
     *
     * @param email the client's email
     * @return true if the client has pending offers, false otherwise
     */
    public boolean hasPendingOffersByEmail(String email) {
        return offerRepository.hasPendingOffersByEmail(email);
    }

    /**
     * Retrieves the list of pending offers associated with the client's email.
     *
     * @param email the client's email
     * @return the list of pending offers
     */
    /**
     * Retrieves the list of pending offers associated with the client's email.
     *
     * @param email the client's email
     * @return the list of pending offers
     */
    public List<Offer> getPendingOffersByClientEmail(String email) {
        List<Offer> pendingOffers = new ArrayList<>();
        for (Offer offer : offerRepository.getOffers()) {
            if (offer.getOfferState() == OfferState.pending && offer.getClient().getEmail().equals(email)) {
                pendingOffers.add(offer);
            }
        }
        return pendingOffers;
    }


    /**
     * Creates a new offer and associates it with the specified published announcement.
     *
     * @param name                  the name of the offer
     * @param client                the client
     * @param orderAmount           the order amount of the offer
     * @param publishedAnnouncement the published announcement to associate the offer with
     * @param offerState            the offer state
     * @return an Optional containing the created offer, or an empty Optional if the creation fails
     */
    public Optional<Offer> createNewOfferToAgent (String name, Client client, double orderAmount, PublishedAnnouncement publishedAnnouncement, OfferState offerState) {
        if (orderAmount <= publishedAnnouncement.getBusiness().getPrice()) {
            Offer offerSent = new Offer();
            offerSent.setName(name);
            offerSent.setClient(client);
            offerSent.setOrderAmount(orderAmount);
            offerSent.setPublishedAnnouncement(publishedAnnouncement);
            offerSent.setOfferState(offerState);
            return offerRepository.add(offerSent);
        } else {
            return Optional.empty();
        }
    }
}
