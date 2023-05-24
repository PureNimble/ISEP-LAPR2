package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The type Offer.
 */
public class Offer {

    private double orderAmount;
    private Client client;
    private PublishedAnnouncement publishedAnnouncement;

    /**
     * Instantiates a new Offer.
     *
     * @param orderAmount                 the price
     * @param client                  the user
     * @param publishedAnnouncement the published announcement
     */
    public Offer (double orderAmount, Client client, PublishedAnnouncement publishedAnnouncement) {
        this.orderAmount = orderAmount;
        this.client = client;
        this.publishedAnnouncement = publishedAnnouncement;
    }

    public String toString() {
        return String.format("%s,%s,%s", orderAmount, client, publishedAnnouncement);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.orderAmount, orderAmount) == 0 && Objects.equals(client, offer.client) && Objects.equals(publishedAnnouncement, offer.publishedAnnouncement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderAmount, client, publishedAnnouncement);
    }
}
