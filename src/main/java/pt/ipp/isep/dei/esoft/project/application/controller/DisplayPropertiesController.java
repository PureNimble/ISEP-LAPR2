package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.PropertyTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TypeOfBusinessRepository;

import java.util.List;

/**
 * This class is responsible for displaying properties and sorting the list of published announcements by city name, price, and state name.
 */
public class DisplayPropertiesController {

    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;
    private TypeOfBusinessRepository typeOfBusinessRepository = null;

    /**
     * Instantiates a new Display properties controller.
     */
    public DisplayPropertiesController(){
        getPublishedAnnouncementRepository();
        getPropertyTypeRepository();
        getTypeOfBusinessRepository();
    }
    /**
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    /**
     * Retrieves the PropertyTypeRepository instance.
     *
     * @return The PropertyTypeRepository instance.
     */
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    /**
     * Retrieves the TypeOfBusinessRepository instance.
     *
     * @return The TypeOfBusinessRepository instance.
     */
    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();
            typeOfBusinessRepository = repositories.getTypeOfBusinessRepository();
        }
        return typeOfBusinessRepository;
    }

    /**
     * Gets published announcements desc.
     *
     * @return the published announcements desc
     */
    public List <PublishedAnnouncement> getPublishedAnnouncementsDesc() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncementsDesc();
    }

    /**
     * Filter list list.
     *
     * @param propertyType  the property type
     * @param businessType  the business type
     * @param numberOfRooms the number of rooms
     * @return the list
     */
    public List<PublishedAnnouncement> filterList(String propertyType, String businessType, int numberOfRooms) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.filterList(propertyType, businessType, numberOfRooms);
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    /**
     * Gets type of business.
     *
     * @return the type of business
     */
    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
    }

    /**
     * Sort announcements asc price list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> sortAnnouncementsAscPrice(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareAscendingPrice(announcements);
    }

    /**
     * Sort announcements asc city list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> sortAnnouncementsAscCity(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareAscendingCity(announcements);
    }

    /**
     * Sort announcements asc state list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> sortAnnouncementsAscState(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareAscendingState(announcements);
    }

    /**
     * Sort announcements des price list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> sortAnnouncementsDesPrice(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareDescendingPrice(announcements);
    }

    /**
     * Sort announcements des city list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> sortAnnouncementsDesCity(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareDescendingCity(announcements);
    }

    /**
     * Sort announcements des state list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> sortAnnouncementsDesState(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareDescendingState(announcements);
    }
}
