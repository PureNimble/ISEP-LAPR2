package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.List;

import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class DealAnalysesController {

    /**
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;
    
    /**
     * Constructs a new instance of DealAnalysesController and initializes the repositories.
     */
    public DealAnalysesController() {
        getPublishedAnnouncementRepository();
    }

    /**
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    /**
     * @param regressionMethod
     * @param param
     * @return The regressionModel results.
     */
    public String regressionModel(String regressionMethod, int param) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        List<PublishedAnnouncement> publishedAnnouncementList = publishedAnnouncementRepository.getPublishedAnnouncements();
        return null;
    }
}