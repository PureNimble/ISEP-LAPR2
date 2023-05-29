package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.OfferState;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.List;
import java.util.Optional;


/**

 The PlaceOfferController class is responsible for handling the operations related to placing offers.
 */
public class PlaceOfferController {

     PublishedAnnouncementRepository publishedAnnouncementRepository = null;

     AuthenticationRepository authenticationRepository = null;

     OfferRepository offerRepository = null;
    /**

     Constructs a new instance of PlaceOfferController and initializes the repositories.
     */
    public PlaceOfferController() {
        getPublishedAnnouncementRepository();
        getAuthenticationRepository();
        getOfferRepository();
    }
    /**

     Constructs a new instance of PlaceOfferController with the specified repositories.
     @param authenticationRepository the authentication repository to be used
     @param offerRepository the offer repository to be used
     @param publishedAnnouncementRepository the published announcement repository to be used
     */
    public PlaceOfferController(AuthenticationRepository authenticationRepository, OfferRepository offerRepository, PublishedAnnouncementRepository publishedAnnouncementRepository) {
        this.offerRepository = offerRepository;
        this.authenticationRepository = authenticationRepository;
        this.publishedAnnouncementRepository = publishedAnnouncementRepository;
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

     Retrieves the list of offers from the offer repository.
     @return the list of offers
     */
    public List <Offer> getOffers() {
        OfferRepository offerRepository = getOfferRepository();
        return offerRepository.getOffers();
    }
    /**

     Creates a new offer and associates it with the specified published announcement.
     @param name the name of the offer
     @param orderAmount the order amount of the offer
     @param publishedAnnouncement the published announcement to associate the offer with
     @return an Optional containing the created offer, or an empty Optional if the creation fails
     */
    public Optional<Offer> createNewOfferToAgent (String name, double orderAmount, PublishedAnnouncement publishedAnnouncement, OfferState offerState) {
        Offer offerSent = new Offer();
        offerSent.setName(name);
        offerSent.setOrderAmount(orderAmount);
        offerSent.setPublishedAnnouncement(publishedAnnouncement);
        offerSent.setOfferState(offerState);
        return offerRepository.add(offerSent);
    }
}
