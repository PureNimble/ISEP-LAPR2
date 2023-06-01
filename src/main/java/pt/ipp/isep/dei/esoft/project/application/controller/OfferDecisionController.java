package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.List;

/**

 The OfferDecisionController class is responsible for handling the operations related to accepting or declining offers.
 */

 public class OfferDecisionController {

    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    private AuthenticationRepository authenticationRepository = null;

    private OfferRepository offerRepository = null;

    /**

     Constructs a new instance of PlaceOfferController and initializes the repositories.
     */
    public OfferDecisionController() {
        getPublishedAnnouncementRepository();
        getAuthenticationRepository();
        getOfferRepository();
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

    public List <Offer> getOffersByPropertyByHighestAmount() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        OfferRepository offerRepository = getOfferRepository();
        List<PublishedAnnouncement> listProperty = publishedAnnouncementRepository.getPublishedAnnouncementsDesc();
        return offerRepository.getOffersByPropertyByHighestAmount(listProperty);
    }

    public void declineOtherOffers(Offer offer, List<Offer> offersList){
        offerRepository.declineOtherOffers(offer, offersList);
    }

 }