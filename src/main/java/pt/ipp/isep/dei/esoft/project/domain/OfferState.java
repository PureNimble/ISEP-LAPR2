package pt.ipp.isep.dei.esoft.project.domain;

public enum OfferState {
    pending,
    accepted,
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
