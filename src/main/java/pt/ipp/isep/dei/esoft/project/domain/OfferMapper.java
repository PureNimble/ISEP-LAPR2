package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferMapper {

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


    public OfferDto toDtoObject(String name,PublishedAnnouncement publishedAnnouncement,double orderAmount,OfferState offerState) {

        OfferDto offerDto;

        offerDto = new OfferDto(name,orderAmount,publishedAnnouncement,offerState);


        return offerDto;
    }


}
