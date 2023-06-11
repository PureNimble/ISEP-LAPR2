package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Announcement request mapper.
 */
public class AnnouncementOffersMapper {
    
    public List<AnnouncementOffersDTO> toDto(List<PublishedAnnouncement> publishedAnnouncementsList, List<Offer> offersList) {

        List<AnnouncementOffersDTO> announcementOffersDTO = new ArrayList<>();

        for (PublishedAnnouncement publishedAnnouncement : publishedAnnouncementsList) {
            List<OfferDto> offersListToDTO = new ArrayList<>();
            AnnouncementOffersDTO announcementOffersDto;
            int counter = 0;
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
            for (OfferDto offerDto : offersListToDTO) {
                if (offerDto.getOfferState().equals(OfferState.accepted)) {
                    counter++;
                }
            }
            if (counter == 0){
                announcementOffersDto = toDtoObject(publishedAnnouncement, offersListToDTO);
                announcementOffersDTO.add(announcementOffersDto);
            }
        }

        return announcementOffersDTO;

    }


    public AnnouncementOffersDTO toDtoObject(PublishedAnnouncement publishedAnnouncement, List<OfferDto> offersListToDTO) {

        AnnouncementOffersDTO announcementOffersDto;
        announcementOffersDto = new AnnouncementOffersDTO(publishedAnnouncement, offersListToDTO);
        return announcementOffersDto;
    }

    public OfferDto toOfferDtoObject(String name, Client client, double orderAmount,OfferState offerState) {

        OfferDto offerDto;

        offerDto = new OfferDto(name, client, orderAmount, offerState);


        return offerDto;
    }
}
