package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OfferRepository {

    private final List<Offer> offers = new ArrayList<>();

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

    public boolean validateOffer(Offer offer) {
        for (Offer offer1 : offers) {
            if (offer1.getOrderAmount() == offer.getOrderAmount() && checkOffer(offer, offer1)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkOffer(Offer offer1, Offer offer2) {
        return !(offer1.getOrderAmount() < offer2.getOrderAmount());
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
