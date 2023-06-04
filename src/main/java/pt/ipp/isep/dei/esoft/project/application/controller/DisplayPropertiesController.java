package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.TypeOfBusiness;
import pt.ipp.isep.dei.esoft.project.repository.PropertyTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TypeOfBusinessRepository;

import java.util.List;
import java.util.Comparator;
/**

 This class is responsible for displaying properties and sorting the list of published announcements by city name, price, and state name.
 */
public class DisplayPropertiesController {

    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;
    private TypeOfBusinessRepository typeOfBusinessRepository = null;
    
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

    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PublishedAnnouncementRepository
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            typeOfBusinessRepository = repositories.getTypeOfBusinessRepository();
        }
        return typeOfBusinessRepository;
    }

    public List <PublishedAnnouncement> getPublishedAnnouncementsDesc() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncementsDesc();
    }

    public List<PublishedAnnouncement> filterList(String propertyType, String businessType, int numberOfRooms) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.filterList(propertyType, businessType, numberOfRooms);
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
    public List<PublishedAnnouncement> sortAnnouncementsAscPrice(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareAscendingPrice(announcements);
    }

    /**

     Sorts a list of published announcements by price.
     @param announcements The list of published announcements to be sorted.
     @return The sorted list of published announcements by price.
     */
    public List<PublishedAnnouncement> sortAnnouncementsDesPrice(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareDescendingPrice(announcements);
    }

    /**

     Sorts a list of published announcements by state name.
     @param announcements The list of published announcements to be sorted.
     @return The sorted list of published announcements by state name.
     */
    private List<PublishedAnnouncement> sortAnnouncementsByStateName(List<PublishedAnnouncement> announcements) {
        return announcements;
    }

    /**
     * Returns a list of all available PropertyType objects.
     *
     * @return a list of PropertyType objects
     */
    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    /**
     * Returns a list of all available type of businesses.
     *
     * @return a list of TypeOfBusiness objects
     */
    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
    }
}
