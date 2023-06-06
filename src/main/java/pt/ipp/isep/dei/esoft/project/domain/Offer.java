package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.Objects;

/**

 The Offer class represents an offer made by a client for a published announcement.
 */
public class Offer {

    private String name;
    private Client client;
    private double orderAmount;
    private PublishedAnnouncement publishedAnnouncement;

    private OfferState offerState;


    /**
     * Instantiates a new Offer.
     *
     * @param name                  the name
     * @param orderAmount           the price
     *                              //     * @param client                the user
     * @param publishedAnnouncement the published announcement
     */
    public Offer(String name, double orderAmount, PublishedAnnouncement publishedAnnouncement,OfferState offerState, Client client) {
        this.name = name;
        this.client = client;
        this.orderAmount = orderAmount;
        this.publishedAnnouncement = publishedAnnouncement;
        this.offerState = offerState;
    }

    /**

     Constructs a new empty instance of Offer.
     */
    public Offer() {

    }


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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**

     Returns a string representation of the Offer object.
     @return a string representation of the Offer object
     */
    public String toString() {
        return String.format("\nOffer: \nThe client %s, which has the following email: %s,  has submitted an offer with the following price: %s. \nStatus: %s \n\nProperty: \n%s", name, client.getClientEmail(), orderAmount, offerState, publishedAnnouncement.toString());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.orderAmount, orderAmount) == 0 && Objects.equals(name, offer.name) && Objects.equals(client, offer.client) && Objects.equals(publishedAnnouncement, offer.publishedAnnouncement) && offerState == offer.offerState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, client, orderAmount, publishedAnnouncement, offerState);
    }
}



