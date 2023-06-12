package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Offer mapper.
 */
public class OfferMapper {

    /**
     * To dto list.
     *
     * @param offerList the offer list
     * @return the list
     */
    public List<OfferDto> toDto(List<Offer> offerList) {

        List<OfferDto> offerDtos = new ArrayList<>();

        for (Offer offer : offerList) {
            OfferDto offerDto;
            String name = offer.getName();
            double orderAmount = offer.getOrderAmount();
            OfferState offerState = offer.getOfferState();
            PublishedAnnouncement publishedAnnouncement = offer.getPublishedAnnouncement();
            offerDto = toDtoObject(name,publishedAnnouncement,orderAmount,offerState);


            offerDtos.add(offerDto);


        }

        return offerDtos;

    }


    /**
     * To dto object offer dto.
     *
     * @param name                  the name
     * @param publishedAnnouncement the published announcement
     * @param orderAmount           the order amount
     * @param offerState            the offer state
     * @return the offer dto
     */
    public OfferDto toDtoObject(String name,PublishedAnnouncement publishedAnnouncement,double orderAmount,OfferState offerState) {

        OfferDto offerDto;

        offerDto = new OfferDto(name,orderAmount,publishedAnnouncement,offerState);


        return offerDto;
    }


}
