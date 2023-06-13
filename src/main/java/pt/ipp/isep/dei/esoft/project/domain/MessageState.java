package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The enum Message state.
 */
public enum MessageState implements Serializable {
    /**
     * Unanswered message state.
     */
    UNANSWERED,
    /**
     * Answered message state.
     */
    ANSWERED;

    @Override
    public String toString() {
        switch (this) {
            case UNANSWERED:
                return "UNANSWERED";
            case ANSWERED:
                return "ANSWERED";
            default:
                return "";
        }
    }
}
