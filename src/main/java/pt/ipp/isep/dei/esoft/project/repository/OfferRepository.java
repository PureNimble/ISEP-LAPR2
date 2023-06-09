package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.OfferState;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.util.*;

/**
 * The OfferRepository class is responsible for managing the storage and retrieval of offers.
 */
public class OfferRepository {
    /**
     * List of offers associated with this object.
     */
    private final List<Offer> offers = new ArrayList<>();

    /**
     * Adds a new offer to the repository.
     *
     * @param offer the offer to be added
     * @return an Optional containing the added offer if the operation is successful, or an empty Optional otherwise
     */
    public Optional<Offer> add(Offer offer) {
        Optional<Offer> newOffer = Optional.empty();
        boolean operationSuccess = false;

        if (validateOffer(offer)) {
            String clientEmail = offer.getClient().getClientEmail();
            if (hasPendingOffersByEmail(clientEmail)) {
                System.out.println("Please wait for your previous offer to be accepted or denied before making another one.");
                return newOffer;
            }
            newOffer = Optional.of(offer);
            operationSuccess = offers.add(newOffer.get());
        }
        if (!operationSuccess) {
            newOffer = Optional.empty();
        }
        return newOffer;
    }

    /**
     * Validates an offer to ensure it is unique and valid.
     *
     * @param offer the offer to be validated
     * @return true if the offer is valid, false otherwise
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
     * Checks if the submitted offer is better than an existing offer.
     *
     * @param offer1 the submitted offer
     * @param offer2 the existing offer
     * @return true if the submitted offer is better, false otherwise
     */
    private boolean checkOffer(Offer offer1, Offer offer2) {
        return !(offer1.getOrderAmount() < offer2.getOrderAmount());
    }

    /**
     * Has pending offers by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean hasPendingOffersByEmail(String email) {
        for (Offer offer : offers) {
            if (offer.getOfferState() == OfferState.pending && offer.getClient().getClientEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the list of offers stored in the repository.
     *
     * @return the list of offers
     */
    public List<Offer> getOffers() {
        return offers;
    }

    /**
     * Decline other offers.
     *
     * @param offer      the offer
     * @param offersList the offers list
     */
    public void declineOtherOffers(Offer offer, List<Offer> offersList) {
        for (int i = 0; i < offersList.size(); i++) {
            Offer offerIndex = offersList.get(i);
            if (!offer.equals(offerIndex) && offerIndex.getPublishedAnnouncement().equals(offer.getPublishedAnnouncement())) {
                offerIndex.setOfferState(OfferState.rejected);
            }
        }
    }

    /**
     * Gets offers by property by highest amount.
     *
     * @param publishedAnnouncementList the published announcement list
     * @return the offers by property by highest amount
     */
    public List<Offer> getOffersByPropertyByHighestAmount(List<PublishedAnnouncement> publishedAnnouncementList) {
        List<Offer> resultList = new ArrayList<Offer>();

        for (PublishedAnnouncement publishedAnnouncement : publishedAnnouncementList) {
            List<Offer> tempList = new ArrayList<Offer>();
            for (Offer offer : offers) {
                if (offer.getOfferState().equals(OfferState.pending)) {
                    if (offer.getPublishedAnnouncement().equals(publishedAnnouncement)) {
                        tempList.add(offer);
                    }
                }
            }
            tempList.sort(Comparator.comparingDouble(Offer::getOrderAmount));
            resultList.addAll(tempList);
        }

        return resultList;
    }

    /**
     * Gets offers by area ascending using bubble sort algorithm.
     *
     * @return the offers by area ascending using bubble sort algorithm
     */
    public List<Offer> getOffersByAreaAscendingUsingBubbleSortAlgorithm() {
        List<Offer> resultList = new ArrayList<Offer>();

        for (Offer offer : offers) {
            if (offer.getOfferState().equals(OfferState.accepted)) {
                resultList.add(offer);
            }
        }

        Offer aux;

        for (int i = 0; i < resultList.size() - 1; i++) {
            for (int j = 0; j < resultList.size() - i - 1; j++) {
                if (resultList.get(j).getPublishedAnnouncement().getProperty().getArea() > resultList.get(j+1).getPublishedAnnouncement().getProperty().getArea()) {
                    aux = resultList.get(j);
                    resultList.set(j,resultList.get(j+1));
                    resultList.set(j+1,aux);
                }
            }
        }

        return resultList;
    }

