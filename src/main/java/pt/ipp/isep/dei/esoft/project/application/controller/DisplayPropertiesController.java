package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.PropertyTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TypeOfBusinessRepository;

import java.util.List;

/**

 This class is responsible for displaying properties and sorting the list of published announcements by city name, price, and state name.
 */
public class DisplayPropertiesController {

    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;
    private TypeOfBusinessRepository typeOfBusinessRepository = null;

    public DisplayPropertiesController(){
        getPublishedAnnouncementRepository();
        getPropertyTypeRepository();
        getTypeOfBusinessRepository();
    }

    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();
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

    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
    }

    public List<PublishedAnnouncement> sortAnnouncementsAscPrice(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareAscendingPrice(announcements);
    }

    public List<PublishedAnnouncement> sortAnnouncementsAscCity(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareAscendingCity(announcements);
    }

    public List<PublishedAnnouncement> sortAnnouncementsAscState(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareAscendingState(announcements);
    }

    public List<PublishedAnnouncement> sortAnnouncementsDesPrice(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareDescendingPrice(announcements);
    }

    public List<PublishedAnnouncement> sortAnnouncementsDesCity(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareDescendingCity(announcements);
    }

    public List<PublishedAnnouncement> sortAnnouncementsDesState(List<PublishedAnnouncement> announcements) {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.compareDescendingState(announcements);
    }
}
