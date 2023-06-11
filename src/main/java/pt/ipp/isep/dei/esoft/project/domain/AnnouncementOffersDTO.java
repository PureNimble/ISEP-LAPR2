package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;


public class AnnouncementOffersDTO {

    private PublishedAnnouncement publishedAnnouncement;

    private List<OfferDto> offers;


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


    public PublishedAnnouncement getPublishedAnnouncementDto() {
        return publishedAnnouncement;
    }

    public List<OfferDto> getListOffersDto(){
        return offers;
    }

    public void setPublishedAnnouncementDto(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }

    public void setListOffersDto(List<OfferDto> offers){
        this.offers = offers;
    }
}
