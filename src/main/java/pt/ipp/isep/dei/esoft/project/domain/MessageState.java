package pt.ipp.isep.dei.esoft.project.domain;

public enum MessageState {
    UNANSWERED,
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
