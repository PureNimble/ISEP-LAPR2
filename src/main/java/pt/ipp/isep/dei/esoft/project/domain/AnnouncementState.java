package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The enum Offer state.
 */
public enum AnnouncementState implements Serializable {

    /**
     * Available announcement state.
     */
    available,

    /**
     * Sold announcement state.
     */
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
