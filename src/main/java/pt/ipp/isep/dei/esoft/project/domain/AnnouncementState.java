package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The enum Offer state.
 */
public enum AnnouncementState {

    available,
    
    sold;

    /**
     * @return string of the order state
     */
    @Override
    public String toString() {
        switch (this) {
            case available:
                return "AVAILABLE";
            case sold:
                return "SOLD";
            default:
                return "";
        }
    }

}