package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Comparator;
/**

 This class is responsible for displaying properties and sorting the list of published announcements by city name, price, and state name.
 */
public class DisplayPropertiesController {

    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;
    
    /**

     Displays a list of published announcements.
     @param announcements The list of published announcements to be displayed.
     */
    private void displayAnnouncements(List<PublishedAnnouncement> announcements) {
    }

    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PublishedAnnouncementRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    public List <PublishedAnnouncement> getPublishedAnnouncementsDesc() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncementsDesc();
    }

    /**

     Sorts a list of published announcements by city name.
     @param announcements The list of published announcements to be sorted.
     @return The sorted list of published announcements by city name.
     */
    private List<PublishedAnnouncement> sortAnnouncementsByCityName(List<PublishedAnnouncement> announcements) {
        return announcements;
    }
    /**

     Sorts a list of published announcements by price.
     @param announcements The list of published announcements to be sorted.
     @return The sorted list of published announcements by price.
     */
    private List<PublishedAnnouncement> sortAnnouncementsByPrice(List<PublishedAnnouncement> announcements) {
        return announcements;
    }
    /**

     Sorts a list of published announcements by state name.
     @param announcements The list of published announcements to be sorted.
     @return The sorted list of published announcements by state name.
     */
    private List<PublishedAnnouncement> sortAnnouncementsByStateName(List<PublishedAnnouncement> announcements) {
        return announcements;
    }
}
