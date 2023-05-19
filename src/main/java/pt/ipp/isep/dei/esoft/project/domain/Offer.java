package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Offer {

    private double price;
    private User user;
    private PublishedAnnouncement publishedAnnouncement;

    public Offer (double price, User user, PublishedAnnouncement publishedAnnouncement) {
        this.price = price;
        this.user = user;
        this.publishedAnnouncement = publishedAnnouncement;
    }

    public String toString() {
        return String.format("%s,%s,%s", price, user, publishedAnnouncement);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.price, price) == 0 && Objects.equals(user, offer.user) && Objects.equals(publishedAnnouncement, offer.publishedAnnouncement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, user, publishedAnnouncement);
    }
}
