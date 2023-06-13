package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The enum Offer state.
 */
public enum OfferState implements Serializable {
    /**
     * Pending offer state.
     */
    pending,
    /**
     * Accepted offer state.
     */
    accepted,
    /**
     * Rejected offer state.
     */
    rejected;

    /**
     * @return string of the order state
     */
    @Override
    public String toString() {
        switch (this) {
            case pending:
                return "PENDING";
            case accepted:
                return "ACCEPTED";
            case rejected:
                return "REJECTED";
            default:
                return "";
        }
    }

}
