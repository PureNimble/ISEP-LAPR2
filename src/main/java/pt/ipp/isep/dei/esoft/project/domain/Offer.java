package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 The Offer class represents an offer made by a client for a published announcement.
 */
public class Offer {

    private String name;
    private double orderAmount;
//    private Client client;
    private PublishedAnnouncement publishedAnnouncement;

    private OfferState offerState;

//    private final double ORDER_AMOUNT_DEFAULT = 0;
//    private final String CLIENT_DEFAULT = "NO CLIENT";
//    private final String PUBLISHED_ANNOUNCEMENT_DEFAULT = "";

    /**
     * Instantiates a new Offer.
     *
     * @param name                  the name
     * @param orderAmount           the price
     *                              //     * @param client                the user
     * @param publishedAnnouncement the published announcement
     */
    public Offer(String name, double orderAmount, PublishedAnnouncement publishedAnnouncement,OfferState offerState) {
        this.name = name;
        this.orderAmount = orderAmount;
//        this.client = client;
        this.publishedAnnouncement = publishedAnnouncement;
        this.offerState = offerState;
    }

    /**

     Constructs a new empty instance of Offer.
     */
    public Offer() {

    }

//    public Offer() {
//        this.orderAmount = ORDER_AMOUNT_DEFAULT;
//        this.client = CLIENT_DEFAULT;
//        this.publishedAnnouncement = PUBLISHED_ANNOUNCEMENT_DEFAULT;
//    }


    public OfferState getOfferState() {
        return offerState;
    }

    public void setOfferState(OfferState offerState) {
        this.offerState = offerState;
    }

    /**

     Retrieves the name of the offer.
     @return the name of the offer
     */
    public String getName() {
        return name;
    }
//    /**
//
//     Retrieves the client associated with the offer.
//     @return the client associated with the offer
//     */
//    public Client getClient() {
//        return client;
//    }
    /**

     Retrieves the published announcement associated with the offer.
     @return the published announcement associated with the offer
     */
    public PublishedAnnouncement getPublishedAnnouncement() {
        return publishedAnnouncement;
    }
    /**

     Retrieves the order amount of the offer.
     @return the order amount of the offer
     */
    public double getOrderAmount() {
        return orderAmount;
    }
    /**

     Sets the name of the offer.
     @param name the name of the offer
     */
    public void setName(String name) {
        this.name = name;
    }
//    /**
//
//     Sets the client associated with the offer.
//     @param client the client associated with the offer
//     */
//    public void setClient(Client client) {
//        this.client = client;
//    }
    /**

     Sets the published announcement associated with the offer.
     @param publishedAnnouncement the published announcement associated with the offer
     */
    public void setPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }
    /**

     Sets the order amount of the offer.
     @param orderAmount the order amount of the offer
     */
    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }
    /**

     Returns a string representation of the Offer object.
     @return a string representation of the Offer object
     */
    public String toString() {
        return String.format("\nOffer: \nThe client %s, has submitted an offer with the following price: %s. \n\nProperty: \n%s", name, orderAmount, publishedAnnouncement.toString());

    }
    /**

     Checks if the Offer object is equal to another object.
     @param o the object to compare
     @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.orderAmount, orderAmount) == 0 && Objects.equals(name, offer.name) && Objects.equals(publishedAnnouncement, offer.publishedAnnouncement);
    }
    /**

     Generates a hash code for the Offer object.
     @return the hash code of the Offer object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, orderAmount, publishedAnnouncement);
    }
}



