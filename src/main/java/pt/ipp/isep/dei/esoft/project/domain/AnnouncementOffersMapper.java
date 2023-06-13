package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Announcement request mapper.
 */
public class AnnouncementOffersMapper {

    /**
     * To dto list.
     *
     * @param publishedAnnouncementsList the published announcements list
     * @param offersList                 the offers list
     * @return the list
     */
    public List<AnnouncementOffersDTO> toDto(List<PublishedAnnouncement> publishedAnnouncementsList, List<Offer> offersList) {

        List<AnnouncementOffersDTO> announcementOffersDTO = new ArrayList<>();

        for (PublishedAnnouncement publishedAnnouncement : publishedAnnouncementsList) {
            List<OfferDto> offersListToDTO = new ArrayList<>();
            AnnouncementOffersDTO announcementOffersDto;
            for (Offer offer : offersList) {
                if (offer.getPublishedAnnouncement().equals(publishedAnnouncement)) {
                    OfferDto offerDto;
                    String name = offer.getName();
                    Client client = offer.getClient();
                    double orderAmount = offer.getOrderAmount();
                    OfferState offerState = offer.getOfferState();
                    offerDto = toOfferDtoObject(name, client, orderAmount, offerState);
                    offersListToDTO.add(offerDto);
                }
            }
            announcementOffersDto = toDtoObject(publishedAnnouncement, offersListToDTO);
            announcementOffersDTO.add(announcementOffersDto);
        }

        return announcementOffersDTO;

    }


    /**
     * To dto object announcement offers dto.
     *
     * @param publishedAnnouncement the published announcement
     * @param offersListToDTO       the offers list to dto
     * @return the announcement offers dto
     */
    public AnnouncementOffersDTO toDtoObject(PublishedAnnouncement publishedAnnouncement, List<OfferDto> offersListToDTO) {

        AnnouncementOffersDTO announcementOffersDto;
        announcementOffersDto = new AnnouncementOffersDTO(publishedAnnouncement, offersListToDTO);
        return announcementOffersDto;
    }

    /**
     * To offer dto object offer dto.
     *
     * @param name        the name
     * @param client      the client
     * @param orderAmount the order amount
     * @param offerState  the offer state
     * @return the offer dto
     */
    public OfferDto toOfferDtoObject(String name, Client client, double orderAmount,OfferState offerState) {

        OfferDto offerDto;

        offerDto = new OfferDto(name, client, orderAmount, offerState);


        return offerDto;
    }
}
