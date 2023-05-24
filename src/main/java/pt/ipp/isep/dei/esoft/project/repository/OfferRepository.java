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
        }
        if (!operationSuccess) {
            newOffer = Optional.empty();
        }
        return newOffer;
    }

    public boolean validateOffer(Offer offer) {
        boolean isValid = !offers.contains(offer);
        return isValid;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
