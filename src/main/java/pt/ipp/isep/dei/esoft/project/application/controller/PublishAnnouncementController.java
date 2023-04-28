package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PublishAnnouncementController {

    UserRepository userRepository = null;

    PropertyTypeRepository propertyTypeRepository = null;

    PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    ComissionRepository comissionRepository = null;

    TypeOfBusinessRepository typeOfBusinessRepository = null;

    AvailableEquipmentRepository availableEquipmentRepository = null;

    public PublishAnnouncementController() {
        getUserRepository();
        getPropertyTypeRepository();
        getPublishedAnnouncementRepository();
    }

    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }


    private AvailableEquipmentRepository getAvailableEquipmentRepository() {
        if (availableEquipmentRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            availableEquipmentRepository = repositories.getAvailableEquipmentRepository();
        }
        return availableEquipmentRepository;
    }

    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription) {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();

        //Get the TaskCategory by its description
        AvailableEquipment availableEquipmentByDescription =
                availableEquipmentRepository.getAvailableEquipmentByDescription(availableEquipmentDescription);

        return availableEquipmentByDescription;

    }

    public List<AvailableEquipment> getAvailableEquipment() {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();
        return availableEquipmentRepository.getAvailableEquipments();
    }



    public User getUserByEmail(String email) {
        UserRepository userRepository = getUserRepository();

        for (User user : getUserRepository().getUsers()) {
            if (user.compare(email) == 0) {
                return user;
            }
        }
        return null;
    }

    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
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

    public TypeOfBusiness getTypeOfBusinessByDescription(String typeOfBusinessDescription) {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();

        //Get the TaskCategory by its description
        TypeOfBusiness typeOfBusinessByDescription =
                typeOfBusinessRepository.getTypeOfBusinessByDescription(typeOfBusinessDescription);

        return typeOfBusinessByDescription;

    }

    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
    }



    private ComissionRepository getComissionRepository() {
        if (comissionRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            comissionRepository = repositories.getComissionRepository();
        }
        return comissionRepository;
    }

    public Comission getComissionByDescription(Double comissionDescription) {
        PropertyTypeRepository comissionRepository = getPropertyTypeRepository();

        //Get the TaskCategory by its description
        Comission comissionByDescription =
                getComissionRepository().getComissionByDescription(comissionDescription);

        return comissionByDescription;

    }

    public List<Comission> getComission() {
        ComissionRepository comissionRepository = getComissionRepository();
        return comissionRepository.getComission();
    }



    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();

        //Get the TaskCategory by its description
        PropertyType propertyTypeByDescription =
                getPropertyTypeRepository().getPropertyTypeByDescription(propertyTypeDescription);

        return propertyTypeByDescription;

    }

    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }


    public List<PublishedAnnouncement> getPublishedAnnoucement() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncements();
    }

    public Business getBusinessByDescription(double priceDescription){

        Business priceByDescription = new Business(priceDescription);

        return priceByDescription;

    }

    public Optional<PublishedAnnouncement> createPublishmentAnnouncementHouse(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract) {

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();

        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(date,typeOfBusiness,property,propertyType,comission,business);

        if (!getPublishedAnnouncementRepository().getPublishedAnnouncements().contains(publishedAnnouncement)) {
            newPublishedAnnouncement = getPublishedAnnouncementRepository().publishedAnnouncement(date,typeOfBusiness,property,propertyType,comission,business,durationOfContract);
        }
        return newPublishedAnnouncement;
    }



}
