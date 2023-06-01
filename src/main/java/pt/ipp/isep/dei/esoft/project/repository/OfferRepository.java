package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.OfferState;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.util.*;

/**

 The OfferRepository class is responsible for managing the storage and retrieval of offers.
 */
public class OfferRepository {

    private final List<Offer> offers = new ArrayList<>();
    /**

     Adds a new offer to the repository.

     @param offer the offer to be added

     @return an Optional containing the added offer if the operation is successful, or an empty Optional otherwise
     */
    public Optional<Offer> add (Offer offer) {
        Optional<Offer> newOffer = Optional.empty();
        boolean operationSuccess = false;

        if (validateOffer(offer)) {
            newOffer = Optional.of(offer);
            operationSuccess = offers.add(newOffer.get());
            System.out.println("\n\nOffer sent with success!\n\n");
        }
        if (!operationSuccess) {
            System.out.println("The offer amount submitted has already been posted for this property. Please contact the agent that is responsible for this property.");
            newOffer = Optional.empty();
        }
        return newOffer;
    }
    /**

     Validates an offer to ensure it is unique and valid.
     @param offer the offer to be validated
     @return true if the offer is valid, false otherwise
     */
    public boolean validateOffer(Offer offer) {
        for (Offer offer1 : offers) {
            if (offer1.getOrderAmount() == offer.getOrderAmount() && checkOffer(offer, offer1)) {
                return false;
            }
        }
        return true;
    }
    /**

     Checks if the submitted offer is better than an existing offer.
     @param offer1 the submitted offer
     @param offer2 the existing offer
     @return true if the submitted offer is better, false otherwise
     */
    private boolean checkOffer(Offer offer1, Offer offer2) {
        return !(offer1.getOrderAmount() < offer2.getOrderAmount());
    }
    /**

     Retrieves the list of offers stored in the repository.
     @return the list of offers
     */
    public List<Offer> getOffers() {
        return offers;
    }

    public void declineOtherOffers (Offer offer, List<Offer> offersList){
        for(int i = 0; i < offersList.size(); i++){
            Offer offerIndex = offersList.get(i);
            if (!offer.equals(offerIndex) && offerIndex.getPublishedAnnouncement().equals(offer.getPublishedAnnouncement())){
                offerIndex.setOfferState(OfferState.rejected);
            }
        }
    }

    public List<Offer> getOffersByPropertyByHighestAmount(List<PublishedAnnouncement> publishedAnnouncementList){
        List<Offer> resultList = new ArrayList<Offer>();

        for (PublishedAnnouncement publishedAnnouncement: publishedAnnouncementList){
            List<Offer> tempList = new ArrayList<Offer>();
            for (Offer offer: offers){
                if (offer.getOfferState().equals(OfferState.pending)){
                    if (offer.getPublishedAnnouncement().equals(publishedAnnouncement)){
                        tempList.add(offer);
                    }
                }
            }
            tempList.sort(Comparator.comparingDouble(Offer::getOrderAmount));
            resultList.addAll(tempList);
        }

        return resultList;
    }

    public List<Offer> getOffersByAreaAscending(){
        List<Offer> resultList = new ArrayList<Offer>();

        for (Offer offer : offers) {
            if (offer.getOfferState().equals(OfferState.accepted)){
                resultList.add(offer);
            }
        }


        resultList.sort(new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                int area1 = o1.getPublishedAnnouncement().getProperty().getArea();

                int area2 = o2.getPublishedAnnouncement().getProperty().getArea();

                if (area1 < area2){
                    return -1;
                }else if (area1 > area2){
                    return 1;
                }else {
                    return 0;
                }

            }
        });



        return resultList;
    }

    public List<Offer> getOffersByAreaDescending(){
        List<Offer> resultList = new ArrayList<Offer>();

        for (Offer offer : offers) {
            if (offer.getOfferState().equals(OfferState.accepted)){
                resultList.add(offer);
            }
        }

        resultList.sort(new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                int area1 = o1.getPublishedAnnouncement().getProperty().getArea();

                int area2 = o2.getPublishedAnnouncement().getProperty().getArea();

                if (area1 > area2){
                    return -1;
                }else if (area1 < area2){
                    return 1;
                }else {
                    return 0;
                }
            }
        });

        return resultList;
    }


}
