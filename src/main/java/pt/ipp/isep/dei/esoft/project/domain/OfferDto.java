package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The type Offer dto.
 */
public class OfferDto {
    /**
     * The name of the offer.
     */
    private String name;
    /**
     * The order amount for the offer.
     */
    private double orderAmount;
    /**
     * The published announcement associated with the offer.
     */
    private PublishedAnnouncement publishedAnnouncement;
    /**
     * The state of the offer.
     */
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
    /**
     * Returns a string representation of the Offer object.
     *
     * @return A formatted string containing the offer details.
     */
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets order amount.
     *
     * @param orderAmount the order amount
     */
    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * Sets published announcement.
     *
     * @param publishedAnnouncement the published announcement
     */
    public void setPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }

    /**
     * Sets offer state.
     *
     * @param offerState the offer state
     */
    public void setOfferState(OfferState offerState) {
        this.offerState = offerState;
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
