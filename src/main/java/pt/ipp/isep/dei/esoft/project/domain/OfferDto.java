package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The type Offer dto.
 */
public class OfferDto {

    private String name;

    private double orderAmount;

    private PublishedAnnouncement publishedAnnouncement;

    private OfferState offerState;


    /**
     * Instantiates a new Offer dto.
     *
     * @param name                  the name
     * @param orderAmount           the order amount
     * @param publishedAnnouncement the published announcement
     * @param offerState            the offer state
     */
    public OfferDto(String name, double orderAmount, PublishedAnnouncement publishedAnnouncement, OfferState offerState) {
        this.name = name;
        this.orderAmount = orderAmount;
        this.publishedAnnouncement = publishedAnnouncement;
        this.offerState = offerState;
    }

    public String toString() {
        return String.format("\nOffer: \nThe client %s, has submitted an offer with the following price: %s. \n\nProperty: \n%s", name, orderAmount, publishedAnnouncement.toString());

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets order amount.
     *
     * @return the order amount
     */
    public double getOrderAmount() {
        return orderAmount;
    }

    /**
     * Gets published announcement.
     *
     * @return the published announcement
     */
    public PublishedAnnouncement getPublishedAnnouncement() {
        return publishedAnnouncement;
    }

    /**
     * Gets offer state.
     *
     * @return the offer state
     */
    public OfferState getOfferState() {
        return offerState;
    }
}
