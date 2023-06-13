package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;


/**
 * The type Announcement offers dto.
 */
public class AnnouncementOffersDTO {

    private PublishedAnnouncement publishedAnnouncement;

    private List<OfferDto> offers;


    /**
     * Instantiates a new Announcement offers dto.
     *
     * @param publishedAnnouncement2 the published announcement 2
     * @param offers                 the offers
     */
    public AnnouncementOffersDTO(PublishedAnnouncement publishedAnnouncement2, List<OfferDto> offers) {
        this.publishedAnnouncement = publishedAnnouncement2;
        this.offers = offers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (publishedAnnouncement != null) {
            sb.append("Published Announcement: ").append(publishedAnnouncement.toString()).append("\n");
        }
        for (OfferDto offerDto : offers) {
            sb.append(offerDto.toString()).append("\n");
        }
        return sb.toString();
    }


    /**
     * Gets published announcement dto.
     *
     * @return the published announcement dto
     */
    public PublishedAnnouncement getPublishedAnnouncementDto() {
        return publishedAnnouncement;
    }

    /**
     * Get list offers dto list.
     *
     * @return the list
     */
    public List<OfferDto> getListOffersDto(){
        return offers;
    }

    /**
     * Sets published announcement dto.
     *
     * @param publishedAnnouncement the published announcement
     */
    public void setPublishedAnnouncementDto(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }

    /**
     * Set list offers dto.
     *
     * @param offers the offers
     */
    public void setListOffersDto(List<OfferDto> offers){
        this.offers = offers;
    }
}
