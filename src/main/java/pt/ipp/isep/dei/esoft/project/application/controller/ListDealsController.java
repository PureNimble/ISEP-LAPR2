package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.OfferDto;
import pt.ipp.isep.dei.esoft.project.domain.OfferMapper;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ListDealsController {

    private OfferRepository offerRepository = null;

    public ListDealsController() {
        getOfferRepository();
    }
    private OfferRepository getOfferRepository() {
        if (offerRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the OfferRepository
            offerRepository = repositories.getOfferRepository();
        }
        return offerRepository;
    }

    public List<Offer> getDealsByAscendingArea(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByAreaAscending();

    }

    public List<Offer> getDealsByDescendinggArea(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByAreaDescending();

    }

    public List<Offer> getOfferMostRecent(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByMostRecent();
    }


    public List<OfferDto> toDtoDescendingArea(){
        OfferMapper offerMapper = new OfferMapper();

      return offerMapper.toDto(getDealsByDescendinggArea());

    }

    public List<OfferDto> toDtoAscendingArea(){
        OfferMapper offerMapper = new OfferMapper();

        return offerMapper.toDto(getDealsByAscendingArea());

    }







}
