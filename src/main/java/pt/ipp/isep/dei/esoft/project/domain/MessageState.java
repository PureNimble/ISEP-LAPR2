package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The enum Message state.
 */
public enum MessageState {
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