    /**
     * Gets offers by area descending using bubble sort algorithm.
     *
     * @return the offers by area descending using bubble sort algorithm
     */
    public List<Offer> getOffersByAreaDescendingUsingBubbleSortAlgorithm() {
        List<Offer> resultList = new ArrayList<Offer>();

        for (Offer offer : offers) {
            if (offer.getOfferState().equals(OfferState.accepted)) {
                resultList.add(offer);
            }
        }

        Offer aux;

        for (int i = 0; i < resultList.size() - 1; i++) {
            for (int j = 0; j < resultList.size() - i - 1; j++) {
                if (resultList.get(j).getPublishedAnnouncement().getProperty().getArea() < resultList.get(j+1).getPublishedAnnouncement().getProperty().getArea()) {
                    aux = resultList.get(j);
                    resultList.set(j,resultList.get(j+1));
                    resultList.set(j+1,aux);
                }
            }
        }
        return resultList;
    }

    /**
     * Gets offers by area ascending using sort selection.
     *
     * @return the offers by area ascending using sort selection
     */
    public List<Offer> getOffersByAreaAscendingUsingSortSelection() {
        List<Offer> resultList = new ArrayList<Offer>();

        for (Offer offer : offers) {
            if (offer.getOfferState().equals(OfferState.accepted)) {
                resultList.add(offer);
            }
        }

        Offer aux;
        int min_Index;

        for (int i = 0; i < resultList.size() - 1; i++) {
            min_Index = i;
            for (int j = i+1; j < resultList.size(); j++) {
                if (resultList.get(j).getPublishedAnnouncement().getProperty().getArea() < resultList.get(min_Index).getPublishedAnnouncement().getProperty().getArea()) {
                  min_Index = j;
                }
            }
            aux = resultList.get(min_Index);
            resultList.set(min_Index,resultList.get(i));
            resultList.set(i,aux);
        }

        return resultList;
    }

    /**
     * Gets offers by area descending using sort selection.
     *
     * @return the offers by area descending using sort selection
     */
    public List<Offer> getOffersByAreaDescendingUsingSortSelection() {
        List<Offer> resultList = new ArrayList<Offer>();

        for (Offer offer : offers) {
            if (offer.getOfferState().equals(OfferState.accepted)) {
                resultList.add(offer);
            }
        }

        Offer aux;
        int max_Index;

        for (int i = 0; i < resultList.size() - 1; i++) {
            max_Index = i;
            for (int j = i+1; j < resultList.size(); j++) {
                if (resultList.get(j).getPublishedAnnouncement().getProperty().getArea() > resultList.get(max_Index).getPublishedAnnouncement().getProperty().getArea()) {
                    max_Index = j;
                }
            }
            aux = resultList.get(max_Index);
            resultList.set(max_Index,resultList.get(i));
            resultList.set(i,aux);
        }
        return resultList;
    }


    /**
     * Gets offers by most recent.
     *
     * @return the offers by most recent
     */
    public List<Offer> getOffersByMostRecent () {
            List<Offer> resultList = new ArrayList<Offer>();

            for (Offer offer : offers) {
                if (offer.getOfferState().equals(OfferState.accepted)) {
                    resultList.add(offer);
                }
            }

/**
 * Sorts the list of offers by the date of the associated published announcements in ascending order,
 * and then reverses the list to obtain a descending order by date.
 *
 * @param resultList The list of offers to be sorted.
 * @return The sorted list of offers in descending order by date.
 */
            resultList.sort(new Comparator<Offer>() {
                @Override
                public int compare(Offer o1, Offer o2) {
                    Date date1 = o1.getPublishedAnnouncement().getDate();
                    Date date2 = o2.getPublishedAnnouncement().getDate();

                    return date1.compareTo(date2);

                }
            });

            Collections.reverse(resultList);


            return resultList;
        }


    }