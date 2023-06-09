package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.OfferDto;
import pt.ipp.isep.dei.esoft.project.domain.OfferMapper;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * The type List deals controller.
 */
public class ListDealsController {

    private OfferRepository offerRepository = null;

    /**
     * Instantiates a new List deals controller.
     */
    public ListDealsController() {
        getOfferRepository();
    }

    /**
     * Retrieves the OfferRepository instance.
     *
     * @return The OfferRepository instance.
     */
    private OfferRepository getOfferRepository() {
        if (offerRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the OfferRepository
            offerRepository = repositories.getOfferRepository();
        }
        return offerRepository;
    }

    /**
     * Get deals by ascending area bubble sort list.
     *
     * @return the list
     */
    public List<Offer> getDealsByAscendingAreaBubbleSort(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByAreaAscendingUsingBubbleSortAlgorithm();

    }

    /**
     * Get deals by descending area bubble sort list.
     *
     * @return the list
     */
    public List<Offer> getDealsByDescendingAreaBubbleSort(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByAreaDescendingUsingBubbleSortAlgorithm();

    }

    /**
     * Get deals by ascending area sort selection list.
     *
     * @return the list
     */
    public List<Offer> getDealsByAscendingAreaSortSelection(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByAreaAscendingUsingSortSelection();

    }

    /**
     * Get deals by descending area sort selection list.
     *
     * @return the list
     */
    public List<Offer> getDealsByDescendingAreaSortSelection(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByAreaDescendingUsingSortSelection();

    }

    /**
     * Get offer most recent list.
     *
     * @return the list
     */
    public List<Offer> getOfferMostRecent(){
        OfferRepository offerRepository = getOfferRepository();

        return offerRepository.getOffersByMostRecent();
    }


    /**
     * To dto descending area list.
     *
     * @return the list
     */
    public List<OfferDto> toDtoDescendingArea(){
        OfferMapper offerMapper = new OfferMapper();

      return offerMapper.toDto(getDealsByDescendingAreaBubbleSort());

    }

    /**
     * To dto ascending area list.
     *
     * @return the list
     */
    public List<OfferDto> toDtoAscendingArea(){
        OfferMapper offerMapper = new OfferMapper();

        return offerMapper.toDto(getDealsByAscendingAreaBubbleSort());

    }

}
