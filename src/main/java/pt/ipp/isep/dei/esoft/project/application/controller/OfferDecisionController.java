package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementOffersDTO;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementOffersMapper;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.List;

/**
 * The OfferDecisionController class is responsible for handling the operations related to accepting or declining offers.
 */
public class OfferDecisionController {
    /**
     * The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;
    /**
     * The AuthenticationRepository instance.
     */
    private AuthenticationRepository authenticationRepository = null;
    /**
     * The OfferRepository instance.
     */
    private OfferRepository offerRepository = null;

    /**
     * Constructs a new instance of PlaceOfferController and initializes the repositories.
     */
    public OfferDecisionController() {
        getPublishedAnnouncementRepository();
        getAuthenticationRepository();
        getOfferRepository();
    }
    /**
     * Retrieves the OfferRepository instance.
     *
     * @return The OfferRepository instance.
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
     * Retrieves the AuthenticationRepository instance.
     *
     * @return The AuthenticationRepository instance.
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
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PublishedAnnouncementRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    public List<Offer> getOffers(){
        OfferRepository offerRepository = getOfferRepository();
        return offerRepository.getPendingOffers();
    }

    /**
     * Gets offers by property by highest amount.
     *
     * @return the offers by property by highest amount
     */
    public List <AnnouncementOffersDTO> getOffersByPropertyByHighestAmount() {
        AnnouncementOffersMapper announcementOffersMapper = new AnnouncementOffersMapper();
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        OfferRepository offerRepository = getOfferRepository();
        List<PublishedAnnouncement> listProperty = publishedAnnouncementRepository.getPublishedAnnouncementsDesc();
        List<Offer> offersList = offerRepository.getOffersByHighestAmount();
        return announcementOffersMapper.toDto(listProperty, offersList);
    }

    /**
     * Decline other offers.
     *
     * @param offer      the offer
     * @param offersList the offers list
     */
    public void declineOtherOffers(Offer offer, List<Offer> offersList){
        offerRepository.declineOtherOffers(offer, offersList);
    }

 }