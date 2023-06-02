package pt.ipp.isep.dei.esoft.project.domain;

public class OfferDto {

    private String name;

    private double orderAmount;

    private PublishedAnnouncement publishedAnnouncement;

    private OfferState offerState;


    public OfferDto(String name, double orderAmount, PublishedAnnouncement publishedAnnouncement, OfferState offerState) {
        this.name = name;
        this.orderAmount = orderAmount;
        this.publishedAnnouncement = publishedAnnouncement;
        this.offerState = offerState;
    }

    public String toString() {
        return String.format("\nOffer: \nThe client %s, has submitted an offer with the following price: %s. \n\nProperty: \n%s", name, orderAmount, publishedAnnouncement.toString());

    }

    public String getName() {
        return name;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public PublishedAnnouncement getPublishedAnnouncement() {
        return publishedAnnouncement;
    }

    public OfferState getOfferState() {
        return offerState;
    }
}
