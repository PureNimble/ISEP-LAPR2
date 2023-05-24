package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**

 The PublishAnnouncementController class is responsible for handling the logic related to publishing announcements,

 such as retrieving user information, property types, available equipment, commissions, types of business, and published

 announcements. It also provides methods for creating new published announcements and retrieving them from the

 repository.
 */
public class PublishAnnouncementController {

    UserRepository userRepository = null;

    PropertyTypeRepository propertyTypeRepository = null;

    PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    ComissionRepository comissionRepository = null;

    TypeOfBusinessRepository typeOfBusinessRepository = null;

    AvailableEquipmentRepository availableEquipmentRepository = null;
    /**

     Constructor that initializes the repository variables.
     */
    public PublishAnnouncementController() {
        getUserRepository();
        getPropertyTypeRepository();
        getPublishedAnnouncementRepository();
    }
    /**

     Retrieves the UserRepository instance and initializes it if it is null.
     @return UserRepository instance.
     */
    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }

    /**

     Retrieves the AvailableEquipmentRepository instance and initializes it if it is null.
     @return AvailableEquipmentRepository instance.
     */
    private AvailableEquipmentRepository getAvailableEquipmentRepository() {
        if (availableEquipmentRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            availableEquipmentRepository = repositories.getAvailableEquipmentRepository();
        }
        return availableEquipmentRepository;
    }
    /**

     Retrieves an AvailableEquipment object by its description.
     @param availableEquipmentDescription Description of the AvailableEquipment object.
     @return AvailableEquipment object.
     */
    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription) {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();

        //Get the TaskCategory by its description
        AvailableEquipment availableEquipmentByDescription =
                availableEquipmentRepository.getAvailableEquipmentByDescription(availableEquipmentDescription);

        return availableEquipmentByDescription;

    }
    /**

     Retrieves a list of all AvailableEquipment objects.
     @return List of AvailableEquipment objects.
     */
    public List<AvailableEquipment> getAvailableEquipment() {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();
        return availableEquipmentRepository.getAvailableEquipments();
    }


    /**

     Retrieves a User object by its email.
     @param email Email of the User object.
     @return User object.
     */
    public Client getUserByEmail(String email) {
        UserRepository userRepository = getUserRepository();

        for (Client client : getUserRepository().getUsers()) {
            if (client.compare(email) == 0) {
                return client;
            }
        }
        return null;
    }
    /**

     Retrieves the PropertyTypeRepository instance and initializes it if it is null.
     @return PropertyTypeRepository instance.
     */
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    /**

     Retrieves the TypeOfBusinessRepository instance and initializes it if it is null.
     @return TypeOfBusinessRepository instance.
     */
    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            typeOfBusinessRepository = repositories.getTypeOfBusinessRepository();
        }
        return typeOfBusinessRepository;
    }
    /**

     Retrieves a TypeOfBusiness object by its description.
     @param typeOfBusinessDescription Description of the TypeOfBusiness object.
     @return TypeOfBusiness object.
     */
    public TypeOfBusiness getTypeOfBusinessByDescription(String typeOfBusinessDescription) {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();

        //Get the TaskCategory by its description
        TypeOfBusiness typeOfBusinessByDescription =
                typeOfBusinessRepository.getTypeOfBusinessByDescription(typeOfBusinessDescription);

        return typeOfBusinessByDescription;

    }
    /**

     Returns a list of all available type of businesses.
     @return a list of TypeOfBusiness objects
     */
    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
    }


    /**
     Retrieves the ComissionRepository instance and initializes it if it is null.
     @return ComissionRepository instance.
     */
    private ComissionRepository getComissionRepository() {
        if (comissionRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            comissionRepository = repositories.getComissionRepository();
        }
        return comissionRepository;
    }
    /**

     Returns the Comission object with the specified description.
     @param comissionDescription the description of the Comission object to be retrieved
     @return the Comission object with the specified description
     */
    public Comission getComissionByDescription(Double comissionDescription) {
        PropertyTypeRepository comissionRepository = getPropertyTypeRepository();

        //Get the TaskCategory by its description
        Comission comissionByDescription =
                getComissionRepository().getComissionByDescription(comissionDescription);

        return comissionByDescription;

    }
    /**

     Returns a list of all available Comission objects.
     @return a list of Comission objects
     */
    public List<Comission> getComission() {
        ComissionRepository comissionRepository = getComissionRepository();
        return comissionRepository.getComission();
    }


    /**

     Returns the PropertyType object with the specified description.
     @param propertyTypeDescription the description of the PropertyType object to be retrieved
     @return the PropertyType object with the specified description
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();

        //Get the TaskCategory by its description
        PropertyType propertyTypeByDescription =
                getPropertyTypeRepository().getPropertyTypeByDescription(propertyTypeDescription);

        return propertyTypeByDescription;

    }
    /**

     Returns a list of all available PropertyType objects.
     @return a list of PropertyType objects
     */
    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }
    /**
     Retrieves the PublishedAnnoucementRepository instance and initializes it if it is null.
     @return PublishedAnnoucementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    /**

     Returns a list of all available PublishedAnnouncement objects.
     @return a list of PublishedAnnouncement objects
     */
    public List<PublishedAnnouncement> getPublishedAnnoucement() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncements();
    }

    /**
     * Gets published announcements desc.
     *
     * @return the published announcements desc
     */
    public List<PublishedAnnouncement> getPublishedAnnouncementsDesc() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncementsDesc();
    }

    /**

     Returns a new Business object with the specified price description.
     @param priceDescription the description of the price for the new Business
     @return a new Business object with the specified price description
     */
    public Business getBusinessByDescription(double priceDescription){

        Business priceByDescription = new Business(priceDescription);

        return priceByDescription;

    }

    /**

     Creates a new published announcement for a house with the specified parameters,
     and returns an Optional containing the new published announcement.
     If a published announcement with the same parameters already exists,
     an empty Optional is returned instead.
     @param date the date of the new published announcement
     @param typeOfBusiness the type of business for the new published announcement
     @param property the property for the new published announcement
     @param propertyType the property type for the new published announcement
     @param comission the commission for the new published announcement
     @param business the business for the new published announcement
     @param durationOfContract the duration of the contract for the new published announcement
     @return an Optional containing the new published announcement if it was created successfully,
     or an empty Optional if a published announcement with the same parameters already exists
     */
    public Optional<PublishedAnnouncement> createPublishmentAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract) {

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();

        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(date,typeOfBusiness,property,propertyType,comission,business);

        if (!getPublishedAnnouncementRepository().getPublishedAnnouncements().contains(publishedAnnouncement)) {
            newPublishedAnnouncement = getPublishedAnnouncementRepository().publishedAnnouncement(date,typeOfBusiness,property,propertyType,comission,business,durationOfContract);
        }
        return newPublishedAnnouncement;
    }



}
